package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public ObservableList<ProblematicaAcademica> obtenerProblematicaAcademicaPorDescripcion(String descripcionBusqueda) throws SQLException {
        ObservableList<ProblematicaAcademica> problematicasAcademicas = FXCollections.observableArrayList();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematicaacademica where descripcion like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + descripcionBusqueda + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                LOG.warn(PersonaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }else {
                int idproblematicaAcademica = 0;
                String titulo = "";
                String descripcion = "";
                int idExperienciaEducativa = 0;
                int idSolucionProblematicaAcademica = 0;
                int idSesionDeTutoriaAcademica = 0;
                int idProfesor = 0;
                do {
                    idproblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    titulo = resultSet.getString("titulo");
                    descripcion = resultSet.getString("descripcion");
                    idExperienciaEducativa = resultSet.getInt("idExperienciaEducativa");
                    idSolucionProblematicaAcademica = resultSet.getInt("idSolucionProblematicaAcademica");
                    idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
                    idProfesor = resultSet.getInt("idProfesor");
                    
                    ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica(idproblematicaAcademica,titulo,descripcion,idSolucionProblematicaAcademica,idSesionDeTutoriaAcademica,idExperienciaEducativa,idProfesor);
                    problematicasAcademicas.add(problematicaAcademica);
                }while (resultSet.next());
            }

        } finally {
            dataBaseConnection.cerrarConexion();
        }

        return problematicasAcademicas;
    }

    @Override
    public ObservableList<ProblematicaAcademica> obtenerProblematicasAcademicas() throws SQLException {
        ObservableList<ProblematicaAcademica> experienciasEducativas = FXCollections.observableArrayList();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematicaacademica";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
               experienciasEducativas.add(getProblematicaAcademica(resultSet));
           }
        } catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return experienciasEducativas;
    }


    @Override
    public ProblematicaAcademica obtenerProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT * from problematicaacademica where idProblematicaAcademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + idProblematicaAcademicaBusqueda + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                problematicaAcademica = getProblematicaAcademica(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(getClass().getName(), e);
            throw e;
        }
        return problematicaAcademica;
    }

    @Override
    public boolean agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "INSERT INTO problematica_academica(titulo, descripcion, idExperienciaEducativa) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, problematicaAcademica.getTitulo());
            statement.setString(2, problematicaAcademica.getDescripcion());
            statement.setInt(3,problematicaAcademica.getIdExperienciaEducativa());
            int excecuteUpdate = statement.executeUpdate();
            if (excecuteUpdate != 0){
                bandera = true;
            }
        }catch (SQLException e){
            LOG.warn(getClass().getName(), new SQLException());
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean eliminarProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "DELETE FROM problematicaacademica WHERE (idProblematicaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProblematicaAcademicaBusqueda);
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

    private ProblematicaAcademica getProblematicaAcademica(ResultSet resultSet) throws SQLException {
        ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica();
        int idProblematicaAcademica = 0;
        String descripcion = "";
        int idExperienciaEducativa = 0;

        idProblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
        descripcion = resultSet.getString("descripcion");
        idExperienciaEducativa = resultSet.getInt("ExperienciaEducativa_idExperienciaEducativa");

        problematicaAcademica.setIdProblematicaAcademica(idProblematicaAcademica);
        problematicaAcademica.setDescripcion(descripcion);
        problematicaAcademica.setIdExperienciaEducativa(idExperienciaEducativa);
        return problematicaAcademica;

    }
}
