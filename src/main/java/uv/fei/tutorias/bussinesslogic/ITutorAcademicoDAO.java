package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

// author @liu

public interface ITutorAcademicoDAO {
    public List<Persona> findTutoresAcademicosByName(String searchName);
    public Persona findTutorAcademicoById(int idTutorAcademico);
    public boolean addTutorAcademico(Persona tutorAcademico);
    public boolean deleteTutorAcademicoById(int idTutorAcademico);
}
