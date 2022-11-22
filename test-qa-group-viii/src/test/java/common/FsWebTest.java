package common;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FsWebTest {

    @ExtendWith({TextReportExtension.class})

    @BeforeAll
    public static void setUp(){

    }

    @Test
    public void fsWebTest(){
        open("https://fs.ee/");
        $(".check > .sec:nth-child(1) > label").click();
        $(".sec:nth-child(3) > .checked").click();
        $(byName("e-width")).click();
        $(byName("e-width")).selectOption("235");
        $(byName("e-height")).click();
        $(byName("e-height")).selectOption("45");
        $(byName("e-diameter")).click();
        $(byName("e-diameter")).selectOption("18");
        $(byName("e-search")).click();
        $(".product:nth-child(3) h3").shouldHave(text("YOKOHAMA"));
        $(".product:nth-child(3) p").shouldHave(text("ICE GUARD STUD (IG65)\\\\n235/45 R18\\\\nTalv (Naastrehv)\\\\nLisainfo"));

    }


    @AfterAll
    public static void closeAllTests(){

    }
}
