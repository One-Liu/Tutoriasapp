package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Estudiante;

import java.util.List;

// author @liu
public interface IEstudianteDAO {
    public List<Estudiante> findEstudianteByName(String searchName);
    public Estudiante findEstudianteById(int idEstudiante);
    public boolean addEstudiante(Estudiante estudiante);
    public boolean deleteEstudianteById(int idEstudiante);
}