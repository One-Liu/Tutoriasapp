package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {

    private final Logger LOGGER = Logger.getLogger(SesionDeTutoriaAcademicaDAO.class);

    @Override
    public ObservableList<SesionDeTutoriaAcademica> obtenerSesionesDeTutoriaAcademica() throws SQLException {
        ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
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
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
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
        Date fecha;
        boolean ocurrio;
        int idPeriodoEscolar;
        
        idSesionDeTutoriaAcademica = resultado.getInt("id");
        fecha = resultado.getDate("fecha");
        ocurrio = resultado.getBoolean("ocurrio");
        idPeriodoEscolar = resultado.getInt("idPeriodoEscolar");
        
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,(java.util.Date) fecha, ocurrio,idPeriodoEscolar);
        return sesionDeTutoriaAcademica;
    }

    @Override
    public boolean agregarSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO sesion_de_tutoria_academica (fecha,idPeriodoEscolar) VALUES (?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) sesionDeTutoriaAcademica.getFecha());
            sentencia.setInt(2, sesionDeTutoriaAcademica.getIdPeriodoEscolar());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
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
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarFechaDeSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) throws SQLException {
        boolean validacion = false;
        String consulta = 
                "UPDATE sesion_de_tutoria_academica " + 
                "SET fecha = ?, " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) sesionDeTutoriaAcademica.getFecha());
            sentencia.setInt(2, sesionDeTutoriaAcademica.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public ObservableList<SesionDeTutoriaAcademica> obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(int idPeriodoEscolar) throws SQLException {
        ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM sesion_de_tutoria_academica WHERE idPeriodoEscolar = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPeriodoEscolar);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(SesionDeTutoriaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
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
}
