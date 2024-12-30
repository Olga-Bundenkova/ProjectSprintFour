package PageObjectModelSamokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObjectModelSamokat.constants.ColourOfSamokat.BLACK;
import static PageObjectModelSamokat.constants.ColourOfSamokat.GREY;

public class OrderPageSamokatTwo {
    WebDriver driver;

    // Локаторы
    private By orderHeader = By.className("Order_Header__BZXOb");
    // Выпадающий календарь "Когда привезти самокат"
    private By dataPickerField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Выпадающий список "Срок аренды"
    private By periodOfRentField = By.xpath(".//span[@class='Dropdown-arrow']");
    // Чек-боксы цвет самоката
    // Чек-бокс "Цвет самоката - чёрный жемчуг"
    public static final By checkBoxBlackPearl = By.id("black");
    // Чек-бокс "Цвет самоката - серая безысходность"
    public static final By checkBoxGrayHopelessness = By.id("grey");
    // Поле ввода "Комментарий для курьера"
    private By commentForTheCourierField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать" в body
    private By modalOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Методы
    public OrderPageSamokatTwo(WebDriver driver) {
        this.driver = driver;
    }

    // Метод, ожидающий загрузки страницы "Про аренду"
    public OrderPageSamokatTwo waitForLoadOrderPageTwo() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    // Заполнение поля "* Когда привезти самокат"
    public OrderPageSamokatTwo inputDate(String newDate) {
        driver.findElement(dataPickerField).sendKeys(newDate);
        return this;
    }

    // Заполнение поля "* Срок аренды"
    public OrderPageSamokatTwo inputPeriodOfRentField(String newPeriodOfRent) {
        driver.findElement(periodOfRentField).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }
    // Метод нажимающий на чек-бокс
    public OrderPageSamokatTwo changeColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(checkBoxBlackPearl).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(checkBoxGrayHopelessness).click();
        }
        return this;
    }

    // Метод заполнения поля "Комментарий для курьера"
    public OrderPageSamokatTwo inputCommentForTheCourierField(String newComment) {
        driver.findElement(commentForTheCourierField).sendKeys(newComment);
        return this;
    }

    // Метод, нажимающий на кнопку "Заказать"
    public void pressModalOrderButton() {
        driver.findElement(modalOrderButton).click();
    }
}
