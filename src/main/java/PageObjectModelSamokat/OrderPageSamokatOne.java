package PageObjectModelSamokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class OrderPageSamokatOne {
    WebDriver driver;

    // Локаторы
    private By orderHeader = By.xpath("//div[text()='Для кого самокат']");
    // Поле ввода "Имя"
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода "Фамилия"
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле ввода "Адрес"
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле ввода "Станция метро"
    private String nameStateMetro = ".//button[@value='%s']";
    private By stateMetro = By.className("select-search__input");
    // Поле ввода "Номер телефона"
    private By mobileField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнока "Далее"
    private By thenButton = By.xpath("//button[text()='Далее']");

    //Методы
    public OrderPageSamokatOne(WebDriver driver) {
        this.driver = driver;
    }

    // Метод, ожидающий загрузки страницы "Для кого замокат"
    public OrderPageSamokatOne waitForLoadOrderPageOne() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    // Заполнение поля "Имя"
    public OrderPageSamokatOne inputNameField(String newName) {
        driver.findElement(nameField).sendKeys(newName);
        return this;
    }

    //Заполнение поля "Фамилия"
    public OrderPageSamokatOne inputSurnameField(String newSurname) {
        driver.findElement(surnameField).sendKeys(newSurname);
        return this;
    }

    // Заполнение поля "Адрес"
    public OrderPageSamokatOne inputAddressField(String newAddress) {
        driver.findElement(addressField).sendKeys(newAddress);
        return this;
    }

    // Заполнение поля "Станция метро"
    public OrderPageSamokatOne inputMetroField(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    // Заполнение поля "Телефон"
    public OrderPageSamokatOne inputMobile(String newMobile) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(mobileField));
        driver.findElement(mobileField).sendKeys(newMobile);
        return this;
    }

    // Метод нажимающий на кнопку "Далее"
    public void pressThenButton() {
        driver.findElement(thenButton).click();
    }
}
