package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

// author @liu
public interface IEstudianteDAO {
    public List<Persona> findEstudianteByName(String searchName);
    public Persona findEstudianteById(int idEstudiante);
    public boolean addEstudiante(Persona estudiante);
    public boolean deleteEstudianteById(int idEstudiante);
}