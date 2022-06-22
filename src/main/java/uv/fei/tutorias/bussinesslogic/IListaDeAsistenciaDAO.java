package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public interface IListaDeAsistenciaDAO {
    public List<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException;
    boolean modificarAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean validarRegistroDeListasDeAsistencia(int idTutorAcademico, int idSesionDeTutoriaAcademica) throws SQLException;
    public List<ListaDeAsistencia> obtenerListaDeAsistenciasPorTutorYSesionDeTutoria(int idTutorAcademico, int idSesionDeTutoriaAcademica) throws SQLException;
}