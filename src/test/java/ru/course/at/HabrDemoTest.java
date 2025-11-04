package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HabrDemoTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.habr.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void development() {
        WebElement menuButton = driver.findElement(
                By.xpath("//button[contains(@class, 'tm-header__burger')]"));
        menuButton.click();
        By devItemLocator = By.xpath("//a[.//span[normalize-space(text())='Разработка']]");
        WebElement devItem = wait.until(ExpectedConditions.elementToBeClickable(devItemLocator));
        devItem.click();
        wait.until(ExpectedConditions.urlContains("/ru/flows/develop/"));
        assertTrue(driver.getCurrentUrl().contains(
                "/ru/flows/develop/"), "Переход на страницу 'Разработка' не удался.");
    }

    @Test
    public void publication() {
        WebElement publicationIcon = driver.findElement(By.cssSelector("svg.tm-header-user-menu__icon_write"));
        publicationIcon.click();
        wait.until(ExpectedConditions.urlToBe("https://habr.com/ru/sandbox/start/"));
        assertEquals("https://habr.com/ru/sandbox/start/", driver.getCurrentUrl());
    }
}