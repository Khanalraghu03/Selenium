import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BannerSelenium {
    public static ChromeDriver driver;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void login() {
        driver.get("https://ggc.gabest.usg.edu/pls/B400/twbkwbis.P_WWWLogin");
        WebElement login = driver.findElement(By.id("UserID"));
        WebElement pin = driver.findElement(By.name("PIN"));
        WebElement submit = driver.findElement(By.xpath("/html/body/div[3]/form/p/input[1]"));
        login.sendKeys("900148636");
        pin.sendKeys("122597");
        submit.click();

    }

    @Test
    public void loginTest() {
        login();
        Assert.assertEquals(driver.getTitle(),"Main Menu");
    }

    public double getTution() {
        // Test my banner amount with the in state estimate

        login();
        WebElement search = driver.findElement(By.name("KEYWRD_IN"));
        search.sendKeys("tuition");

        WebElement go = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[1]/div/form/input[2]"));
        go.click();

        WebElement aSBTerm = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[3]/td[2]/a"));
        aSBTerm.click();

        WebElement inStateElement = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[21]/td/p"));
        WebElement eRefundElement = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[4]/td[2]/p"));

        String instateString = inStateElement.getText();
        String eRefundString = eRefundElement.getText();
        instateString = instateString.replaceAll("[^\\d.]","");
        eRefundString = eRefundString.replaceAll("[^\\d.]","");

        double inState = Double.parseDouble(instateString);
        double eRefund = Double.parseDouble(eRefundString);

        return  inState - eRefund;

    }


    public double getEstimate() {
        WebElement total = driver.findElement(By.name("totalcost"));
        String strTotal = total.getAttribute("value");
        return Double.parseDouble(strTotal.replaceAll("[^\\d.]",""));
    }

    public double getInStateEstimate(String link){
        driver.get(link);
        WebElement inState = driver.findElement(By.cssSelector("label[for=inorout1]"));
        inState.click();

        return getEstimate();
    }

    public double getOutStateEstimate(String link){
        driver.get(link);
        WebElement outOfState = driver.findElement(By.cssSelector("label[for=inorout0]"));
        outOfState.click();

        return getEstimate();
    }

    @Test
    public void testTuitionEstimate() {
        String link = "http://www.ggc.edu/admissions/tuition-and-financial-aid-calculators/index.html#";
        //Test in state tuition estimate
        Assert.assertEquals(5762/2,getInStateEstimate(link),5);

        //Test out state tuition estimate
        Assert.assertEquals(16744/2,getOutStateEstimate(link),5);

        //Test my tuition with in state estimate with $100 give and take
         Assert.assertEquals(2967.02,getInStateEstimate(link),100);

         //Test my tuition through banner with in state estimate with $100 give and take
         Assert.assertEquals(getTution(), getInStateEstimate(link),100);

    }

}
