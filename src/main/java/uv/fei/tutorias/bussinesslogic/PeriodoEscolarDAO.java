package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    private final Logger LOGGER = Logger.getLogger(PeriodoEscolarDAO.class);

    @Override
    public ArrayList<PeriodoEscolar> findPeriodosEscolaresByFechaInicio(String date) {
        ArrayList<PeriodoEscolar> periodosEscolares = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM periodo_escolar WHERE fechaInicio LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + date + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado periodos escolares con la fecha de inicio " + date);
            } else {
                do {
                    periodosEscolares.add(getPeriodoEscolar(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(PeriodoEscolarDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return periodosEscolares;
        }
    }

    @Override
    public PeriodoEscolar findPeriodoEscolarById(int idPeriodoEscolar) {
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM periodo_escolar WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el periodo escolar con el id " + idPeriodoEscolar);
            }
            periodoEscolar = getPeriodoEscolar(resultSet);
        } catch (SQLException ex) {
            LOGGER.error(PeriodoEscolarDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return periodoEscolar;
        }
    }

    @Override
    public PeriodoEscolar getPeriodoEscolar(ResultSet resultSet) {
        int idPeriodoEscolar = 0;
        String fechaInicioPeriodoEscolar = "";
        String fechaTerminoPeriodoEscolar = "";
        try {
            idPeriodoEscolar = resultSet.getInt("id");
            fechaInicioPeriodoEscolar = resultSet.getString("fechaInicio");
            fechaTerminoPeriodoEscolar = resultSet.getString("fechaTermino");
        } catch (SQLException ex) {
            LOGGER.error(PeriodoEscolarDAO.class.getName(),ex);
        }
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(idPeriodoEscolar,fechaInicioPeriodoEscolar,fechaTerminoPeriodoEscolar);
        return periodoEscolar;
    }

    @Override
    public boolean addPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO periodo_escolar (fechaInicio,fechaTermino) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, periodoEscolar.getFechaInicio());
            statement.setString(2, periodoEscolar.getFechaTermino());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El periodo escolar no se ha agregado");
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(PeriodoEscolarDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deletePeriodoEscolarById(int idPeriodoEscolar) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM periodo_escolar WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado el periodo escolar con el id " + idPeriodoEscolar);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(PeriodoEscolarDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}