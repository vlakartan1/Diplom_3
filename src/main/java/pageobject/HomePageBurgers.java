package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageBurgers {
    public final String buttonLoginToAccount = ".//div/main/section[2]/div/button";//Кнопка Войти в аккаунт на главной странице
    public final String loginToPersonalAccount = ".//p[text()='Личный Кабинет']"; //Кнопка Вход в личный кабинет на главной странице
    public final String checkout = ".//button[text()='Оформить заказ']";//Кнопка Оформить заказ на главной странице после регистрации
    public final String bunSection = ".//div/main/section[1]/div[1]/div[1]";//Раздел Булки
    public final String saucesSection = ".//div/main/section[1]/div[1]/div[2]";//Раздел Соусы
    public final String fillingSection = ".//div/main/section[1]/div[1]/div[3]";//Раздел Начинки
    public final String assembleTheBurger = ".//h1[text()='Соберите бургер']";//Текст Соберите бургер
    public final String activeNameClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";


    private final WebDriver driver;

    public HomePageBurgers(WebDriver driver) {
        this.driver = driver;
    }


    @Step("ожидание отклика")
    public void wait(String lokator) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lokator)));
    }

    @Step(" Нажать на кнопку Войти в аккаунт и подождать отклика 2 сек Кнопки Войти на странице входа")
    public void loginToAccount() {
        LoginPage loginPage = new LoginPage(driver);
        wait(buttonLoginToAccount);
        driver.findElement(By.xpath(buttonLoginToAccount)).click();
        wait(loginPage.buttonEnter);
    }

    @Step("Нажать на кнопку Личный кабинет и подождать отклика 2 сек Кнопки Войти на странице входа")
    public void setLoginToPersonalAccount() {
        wait(loginToPersonalAccount);
        driver.findElement(By.xpath(loginToPersonalAccount)).click();
    }


    @Step("Кликнуть на раздел Булки")
    public void clickOnTheBuns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bunSection))).click();
    }

    @Step("Кликнуть на раздел Соусы")
    public void clickOnTheSauces() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(saucesSection))).click();
    }

    @Step("Кликнуть на раздел Соусы")
    public void clickOnTheFillings() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(fillingSection))).click();
    }

    @Step("Получить текущее название класса")
    public String getCurrentClass(String section) {
        String nameClass = driver.findElement(By.xpath(section)).getAttribute("class");
        System.out.println("Наименование текущего класса: " + nameClass);
        System.out.println("Наименование активного класса: " + activeNameClass);
        return nameClass;
    }
}
