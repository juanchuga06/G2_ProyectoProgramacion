package UserInterfaceComponent.Form;

public final class SesionSistema {

    private static int idBibliotecario;

    private SesionSistema() { }   // no instanciable

    public static int getIdBibliotecario() {
        return idBibliotecario;
    }

    public static void iniciarSesion(int id) {
        idBibliotecario = id;
    }

    public static void cerrarSesion() {
        idBibliotecario = 0;
    }
}