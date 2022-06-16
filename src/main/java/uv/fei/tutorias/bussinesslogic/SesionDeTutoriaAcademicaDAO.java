package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {

    private final Logger LOGGER = Logger.getLogger(SesionDeTutoriaAcademicaDAO.class);

    @Override
    public List<SesionDeTutoriaAcademica> obtenerSesionesDeTutoriaAcademica() throws SQLException {
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                sesionesDeTutoriaAcademica.add(getSesionDeTutoriaAcademica(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return sesionesDeTutoriaAcademica;
    }

    @Override
    public SesionDeTutoriaAcademica obtenerSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                sesionDeTutoriaAcademica = getSesionDeTutoriaAcademica(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return sesionDeTutoriaAcademica;
    }

    private SesionDeTutoriaAcademica getSesionDeTutoriaAcademica(ResultSet resultado) throws SQLException {
        int idSesionDeTutoriaAcademica;
        Date fecha;
        boolean ocurrio;
        int idPeriodoEscolar;
        int idFechaDeCierreEntregaDeReporte;
        
        idSesionDeTutoriaAcademica = resultado.getInt("id");
        fecha = resultado.getDate("fecha");
        ocurrio = resultado.getBoolean("ocurrio");
        idPeriodoEscolar = resultado.getInt("idPeriodoEscolar");
        idFechaDeCierreEntregaDeReporte = resultado.getInt("idFechaCierreEntregaReporte");
        
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,(java.util.Date) fecha, ocurrio,idPeriodoEscolar, idFechaDeCierreEntregaDeReporte);
        return sesionDeTutoriaAcademica;
    }

    @Override
    public boolean agregarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean resultado = false;
        String consulta = "INSERT INTO sesion_de_tutoria_academica (fecha,idPeriodoEscolar,idFechaDeCierreEntregaDeReporte) VALUES (?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) sesionDeTutoriaAcademica.getFecha());
            sentencia.setInt(2, sesionDeTutoriaAcademica.getIdPeriodoEscolar());
            sentencia.setInt(3, sesionDeTutoriaAcademica.getIdFechaDeCierreEntregaDeReporte());
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
    public boolean eliminarSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException {
        boolean resultado = false;
        String consulta = "DELETE FROM sesion_de_tutoria_academica WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idSesionDeTutoriaAcademica);
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
    public boolean modificarFechaDeSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean resultado = false;
        String consulta = 
                "UPDATE sesion_de_tutoria_academica " + 
                "SET fecha = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) sesionDeTutoriaAcademica.getFecha());
            sentencia.setInt(2, sesionDeTutoriaAcademica.getId());
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
    public List<SesionDeTutoriaAcademica> obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(int idPeriodoEscolar) throws SQLException {
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica WHERE idPeriodoEscolar = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPeriodoEscolar);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                sesionesDeTutoriaAcademica.add(getSesionDeTutoriaAcademica(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return sesionesDeTutoriaAcademica;
    }
}
