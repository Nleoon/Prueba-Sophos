package co.com.prueba.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ConvertirStringFormula{

    public static String convertirStringAformula(String operacion) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object result = null;
        try {
            result = engine.eval(operacion);
            System.out.println("El resultado de la operacion es: " + result);
        } catch (ScriptException e) {
            System.out.println("Error al evaluar la operacion: " + e.getMessage());
        }
        return String.valueOf(result);
    }

    public static Date sumarDiasAFecha(Date fecha, int days_por_sumar){
        if (days_por_sumar==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, days_por_sumar);
        return calendar.getTime();
    }

    public static String sumarDiasFecha(String fecha, int numDias) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicial = LocalDate.parse(fecha, formatter);
        LocalDate fechaResultante = fechaInicial.plusDays(numDias);
        return fechaResultante.format(formatter);
    }
}
