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

public class ClickOnTheSaucesTest {

    private static WebDriver driver;

    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Кликнуть на раздел Соусы")
    @DisplayName("Перейти на раздел Соусы")
    public void clickOnTheSaucesTest() {
        clickOnTheSauces();
    }

    @Step("Перейти Кликнуть на раздел Соусы")
    private static void clickOnTheSauces() {

        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        Assert.assertNotEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.saucesSection));//проверяем, что раздел Соусы не активный
        homePageBurgers.clickOnTheSauces();//кликаем на раздел Соусы
        Assert.assertEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.saucesSection));//проверяем, что раздел Соусы стал активным
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
