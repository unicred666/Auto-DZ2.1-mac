package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveTest {

    private WebDriver driver;

    @BeforeAll
    static void setAll() {
        System.setProperty("webdriver.chrome.driver", "driver/mac/chromedriver");
    }

    @BeforeEach
     public void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }


    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79165454176");
        driver.findElement(By.tagName("label")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        assertEquals(expected, actual);
    }

    @Test
    // проверка двойной фамилии
    public void shouldPositiveTest1() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79991234567");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        assertEquals(expected, actual);

    }

}