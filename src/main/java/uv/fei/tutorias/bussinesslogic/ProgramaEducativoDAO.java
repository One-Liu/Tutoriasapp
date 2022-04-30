package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAO implements IProgramaEducativoDAO {
    
    private final Logger LOGGER = Logger.getLogger(ProgramaEducativoDAO.class);

    @Override
    public ArrayList<ProgramaEducativo> findProgramasEducativosByName(String searchName) {
        ArrayList<ProgramaEducativo> programasEducativos = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM ProgramaEducativo WHERE nombre LIKE ?";
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
            LOGGER.error(ProgramaEducativoDAO.class.getName(),ex);
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
            String query = "SELECT * FROM ProgramaEducativo WHERE idProgramaEducativo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Programa Educativo not found");
            }
            programaEducativo = getProgramaEducativo(resultSet);
        } catch (SQLException ex) {
            LOGGER.error(ProgramaEducativoDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return programaEducativo;
        }
    }

    @Override
    public ProgramaEducativo getProgramaEducativo(ResultSet resultSet) {
        int idProgramaEducativo = 0;
        String nombreProgramaEducativo = "";
        try {
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
            nombreProgramaEducativo = resultSet.getString("nombre");
        } catch (SQLException ex) {
            LOGGER.error(ProgramaEducativoDAO.class.getName(),ex);
        }
        ProgramaEducativo programaEducativo = new ProgramaEducativo(idProgramaEducativo,nombreProgramaEducativo);
        return programaEducativo;
    }
    
    @Override
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO ProgramaEducativo (nombre) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, programaEducativo.getNombre());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Programa Educativo not added");
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(ProgramaEducativoDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteProgramaEducativoById(int idProgramaEducativo) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM ProgramaEducativo WHERE idProgramaEducativo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            int affectedRows = statement.executeUpdate();
            if(affectedRows != 0) {
                throw new SQLException("ERROR: Programa Educativo not deleted");
            }
        } catch (SQLException ex) {
            LOGGER.error(ProgramaEducativoDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
