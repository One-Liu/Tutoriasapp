package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.sql.SQLException;
import domain.SesionDeTutoriaAcademica;

// author @liu
public interface ISesionDeTutoriaAcademicaDAO {
    public ArrayList<SesionDeTutoriaAcademica> obtenerSesionesDeTutoriaAcademica() throws SQLException;
    public SesionDeTutoriaAcademica obtenerSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException;
    public boolean agregarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException;
    public boolean eliminarSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException;
    public boolean modificarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException;
}
