package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;

import uv.fei.tutorias.domain.Profesor;

public interface IProfesorDAO {
    public List<Profesor> findProfesoresByName(String searchName) throws SQLException;
    public Profesor findProfesorById(int idProfesor) throws SQLException;
    public boolean addProfesor(Profesor profesor) throws SQLException;
    public boolean deleteProfesorById(int idProfesor) throws SQLException;
    public List<Profesor> obtenerProfesores() throws SQLException;
}
