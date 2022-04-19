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

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    @Override
    public List<PeriodoEscolar> findPeriodosEscolaresByFechaInicio(String date) {
        List<PeriodoEscolar> periodosEscolares = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT PE.idPeriodoEscolar,PE.fechaInicio AS fechaInicioPeriodoEscolar, " + 
                "PE.fechaTermino AS fechaTerminoPeriodoEscolar FROM PeriodoEscolar PE WHERE fechaInicio LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + date + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Periodo Escolar not found");
            } else {
                do {
                    periodosEscolares.add(getPeriodoEscolar(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String query = "SELECT PE.idPeriodoEscolar,PE.fechaInicio AS fechaInicioPeriodoEscolar, " + 
                "PE.fechaTermino AS fechaTerminoPeriodoEscolar FROM PeriodoEscolar PE WHERE idPeriodoEscolar = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Periodo Escolar not found");
            }
            periodoEscolar = getPeriodoEscolar(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return periodoEscolar;
        }
    }
    
    @Override
    public PeriodoEscolar getPeriodoEscolar(ResultSet resultSet) {
        int idPeriodoEscolar = 0;
        String fechaInicio = "";
        String fechaTermino = "";
        try {
            idPeriodoEscolar = resultSet.getInt("idPeriodoEscolar");
            fechaInicio = resultSet.getString("fechaInicioPeriodoEscolar");
            fechaTermino = resultSet.getString("fechaTerminoPeriodoEscolar");
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(idPeriodoEscolar,fechaInicio,fechaTermino);
        return periodoEscolar;
    }

    @Override
    public boolean addPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO PeriodoEscolar (fechaInicio,fechaTermino) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, periodoEscolar.getFechaInicio());
            statement.setString(2, periodoEscolar.getFechaTermino());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Periodo Escolar not added");
            }
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String query = "DELETE FROM PeriodoEscolar WHERE idPeriodoEscolar = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Periodo Escolar not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
