package BusinessLogicComponent.entities.Libros;

import javax.swing.ImageIcon;

public class Portada {
    private Integer IdPortada;
    private ImageIcon Portada;
    private Integer IdLibro;

    public Portada() {
    }

    public Portada(Integer idPortada, ImageIcon portada, Integer idLibro) {
        setIdPortada(idPortada);
        setPortada(portada);
        setIdLibro(idLibro);
    }
   
    public Portada(ImageIcon portada, Integer idLibro) {
        setPortada(portada);
        setIdLibro(idLibro);
    }
    
    public Integer getIdPortada() {
        return IdPortada;
    }

    public void setIdPortada(Integer idPortada) {
        IdPortada = idPortada;
    }

    public ImageIcon getPortada() {
        return Portada;
    }

    public void setPortada(ImageIcon portada) {
        Portada = portada;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }




}
