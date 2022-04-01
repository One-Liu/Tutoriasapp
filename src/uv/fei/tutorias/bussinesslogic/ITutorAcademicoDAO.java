package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

import java.util.List;

// author @liu
public interface ITutorAcademicoDAO {
    public List<TutorAcademico> findTutoresAcademicosByName(String searchName);
    public TutorAcademico findTutorAcademicoById(int idTutorAcademico);
    public boolean addTutorAcademico(Persona tutorAcademico);
    public boolean deleteTutorAcademicoById(int idTutorAcademico);
}
