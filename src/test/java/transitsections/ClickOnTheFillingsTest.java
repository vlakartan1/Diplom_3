package transitsections;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePageBurgers;

public class ClickOnTheFillingsTest {

    private static WebDriver driver;

    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Кликнуть на раздел Начинки")
    @DisplayName("Перейти на раздел Начинки")
    public void clickOnTheFillingTest() {
        clickOnTheFilling();
    }

    @Step("Перейти Кликнуть на раздел Начинки")
    private static void clickOnTheFilling() {

        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        Assert.assertNotEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.fillingSection));//проверяем, что раздел Начинки не активный
        homePageBurgers.clickOnTheFillings();//кликаем на раздел Начинки
        Assert.assertEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.fillingSection));//проверяем, что раздел Начинки стал активным
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
