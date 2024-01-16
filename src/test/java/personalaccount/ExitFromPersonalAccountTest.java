package personalaccount;

import api.CreateAndRemoveUser;
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
import pageobject.PersonalAccountPage;

public class ExitFromPersonalAccountTest {
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
    @Description("Выход из Личного кабинета")
    @DisplayName("Выйти из Личного кабинета")
    public void exitFromPersonalAccountTest() {
        exitFromPersonalAccount();
    }

    @Step("Выйти из Личного кабинета")
    private static void exitFromPersonalAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.loginAndTransitToAccount();//Залогиниться в Личном кабинете и перейти в него
        String result = personalAccountPage.exitFromPersonalAccount();//Выйти из ЛК
        Assert.assertEquals("Войти", result);
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
