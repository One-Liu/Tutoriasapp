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
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM FechaDeCierreEntregaDeReporte WHERE fecha LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchDate + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            } else {
                do {
                    fechasDeCierreEntregaReporte.add(getFechaDeCierreEntregaDeReporte(resultSet));
                } while (resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechasDeCierreEntregaReporte;
        }
    }

    @Override
    public FechaDeCierreEntregaDeReporte findFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaReporte) {
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM FechaDeCierreEntregaDeReporte WHERE idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporte = getFechaDeCierreEntregaDeReporte(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechaDeCierreEntregaReporte;
        }
    }

    @Override
    public FechaDeCierreEntregaDeReporte getFechaDeCierreEntregaDeReporte(ResultSet resultSet) {
        int idFechaDeCierreEntregaReporte = 0;
        String fechaDeCierre = "";
        int idReporteDeTutoriaAcademica = 0;
        try {
            idFechaDeCierreEntregaReporte = resultSet.getInt("idFechaDeCierreEntregaDeReporte");
            fechaDeCierre = resultSet.getString("fecha");
            idReporteDeTutoriaAcademica = resultSet.getInt("idReporteDeTutoriaAcademica");
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        }
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte,fechaDeCierre,idReporteDeTutoriaAcademica);
        return fechaDeCierreEntregaReporte;
    }

    @Override
    public boolean addFechaDeCierreEntregaDeReporte(FechaDeCierreEntregaDeReporte fechaDeCierreEntregaDeReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO FechaDeCierreEntregaDeReporte (fecha, idReporteDeTutoriaAcademica) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fechaDeCierreEntregaDeReporte.getFecha());
            statement.setInt(2, fechaDeCierreEntregaDeReporte.getIdReporteDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not added");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteFechaDeCierreEntregaDeReporteById(int idFechaDeCierreEntregaDeReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM FechaDeCierreEntregaDeReporte WHERE idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaDeReporte);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaDeReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}