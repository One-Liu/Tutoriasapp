package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import dataaccess.ConexionBD;
import domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    private final Logger LOGGER = Logger.getLogger(PeriodoEscolarDAO.class);
    
    @Override
    public ArrayList<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException {
        ArrayList<PeriodoEscolar> periodosEscolares = new ArrayList<>();
        String consulta = "SELECT * FROM periodo_escolar";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado periodos escolares");
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
                throw new SQLException("No se ha encontrado el periodo escolar con el id " + idPeriodoEscolar);
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
        String fechaInicio;
        String fechaTermino;

        idPeriodoEscolar = resultado.getInt("id");
        fechaInicio = resultado.getString("fechaInicio");
        fechaTermino = resultado.getString("fechaTermino");
        
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(idPeriodoEscolar,fechaInicio,fechaTermino);
        return periodoEscolar;
    }

    @Override
    public boolean agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO periodo_escolar (fechaInicio,fechaTermino) VALUES (?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, periodoEscolar.getFechaInicio());
            sentencia.setString(2, periodoEscolar.getFechaTermino());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: El periodo escolar no se ha agregado");
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
                throw new SQLException("ERROR: No se ha eliminado el periodo escolar con el id " + idPeriodoEscolar);
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
            sentencia.setString(1, periodoEscolar.getFechaInicio());
            sentencia.setString(2, periodoEscolar.getFechaTermino());
            sentencia.setInt(3, periodoEscolar.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado el periodo escolar con el id " + periodoEscolar.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}