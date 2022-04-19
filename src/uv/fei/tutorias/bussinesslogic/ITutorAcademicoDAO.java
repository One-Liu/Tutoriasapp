package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.List;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public interface ITutorAcademicoDAO {
    public List<TutorAcademico> findTutoresAcademicosByName(String searchName);
    public TutorAcademico findTutorAcademicoById(int idTutorAcademico);
    public TutorAcademico getTutorAcademico(ResultSet resultSet);
    public boolean addTutorAcademico(Persona tutorAcademico);
    public boolean deleteTutorAcademicoById(int idTutorAcademico);
}
