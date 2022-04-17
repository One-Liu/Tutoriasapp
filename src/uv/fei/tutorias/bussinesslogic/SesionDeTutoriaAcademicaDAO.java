package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {
    @Override
    public List<SesionDeTutoriaAcademica> findSesionesDeTutoriaAcademicaByFecha(String searchDate) {
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT fecha, idPeriodoEscolar FROM SesionDeTutoriaAcademica WHERE fecha LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchDate + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("SesionDeTutoriaAcademica not found");
            } else {
                do {
                    sesionesDeTutoriaAcademica.add(getSesionDeTutoriaAcademica(resultSet));
                } while(resultSet.next());
            }
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return sesionesDeTutoriaAcademica;
        }
    }
    
    @Override
    public SesionDeTutoriaAcademica findSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT fecha, idPeriodoEscolar FROM SesionDeTutoriaAcademica WHERE idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("SesionDeTutoriaAcademica not found");
            }
            sesionDeTutoriaAcademica = getSesionDeTutoriaAcademica(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return sesionDeTutoriaAcademica;
        }
    }

    @Override
    public boolean addSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO SesionDeTutoriaAcademica (fecha, idPeriodoEscolar) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sesionDeTutoriaAcademica.getFecha());
            statement.setInt(2, sesionDeTutoriaAcademica.getPeriodoEscolar().getIdPeriodoEscolar());
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: SesionDeTutoriaAcademica not added");
            } else {
                System.out.println("SesionDeTutoriaAcademica added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM SesionDeTutoriaAcademica WHERE idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: SesionDeTutoriaAcademica not deleted");
            } else {
                System.out.println("SesionDeTutoriaAcademica deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica(ResultSet resultSet) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        String fecha = "";
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        try {
            fecha = resultSet.getString("fecha");
            sesionDeTutoriaAcademica.setFecha(fecha);
            periodoEscolar = periodoEscolarDao.getPeriodoEscolar(resultSet);
            sesionDeTutoriaAcademica.setPeriodoEscolar(periodoEscolar);
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return sesionDeTutoriaAcademica;
        }
    }
}
