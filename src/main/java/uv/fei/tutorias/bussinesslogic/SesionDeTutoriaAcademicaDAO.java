package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAO implements ISesionDeTutoriaAcademicaDAO {

    private final Logger LOGGER = Logger.getLogger(SesionDeTutoriaAcademicaDAO.class);

    @Override
    public ArrayList<SesionDeTutoriaAcademica> findSesionesDeTutoriaAcademicaByFecha(String searchDate) {
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM sesion_de_tutoria_academica WHERE fecha LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchDate + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("No se han encontrado sesiones de tutoría académica con la fecha " + searchDate);
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
            String query = "SELECT * FROM sesion_de_tutoria_academica WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado la sesión de tutoría académica con el id " + idSesionDeTutoriaAcademica);
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
        String fechaSesionDeTutoriaAcademica = "";
        int idPeriodoEscolar = 0;
        try {
            idSesionDeTutoriaAcademica = resultSet.getInt("id");
            fechaSesionDeTutoriaAcademica = resultSet.getString("fecha");
            idPeriodoEscolar = resultSet.getInt("idPeriodoEscolar");
        } catch(SQLException ex) {
            LOGGER.error(SesionDeTutoriaAcademicaDAO.class.getName(), ex);
        }
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,fechaSesionDeTutoriaAcademica,idPeriodoEscolar);
        return sesionDeTutoriaAcademica;
    }

    @Override
    public boolean addSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO sesion_de_tutoria_academica (fecha,idPeriodoEscolar) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sesionDeTutoriaAcademica.getFecha());
            statement.setInt(2, sesionDeTutoriaAcademica.getIdPeriodoEscolar());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: La sesión de tutoría académica no se ha agregado");
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
            String query = "DELETE FROM sesion_de_tutoria_academica WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminidao la sesión de tutoría académica con el id " + idSesionDeTutoriaAcademica);
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
