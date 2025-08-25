package platzi.play.plataforma;

import platzi.play.contenido.Contenido;

public class Usuario {
    public String nombre;
    public String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public void ver(Contenido contenido) {
        System.out.println(nombre + " está viendo...");
        contenido.reproducir();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
