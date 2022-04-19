package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.List;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public interface IEstudianteDAO {
    public List<Estudiante> findEstudianteByName(String searchName);
    public Estudiante findEstudianteById(int idEstudiante);
    public Estudiante getEstudiante(ResultSet resultSet);
    public boolean addEstudiante(Estudiante estudiante);
    public boolean deleteEstudianteById(int idEstudiante);
}