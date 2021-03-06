package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

import java.sql.*;

public class SolucionAProblematicaAcademicaDAO implements ISolucionAProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(SolucionAProblematicaAcademicaDAO.class);

    @Override
    public boolean addSolucionProblematicaAcademica(SolucionAProblematicaAcademica solucionAProblematicaAcademica) {
        boolean bandera = false;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO  solucionaproblematicaacademica (idsolucionaproblematicaacademica,descripcion) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,solucionAProblematicaAcademica.getIdSolucionAProblematicaAcademica());
            statement.setString(2,solucionAProblematicaAcademica.getDescripcion());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error la problematica academica no se ha agregado");
            }
            bandera =true;
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
            bandera =false;
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return bandera;
    }

    @Override
    public boolean deleteSolucionAProblematicaAcademicaById(int idProblematicaAcademica) {
        boolean bandera = false;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "Delete from solucionaproblematicaacademica where (idsolucionaproblematicaacademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProblematicaAcademica);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error no se ha eliminado ninguna solucion a la problematica academica");
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        bandera = true;
        return bandera;
    }

    @Override
    public SolucionAProblematicaAcademica findSolucionAProblematicaAcademicaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        SolucionAProblematicaAcademica solucionAProblematicaAcademica = new SolucionAProblematicaAcademica();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "Select * from solucionaproblematicaacademica where idsolucionaproblematicaacademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false){
                throw new SQLException("Solucion a problematica academica no encontrada");
            }else {
                int idSolucProblematicaAcademica = 0;
                String descripcion = "";
                do {
                    idSolucProblematicaAcademica = resultSet.getInt("idsolucionaproblematicaacademica");
                    descripcion = resultSet.getString("descripcion");

                    solucionAProblematicaAcademica.setIdSolucionAProblematicaAcademica(idSolucProblematicaAcademica);
                    solucionAProblematicaAcademica.setDescripcion(descripcion);
                }while (resultSet.next());

            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return solucionAProblematicaAcademica;
    }
}
