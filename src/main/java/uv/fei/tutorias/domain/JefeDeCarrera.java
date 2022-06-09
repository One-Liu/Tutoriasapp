package uv.fei.tutorias.domain;

public class JefeDeCarrera extends Usuario {
    private int id;
    private Usuario usuario;

    public JefeDeCarrera(String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.usuario = usuario;
    }

    public JefeDeCarrera() {
        this.usuario = new Usuario();

    }
    public JefeDeCarrera(Persona persona){
        super(persona.getNombre(),persona.getApellidoPaterno(),persona.getApellidoPaterno());
        this.usuario = new Usuario();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
