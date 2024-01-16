package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    public final String fieldName = ".//div/main/div/form/fieldset[1]/div/div/input";// поле для заполнения Имени на странице Регистрации
    public final String fieldEmailReg = ".//div/main/div/form/fieldset[2]/div/div/input";//поле для заполнения EMAIL на странице Регистрации
    public final String fildPasswordReg = ".//input[@type='password']";//поле для заполнения Пароля на странице Регистрации
    public final String registerReg = ".//button[text()='Зарегистрироваться']";//кнопка Зарегистрироваться на странице Регистрации
    public final String incorrectPassword = ".//p[text()='Некорректный пароль']"; //Текст Некорректный пароль на странице регистрации
    public final String loginToAccountReg = "//a[@href='/login']";//кнопка ВОЙТИ на странице регистрации


    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("ожидание отклика")
    public void wait(String lokator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lokator)));
    }


    @Step("Внести Имя, почту, пароль и нажать Зарегистрироваться")
    public void fillOutTheRegistForm(String name, String email, String password) {

        System.out.println("Попытка зарегистрировать пользователя");
        driver.findElement(By.xpath(fieldName)).sendKeys(name);//внести имя
        driver.findElement(By.xpath(fieldEmailReg)).sendKeys(email);//внести email
        driver.findElement(By.xpath(fildPasswordReg)).sendKeys(password);//внести пароль
        driver.findElement(By.xpath(registerReg)).click();//кликнуть на кнопку Зарегистрироваться

    }


    @Step("Попытка зарегистрировать пользователя с некорректным паролем")
    public String incorrectPassword(String name, String email, String password) {
        fillOutTheRegistForm(name, email, password);
        wait(incorrectPassword);
        System.out.println("Некорректный пароль");
        return driver.findElement(By.xpath(incorrectPassword)).getText();//получить текст "Некорректный пароль" на странице входа
    }


    @Step("Успешная регистрации пользователя")
    public String correctPassword(String name, String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        fillOutTheRegistForm(name, email, password);
        wait(loginPage.buttonEnter);
        System.out.println("Пользователь успешно зарегистрирован");
        return driver.findElement(By.xpath(loginPage.buttonEnter)).getText();//получить текст "Войти" на странице входа
    }

    @Step("Войти в аккаунт на странице Регистрации по кнопке ВОЙТИ")
    public void loginToAccountReg() {
        wait(loginToAccountReg);
        driver.findElement(By.xpath(loginToAccountReg)).click();//кликнуть на кнопку ВОЙТИ
        LoginPage loginPage = new LoginPage(driver);
        wait(loginPage.buttonEnter);
    }

}