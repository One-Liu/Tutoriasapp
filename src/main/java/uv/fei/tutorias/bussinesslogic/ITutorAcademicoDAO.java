package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import uv.fei.tutorias.domain.TutorAcademico;
import java.util.List;

// author @liu
public interface ITutorAcademicoDAO {
    public List<TutorAcademico> obtenerTutoresAcademicos() throws SQLException;
    public TutorAcademico obtenerTutorAcademicoPorId(int idTutorAcademico) throws SQLException;
    public boolean agregarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException;
    TutorAcademico buscarTutorAcademicoPorElIdDeUsuario(int idUsuario) throws SQLException;
    public List<TutorAcademico> obtenerTutoresAcademicosDistintosA(int idTutorAcademico) throws SQLException;
}
