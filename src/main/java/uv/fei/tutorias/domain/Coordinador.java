package uv.fei.tutorias.domain;

// author @liu
public class Coordinador extends Persona {
    private int id;
    private int idProgramaEducativo;
    private Usuario usuario;

    // Constructors of uv.fei.tutorias.domain.Coordinador
    public Coordinador() {
        super("","","");
        this.id = 0;
        this.idProgramaEducativo = 0;
        this.usuario = new Usuario();
    }

    public Coordinador(String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.idProgramaEducativo = idProgramaEducativo;
        this.usuario = new Usuario();
    }

    public Coordinador(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.idProgramaEducativo = idProgramaEducativo;
        this.usuario = new Usuario();
    }

    public Coordinador(String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.idProgramaEducativo = idProgramaEducativo;
        this.usuario = usuario;
    }

    public Coordinador(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.idProgramaEducativo = idProgramaEducativo;
        this.usuario = usuario;
    }

    public Coordinador(Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.id = 0;
        this.idProgramaEducativo = 0;
        this.usuario = new Usuario();
    }

    // Getters of uv.fei.tutorias.domain.Coordinador
    public int getId() {
        return id;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Setters of uv.fei.tutorias.domain.Coordinador
    public void setId(int id) {
        this.id = id;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.id == tmpCoordinador.getId()
                    && this.idPersona == tmpCoordinador.getIdPersona()
                    && this.nombre.equals(tmpCoordinador.getNombre())
                    && this.apellidoPaterno.equals(tmpCoordinador.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpCoordinador.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpCoordinador.getIdProgramaEducativo()
                    && this.usuario.equals(tmpCoordinador.getUsuario())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellidoPaterno() + " " + getApellidoMaterno();
    }
}