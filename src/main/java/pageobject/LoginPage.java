package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public final String fieldEmail = ".//input[@name='name']";// поле для заполнения EMAIL на странице Входа
    public final String fildPassword = ".//input[@type='password']"; //поле для ввода пароля на странице Входа
    public final String buttonEnter = ".//button[text()='Войти']"; //кнопка Войти на странице Входа
    public final String register = ".//a[text()='Зарегистрироваться']";//кнопка Зарегистрироваться на странице Входа
    public final String restorePassword = ".//a[text()='Восстановить пароль']";//кнопка Восстановить пароль на странице Входа
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("ожидание отклика")
    public void wait(String lokator) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lokator)));
    }


    @Step("Внести почту, пароль и нажать Войти")
    public String fillOutTheLoginForm(String email, String password) {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        System.out.println("Попытка входа в аккаунт");
        wait(buttonEnter);
        driver.findElement(By.xpath(fieldEmail)).sendKeys(email);//внести email
        driver.findElement(By.xpath(fildPassword)).sendKeys(password);//внести пароль
        driver.findElement(By.xpath(buttonEnter)).click();//кликнуть на кнопку Войти
        wait(homePageBurgers.checkout);
        System.out.println("Успешный вход в аккаунт");
        return driver.findElement(By.xpath(homePageBurgers.checkout)).getText();//получить текст "Оформить заказ" на главной странице
    }

    @Step("Нажать на кнопку Зарегистрироваться на странице входа")
    public void register() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        homePageBurgers.loginToAccount();//нажать на кнопку Войти в аккаунт на Главной странице
        driver.findElement(By.xpath(register)).click();//кликнуть на кнопке Зарегистрироваться на странице Входа
        wait(registerPage.registerReg);
    }

    @Step("Нажать на кнопку Восстановить пароль на странице входа")
    public void passwordRecovery() {
        wait(restorePassword);
        driver.findElement(By.xpath(restorePassword)).click();//нажать на кнопку Восстановить пароль
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        wait(passwordRecoveryPage.recovery);
    }


}
