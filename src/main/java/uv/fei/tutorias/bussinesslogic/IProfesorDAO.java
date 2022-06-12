package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.Profesor;

public interface IProfesorDAO {
    public ObservableList<Profesor> findProfesoresByName(String searchName);
    public Profesor findProfesorById(int idProfesor);
    public boolean addProfesor(Profesor profesor);
    public boolean deleteProfesorById(int idProfesor);
    public ObservableList<Profesor> obtenerProfesores() throws SQLException;
}
