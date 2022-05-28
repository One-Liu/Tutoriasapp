package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import domain.TutorAcademico;
import java.util.List;

// author @liu
public interface ITutorAcademicoDAO {
    public List<TutorAcademico> obtenerTutoresAcademicos() throws SQLException;
    public TutorAcademico obtenerTutorAcademicoPorId(int idTutorAcademico) throws SQLException;
    public boolean agregarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException;
    public boolean eliminarTutorAcademicoPorId(int idTutorAcademico) throws SQLException;
    public boolean modificarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException;
}
