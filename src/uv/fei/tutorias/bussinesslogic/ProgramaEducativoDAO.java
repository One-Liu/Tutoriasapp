package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ProgramaEducativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// author @liu
public class ProgramaEducativoDAO implements IProgramaEducativoDAO {

    @Override
    public List<ProgramaEducativo> findProgramasEducativosByName(String searchName) {
        List<ProgramaEducativo> programasEducativos = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT nombre AS nombreProgramaEducativo \n" +
                "FROM ProgramaEducativo\n" +
                "WHERE nombre LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Programa Educativo not found");
            } else {
                do {
                    programasEducativos.add(getProgramaEducativo(resultSet));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return programasEducativos;
        }
    }

    @Override
    public ProgramaEducativo findProgramaEducativoById(int idProgramaEducativo) {
        ProgramaEducativo programaEducativo = new ProgramaEducativo();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT nombre AS nombreProgramaEducativo \n" +
                "FROM ProgramaEducativo\n" +
                "WHERE idProgramaEducativo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Programa Educativo not found");
            }
            programaEducativo = getProgramaEducativo(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return programaEducativo;
        }
    }

    @Override
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO ProgramaEducativo (nombre) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, programaEducativo.getNombre());
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows != 0) {
                System.out.println("Programa Educativo added");
                return true;
            } else {
                throw new SQLException("ERROR: Programa Educativo not added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteProgramaEducativoById(int idProgramaEducativo) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM ProgramaEducativo WHERE idProgramaEducativo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows != 0) {
                System.out.println("Programa Educativo deleted");
                return true;
            } else {
                throw new SQLException("ERROR: Programa Educativo not deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ProgramaEducativo getProgramaEducativo(ResultSet resultSet) {
        String nombre = "";
        ProgramaEducativo programaEducativo = new ProgramaEducativo();
        try {
            nombre = resultSet.getString("nombreProgramaEducativo");
            programaEducativo.setNombre(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return programaEducativo;
        }
    }
}
