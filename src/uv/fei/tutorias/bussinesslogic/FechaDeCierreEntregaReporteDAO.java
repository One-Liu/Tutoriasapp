package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.FechaDeCierreEntregaReporte;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

// author @liu
public class FechaDeCierreEntregaReporteDAO implements IFechaDeCierreEntregaReporteDAO {
    
    static final Logger LOGGER = Logger.getLogger(FechaDeCierreEntregaReporteDAO.class);
    
    @Override
    public List<FechaDeCierreEntregaReporte> findFechasDeCierreEntregaReporteByFecha(String searchDate) {
        List<FechaDeCierreEntregaReporte> fechasDeCierreEntregaReporte = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT FDCEDR.idFechaDeCierreEntregaDeReporte, FDCEDR.fecha AS fechaFechaDeCierreEntregaDeReporte, " +
                "FDCEDR.idReporteDeTutoriaAcademica " +
                "FROM FechaDeCierreEntregaDeReporte FDCEDR " +
                "WHERE FDCEDR.fecha LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchDate + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            } else {
                do {
                    fechasDeCierreEntregaReporte.add(getFechaDeCierreEntregaReporte(resultSet));
                } while (resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechasDeCierreEntregaReporte;
        }
    }

    @Override
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte) {
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT FDCEDR.idFechaDeCierreEntregaDeReporte, FDCEDR.fecha AS fechaFechaDeCierreEntregaDeReporte, " +
                "FDCEDR.idReporteDeTutoriaAcademica " +
                "FROM FechaDeCierreEntregaDeReporte FDCEDR " +
                "WHERE FDCEDR.idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporte = getFechaDeCierreEntregaReporte(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechaDeCierreEntregaReporte;
        }
    }

    @Override
    public FechaDeCierreEntregaReporte getFechaDeCierreEntregaReporte(ResultSet resultSet) {
        int idFechaDeCierreEntregaReporte = 0;
        String fecha = "";
        int idReporteDeTutoriaAcademica = 0;
        try {
            idFechaDeCierreEntregaReporte = resultSet.getInt("idFechaDeCierreEntregaDeReporte");
            fecha = resultSet.getString("fechaFechaDeCierreEntregaDeReporte");
            idReporteDeTutoriaAcademica = resultSet.getInt("idReporteDeTutoriaAcademica");
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaReporteDAO.class.getName(),ex);
        }
        //ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDao = new ReporteDeTutoriaAcademicaDAO();
        //ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = reporteDeTutoriaAcademicaDao.findReporteDeTutoriaAcademicaById(idReporteDeTutoriaAcademica);
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = null; //BORRAR ESTA LÍNEA DE CÓDIGO
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte(idFechaDeCierreEntregaReporte,fecha,reporteDeTutoriaAcademica);
        return fechaDeCierreEntregaReporte;
    }
    
    @Override
    public boolean addFechaDeCierreEntregaReporte(FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO FechaDeCierreEntregaDeReporte (fecha, idReporteDeTutoriaAcademica) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fechaDeCierreEntregaReporte.getFecha());
            statement.setInt(2, fechaDeCierreEntregaReporte.getReporteDeTutoriaAcademica().getIdReporteDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not added");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM FechaDeCierreEntregaDeReporte WHERE idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(FechaDeCierreEntregaReporteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
