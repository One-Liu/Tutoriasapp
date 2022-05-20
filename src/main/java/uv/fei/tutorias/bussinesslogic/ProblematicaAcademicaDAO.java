package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public List<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName) {
        ArrayList<ProblematicaAcademica> problematicasAcademicas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT  * from problematicaacademica where descripcion like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + serchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("Problematica academica not found");
            }else {
                int idproblematicaAcademica = 0;
                String descripcion = "";
                int experienciaEducativaIdExperienciaEducativa = 0;
                do {
                    idproblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    descripcion = resultSet.getString("descripcion");
                    experienciaEducativaIdExperienciaEducativa = resultSet.getInt("ExperienciaEducativa_idExperienciaEducativa");

                    ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica(idproblematicaAcademica,descripcion,experienciaEducativaIdExperienciaEducativa);
                    problematicasAcademicas.add(problematicaAcademica);
                }while (resultSet.next());
            }

        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return problematicasAcademicas;
    }

    @Override
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();

        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * from problematicaacademica where idProblematicaAcademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("Problematica academica not found");
            }else {
                int idProblematicaAcademica = 0;
                String descripcion = "";
                int idExperienciaEducativa = 0;
                do {
                    idProblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    descripcion = resultSet.getString("descripcion");
                    idExperienciaEducativa = resultSet.getInt("ExperienciaEducativa_idExperienciaEducativa");

                    problematicaAcademica.setIdProblematicaAcademica(idProblematicaAcademica);
                    problematicaAcademica.setDescripcion(descripcion);
                    problematicaAcademica.setIdExperienciaEducativa(idExperienciaEducativa);
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }
        return problematicaAcademica;
    }

    @Override
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "INSERT INTO problematicaa_cademica(descripcion, ExperienciaEducativa_idExperienciaEducativa ) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, problematicaAcademica.getDescripcion());
            statement.setInt(2,problematicaAcademica.getIdExperienciaEducativa());
            int excecuteUpadate = statement.executeUpdate();
            if (excecuteUpadate == 0){
                throw new SQLException("Error problematica academica no se ha agregado");
            }else{
                result = true;
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean deleteProblematicaAcademicaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "DELETE FROM problematicaacademica WHERE (idProblematicaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error la problematica academica no se ha eliminado");
            }else {
                LOG.info("Problematica academica eliminada con el id " + searchId);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return true;
    }
}
