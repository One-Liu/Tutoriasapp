package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public interface IEstudianteDAO {
    public ArrayList<Estudiante> findEstudianteByName(String searchName);
    public Estudiante findEstudianteById(int idEstudiante);
    public boolean addEstudiante(Estudiante estudiante);
    public boolean deleteEstudianteById(int idEstudiante);
}
