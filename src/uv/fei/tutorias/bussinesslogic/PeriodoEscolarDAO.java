package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.PeriodoEscolar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    @Override
    public List<PeriodoEscolar> findPeriodosEscolaresByFechaInicio(String date) {
        List<PeriodoEscolar> periodosEscolares = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT fechaInicio, fechaTermino FROM PeriodoEscolar WHERE FechaInicio LIKE ?";
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
            String query = "SELECT fechaInicio, fechaTermino FROM PeriodoEscolar WHERE idPeriodoEscolar = ?";
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
    public boolean addPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO PeriodoEscolar (fechaInicio,fechaTermino) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, periodoEscolar.getFechaInicio());
            statement.setString(2, periodoEscolar.getFechaTermino());
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows != 0) {
                System.out.println("Periodo Escolar added");
                return true;
            } else {
                throw new SQLException("ERROR: Periodo Escolar not added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deletePeriodoEscolarById(int idPeriodoEscolar) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM PeriodoEscolar WHERE idPeriodoEscolar = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows != 0) {
                System.out.println("Periodo Escolar deleted");
                return true;
            } else {
                throw new SQLException("ERROR: Periodo Escolar not deleted");
            }
        } catch(SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public PeriodoEscolar getPeriodoEscolar(ResultSet resultSet) {
        String fechaInicio = "";
        String fechaTermino = "";
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        try {
            fechaInicio = resultSet.getString("fechaInicio");
            periodoEscolar.setFechaInicio(fechaInicio);
            fechaTermino = resultSet.getString("fechaTermino");
            periodoEscolar.setFechaTermino(fechaTermino);
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return periodoEscolar;
        }
    }
}
