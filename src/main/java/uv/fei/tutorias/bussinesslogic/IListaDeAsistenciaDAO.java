package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import domain.ListaDeAsistencia;

// author @liu
public interface IListaDeAsistenciaDAO {
    public ListaDeAsistencia obtenerListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public ArrayList<ListaDeAsistencia> obtenerListasDeAsistenciaPorIdEstudiante(int idEstudiante) throws SQLException;
    public ArrayList<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException;
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
    public boolean eliminarListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException;
    public boolean modificarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException;
}