package platzi.play.plataforma;

import platzi.play.contenido.*;
import platzi.play.excepxion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido; // Agregación
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Contenido elemento) {
        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces.");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Contenido contenido) {
        int conteoActal = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActal + 1);
    }

    public List<String> getTitulos() {
//        for (Contenido pelicula : contenido) {
//            System.out.println(pelicula.getTitulo());
//        }
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumen() {
        return contenido.stream()
                .map(contenido -> new ResumenContenido(contenido.getTitulo(), contenido.getDuracion(), contenido.getGenero()))
                .toList();
    }

    public int getTotalDuracion() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparing(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas() {
        return contenido.stream()
                .filter(contenido1 -> contenido1 instanceof Pelicula)
                .map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();
    }

    public List<Documental> getDocumentales() {
        return contenido.stream()
                .filter(contenido1 -> contenido1 instanceof Documental)
                .map(contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();
    }


    public void eliminar(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    public Contenido buscarPorTitulo(String titulo) {
//        for (Contenido pelicula : contenido) {
//            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){
//                return pelicula;
//            }
//        }
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
