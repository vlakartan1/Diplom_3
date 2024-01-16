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

public class ClickOnTheBunsTest {
    private static WebDriver driver;

    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Кликнуть на раздел Булки")
    @DisplayName("Перейти на раздел Булки")
    public void clickOnTheBunsTest() {
        clickOnTheBuns();
    }

    @Step("Перейти Кликнуть на раздел Булки")
    private static void clickOnTheBuns() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        Assert.assertEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.bunSection));//проверяем, что раздел Булки активный
        homePageBurgers.clickOnTheSauces();//кликаем на раздел Соусы
        Assert.assertNotEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.bunSection));//проверяем, что раздел Булки стал не активным
        homePageBurgers.clickOnTheBuns();//кликаем на раздел Булки
        Assert.assertEquals(homePageBurgers.activeNameClass, homePageBurgers.getCurrentClass(homePageBurgers.bunSection));//проверяем, что раздел Булки стал опять активным
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
