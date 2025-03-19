package guru.qa;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testFillingPracticeForm(){

        open("https://demoqa.com/automation-practice-form");

        // Text boxes

        $("#firstName").setValue("Sergey");
        $("#lastName").setValue("Dubinin");
        $("#userEmail").setValue("sergey@mail.com");

        // Radio button (Custom)

        $("#genterWrapper").$("input[value='Male']").parent().click();

        // Text box

        $("#userNumber").setValue("8900500511");

        // date box

        $("#dateOfBirth-wrapper").$("#dateOfBirthInput").click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__year-select").find(byText("1994")).click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__month-select").find(byText("June")).click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__month").find(byText("8")).click();
        $("#dateOfBirthInput").sendKeys(Keys.ESCAPE);

        // Drop-down list

        $("#subjectsInput").setValue("English");
        $$(".subjects-auto-complete__option").findBy(exactText("English")).click();

        // Checkbox

        $("#hobbiesWrapper").$(byText("Sports")).click();

        // Select file

        $("#uploadPicture").scrollTo().uploadFile(new File("src\\test\\resources\\img\\photo.jpg"));

        // Text box

        $("#currentAddress").setValue("My address");

        // Drop-down list

        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();

        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();

        // Button

        $("#submit").click();

        // Assertions

        // Modal header
        $("div[role='dialog']").$(".modal-title.h4").shouldHave(exactText("Thanks for submitting the form"));

        // Table
        $("div.table-responsive").$$("thead>tr>th").get(0).shouldHave(exactText("Label"));
        $("div.table-responsive").$$("thead>tr>th").get(1).shouldHave(exactText("Values"));

        $("div.table-responsive").$$("tbody>tr").get(0).$$("td").get(0).shouldHave(exactText("Student Name"));
        $("div.table-responsive").$$("tbody>tr").get(0).$$("td").get(1).shouldHave(exactText("Sergey Dubinin"));

        $("div.table-responsive").$$("tbody>tr").get(1).$$("td").get(0).shouldHave(exactText("Student Email"));
        $("div.table-responsive").$$("tbody>tr").get(1).$$("td").get(1).shouldHave(exactText("sergey@mail.com"));

        $("div.table-responsive").$$("tbody>tr").get(2).$$("td").get(0).shouldHave(exactText("Gender"));
        $("div.table-responsive").$$("tbody>tr").get(2).$$("td").get(1).shouldHave(exactText("Male"));

        $("div.table-responsive").$$("tbody>tr").get(3).$$("td").get(0).shouldHave(exactText("Mobile"));
        $("div.table-responsive").$$("tbody>tr").get(3).$$("td").get(1).shouldHave(exactText("8900500511"));

        $("div.table-responsive").$$("tbody>tr").get(4).$$("td").get(0).shouldHave(exactText("Date of Birth"));
        $("div.table-responsive").$$("tbody>tr").get(4).$$("td").get(1).shouldHave(exactText("08 June,1994"));

        $("div.table-responsive").$$("tbody>tr").get(5).$$("td").get(0).shouldHave(exactText("Subjects"));
        $("div.table-responsive").$$("tbody>tr").get(5).$$("td").get(1).shouldHave(exactText("English"));

        $("div.table-responsive").$$("tbody>tr").get(6).$$("td").get(0).shouldHave(exactText("Hobbies"));
        $("div.table-responsive").$$("tbody>tr").get(6).$$("td").get(1).shouldHave(exactText("Sports"));

        $("div.table-responsive").$$("tbody>tr").get(7).$$("td").get(0).shouldHave(exactText("Picture"));
        $("div.table-responsive").$$("tbody>tr").get(7).$$("td").get(1).shouldHave(exactText("photo.jpg"));

        $("div.table-responsive").$$("tbody>tr").get(8).$$("td").get(0).shouldHave(exactText("Address"));
        $("div.table-responsive").$$("tbody>tr").get(8).$$("td").get(1).shouldHave(exactText("My address"));

        $("div.table-responsive").$$("tbody>tr").get(9).$$("td").get(0).shouldHave(exactText("State and City"));
        $("div.table-responsive").$$("tbody>tr").get(9).$$("td").get(1).shouldHave(exactText("Haryana Karnal"));

        // Close modal

        $("#closeLargeModal").click();
    }
}
