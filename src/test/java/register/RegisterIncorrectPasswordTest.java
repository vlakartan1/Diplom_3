package register;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.LoginPage;
import pageobject.RegisterPage;
import api.CreateAndRemoveUser;

public class RegisterIncorrectPasswordTest {

    private static WebDriver driver;


    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Регистрация пользователя с некорректным паролем")
    @DisplayName("Тест на регистрацию пользователя с некорректным паролем")
    public void registerTest() {
        register();
    }

    @Step("Выполнить регистрацию пользователя с некорректным паролем")
    private static void register() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.register();

        RegisterPage register = new RegisterPage(driver);
        String result = register.incorrectPassword(CreateAndRemoveUser.NAME, CreateAndRemoveUser.EMAIL, "12345");
        MatcherAssert.assertThat("Результат не совпадает", result, Matchers.is("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
