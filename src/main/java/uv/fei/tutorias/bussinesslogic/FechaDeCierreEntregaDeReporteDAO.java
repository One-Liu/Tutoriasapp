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
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public class FechaDeCierreEntregaDeReporteDAO implements IFechaDeCierreEntregaDeReporteDAO {

    private final Logger LOGGER = Logger.getLogger(FechaDeCierreEntregaDeReporteDAO.class);

    @Override
    public List<FechaDeCierreEntregaDeReporte> obtenerFechasDeCierreEntregaDeReporte() throws SQLException {
        List<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporte = new ArrayList<>();
        String consulta = "SELECT * FROM fecha_cierre_entrega_reporte";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                fechasDeCierreEntregaReporte.add(getFechaDeCierreEntregaDeReporte(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return fechasDeCierreEntregaReporte;
    }

    @Override
    public FechaDeCierreEntregaDeReporte obtenerFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaReporte) throws SQLException {
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte();
        String consulta = "SELECT * FROM fecha_cierre_entrega_reporte WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                fechaDeCierreEntregaReporte = getFechaDeCierreEntregaDeReporte(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return fechaDeCierreEntregaReporte;
    }

    private FechaDeCierreEntregaDeReporte getFechaDeCierreEntregaDeReporte(ResultSet resultado) throws SQLException {
        int idFechaDeCierreEntregaReporte;
        Date fechaDeCierre;
        
        idFechaDeCierreEntregaReporte = resultado.getInt("id");
        fechaDeCierre = resultado.getDate("fecha");
        
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte, (java.util.Date) fechaDeCierre);
        return fechaDeCierreEntregaReporte;
    }

    @Override
    public boolean agregarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException {
        boolean resultado = false;
        String consulta = "INSERT INTO fecha_cierre_entrega_reporte (fecha) VALUES (?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) fechaDeCierreEntregaDeReporte.getFecha());
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
    public boolean eliminarFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaDeReporte) throws SQLException {
        boolean resultado = false;
        String consulta = "DELETE FROM fecha_cierre_entrega_reporte WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idFechaDeCierreEntregaDeReporte);
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
    public boolean modificarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException {
        boolean resultado = false;
        String consulta = 
                "UPDATE fecha_cierre_entrega_reporte " + 
                "SET fecha = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, (Date) fechaDeCierreEntregaDeReporte.getFecha());
            sentencia.setInt(2, fechaDeCierreEntregaDeReporte.getId());
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
}