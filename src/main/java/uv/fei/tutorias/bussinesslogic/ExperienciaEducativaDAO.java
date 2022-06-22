package uv.fei.tutorias.bussinesslogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO{
    private final Logger LOG = Logger.getLogger(ExperienciaEducativaDAO.class);



    @Override
    public ExperienciaEducativa obtenerExperienciaEducativaPorId(int searchId) throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        String query = "SELECT * FROM experiencia_educativa WHERE id like ?";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                experienciaEducativa = getExperienciaEducativa(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciaEducativa;

    }
    public ObservableList<ExperienciaEducativa> buscarExperienciaEducativasSinTutor() throws SQLException {
        ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "SELECT * From experiencia_educativa WHERE idProfesor = 0";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                experienciasEducativas.add(getExperienciaEducativa(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciasEducativas;

    }
    @Override
    public boolean agregarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        String query = "INSERT INTO experiencia_educativa(nombreEE, nrc, idProfesor) VALUES(?,?,?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, experienciaEducativa.getNombre());
            statement.setString(2,experienciaEducativa.getNrc());
            statement.setInt(3, experienciaEducativa.getIdProfesor());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0){
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean eliminarExperienciaEducativa(int searchId) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "DELETE FROM experiencia_educativa where (id = ?)";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0){
                bandera = true;
            }
        } catch (SQLException e) {
            LOG.warn(getClass().getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }
    @Override
    public boolean modificarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        String query = "UPDATE experiencia_educativa SET idProfesor = ? WHERE (id = ?) and (`idProfesor` = '0')";
        try (Connection connection = dataBaseConnection.abrirConexion();) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, experienciaEducativa.getIdProfesor());
            statement.setInt(2,experienciaEducativa.getIdExperienciaEducativa());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0){
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    private ExperienciaEducativa getExperienciaEducativa(ResultSet resultSet) throws SQLException {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        int idExperienciaEducativa = 0;
        String nombreEE = "";
        int profesorIdProfesor = 0;
        String nrc = "";
        idExperienciaEducativa = resultSet.getInt("id");
        nombreEE = resultSet.getString("nombreEE");
        profesorIdProfesor = resultSet.getInt("idProfesor");
        nrc = resultSet.getString("nrc");

        experienciaEducativa.setIdExperienciaEducativa(idExperienciaEducativa);
        experienciaEducativa.setNombre(nombreEE);
        experienciaEducativa.setIdProfesor(profesorIdProfesor);
        experienciaEducativa.setNrc(nrc);


        return experienciaEducativa;

    }
    
    @Override
    public List<ExperienciaEducativa> obtenerExperienciasEducativas() throws SQLException {
        List<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "SELECT * From experiencia_educativa";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                LOG.warn(ExperienciaEducativaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            }else {
                do {
                    experienciasEducativas.add(getExperienciaEducativa(resultSet));
                }while (resultSet.next());
            }

        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciasEducativas;
    }
}
