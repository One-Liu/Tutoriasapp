package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public interface IEstudianteDAO {
    public ObservableList<Estudiante> obtenerEstudiantes() throws SQLException;
    public Estudiante obtenerEstudiantePorId(int idEstudiante) throws SQLException;
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException;
    public boolean eliminarEstudiantePorId(int idEstudiante) throws SQLException;
    public boolean modificarEstudiante(Estudiante estudiante) throws SQLException;
    public ArrayList<Estudiante> recuperarTodosEstudiantesPorIDTutor(int idTutor)throws SQLException;
}
