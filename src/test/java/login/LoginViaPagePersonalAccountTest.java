package login;

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
import pageobject.LoginPage;
import api.CreateAndRemoveUser;

public class LoginViaPagePersonalAccountTest {

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
    @Description("Вход в аккаунт через кнопку Личный кабинет")
    @DisplayName("«Войти в аккаунт» с кнопки Личный кабинет")
    public void loginViaPersonalAccountTest() {
        loginViaPersonalAccount();
    }

    @Step("Выполнить вход через страницу Личный кабинет")
    private static void loginViaPersonalAccount() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        homePageBurgers.setLoginToPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        String result = loginPage.fillOutTheLoginForm(CreateAndRemoveUser.EMAIL, CreateAndRemoveUser.PASSWORD);

        Assert.assertEquals("Оформить заказ", result);
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
