package co.com.prueba.utils;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import java.io.File;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class BaseClass {

    // ACCIONES DEL ACTOR
    public static Performable click(Target locator) {
        return Click.on(locator);
    }

    public static Performable sendKey (String texto, Target locator) {
        return SendKeys.of(texto).into(locator);
    }

    public static Performable scrollTop (Target locator) {
        return Scroll.to(locator).andAlignToTop();
    }

    // ESPERAS
    public static Performable waitIsEnabled (Target locator, int timeForNoMoreThan){
        return WaitUntil.the(locator, isEnabled()).forNoMoreThan(timeForNoMoreThan).seconds();
    }
}
