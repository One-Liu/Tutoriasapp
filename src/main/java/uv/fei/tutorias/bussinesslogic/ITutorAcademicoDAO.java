package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import uv.fei.tutorias.domain.TutorAcademico;

import java.util.ArrayList;
import java.util.List;

// author @liu
public interface ITutorAcademicoDAO {
    public List<TutorAcademico> findTutoresAcademicosByName(String searchName);

    public ArrayList<TutorAcademico> recuperarTodosTutoresAcademicos();

    public TutorAcademico findTutorAcademicoById(int idTutorAcademico);
    public boolean addTutorAcademico(TutorAcademico tutorAcademico);
    public boolean deleteTutorAcademicoById(int idTutorAcademico);
}
