package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;

import java.util.List;

public interface IEstudianteProblematicaAcademicaDAO {
    public List<EstudiantesProblematicasAcademicas> obtenerEstudianteProblematicaAcademicaPorId(int searchIdproblematicaAcademica);
    public boolean agregarEstudianteProblemtaicaAcademica(EstudiantesProblematicasAcademicas estudiante_problematicaAcademica);
    public boolean eliminarEstudianteProblematicaAcademicaPorId(int searchId);
}
