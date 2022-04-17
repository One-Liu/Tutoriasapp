package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.FechaDeCierreEntregaReporte;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

// author @liu
public class FechaDeCierreEntregaReporteDAO implements IFechaDeCierreEntregaReporteDAO {
    @Override
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteByFecha(String searchDate) {
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, searchDate);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporte = getFechaDeCierreEntregaReporte(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechaDeCierreEntregaReporte;
        }
    }

    @Override
    public FechaDeCierreEntregaReporte findFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte) {
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporte = getFechaDeCierreEntregaReporte(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return fechaDeCierreEntregaReporte;
        }
    }

    @Override
    public boolean addFechaDeCierreEntregaReporte(FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fechaDeCierreEntregaReporte.getFecha());
            statement.setInt(2, fechaDeCierreEntregaReporte.getReporteDeTutoriaAcademica().getIdReporteDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not added");
            } else {
                System.out.println("FechaDeCierreEntregaReporte added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteFechaDeCierreEntregaReporteById(int idFechaDeCierreEntregaReporte) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: FechaDeCierreEntregaReporte not deleted");
            } else {
                System.out.println("FechaDeCierreEntregaReporte deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public FechaDeCierreEntregaReporte getFechaDeCierreEntregaReporte(ResultSet resultSet) {
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte();
        String fecha = "";
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDao = new ReporteDeTutoriaAcademicaDAO();
        try {
            fecha = resultSet.getString("fecha");
            fechaDeCierreEntregaReporte.setFecha(fecha);
            reporteDeTutoriaAcademica = reporteDeTutoriaAcademicaDao.getReporteDeTutoriaAcademica(resultSet);
            fechaDeCierreEntregaReporte.setReporteDeTutoriaAcademica(reporteDeTutoriaAcademica);
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return fechaDeCierreEntregaReporte;
        }
    }
}
