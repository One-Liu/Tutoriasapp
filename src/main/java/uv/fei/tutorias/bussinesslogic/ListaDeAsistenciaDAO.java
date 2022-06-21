package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ListaDeAsistencia;

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
            if(resultado.next()) {
                listaDeAsistencia = getListaDeAsistencia(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return listaDeAsistencia;
    }

    @Override
    public List<ListaDeAsistencia> obtenerListasDeAsistenciaPorIdEstudiante(int idEstudiante) throws SQLException {
        List<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        String consulta = "SELECT * FROM lista_de_asistencia WHERE idEstudiante = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                listasDeAsistencia.add(getListaDeAsistencia(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return listasDeAsistencia;
    }

    @Override
    public List<ListaDeAsistencia> obtenerListasDeAsistencia() throws SQLException {
        List<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        String consulta = "SELECT * FROM lista_de_asistencia";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                listasDeAsistencia.add(getListaDeAsistencia(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return listasDeAsistencia;
    }

    @Override
    public boolean modificarAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException {
        boolean resultado = false;
        String consulta = "UPDATE lista_de_asistencia t " +
                "SET t.asistio = ? " +
                "WHERE t.idEstudiante = ? " +
                "  AND t.idSesionDeTutoriaAcademica = ? ";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setBoolean(1, listaDeAsistencia.getAsistio());
            sentencia.setInt(2, listaDeAsistencia.getIdEstudiante());
            sentencia.setInt(3, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }

    private ListaDeAsistencia getListaDeAsistencia(ResultSet resultado) throws SQLException {
        int idListaDeAsistencia;
        String hora;
        boolean asistio;
        int idEstudiante;
        int idSesionDeTutoriaAcademica;

        idListaDeAsistencia = resultado.getInt("id");
        hora = resultado.getString("hora");
        asistio = resultado.getBoolean("asistio");
        idEstudiante = resultado.getInt("idEstudiante");
        idSesionDeTutoriaAcademica = resultado.getInt("idSesionDeTutoriaAcademica");

        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia(idListaDeAsistencia,hora,asistio,idSesionDeTutoriaAcademica,idEstudiante);
        return listaDeAsistencia;
    }

    @Override
    public boolean agregarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException {
        boolean resultado = false;
        String consulta = "INSERT INTO lista_de_asistencia (hora,asistio,idEstudiante,idSesionDeTutoriaAcademica) VALUES (?,?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,listaDeAsistencia.getHora());
            sentencia.setBoolean(2, listaDeAsistencia.getAsistio());
            sentencia.setInt(3, listaDeAsistencia.getIdEstudiante());
            sentencia.setInt(4, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean eliminarListaDeAsistenciaPorId(int idListaDeAsistencia) throws SQLException {
        boolean resultado = false;
        String consulta = "DELETE FROM lista_de_asistencia WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idListaDeAsistencia);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) throws SQLException {
        boolean resultado = false;
        String consulta =
                "UPDATE lista_de_asistencia " +
                "SET hora = ?, " +
                "SET asistio = ?, " +
                "SET idEstudiante = ?, " +
                "SET idSesionDeTutoriaAcademica = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, listaDeAsistencia.getHora());
            sentencia.setBoolean(2, listaDeAsistencia.getAsistio());
            sentencia.setInt(3, listaDeAsistencia.getIdEstudiante());
            sentencia.setInt(4, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            sentencia.setInt(5, listaDeAsistencia.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }
    
    @Override
    public boolean validarRegistroDeListasDeAsistencia(int idTutorAcademico, int idSesionDeTutoriaAcademica) throws SQLException {
        boolean listasDeAsistenciaRegistradas = false;
        String consulta = "SELECT LDA.id " +
            "FROM lista_de_asistencia LDA INNER JOIN estudiante E ON E.id = LDA.idEstudiante " +
            "WHERE E.idTutorAcademico = ? AND LDA.idSesionDeTutoriaAcademica = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            sentencia.setInt(2, idSesionDeTutoriaAcademica);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                listasDeAsistenciaRegistradas = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return listasDeAsistenciaRegistradas;
    }

    public ListaDeAsistencia buscarListasDeAsistenciasPorIdSesiondeTutoriaAcademica(int id) {
        ListaDeAsistencia ListaDeAsistencia = new ListaDeAsistencia();
        return ListaDeAsistencia;
    }
}
