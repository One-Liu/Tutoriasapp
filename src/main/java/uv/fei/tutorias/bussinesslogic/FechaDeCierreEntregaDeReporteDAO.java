package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public class FechaDeCierreEntregaDeReporteDAO implements IFechaDeCierreEntregaDeReporteDAO {

    private final Logger LOGGER = Logger.getLogger(FechaDeCierreEntregaDeReporteDAO.class);

    @Override
    public ArrayList<FechaDeCierreEntregaDeReporte> findFechasDeCierreEntregaDeReporteByFecha(String searchDate) {
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporte = new ArrayList<>();
        String query = "SELECT * FROM fecha_cierre_entrega_reporte WHERE fecha LIKE ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchDate + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado fechas de cierre de entrega de reporte con la fecha " + searchDate);
            } else {
                do {
                    fechasDeCierreEntregaReporte.add(getFechaDeCierreEntregaDeReporte(resultSet));
                } while (resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.warn(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return fechasDeCierreEntregaReporte;
    }

    @Override
    public FechaDeCierreEntregaDeReporte findFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaReporte) {
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte();
        String query = "SELECT * FROM fecha_cierre_entrega_reporte WHERE id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado la fecha de cierre de entrega de reporte con el id " + idFechaDeCierreEntregaReporte);
            }
            fechaDeCierreEntregaReporte = getFechaDeCierreEntregaDeReporte(resultSet);
        } catch(SQLException ex) {
            LOGGER.warn(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return fechaDeCierreEntregaReporte;
    }

    private FechaDeCierreEntregaDeReporte getFechaDeCierreEntregaDeReporte(ResultSet resultSet) {
        int idFechaDeCierreEntregaReporte = 0;
        String fechaDeCierre = "";
        try {
            idFechaDeCierreEntregaReporte = resultSet.getInt("id");
            fechaDeCierre = resultSet.getString("fecha");
        } catch(SQLException ex) {
            LOGGER.warn(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        }
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte,fechaDeCierre);
        return fechaDeCierreEntregaReporte;
    }

    @Override
    public boolean addFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) {
        boolean result = false;
        String query = "INSERT INTO fecha_cierre_entrega_reporte (fecha) VALUES (?)";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fechaDeCierreEntregaDeReporte.getFecha());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: La fecha de cierre de entrega de reporte no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean deleteFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaDeReporte) {
        boolean result = false;
        String query = "DELETE FROM fecha_cierre_entrega_reporte WHERE id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaDeReporte);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado la fecha de cierre de entrega de reporte con el id " + idFechaDeCierreEntregaDeReporte);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }
}