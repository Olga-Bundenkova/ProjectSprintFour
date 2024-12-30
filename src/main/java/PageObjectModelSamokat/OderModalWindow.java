package PageObjectModelSamokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OderModalWindow {
    WebDriver driver;

    // Локаторы
    private By orderModalOne = By.xpath("//div[text()='Хотите оформить заказ?']");
    private By orderModalTwo = By.xpath("//div[text()='Заказ оформлен']");
    // Локаторы модального окна "Хотите оформить заказ?"
    // Кнопка "Да" в модальном окне оформления заказа
    private By modalYesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // Локаторы модального окна "Заказ оформлен"
    // Кнопка "Посмотреть статус заказа"
    private By modalStatusOfOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");
    //Текст с номером заказа
    private By modalNumberOfrder = By.xpath(".//div[text()='Заказ оформлен']");

    // Методы
    public OderModalWindow(WebDriver driver) {
        this.driver = driver;
    }

    // Метод, ожидающий загрузки окна "Хотите оформить заказ?"
    public OderModalWindow waitForLoadOrderModalOne() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderModalOne).getText() != null
                && !driver.findElement(orderModalOne).getText().isEmpty()
        ));
        return this;
    }

    // Метод, нажимающий на кнопку "Да"
    public void pressModalYesButton() {
        driver.findElement(modalYesButton).click();
    }

    // Метод, ожидающий загрузки окна "Заказ оформлен"
    public OderModalWindow waitForLoadOrderModalTwo() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderModalTwo).getText() != null
                && !driver.findElement(orderModalTwo).getText().isEmpty()
        ));
        return this;
    }

    // Метод, получающий подтверждение что заказ оформлен
    public String getModalOderNumber() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(modalNumberOfrder).getText() != null
                && !driver.findElement(modalNumberOfrder).getText().isEmpty()
        ));
        return driver.findElement(modalNumberOfrder).getText();
    }

    // Метод, нажимающий на кнопку "Посмотреть статус"
    public void pressModalStatusOfOrderButton() {
        driver.findElement(modalStatusOfOrderButton).click();
    }
}
