package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;

import java.sql.SQLException;
import java.util.List;

public interface IEstudianteProblematicaAcademicaDAO {
    public List<EstudiantesProblematicasAcademicas> obtenerEstudianteProblematicaAcademicaPorId(int searchIdproblematicaAcademica) throws SQLException;
    public boolean agregarEstudianteProblematicaAcademica(EstudiantesProblematicasAcademicas estudiante_problematicaAcademica) throws SQLException;
    public boolean eliminarEstudianteProblematicaAcademicaPorId(int searchId) throws SQLException;
}
