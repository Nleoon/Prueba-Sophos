package co.com.prueba.stepDefinitions;

import co.com.prueba.questions.GetText;
import co.com.prueba.taks.CompletarForm;
import co.com.prueba.taks.IniciarSesion;
import co.com.prueba.userInterfaces.TestPage;
import co.com.prueba.utils.MyScreenRecorder;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import java.io.File;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.core.IsEqual.equalTo;

public class CompletarFormulario {

    //CREAR AVRIABLES DE NAVEGADOR Y ACTOR
    @Managed(driver= "chrome")
    WebDriver driver;

    @Before("@CompletarFormulario")
    public void setThestage() throws Exception {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Global");

        // ALMACENAMOS EL NOMBRE DE LA CLASE
        String nomScenario = Thread.currentThread().getStackTrace()[1].getClassName();
        // CREAR CARPETA PARA ALMACENAMIENTO DE VIDEOS
        File rutaCarpeta = MyScreenRecorder.crearCarpeta(nomScenario);
        // INICIAMOS LA GRABACION
        MyScreenRecorder.startRecording(rutaCarpeta);
    }


    @Given("^el usuario ingresa en la pagina de prueba de automatizacion con sus credenciales (.*),(.*)$")
    public void elUsuarioFuncionarioIngresaEnElPortalTerceroConSusCredenciales(String username, String password) {
        // LOGIN
        theActorInTheSpotlight().attemptsTo(IniciarSesion.iniciarSesion(username, password));
    }

    @When("^el usuario ingresa en el apartado test y completa el formulario (.*) veces$")
    public void elUsuarioIngresaEnElApartadoTestYCompletaElFormularioVeces(int numero_veces) {
        // COMPLETAR FORMULARIO
        theActorInTheSpotlight().attemptsTo(CompletarForm.completarForm());
    }


    @Then("^el envio del formulario ha sido completado con exito$")
    public void elEnvioDelFormularioHaSidoCompletadoConExito() {
        theActorInTheSpotlight().should(seeThat("Confirmacion final", GetText.getText(TestPage.CONFIRMACION_FINAL),equalTo("Felicidades, has terminado la prueba exitosamente ")));
    }


    @After("@CompletarFormulario")
    public void finishThestage() throws Exception {
        // FINALIZA LA GRABACION DE EVIDENCIA
        MyScreenRecorder.stopRecording();
    }

}
