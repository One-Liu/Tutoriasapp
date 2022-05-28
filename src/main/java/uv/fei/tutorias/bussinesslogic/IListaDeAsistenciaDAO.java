package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public interface IListaDeAsistenciaDAO {
    public ListaDeAsistencia obtenerListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public ListaDeAsistencia obtenerListasDeAsistenciaPorIdEstudiante(int idEstudiante) throws SQLException;
    public ArrayList<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException;
    public ObservableList<ListaDeAsistencia> buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(int idSesionDeTutoriaAcademica)throws SQLException;
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean eliminarListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public boolean modificarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
}