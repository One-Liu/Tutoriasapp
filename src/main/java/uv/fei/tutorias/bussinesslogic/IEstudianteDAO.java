package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public interface IEstudianteDAO {
    public List<Estudiante> obtenerEstudiantes() throws SQLException;
    public Estudiante obtenerEstudiantePorId(int idEstudiante) throws SQLException;
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException;
    public boolean eliminarEstudiantePorId(int idEstudiante) throws SQLException;
    public boolean modificarAsignacionDeTutor(Estudiante estudiante) throws SQLException;
    public List<Estudiante> obtenerEstudiantesDeTutor(int idTutorAcademico) throws SQLException;
    public List<Estudiante> obtenerEstudiantesSinTutorAsignado() throws SQLException;
    public List<Estudiante> obtenerEstudiantesConTutorAsignado() throws SQLException;
}
