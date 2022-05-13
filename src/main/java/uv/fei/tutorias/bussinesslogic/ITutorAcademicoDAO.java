package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

import java.util.List;

// author @liu

public interface ITutorAcademicoDAO {
    public List<Persona> findTutoresAcademicosByName(String searchName);
    public Persona findTutorAcademicoById(int idTutorAcademico);
    public boolean addTutorAcademico(TutorAcademico tutorAcademico);
    public boolean deleteTutorAcademicoById(int idTutorAcademico);
}
