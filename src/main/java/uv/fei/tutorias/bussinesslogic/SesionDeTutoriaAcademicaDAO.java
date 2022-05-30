package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {

    private final Logger LOGGER = Logger.getLogger(SesionDeTutoriaAcademicaDAO.class);

    @Override
    public ArrayList<SesionDeTutoriaAcademica> obtenerSesionesDeTutoriaAcademica() throws SQLException {
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado sesiones de tutoría académica");
            } else {
                do {
                    sesionesDeTutoriaAcademica.add(getSesionDeTutoriaAcademica(resultado));
                } while(resultado.next());
            }
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
            if(!resultado.next()) {
                throw new SQLException("No se ha encontrado la sesión de tutoría académica con el id " + idSesionDeTutoriaAcademica);
            } else {
                sesionDeTutoriaAcademica = getSesionDeTutoriaAcademica(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return sesionDeTutoriaAcademica;
    }

    private SesionDeTutoriaAcademica getSesionDeTutoriaAcademica(ResultSet resultado) throws SQLException {
        int idSesionDeTutoriaAcademica;
        String fecha;
        int idPeriodoEscolar;
        
        idSesionDeTutoriaAcademica = resultado.getInt("id");
        fecha = resultado.getString("fecha");
        idPeriodoEscolar = resultado.getInt("idPeriodoEscolar");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,fecha,idPeriodoEscolar);
        return sesionDeTutoriaAcademica;
    }

    @Override
    public boolean agregarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO sesion_de_tutoria_academica (fecha,idPeriodoEscolar) VALUES (?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, sesionDeTutoriaAcademica.getFecha());
            sentencia.setInt(2, sesionDeTutoriaAcademica.getIdPeriodoEscolar());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: La sesión de tutoría académica no se ha agregado");
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarSesionDeTutoriaAcademicaPorId(int idSesionDeTutoriaAcademica) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM sesion_de_tutoria_academica WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idSesionDeTutoriaAcademica);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha eliminado la sesión de tutoría académica con el id " + idSesionDeTutoriaAcademica);
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean validacion = false;
        String consulta = 
                "UPDATE sesion_de_tutoria_academica " + 
                "SET fecha = ?, " +
                "SET hora = ?, " +
                "SET idPeriodoEscolar = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, sesionDeTutoriaAcademica.getFecha());
            sentencia.setString(2, sesionDeTutoriaAcademica.getHora());
            sentencia.setInt(3, sesionDeTutoriaAcademica.getIdPeriodoEscolar());
            sentencia.setInt(4, sesionDeTutoriaAcademica.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado la sesion de tutoria academica con el id " + sesionDeTutoriaAcademica.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}
