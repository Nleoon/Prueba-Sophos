package co.com.prueba.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class TestPage {

    // FECHA
    public static final Target ENUNCIADO_FECHA = Target.the("ENUNCIADO FECHA").
            locatedBy("//p[@class='text-center text-xl'][contains(text(),'Indique')]");
    public static final Target INPUT_FECHA = Target.the("INPUT FECHA").
            locatedBy("//input[@type='date']");

    // OPERACION
    public static final Target OPERACION = Target.the("OPERACION").
            locatedBy("//p[@class='text-center text-xl font-bold']");
    public static final Target RADIO_RESULTADO_OPERACION = Target.the("RESULTADO OPERACION").
            locatedBy("//input[@type='radio'][@value='{0}']");

    // LETRA
    public static final Target ENUNCIADO_LETRA = Target.the("ENUNCIADO PUNTO ESCRIBIR LETRA").
            locatedBy("//p[@class='text-center text-xl'][contains(text(),'Escriba')]");
    public static final Target TXT_LETRA = Target.the("TEXT AREA - ESCRIBIR LETRA").
            locatedBy("//textarea[@type='text']");

    // MULTIPLOS
    public static final Target ENUNCIADO_MULTIPLOS = Target.the("ENUNCIADO PUNTO MULTIPLOS").
            locatedBy("//p[@class='text-center text-xl'][contains(text(),'Selecciona')]");
    public static final Target OPC_RESPUESTA = Target.the("OPCIONES DE RESPUESTA MULTIPLOS").
            locatedBy("(//input[@name='checkbox'])[{0}]");
    public static final Target CHECK_VALUE = Target.the("OPCIONES DE RESPUESTA MULTIPLOS").
            locatedBy("//input[@name='checkbox'][@value='{0}']");
    public static final Target CHECKBOX = Target.the("CHECKBOX").
            locatedBy("//input[@type='checkbox']");


    public static final Target TEXTO_CICLOS = Target.the("TEXTO CICLOS").
            locatedBy("//p[@class='text-xl text-center text-green-500']");
    public static final Target BTN_ENVIAR = Target.the("BOTON ENVIAR").
            locatedBy("//button[@type='submit']");
    public static final Target CONFIRMACION_FINAL = Target.the("CONFIRMACION FINAL").
            locatedBy("//h1[@class='text-center text-3xl p-5']");
}
