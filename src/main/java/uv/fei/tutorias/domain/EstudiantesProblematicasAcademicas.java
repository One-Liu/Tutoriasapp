package uv.fei.tutorias.domain;

// author @liu

public class EstudiantesProblematicasAcademicas {
    private int id;
    private int idEstudiante;
    private int idProblematicaAcademica;
    private Estudiante estudiante;
    private ProblematicaAcademica problematicaAcademica;

    public EstudiantesProblematicasAcademicas(int idEstudiante, int idProblematicaAcademica) {
        this.idEstudiante = idEstudiante;
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public EstudiantesProblematicasAcademicas() {
        this.id = 0;
        this.idEstudiante = 0;
        this.idProblematicaAcademica = 0;
        this.estudiante = new Estudiante();
        this.problematicaAcademica = new ProblematicaAcademica();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ProblematicaAcademica getProblematicaAcademica() {
        return problematicaAcademica;
    }

    public void setProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        this.problematicaAcademica = problematicaAcademica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstudiantesProblematicasAcademicas)) return false;

        EstudiantesProblematicasAcademicas that = (EstudiantesProblematicasAcademicas) o;

        if (getId() != that.getId()) return false;
        if (getIdEstudiante() != that.getIdEstudiante()) return false;
        return getIdProblematicaAcademica() == that.getIdProblematicaAcademica();
    }



    @Override
    public String toString() {
        return "EstudiantesProblematicasAcademicas{" +
                "id=" + id +
                ", idEstudiante='" + idEstudiante + '\'' +
                ", idProblematicaAcademica='" + idProblematicaAcademica + '\'' +
                '}';
    }
}