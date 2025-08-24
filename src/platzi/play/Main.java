package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.ScannerUtils;

import java.util.List;

public class Main {
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;

    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma("Gestion peliculas");

        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getTotalDuracion() + " minutos de contenido.");
        while (true) {
            int opcion;
            opcion = ScannerUtils.capturarNumero("""
                    Ingrese una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    8. Eliminar
                    9. Salir
                    """);
            System.out.println("Opción Elegida: " + opcion);


            switch (opcion) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));

                }
                case MOSTRAR_TODO -> {
                    List<String> titulos = plataforma.getTitulos();
                    titulos.forEach(System.out::println);

                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);

                    if (pelicula != null){
                        System.out.println(pelicula.obtenerFichaTecnica());
                    }
                    else {
                        System.out.println("El nombre no existe dentro de " + plataforma.getNombre());
                    }

                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar");
                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Ingrese la cantidad de resultados a mostrar");
                    List<Pelicula> contenidosPopulares = plataforma.getPopulares(cantidad);
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()+ "\n" ));
                }
                case ELIMINAR -> {
                    String nombreEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreEliminar);
                    if (pelicula != null) {
                        plataforma.eliminar(pelicula);
                        System.out.println(nombreEliminar+ " Eliminado correctamente ✖️");
                    }
                    else {
                        System.out.println("El nombre no existe dentro de \" + plataforma.getNombre()");
                    }
                }
                case SALIR -> System.exit(0);
            }
        }
    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA, 4.5));
        plataforma.agregar(new Pelicula("El Señor de los Anillos", 201, Genero.FANTASIA, 4.9));
        plataforma.agregar(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION, 4.8));
        plataforma.agregar(new Pelicula("Toy Story", 81, Genero.CIENCIA_FICCION, 4.7));
        plataforma.agregar(new Pelicula("Gladiador", 155, Genero.ACCION, 4.6));
        plataforma.agregar(new Pelicula("Titanic", 195, Genero.DRAMA, 4.3));
        plataforma.agregar(new Pelicula("Matrix", 136, Genero.CIENCIA_FICCION, 4.9));
        plataforma.agregar(new Pelicula("Coco", 105, Genero.ANIMADA, 4.8));
        plataforma.agregar(new Pelicula("Interestelar", 169, Genero.CIENCIA_FICCION, 5));

    }
}
