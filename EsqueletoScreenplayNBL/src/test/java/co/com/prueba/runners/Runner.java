package co.com.prueba.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

// INDICAR QUE LA PRUEBA SE VA A EJECUTAR CON SERENITY
@RunWith(CucumberWithSerenity.class)

// CONFIGURACIÓN
@CucumberOptions(
        features = "src/test/resources/features/prueba_automation.feature", // UBICACION DE LOS FEATURES
        glue = "co.com.prueba/stepDefinitions/", // UBICACION DE LOS STEPS
        tags = "", // SCENARIO QUE SE QUIERE PROBAR
        snippets = SnippetType.CAMELCASE // LECTURA DE PASOS EN JAVA
)

public class Runner {
}
