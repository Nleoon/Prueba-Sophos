package co.com.prueba.taks;

import co.com.prueba.questions.GetText;
import co.com.prueba.userInterfaces.TestPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static co.com.prueba.utils.BaseClass.*;
import static co.com.prueba.utils.ConvertirStringFormula.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.core.IsEqual.equalTo;

public class CompletarForm implements Task {

    public CompletarForm(){
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        for(int i = 0; i<10; i++){

            // FECHAS
            actor.attemptsTo(
                    waitIsEnabled(TestPage.ENUNCIADO_FECHA,10)
            );

            String enunciado_fecha = actor.asksFor(GetText.getText(TestPage.ENUNCIADO_FECHA));

            String[] enunciado_fecha_dividido = enunciado_fecha.split(" ");
            int dias_por_sumar = Integer.valueOf(enunciado_fecha_dividido[6]);

            String[] enunciado_fecha_dividido_2 = enunciado_fecha.split(":");
            String fecha = String.valueOf(enunciado_fecha_dividido_2[1]);
            String fecha_inicial = fecha.replace(" ","");

            String fecha_final = sumarDiasFecha(fecha_inicial,dias_por_sumar);
            System.out.println(fecha_final + " ESTA ES LA FECHA FINAL");

            actor.attemptsTo(
                sendKey(fecha_final,TestPage.INPUT_FECHA)
            );



            // OPERACIONES
            actor.attemptsTo(
                    waitIsEnabled(TestPage.OPERACION,10)
            );

            String operacion = actor.asksFor(GetText.getText(TestPage.OPERACION));
            String operacion2 = operacion.replace("?","");
            String operacion3 = operacion2.replace("=","");

            String resultado_operacion = convertirStringAformula(operacion3);

            actor.attemptsTo(
                    waitIsEnabled(TestPage.TEXTO_CICLOS,10),
                    Click.on(TestPage.RADIO_RESULTADO_OPERACION.of(resultado_operacion))
            );



            // LETRA
            actor.attemptsTo(
                    Scroll.to(TestPage.BTN_ENVIAR).andAlignToBottom()
            );

            String enunciado_letra = actor.asksFor(GetText.getText(TestPage.ENUNCIADO_LETRA));
            String[] enunciado_dividido = enunciado_letra.split(" ");
            int numero_repeticiones = Integer.valueOf(enunciado_dividido[1]);
            String letra_deseada = String.valueOf(enunciado_dividido[5]);
            String letra_sin_comillas = letra_deseada.replace("\"","");

            for (int e = 0; e < numero_repeticiones; e++) {
                actor.attemptsTo(
                        sendKey(letra_sin_comillas,TestPage.TXT_LETRA)
                );
            }
            System.out.println("SE ESCRIBIO " + numero_repeticiones + " VECES LA LETRA " + letra_sin_comillas);



            // MULTIPLOS
            actor.attemptsTo(
                    Scroll.to(TestPage.BTN_ENVIAR).andAlignToBottom()
            );

            String enunciado_multiplos = actor.asksFor(GetText.getText(TestPage.ENUNCIADO_MULTIPLOS));
            String[] enunciado_multiplos_dividido = enunciado_multiplos.split(" ");
            int numero_multiplos = Integer.valueOf(enunciado_multiplos_dividido[5]);

            // CONTADOR CHECKBOX
            List<WebElementFacade> checkboxes = TestPage.CHECKBOX.resolveAllFor(actor);
            int cantidadCheckboxes = checkboxes.size();

            for (int o = 1; o <= cantidadCheckboxes; o++) {
                Integer valor_opciones = actor.asksFor(GetText.getValue(TestPage.OPC_RESPUESTA.of(String.valueOf(o))));
                if (valor_opciones % numero_multiplos == 0) {
                    actor.attemptsTo(
                            Click.on(TestPage.CHECK_VALUE.of(String.valueOf(valor_opciones)))
                    );
                }
            }
            System.out.println("SE SELECCIONARON TODOS LOS MULTIPLOS DE "+ numero_multiplos);

            actor.attemptsTo(
                    Click.on(TestPage.BTN_ENVIAR)
            );

        }

        actor.should(
                seeThat("Validacion del filtro de ID usuario", GetText.getBoolean(TestPage.CONFIRMACION_FINAL),equalTo(true))
        );
    }

    public static CompletarForm completarForm(){
        return instrumented(CompletarForm.class);
    }
}

