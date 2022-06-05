package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico extends Usuario {
    private int idTutorAcademico;

    public TutorAcademico() {
        this.idTutorAcademico = 0;
    }

    public TutorAcademico(Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.idTutorAcademico = 0;
    }

    public TutorAcademico(int id, Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.idTutorAcademico = id;
    }
    
    public TutorAcademico(Persona persona, Usuario usuario) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.idTutorAcademico = 0;
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre,apellidoPaterno,apellidoMaterno);
        this.idTutorAcademico = 0;
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.idTutorAcademico = 0;
    }

    
    public int getId() {
        return idTutorAcademico;
    }

    public void setId(int id) {
        this.idTutorAcademico = id;
    }

    @Override
    public String toString() {
        return "TutorAcademico{" +
                "id=" + idTutorAcademico +
                ", Persona="+getNombre()+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(
                    this.idTutorAcademico == tmpTutorAcademico.getId()
                    && this.nombre.equals(tmpTutorAcademico.getNombre())
                    && this.apellidoPaterno.equals(tmpTutorAcademico.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpTutorAcademico.getApellidoMaterno())
            ) {
                return true;
            }
        }
        return false;
    }

}