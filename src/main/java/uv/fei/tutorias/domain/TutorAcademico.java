package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico extends Persona {
    private int id;
    private int idUsuario;

    // Constructors of uv.fei.tutorias.domain.TutorAcademico
    public TutorAcademico() {
        super("","","");
        this.id = 0;
        this.idUsuario = 0;
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno, int idUsuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.idUsuario = idUsuario;
    }

    public TutorAcademico(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int idUsuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.idUsuario = idUsuario;
    }

    // Getters of uv.fei.tutorias.domain.TutorAcademico
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Setters of uv.fei.tutorias.domain.TutorAcademico
    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(this.id == tmpTutorAcademico.getId()
                    && getIdPersona() == tmpTutorAcademico.getIdPersona()
                    && this.idUsuario == tmpTutorAcademico.getIdUsuario()) {
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