package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

import java.sql.*;

public class SolucionAProblematicaAcademicaDAO implements ISolucionAProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(SolucionAProblematicaAcademicaDAO.class);

    @Override
    public int agregarSolucionProblematicaAcademica(String solucionProblematicaAcademicaTexto) throws SQLException {
        int id;
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "INSERT INTO  solucion_a_problematica_academica (descripcion) VALUES (?) ";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,solucionProblematicaAcademicaTexto);
            int executeUpdate = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (executeUpdate == 0) {
                id = -1;
            }else {
                resultSet.next();
                id=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return id;
    }

    @Override
    public boolean eliminarSolucionAProblematicaAcademicaById(int idProblematicaAcademica) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "Delete from solucionaproblematicaacademica where (idsolucionaproblematicaacademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProblematicaAcademica);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0){
                bandera = true;
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public SolucionAProblematicaAcademica buscarSolucionAProblematicaAcademicaById(int searchId) {
        ConexionBD dataBaseConnection = new ConexionBD();
        SolucionAProblematicaAcademica solucionAProblematicaAcademica = new SolucionAProblematicaAcademica();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "Select * from solucionaproblematicaacademica where idsolucionaproblematicaacademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                solucionAProblematicaAcademica = getSolucionAProblematicaAcademica(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return solucionAProblematicaAcademica;
    }

    private SolucionAProblematicaAcademica getSolucionAProblematicaAcademica(ResultSet resultSet) throws SQLException {
        SolucionAProblematicaAcademica solucionAProblematicaAcademica = new SolucionAProblematicaAcademica();
        int idSolucProblematicaAcademica;
        String descripcion;
        idSolucProblematicaAcademica = resultSet.getInt("idsolucionaproblematicaacademica");
        descripcion = resultSet.getString("descripcion");
        solucionAProblematicaAcademica.setIdSolucionAProblematicaAcademica(idSolucProblematicaAcademica);
        solucionAProblematicaAcademica.setDescripcion(descripcion);
        return solucionAProblematicaAcademica;

    }
    
    @Override
    public boolean modificarSolucionAProblematicaAcademica(SolucionAProblematicaAcademica solucionAProblematicaAcademica) throws SQLException {
        boolean resultado = false;
        String consulta = "UPDATE solucion_a_problematica_academica " +
            "SET descripcion = ? " + 
            "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()){
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, solucionAProblematicaAcademica.getDescripcion());
            sentencia.setInt(2, solucionAProblematicaAcademica.getIdSolucionAProblematicaAcademica());
            int columnasAfectadas = sentencia.executeUpdate();
            if (columnasAfectadas != 0){
                resultado = true;
            }
        } catch (SQLException excepcionSQL) {
            LOG.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }
}
