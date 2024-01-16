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

public class RegisterUserTest {

    private static WebDriver driver;


    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @Description("Регистрация пользователя")
    @DisplayName("Зарегистрировать пользователя")
    public void registerTest(){
        register();
    }

    @Step("Выполнить регистрацию пользователя")
    private static void register(){
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage register = new RegisterPage(driver);
        loginPage.register();
        String result = register.correctPassword(CreateAndRemoveUser.NAME, CreateAndRemoveUser.EMAIL, CreateAndRemoveUser.PASSWORD);

        MatcherAssert.assertThat("Результат не совпадает", result, Matchers.is("Войти"));
    }


    @Step("Удалить созданного пользователя")
    public void removeUser(){
        CreateAndRemoveUser createAndRemoveUser = new CreateAndRemoveUser();
        createAndRemoveUser.removeUserByToken();
    }
    @After
    public void tearDown() {
        driver.quit();
        removeUser();
    }
}
