package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {
    
    static final Logger LOGGER = Logger.getLogger(SesionDeTutoriaAcademicaDAO.class);
    
    @Override
    public List<SesionDeTutoriaAcademica> findSesionesDeTutoriaAcademicaByFecha(String searchDate) {
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT SDTA.idSesionDeTutoriaAcademica, SDTA.fecha AS fechaSesionDeTutoriaAcademica, SDTA.idPeriodoEscolar " +
                "FROM SesionDeTutoriaAcademica SDTA " +
                "WHERE SDTA.fecha LIKE ?";
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
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
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
            String query = "SELECT SDTA.idSesionDeTutoriaAcademica, SDTA.fecha AS fechaSesionDeTutoriaAcademica, SDTA.idPeriodoEscolar " +
                "FROM SesionDeTutoriaAcademica SDTA " +
                "WHERE SDTA.idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("SesionDeTutoriaAcademica not found");
            }
            sesionDeTutoriaAcademica = getSesionDeTutoriaAcademica(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return sesionDeTutoriaAcademica;
        }
    }
    
    @Override
    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica(ResultSet resultSet) {
        int idSesionDeTutoriaAcademica = 0;
        String fecha = "";
        int idPeriodoEscolar = 0;
        try {
            idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
            fecha = resultSet.getString("fechaSesionDeTutoriaAcademica");
            idPeriodoEscolar = resultSet.getInt("idPeriodoEscolar");
        } catch(SQLException ex) {
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
        }
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolar = periodoEscolarDao.findPeriodoEscolarById(idPeriodoEscolar);
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,fecha,periodoEscolar);
        return sesionDeTutoriaAcademica;
    }

    @Override
    public boolean addSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO SesionDeTutoriaAcademica (fecha, idPeriodoEscolar) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sesionDeTutoriaAcademica.getFecha());
            statement.setInt(2, sesionDeTutoriaAcademica.getPeriodoEscolar().getIdPeriodoEscolar());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: SesionDeTutoriaAcademica not added");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM SesionDeTutoriaAcademica WHERE idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: SesionDeTutoriaAcademica not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
