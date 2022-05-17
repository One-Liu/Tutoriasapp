package uv.fei.tutorias.domain;

// author @liu
public class Coordinador extends Persona {
    private int id;
    private int idProgramaEducativo;
    private int idUsuario;

    // Constructors of uv.fei.tutorias.domain.Coordinador
    public Coordinador() {
        super("","","");
        this.id = 0;
        this.idProgramaEducativo = 0;
        this.idUsuario = 0;
    }

    public Coordinador(String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo, int idUsuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idUsuario = idUsuario;
    }

    public Coordinador(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo, int idUsuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idUsuario = idUsuario;
    }

    // Getters of uv.fei.tutorias.domain.Coordinador
    public int getId() {
        return id;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Setters of uv.fei.tutorias.domain.Coordinador
    public void setId(int id) {
        this.id = id;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.id == tmpCoordinador.getId()
                    && getIdPersona() == tmpCoordinador.getIdPersona()
                    && this.idProgramaEducativo == tmpCoordinador.getIdProgramaEducativo()
                    && this.idUsuario == tmpCoordinador.getIdUsuario()) {
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