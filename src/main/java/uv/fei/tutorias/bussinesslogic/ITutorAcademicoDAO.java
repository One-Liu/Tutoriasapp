package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import uv.fei.tutorias.domain.TutorAcademico;

import java.util.ArrayList;
import javafx.collections.ObservableList;

// author @liu
public interface ITutorAcademicoDAO {
    public ObservableList<TutorAcademico> obtenerTutoresAcademicos() throws SQLException;
    public TutorAcademico obtenerTutorAcademicoPorId(int idTutorAcademico) throws SQLException;
    public boolean agregarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException;
    public boolean eliminarTutorAcademicoPorId(int idTutorAcademico) throws SQLException;
    public boolean modificarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException;
    public ArrayList<TutorAcademico> recuperarTodosTutoresAcademicos() throws SQLException;
}
