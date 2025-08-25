package platzi.play.util;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";

    public static void escribirContenido(Pelicula contenido) {
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
                );
        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo.");;
        }
    }

    public static List<Pelicula> leerContenido() {
        List<Pelicula> contenidoDesdeArchivo = new ArrayList<>();

        try{
            List<String> lineas= Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);
                if(datos.length == 5){
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion);
                    pelicula.setFechaEstreno(fechaEstreno);

                    contenidoDesdeArchivo.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Falló al cargar el archivo: "+ e.getMessage());
        }
        return contenidoDesdeArchivo;
    }
}
