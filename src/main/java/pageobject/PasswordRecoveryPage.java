package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {

    public final String buttonEnter = "//a[@href='/login']";//кнопка Войти на странице Восстановления пароля
    public final String recovery = ".//button[text()='Восстановить']";//Кнопка Восстановить на странице восстановления пароля

    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("ожидание отклика")
    public void wait(String lokator) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lokator)));
    }

    @Step("Нажать на кнопку Войти на странице Восстановления пароля")
    public void passwordRecovery() {
        LoginPage loginPage = new LoginPage(driver);
        wait(recovery);
        driver.findElement(By.xpath(buttonEnter)).click();
        wait(loginPage.buttonEnter);
    }
}
