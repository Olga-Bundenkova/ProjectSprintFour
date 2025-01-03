package PageObjectModelSamokat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static PageObjectModelSamokat.constants.OrderButton.BODY_BUTTON;
import static PageObjectModelSamokat.constants.OrderButton.HEADER_BUTTON;

public class HomePageSamokat {

    WebDriver driver;

    // Локаторы
    // Обязательные локаторы ( необходимые для основных тестов)
    // Шапка сайта
    private By homeHeader = By.className("Home_Header__iJKdX");
    // Кнопка "Заказать" в header
    private By orderButtonHeader = By.className("Button_Button__ra12g");
    // Кнопка "Заказать" в body
    private By orderButtonBody = By.xpath("//div[contains(@class, 'Home_RoadMap__2tal_')]//div[contains(@class, 'Home_FinishButton__1_cWm')]//button[contains(text(), 'Заказать')]");
    // Раздел "Вопросы о важном"
    private By questionsHeader = By.className("Home_FourPart__1uthg");

    // Необязательные локаторы (необходимые для дополнительных тестов)
    // Логотип "Яндекс"
    private By logoYandex = By.className("Header_LogoYandex__3TSOI");

    // Методы
    // Обязательные методы (необходимые для основных тестов)

    public HomePageSamokat(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания загрузки страницы
    public HomePageSamokat waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    // Метод для нажатия на кнопку "Заказать" в шапке сайта
    public HomePageSamokat pressOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
        return this;
    }

    // Метод для прокрутки к кнопке "Заказать" в теле сайта
    public HomePageSamokat scrollToOrderButtonBody() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBody));
        return this;
    }

    // Метод для нажатия на кнопку "Заказать" в теле сайта
    public HomePageSamokat pressOrderButtonBody() {
        driver.findElement(orderButtonBody).click();
        return this;
    }

    // Метод для нажатия на кнопку заказать в Header или Body
    public void pressOrderButton(Enum button) {
        if (button.equals(HEADER_BUTTON)) {
            pressOrderButtonHeader();
        } else if (button.equals(BODY_BUTTON)) {
            scrollToOrderButtonBody();
            pressOrderButtonBody();
        }
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public HomePageSamokat scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    // Метод для нажатия на вопрос в блоке "Вопросы о важном"
    public HomePageSamokat pressTheQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    // Метод ожидания загрузки ответа на вопрос и получения текста ответа
    public void waitLoadAfterPressQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    // Метод нажимающий на логотип "Яндекс"
    public void pressLogoYandex() {
        driver.findElement(logoYandex).click();
    }

}
