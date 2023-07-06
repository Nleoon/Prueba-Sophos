package co.com.prueba.taks;

import co.com.prueba.userInterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static co.com.prueba.utils.BaseClass.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IniciarSesion implements Task {

    private String usuario;
    private String password;

    public IniciarSesion(String usuario,String password){
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.wasAbleTo(
                Open.url("https://tasks.evalartapp.com/automatization/")
        );

        actor.attemptsTo(
                waitIsEnabled(LoginPage.INPUT_USUARIO,60),
                sendKey(usuario,LoginPage.INPUT_USUARIO),
                sendKey(password,LoginPage.INPUT_PASSWORD),
                click(LoginPage.BTN_ENVIAR)
        );
    }

    public static IniciarSesion iniciarSesion(String usuario,String password){
        return instrumented(IniciarSesion.class,usuario,password);
    }
}

