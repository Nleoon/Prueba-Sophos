package co.com.prueba.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.targets.Target;

public class GetText {

    public static Question<String> getText(Target locator){
        return actor -> TextContent.of(locator).viewedBy(actor).asString().trim();
    }

    public static Question<Integer> getValue(Target locator){
        return actor -> Value.of(locator).viewedBy(actor).asInteger().intValue();
    }

    public static Question<Boolean> getBoolean(Target locator){
        return actor -> TextContent.of(locator).viewedBy(actor).asBoolean();
    }
}
