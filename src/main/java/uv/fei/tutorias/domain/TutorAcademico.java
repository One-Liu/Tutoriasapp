package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico extends Persona {
    private int id;
    private Usuario usuario;

    // Constructors of uv.fei.tutorias.domain.TutorAcademico
    public TutorAcademico() {
        super("","","");
        this.id = 0;
        this.usuario = new Usuario();
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.usuario = new Usuario();
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.usuario = usuario;
    }

    public TutorAcademico(int id, String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.usuario = usuario;
    }

    // Getters of uv.fei.tutorias.domain.TutorAcademico
    public int getId() {
        return id;
    }

    public int getUsuario() {
        return usuario;
    }

    // Setters of uv.fei.tutorias.domain.TutorAcademico
    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(this.id == tmpTutorAcademico.getId()
                    && this.idPersona == tmpTutorAcademico.getIdPersona()
                    && this.nombre.equals(tmpTutorAcademico.getNombre())
                    && this.apellidoPaterno.equals(tmpTutorAcademico.getApellidoPaterno)
                    && this.apellidoMaterno.equals(tmpTutorAcademico.getApellidoMaterno)
                    && this.usuario.equals(tmpTutorAcademico.getUsuario())) {
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