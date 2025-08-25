package platzi.play.contenido;

public class Documental extends Contenido {
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documental narrado por " + getNarrador());
    }

    public String getNarrador() {
        return narrador;
    }
}
