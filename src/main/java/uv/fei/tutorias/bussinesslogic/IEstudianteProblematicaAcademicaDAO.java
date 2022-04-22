package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Estudiante_ProblematicaAcademica;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IEstudianteProblematicaAcademicaDAO {
    public Estudiante_ProblematicaAcademica findEstudianteProblematicaAcademicaById(int searchId);
    public boolean addEstudianteProblematicaAcademica(Estudiante_ProblematicaAcademica estudiante_problematicaAcademica);
    public boolean deleteEstudinateProblematicaAcademica(int searchId);
}
