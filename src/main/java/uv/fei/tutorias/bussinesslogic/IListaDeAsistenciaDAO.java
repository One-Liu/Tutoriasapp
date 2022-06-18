package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public interface IListaDeAsistenciaDAO {
    public ListaDeAsistencia obtenerListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public List<ListaDeAsistencia> obtenerListasDeAsistenciaPorIdEstudiante(int idEstudiante) throws SQLException;
    public List<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException;
    public List<ListaDeAsistencia> buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(int idSesionDeTutoriaAcademica)throws SQLException;
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean eliminarListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public boolean modificarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean validarRegistroDeListasDeAsistencia(int idTutorAcademico, int idSesionDeTutoriaAcademica) throws SQLException;
}