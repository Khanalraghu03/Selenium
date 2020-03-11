import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSelinium() {
        driver.get(" https://ihgrewardsclubdining.rewardsnetwork.com/join.htm");
        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("John");

        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Doe");

        WebElement zipcode = driver.findElement(By.name("zipcode"));
        zipcode.sendKeys("11111");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("johndoe@doe.com");

        WebElement emailConfirm = driver.findElement(By.name("emailConfirm"));
        emailConfirm.sendKeys("johndoe@doe.com");

        WebElement checkedAgreement = driver.findElement(By.id("acceptDFFTerms1"));
        checkedAgreement.click();

        WebElement next = driver.findElement(By.cssSelector("#enrollment > div > div.w300.f_right.pad_t20.iefx > input"));
        next.click();

        WebElement element = driver.findElement(By.name("address1"));
        Assert.assertNotNull(element);

    }
}
