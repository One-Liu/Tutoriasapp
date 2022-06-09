package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public interface ISesionDeTutoriaAcademicaDAO {
    public ObservableList<SesionDeTutoriaAcademica> obtenerSesionesDeTutoriaAcademica() throws SQLException;
    public SesionDeTutoriaAcademica obtenerSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException;
    public boolean agregarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException;
    public boolean eliminarSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException;
    public boolean modificarFechaDeSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException;
    public ObservableList<SesionDeTutoriaAcademica> obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(int idPeriodoEscolar) throws SQLException;
}
