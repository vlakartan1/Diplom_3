package testtransit;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import api.CreateAndRemoveUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.PersonalAccountPage;

public class TransitFromAccountToStellarBurgersTest {
    private static WebDriver driver;

    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");

        CreateAndRemoveUser createAndRemoveUser = new CreateAndRemoveUser();
        createAndRemoveUser.setNewUser();
    }

    @Test
    @Description("Кликнуть из Личного кабинета на логотип Stellar Burgers")
    @DisplayName("Перейти из Личного кабинета в Stellar Burgers")
    public void transitFromAccountToStellarBurgersTest() {
        transitFromAccountToStellarBurgers();
    }

    @Step("Перейти из Личного кабинета в Stellar Burgers")
    private static void transitFromAccountToStellarBurgers() {

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.loginAndTransitToAccount();//Залогиниться в Личном кабинете и перейти в него
        String result = personalAccountPage.goToStellarBurgers();//Перейти из ЛК в Stellar Burgers
        Assert.assertEquals("Соберите бургер", result);
    }

    @Step("Удалить тестового пользователя")
    public void removeUser() {
        CreateAndRemoveUser createAndRemoveUser = new CreateAndRemoveUser();
        createAndRemoveUser.removeUserByToken();
    }

    @After
    public void tearDown() {
        driver.quit();
        removeUser();
    }
}
