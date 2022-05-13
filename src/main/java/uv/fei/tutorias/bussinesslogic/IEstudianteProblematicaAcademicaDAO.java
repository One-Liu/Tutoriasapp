package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;

import java.util.List;

public interface IEstudianteProblematicaAcademicaDAO {
    public List<EstudiantesProblematicasAcademicas> findEstudiantesProblematicasAcademicasById(int searchIdproblematicaAcademica);
    public boolean addEstudiantesProblematicasAcademicas(EstudiantesProblematicasAcademicas estudiante_problematicaAcademica);
    public boolean deleteEstudinatesProblematicasAcademicasById(int searchId);
}
