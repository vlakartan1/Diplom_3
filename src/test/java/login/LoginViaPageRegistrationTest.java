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
import pageobject.LoginPage;
import pageobject.RegisterPage;
import api.CreateAndRemoveUser;

public class LoginViaPageRegistrationTest {

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
    @Description("Войти в аккаунт через страницу регистрации")
    @DisplayName("Тест: Войти в аккаунт через страницу Регистрации")
    public void loginToAccountPageRegTest() {
        loginToAccountPageReg();
    }

    @Step("Войти в аккаунт через страницу регистрации")
    private static void loginToAccountPageReg() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.register();// нажимаем на кнопку Войти в аккаунт на главной странице

        RegisterPage pageRegister = new RegisterPage(driver);
        pageRegister.loginToAccountReg();//нажимаем на кнопку ВОЙТИ на странице регистрации

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
