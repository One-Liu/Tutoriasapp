package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public class FechaDeCierreEntregaDeReporteDAO implements IFechaDeCierreEntregaDeReporteDAO {

    private final Logger LOGGER = Logger.getLogger(FechaDeCierreEntregaDeReporteDAO.class);

    @Override
    public ArrayList<FechaDeCierreEntregaDeReporte> obtenerFechasDeCierreEntregaDeReporte() throws SQLException {
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporte = new ArrayList<>();
        String consulta = "SELECT * FROM fecha_cierre_entrega_reporte";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado fechas de cierre de entrega de reporte");
            } else {
                do {
                    fechasDeCierreEntregaReporte.add(getFechaDeCierreEntregaDeReporte(resultado));
                } while(resultado.next());
            }
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
            if(!resultado.next()) {
                throw new SQLException("No se ha encontrado la fecha de cierre de entrega de reporte con el id " + idFechaDeCierreEntregaReporte);
            } else {
                fechaDeCierreEntregaReporte = getFechaDeCierreEntregaDeReporte(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return fechaDeCierreEntregaReporte;
    }

    private FechaDeCierreEntregaDeReporte getFechaDeCierreEntregaDeReporte(ResultSet resultado) throws SQLException {
        int idFechaDeCierreEntregaReporte;
        String fechaDeCierre;
        
        idFechaDeCierreEntregaReporte = resultado.getInt("id");
        fechaDeCierre = resultado.getString("fecha");
        
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte,fechaDeCierre);
        return fechaDeCierreEntregaReporte;
    }

    @Override
    public boolean agregarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO fecha_cierre_entrega_reporte (fecha) VALUES (?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, fechaDeCierreEntregaDeReporte.getFecha());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: La fecha de cierre de entrega de reporte no se ha agregado");
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarFechaDeCierreEntregaDeReportePorId(int idFechaDeCierreEntregaDeReporte) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM fecha_cierre_entrega_reporte WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idFechaDeCierreEntregaDeReporte);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha eliminado la fecha de cierre de entrega de reporte con el id " + idFechaDeCierreEntregaDeReporte);
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) throws SQLException {
        boolean validacion = false;
        String consulta = 
                "UPDATE fecha_cierre_entrega_reporte " + 
                "SET fecha = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, fechaDeCierreEntregaDeReporte.getFecha());
            sentencia.setInt(2, fechaDeCierreEntregaDeReporte.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado la fecha de cierre de entrega de reporte con el id " + fechaDeCierreEntregaDeReporte.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}