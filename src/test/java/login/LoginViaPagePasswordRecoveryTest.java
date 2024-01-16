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
import pageobject.PasswordRecoveryPage;
import api.CreateAndRemoveUser;

public class LoginViaPagePasswordRecoveryTest {

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
    @Description("Войти в аккаунт через кнопку на странице восстановления пароля")
    @DisplayName("Тест: Войти в аккаунт через кнопку в форме восстановления пароля")
    public void registerTest() {
        loginToAccountPagePasswordRecovery();
    }

    @Step("Войти в аккаунт через кнопку в форме восстановления пароля")
    private static void loginToAccountPagePasswordRecovery() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        homePageBurgers.loginToAccount();// нажимаем на кнопку Войти в аккаунт на главной странице

        LoginPage loginPage = new LoginPage(driver);
        loginPage.passwordRecovery();//нажимаем на кнопку Восстановить пароль на странице Входа

        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.passwordRecovery();//нажимаем на кнопку Войти на странице Восстановления пароля

        String result = loginPage.fillOutTheLoginForm(CreateAndRemoveUser.EMAIL, CreateAndRemoveUser.PASSWORD);//Вносим данные в поля

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
