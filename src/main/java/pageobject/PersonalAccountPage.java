package pageobject;

import io.qameta.allure.Step;
import api.CreateAndRemoveUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    public final String profil = ".//a[@href='/account/profile']";//текст "Профиль" на странице Личного кабинета
    public final String constructor = ".//p[text()='Конструктор']";//Кнопка Конструктор
    public final String stellarBurger = ".//div[@class='AppHeader_header__logo__2D0X2']";//кнопка Stellar Burgers

    public final String exit = ".//button[text()='Выход']";//Выход из ЛК

    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("ожидание отклика")
    public void wait(String lokator) {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lokator)));
    }

    @Step("Переход из личного кабинета в конструктор ")
    public String goToConstructor() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        wait(profil);
        driver.findElement(By.xpath(constructor)).click();//Нажать на кнопку Конструктор из ЛК
        wait(homePageBurgers.assembleTheBurger);
        return driver.findElement(By.xpath(homePageBurgers.assembleTheBurger)).getText();//получить текст "Собери бургер" на гл. странице
    }

    @Step("Переход из личного кабинета на логотип Stellar Burgers ")
    public String goToStellarBurgers() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        wait(profil);
        driver.findElement(By.xpath(stellarBurger)).click();//Нажать на логотип Stellar Burgers
        wait(homePageBurgers.assembleTheBurger);
        return driver.findElement(By.xpath(homePageBurgers.assembleTheBurger)).getText();//получить текст "Собери бургер" на гл. странице
    }

    @Step("Выход из Личного кабинета")
    public String exitFromPersonalAccount() {
        LoginPage loginPage = new LoginPage(driver);
        wait(profil);
        driver.findElement(By.xpath(exit)).click();//Нажать на Выход
        wait(loginPage.buttonEnter);
        return driver.findElement(By.xpath(loginPage.buttonEnter)).getText();//получить текст "Войти" на странице
    }

    @Step("Войти на странице входа в Личном кабинете")
    public String loginToAccount() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        homePageBurgers.setLoginToPersonalAccount();
        wait(profil);
        return driver.findElement(By.xpath(profil)).getText();//получить текст "Профиль" в ЛК
    }

    @Step("Залогиниться в Личном кабинете и перейти в личный кабинет")
    public void loginAndTransitToAccount() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePageBurgers.setLoginToPersonalAccount();
        wait(loginPage.buttonEnter);
        loginPage.fillOutTheLoginForm(CreateAndRemoveUser.EMAIL, CreateAndRemoveUser.PASSWORD);
        homePageBurgers.setLoginToPersonalAccount();
        wait(profil);
    }


}
