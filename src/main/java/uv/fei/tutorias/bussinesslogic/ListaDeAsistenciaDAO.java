package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ListaDeAsistencia;
//TODO agregar asistio
// author @liu
public class ListaDeAsistenciaDAO implements IListaDeAsistenciaDAO {

    private final Logger LOGGER = Logger.getLogger(ListaDeAsistenciaDAO.class);

    @Override
    public ListaDeAsistencia obtenerListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException {
        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia();
        String consulta = "SELECT * FROM lista_de_asistencia WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idListaDeAsistencia);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se ha encontrado la lista de asistencia con el id " + listaDeAsistencia);
            } else {
                listaDeAsistencia = getListaDeAsistencia(resultado);
            }
        } catch (SQLException ex) {
            LOGGER.warn(ListaDeAsistenciaDAO.class.getName(), ex);
            throw new SQLException("No hay conexion a la base de datos");
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return listaDeAsistencia;
    }

    @Override
    public ArrayList<ListaDeAsistencia> obtenerListasDeAsistenciaPorIdEstudiante(int idEstudiante) throws SQLException {
        ArrayList<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        String consulta = "SELECT * FROM lista_de_asistencia WHERE idEstudiante = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado listas de asistencia con el idEstudiante " + idEstudiante);
            } else {
                do {
                    listasDeAsistencia.add(getListaDeAsistencia(resultado));
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(ListaDeAsistenciaDAO.class.getName(), ex);
            throw new SQLException("No hay conexion a la base de datos");
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return listasDeAsistencia;
    }

    @Override
    public ArrayList<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException {
        ArrayList<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        String consulta = "SELECT * FROM lista_de_asistencia";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado listas de asistencia");
            } else {
                do {
                    listasDeAsistencia.add(getListaDeAsistencia(resultado));
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(ListaDeAsistenciaDAO.class.getName(), ex);
            throw new SQLException("No hay conexion a la base de datos");
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return listasDeAsistencia;
    }

    @Override
    public ObservableList<ListaDeAsistencia> buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(int idSesionDeTutoriaAcademica) throws SQLException {
        return null;
    }

    private ListaDeAsistencia getListaDeAsistencia(ResultSet resultado) throws SQLException {
            int idListaDeAsistencia;
            String hora;
            int idEstudiante;
            int idSesionDeTutoriaAcademica;

            idListaDeAsistencia = resultado.getInt("id");
            hora = resultado.getString("hora");
            idEstudiante = resultado.getInt("idEstudiante");
            idSesionDeTutoriaAcademica = resultado.getInt("idSesionDeTutoriaAcademica");

            ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia(idListaDeAsistencia,hora,idSesionDeTutoriaAcademica,idEstudiante);
            return listaDeAsistencia;
        }

    @Override
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO lista_de_asistencia (hora,idEstudiante,idSesionDeTutoriaAcademica) VALUES (?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,listaDeAsistencia.getHora());
            sentencia.setInt(2, listaDeAsistencia.getIdEstudiante());
            sentencia.setInt(3, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: La lista de asistencia no se ha agregado");
            } else {
                validacion = true;
            }
        }catch (SQLException ex) {
            LOGGER.warn(ListaDeAsistenciaDAO.class.getName(), ex);
            throw new SQLException("No hay conexion a la base de datos");
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM lista_de_asistencia WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idListaDeAsistencia);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha eliminado la lista de asistencia con el id " + idListaDeAsistencia);
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean modificarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException {
        boolean validacion = false;
        String consulta =
                "UPDATE lista_de_asistencia " +
                "SET hora = ?, " +
                "SET idEstudiante = ?, " +
                "SET idSesionDeTutoriaAcademica = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, listaDeAsistencia.getHora());
            sentencia.setInt(2, listaDeAsistencia.getIdEstudiante());
            sentencia.setInt(3, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            sentencia.setInt(4, listaDeAsistencia.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado la lista de asistencia con el id " + listaDeAsistencia.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}
