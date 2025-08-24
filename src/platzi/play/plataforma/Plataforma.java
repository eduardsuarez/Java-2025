package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido; // Agregación

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }
    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);

    }

    public List<String> getTitulos(){
//        for (Pelicula pelicula : contenido) {
//            System.out.println(pelicula.getTitulo());
//        }
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }
    public int getTotalDuracion(){
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

    public void eliminar(Pelicula elemento){
        this.contenido.remove(elemento);
    }

    public Pelicula buscarPorTitulo(String titulo) {
//        for (Pelicula pelicula : contenido) {
//            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){
//                return pelicula;
//            }
//        }
         return  contenido.stream()
                .filter(contenido-> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equalsIgnoreCase(genero))
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
