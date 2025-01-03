import PageObjectModelSamokat.HomePageSamokat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeOptions;

import static PageObjectModelSamokat.constants.HomePageSamokatConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageSamokatTests {

    private WebDriver driver;
    private final By question;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public HomePageSamokatTests(By question, By answer, By labelResult, String expected) {
        this.question = question;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {QUESTION_0, ANSWER_0, ITEM_ANSWER_0, TEXT_ANSWER_0},
                {QUESTION_1, ANSWER_1, ITEM_ANSWER_1, TEXT_ANSWER_1},
                {QUESTION_2, ANSWER_2, ITEM_ANSWER_2, TEXT_ANSWER_2},
                {QUESTION_3, ANSWER_3, ITEM_ANSWER_3, TEXT_ANSWER_3},
                {QUESTION_4, ANSWER_4, ITEM_ANSWER_4, TEXT_ANSWER_4},
                {QUESTION_5, ANSWER_5, ITEM_ANSWER_5, TEXT_ANSWER_5},
                {QUESTION_6, ANSWER_6, ITEM_ANSWER_6, TEXT_ANSWER_6},
                {QUESTION_7, ANSWER_7, ITEM_ANSWER_7, TEXT_ANSWER_7},
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

    // Тест "Вопросы о важном" нажатие на кнопку открывает соответствующий текст.
    @Test
    public void checkTheAccordionButtonText() {
        // Создаем объект класса HomePageSamokat
        HomePageSamokat objHomePageSamokat = new HomePageSamokat(driver);
        // Ждем пока откроется домашняя страница
        objHomePageSamokat.waitForLoadHomePage();
        // Спускаем страницу до раздела "Вопросы о важном"
        objHomePageSamokat.scrollToQuestions();
        // Нажимаем на кнопку вопроса
        objHomePageSamokat.pressTheQuestion(question);
        // Ждем загрузки ответа на вопрос и получения текста ответа
        objHomePageSamokat.waitLoadAfterPressQuestion(labelResult);
        // Получаем текст ответа
        String result = driver.findElement(answer).getText();

        // Сравниваем полученный текст с искомым
        assertEquals(expected, result);
    }

    // Тест на нажатие на логотип "Яндекс" (если останется время)

    // Дествие, которое должно производиться после окончания тестов
    @After
    public void closeTheBrowser() {
        // Закрываем браузер
        driver.quit();
    }

}
