import PageObjectModelSamokat.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static PageObjectModelSamokat.constants.ColourOfSamokat.BLACK;
import static PageObjectModelSamokat.constants.ColourOfSamokat.GREY;
import static PageObjectModelSamokat.constants.OrderButton.BODY_BUTTON;
import static PageObjectModelSamokat.constants.OrderButton.HEADER_BUTTON;
import static PageObjectModelSamokat.constants.OrderPageSamokatConstants.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageSamokatTests {

        private WebDriver driver;
        private final String name;
        private final String surname;
        private final String address;
        private final int stateNumber;
        private final String mobile;
        private final String date;
        private final String periodOfRent;
        private final Enum colour;
        private final String comment;
        private final String expectedHeader = "Заказ оформлен";
        private final Enum button;

        public OrderPageSamokatTests(Enum button, String name, String surname, String address, int stateNumber, String mobile,
                               String date, String periodOfRent, Enum colour, String comment) {
            this.button = button;
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.stateNumber = stateNumber;
            this.mobile = mobile;
            this.date = date;
            this.periodOfRent = periodOfRent;
            this.colour = colour;
            this.comment = comment;
        }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {HEADER_BUTTON, "Иван", "Иванов", "Победа 1", 10, "79271111111", "28.12.2024", SIX_DAYS,GREY, "раз раз раз"},
                {HEADER_BUTTON, "Пётр", "Петров", "Свобода 2",5, "79172222222", "28.12.2024", FIVE_DAYS,BLACK, "два два два"},
                {HEADER_BUTTON, "Фёдор", "Фёдоров", "Ленина 3",57, "79513333333", "28.12.2024", ONE_DAY,BLACK, "три три три"},
                {BODY_BUTTON, "Иван", "Иванов", "Победа 1", 10, "79271111111", "28.12.2024", SIX_DAYS,GREY, "раз раз раз"},
                {BODY_BUTTON, "Пётр", "Петров", "Свобода 2",5, "79172222222", "28.12.2024", FIVE_DAYS,BLACK, "два два два"},
                {BODY_BUTTON, "Фёдор", "Фёдоров", "Ленина 3",57, "79513333333", "28.12.2024", ONE_DAY,BLACK, "три три три"},
        };
    }

    // Действие, которое должно производиться до начала тестов на домашней странице
    @Before
    public void startUp() {
    // драйвер для браузера Chrome
    ChromeOptions options = new ChromeOptions();

    driver = new ChromeDriver(options);
    // переход на страницу тестового приложения
    driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Тест на позитивный сценарий ввода данных на странице "Заказать"
    @Test
    public void testCreateTheOrder() {
        // Создаем объект класса HomePageSamokat
        HomePageSamokat objHomePageSamokat = new HomePageSamokat(driver);
        // Ждем пока откроется домашняя страница
        objHomePageSamokat.waitForLoadHomePage();
        // Нажимаем на кнопку "Заказать"
        objHomePageSamokat.pressOrderButton(button);

        // Создаем объект класса OrderPageSamokatOne
        OrderPageSamokatOne objOrderPageSamokatOne = new OrderPageSamokatOne(driver);
        // Ждем пока загрузится страница "Для кого самокат"
        objOrderPageSamokatOne.waitForLoadOrderPageOne();
        // Заполняем поле "Имя"
        objOrderPageSamokatOne.inputNameField(name);
        // Заполняем поле "Фамилия"
        objOrderPageSamokatOne.inputSurnameField(surname);
        // Заполняем поле "Адресс"
        objOrderPageSamokatOne.inputAddressField(address);
        // Заполняем поле "Метро"
        objOrderPageSamokatOne.inputMetroField(stateNumber);
        // Заполняем поле "Телефон"
        objOrderPageSamokatOne.inputMobile(mobile);
        // Нажимаем кнопку "Далее"
        objOrderPageSamokatOne.pressThenButton();
        //Создаем объект класса OrderPageSamokatTwo
        OrderPageSamokatTwo objOrderPageSamokatTwo = new OrderPageSamokatTwo(driver);
        // Ждем пока загрузится страница "Про аренду"
        objOrderPageSamokatTwo.waitForLoadOrderPageTwo();
        // Заполняем поле "* Когда привезти самокат"
        objOrderPageSamokatTwo.inputDate(date);
        // Заполняем поле "* Срок аренды"
        objOrderPageSamokatTwo.inputPeriodOfRentField(periodOfRent);
        // Выбираем цвет самоката
        objOrderPageSamokatTwo.changeColour(colour);
        // Заполняем поле "Комментарий"
        objOrderPageSamokatTwo.inputCommentForTheCourierField(comment);
        // Нажимаем кнопку "Заказать"
        objOrderPageSamokatTwo.pressModalOrderButton();
        //Создаем объект класса OderModalWindow
        OderModalWindow objOderModalWindow = new OderModalWindow(driver);
        // Ждем пока загрузится модальное окно "Хотите оформить заказ?"
        objOderModalWindow.waitForLoadOrderModalOne();
        // Нажимаем на кнопку "Да"
        objOderModalWindow.pressModalYesButton();
        // Ждем пока загрузится модальное окно "Заказ оформлен"
        objOderModalWindow.waitForLoadOrderModalTwo();

        // Проверяем что заказ оформлен
        assertTrue(objOderModalWindow.getModalOderNumber().contains(expectedHeader));

}

    // Действие, которое должно производиться после окончания тестов
    @After
    public void closeTheBrowser() {
        // Закрываем браузер
        driver.quit();
    }
}
