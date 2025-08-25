package platzi.play.plataforma;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepxion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido; // Agregación
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Pelicula elemento) {
        Pelicula contenido = this.buscarPorTitulo(elemento.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }
    public void reproducir(Pelicula contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido "+ conteoActual + " veces.");

        this.contarVisualizcion(contenido);
        contenido.reproducir();
    }
    private void contarVisualizcion(Pelicula contenido) {
        int conteoActal = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActal + 1);
    }

    public List<String> getTitulos() {
//        for (Pelicula pelicula : contenido) {
//            System.out.println(pelicula.getTitulo());
//        }
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumen() {
        return contenido.stream()
                .map(pelicula -> new ResumenContenido(pelicula.getTitulo(), pelicula.getDuracion(), pelicula.getGenero()))
                .toList();
    }

    public int getTotalDuracion() {
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparing(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    public Pelicula buscarPorTitulo(String titulo) {
//        for (Pelicula pelicula : contenido) {
//            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){
//                return pelicula;
//            }
//        }
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
