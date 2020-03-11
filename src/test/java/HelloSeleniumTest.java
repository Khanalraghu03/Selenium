import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSeleniumTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testGGCMainPage() {
        driver.get("https://www.ggc.edu");
        boolean b = driver.getPageSource().contains("virus");
        Assert.assertTrue(b);
    }

    @Test
    public void testMagnifier() {
        driver.get("https://www.ggc.edu");
        WebElement element = driver.findElement(By.className("toggle-search"));
        Assert.assertNotNull(element);
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Will I survive the coronavirus");
        element.submit();
        boolean b = driver.getPageSource().contains("you will live");
//        Assert.assertTrue(b);
    }
    @Test
    public void testSelinium() {
        driver.get(" https://ihgrewardsclubdining.rewardsnetwork.com/join.htm");
        WebElement firstName = driver.findElement(By.name("firstName"));
        WebElement lastName = driver.findElement(By.name("lastName"));
        WebElement zipcode = driver.findElement(By.name("zipcode"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement emailConfirm = driver.findElement(By.name("emailConfirm"));

        WebElement checkedAgreement = driver.findElement(By.id("acceptDFFTerms1"));
        checkedAgreement.click();

        WebElement next = driver.findElement(By.cssSelector("#enrollment > div > div.w300.f_right.pad_t20.iefx > input"));
        next.click();


        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        zipcode.sendKeys("11111");
        email.sendKeys("johndoe@doe.com");
        emailConfirm.sendKeys("johndoe@doe.com");

        firstName.submit();
        lastName.submit();
        zipcode.submit();
        email.submit();
        emailConfirm.submit();

        WebElement element = driver.findElement(By.name("address1"));
        Assert.assertNotNull(element);

    }
}
