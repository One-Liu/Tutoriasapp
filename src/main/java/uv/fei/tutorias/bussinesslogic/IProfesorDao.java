package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.util.List;

public interface IProfesorDao {
    public List<Profesor> findProfesoresByName(String searchName);
    public Profesor findProfesorById(int idProfesor);
    public boolean addProfesor(Persona profesor);
    public boolean deleteProfesorById(int idProfesor);
}
