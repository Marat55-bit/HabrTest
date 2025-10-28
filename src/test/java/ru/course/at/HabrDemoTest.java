package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HabrDemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void development() {
        WebElement menuButton = driver.findElement(By.cssSelector("button.tm-header__button.tm-header__burger[data-v-56ed7aae]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        menuButton.click();

        By devItemLocator = By.cssSelector("a.menu-item[data-test-id=\"menu-item\"][href=\"/ru/flows/develop/\"]");
        WebElement devItem = wait.until(ExpectedConditions.elementToBeClickable(devItemLocator));
        devItem.click();

        wait.until(ExpectedConditions.urlContains("/ru/flows/develop/"));
        assertTrue(driver.getCurrentUrl().contains("/ru/flows/develop/"), "Переход на страницу 'Разработка' не удался.");
    }
}
