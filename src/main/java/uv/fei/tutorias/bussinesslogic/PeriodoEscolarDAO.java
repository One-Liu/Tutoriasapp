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
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    private final Logger LOGGER = Logger.getLogger(PeriodoEscolarDAO.class);
    
    @Override
    public ObservableList<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException {
        ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM periodo_escolar";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(PeriodoEscolarDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                do {
                    periodosEscolares.add(getPeriodoEscolar(resultado));
                } while(resultado.next());
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return periodosEscolares;
    }

    @Override
    public PeriodoEscolar obtenerPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException {
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        String consulta = "SELECT * FROM periodo_escolar WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPeriodoEscolar);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(PeriodoEscolarDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                periodoEscolar = getPeriodoEscolar(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return periodoEscolar;
    }

    private PeriodoEscolar getPeriodoEscolar(ResultSet resultado) throws SQLException {
        int idPeriodoEscolar;
        Date fechaInicio;
        Date fechaTermino;

        idPeriodoEscolar = resultado.getInt("id");
        fechaInicio = resultado.getDate("fechaInicio");
        fechaTermino = resultado.getDate("fechaTermino");
        
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(idPeriodoEscolar,(java.util.Date) fechaInicio,(java.util.Date) fechaTermino);
        return periodoEscolar;
    }

    @Override
    public boolean agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO periodo_escolar VALUES(NULL,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) periodoEscolar.getFechaInicio());
            sentencia.setDate(2, (Date) periodoEscolar.getFechaTermino());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(PeriodoEscolarDAO.class.getName(), new SQLException());
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
    public boolean eliminarPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM periodo_escolar WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPeriodoEscolar);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(PeriodoEscolarDAO.class.getName(), new SQLException());
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
    public boolean modificarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException {
        boolean validacion = false;
        String consulta = 
                "UPDATE periodo_escolar " + 
                "SET fechaInicio = ?, " +
                "SET fechaTermino = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) periodoEscolar.getFechaInicio());
            sentencia.setDate(2, (Date) periodoEscolar.getFechaTermino());
            sentencia.setInt(3, periodoEscolar.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(PeriodoEscolarDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}