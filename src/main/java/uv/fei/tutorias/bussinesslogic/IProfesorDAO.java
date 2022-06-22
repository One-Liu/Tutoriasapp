package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;

import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

public interface IProfesorDAO {

    public boolean agregarProfesor(Profesor profesor) throws SQLException;

    boolean agregarProfesorYPersona(Persona persona) throws SQLException;

    Profesor obtenerProfesorPorId(int searchId) throws SQLException;

    public boolean eliminarProfesorPorId(int idProfesor) throws SQLException;
    public List<Profesor> obtenerProfesores() throws SQLException;
}
