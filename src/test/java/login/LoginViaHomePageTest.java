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


public class LoginViaHomePageTest {
    private static WebDriver driver;

    @Before
    public void pageLaunchSettings() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        options.setBinary("C:/Users/User/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");

        CreateAndRemoveUser createAndRemoveUser = new CreateAndRemoveUser();
        createAndRemoveUser.setNewUser();
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Тест: «Войти в аккаунт» с главной страницы")
    public void loginToAccountTest() {
        loginToAccount();
    }

    @Step("Вход в аккаунт с кнопки на главной странице Войти в аккаунт")
    private static void loginToAccount() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        homePageBurgers.loginToAccount();

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
