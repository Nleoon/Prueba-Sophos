package co.com.prueba.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    // LOGIN
    public static final Target INPUT_USUARIO = Target.the("USUARIO").
            locatedBy("//input[@typer='text']");
    public static final Target INPUT_PASSWORD = Target.the("PASSWORD").
            locatedBy("//input[@type='password']");
    public static final Target BTN_ENVIAR = Target.the("BOTON ENVIAR").
            locatedBy("//button[@type='submit']");
}
