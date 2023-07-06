package co.com.prueba.utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MIME_AVI;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class MyScreenRecorder extends ScreenRecorder {

    // METODOD PARA GUARDAR LA FECHA DEL SISTEMA
    public static String fechaHora() {
        // TOMAMOS LA FECHA DEL SISTEMA
        LocalDateTime fechaSistema = LocalDateTime.now();
        // DEFINIR FORMATO FECHA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        // DAR FORMATO A LA FECHA DEL SISTEMA
        String formatFecha = fecha.format(fechaSistema);
        return formatFecha;
    }

    // METODO PARA CREAR LA CARPETA DONDE SE ALMACENAN LOS SCREEN'S
    public static File crearCarpeta(String nomScenario)  {
        String rutaEvidencia = "./output/ScreenRecording/";
        // ALMACENAMOS LA FECHA DEL SISTEMA
        String fecha = fechaHora();
        // CREAMOS EL NOMBRE DE LA CARPETA
        String nomCarpeta = nomScenario+" - "+fecha;
        // OBTENEMOS LA RETA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
        File directorio = new File(rutaEvidencia+nomCarpeta);
        // CREAMOS LA CARPETA
        directorio.mkdir();
        return directorio;
    }

    // SCREEN RECORDER
    public static ScreenRecorder screenRecorder;
    public String name;

    public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat,
                            Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException{

        if(!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File (movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    public static void startRecording(File rutaCarpeta) throws Exception
    {
            //File file = new File ("./output/ScreenRecording/");
            String fecha = fechaHora();
            System.out.println("Inicia el proceso de grabacion");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;

            Rectangle captureSize = new Rectangle(0, 0, width, height);

            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

            screenRecorder = new MyScreenRecorder(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, rutaCarpeta, "ScreenRecorder_Evidencia");

            screenRecorder.start();
    }

    public static void stopRecording() throws Exception{
        screenRecorder.stop();
        System.out.println("Finaliza el proceso de grabacion");
    }
}
