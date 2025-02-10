package BusinessLogicComponent.entities.Personas;

public class Direccion {
    private Integer IdDireccion;
    private String CallePrimaria;
    private String CalleSecundaria;
    private Cliente Cliente;
    private Bibliotecario Bibliotecario;
    
    // Contstructor para Bibliotecario
    public Direccion(Integer idDireccion, String callePrimaria, String calleSecundaria, 
                     Bibliotecario bibliotecario) {
        setIdDireccion(idDireccion);
        setCallePrimaria(callePrimaria);
        setCalleSecundaria(calleSecundaria);
        setBibliotecario(bibliotecario);
    }

    public Direccion(String callePrimaria, String calleSecundaria, 
                     Bibliotecario bibliotecario) {
        setCallePrimaria(callePrimaria);
        setCalleSecundaria(calleSecundaria);
        setBibliotecario(bibliotecario);
    }

    // Constructor para Cliente
    public Direccion(Integer idDireccion, String callePrimaria, String calleSecundaria,
                     Cliente cliente) {
        setIdDireccion(idDireccion);
        setCallePrimaria(callePrimaria);
        setCalleSecundaria(calleSecundaria);
        setCliente(cliente);
    }

    public Direccion(String callePrimaria, String calleSecundaria, Cliente cliente) {
        setCallePrimaria(callePrimaria);
        setCalleSecundaria(calleSecundaria);
        setCliente(cliente);
    }

    
    public Integer getIdDireccion() {
        return IdDireccion;
    }
    public void setIdDireccion(Integer idDireccion) {
        if(idDireccion == null || idDireccion <= 0)
            return;
        else
            this.IdDireccion = idDireccion;
    }
    public String getCallePrimaria() {
        return CallePrimaria;
    }
    public void setCallePrimaria(String callePrimaria) {
        if(callePrimaria == null || callePrimaria.isEmpty())
            this.CallePrimaria = "CalleComun";
        else
            this.CallePrimaria = callePrimaria;
    }
    public String getCalleSecundaria() {
        return CalleSecundaria;
    }
    public void setCalleSecundaria(String calleSecundaria) {
        if(calleSecundaria == null || calleSecundaria.isEmpty())
            this.CalleSecundaria = "CalleComun";
        else
            this.CalleSecundaria = calleSecundaria;
    }
    public Cliente getCliente() {
        return Cliente;
    }
    public void setCliente(Cliente cliente) {
        if(cliente == null)
            return;
        else{
            this.Cliente = cliente;
        }
        
    }
    public Bibliotecario getBibliotecario() {
        return Bibliotecario;
    }
    public void setBibliotecario(Bibliotecario bibliotecario) {
        if(bibliotecario == null)
            return;
        else
            this.Bibliotecario = bibliotecario;
    }


}
