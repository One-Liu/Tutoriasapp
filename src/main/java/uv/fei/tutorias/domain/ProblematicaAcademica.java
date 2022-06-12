package uv.fei.tutorias.domain;

// author @liu

import java.util.Objects;


public class ProblematicaAcademica {
    private int idProblematicaAcademica;
    private String titulo;
    private String descripcion;
    private int idSolucionProblematicaAcademica;
    private int idSesionDeTutoriaAcademica;
    private int idExperienciaEducativa;
    private int idProfesor;

    public ProblematicaAcademica() {
        this.idProblematicaAcademica = 0;
        this.titulo = "";
        this.descripcion = "";
        this.idSolucionProblematicaAcademica = 0;
        this.idSesionDeTutoriaAcademica = 0;
        this.idExperienciaEducativa = 0;
        this.idProfesor = 0;
    }

    public ProblematicaAcademica(int idProblematicaAcademica, String titulo, String descripcion, int idSolucionProblematicaAcademica, int idSesionDeTutoriaAcademica, int idExperienciaEducativa, int idProfesor) {
        this.idProblematicaAcademica = idProblematicaAcademica;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idSolucionProblematicaAcademica = idSolucionProblematicaAcademica;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idProfesor = idProfesor;
    }

    public ProblematicaAcademica(String titulo, String descripcion, int idSesionDeTutoriaAcademica, int idSolucionProblematicaAcademica, int idExperienciaEducativa, int idProfesor) {
        this.idProblematicaAcademica = 0;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idSolucionProblematicaAcademica = idSolucionProblematicaAcademica;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idProfesor = idProfesor;
    }

    // Getters of uv.fei.tutorias.domain.ProblematicaAcademica
    public int getIdProblematicaAcademica() {
        return idProblematicaAcademica;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public int getIdSolucionProblematicaAcademica() {
        return idSolucionProblematicaAcademica;
    }

    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public int getIdProfesor() {
        return idProfesor;
    }
    
    // Setters of uv.fei.tutorias.domain.ProblematicaAcademica
    public void setIdProblematicaAcademica(int idProblematicaAcademica) {
        this.idProblematicaAcademica = idProblematicaAcademica;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public void setIdSolucionProblematicaAcademica(int idSolucionProblematicaAcademica) {
        this.idSolucionProblematicaAcademica = idSolucionProblematicaAcademica;
    }

    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProblematicaAcademica other = (ProblematicaAcademica) obj;
        if (this.idProblematicaAcademica != other.idProblematicaAcademica) {
            return false;
        }
        if (this.idSolucionProblematicaAcademica != other.idSolucionProblematicaAcademica) {
            return false;
        }
        if (this.idSesionDeTutoriaAcademica != other.idSesionDeTutoriaAcademica) {
            return false;
        }
        if (this.idExperienciaEducativa != other.idExperienciaEducativa) {
            return false;
        }
        if (this.idProfesor != other.idProfesor) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }

    @Override
    public int hashCode() {
        int result = getIdProblematicaAcademica();
        result = 31 * result + (getDescripcion() != null ? getDescripcion().hashCode() : 0);
        result = 31 * result + getIdExperienciaEducativa();
        return result;
    }
}