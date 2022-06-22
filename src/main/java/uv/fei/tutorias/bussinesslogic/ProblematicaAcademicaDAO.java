package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public List<ProblematicaAcademica> obtenerProblematicaAcademicaPorDescripcion(String descripcionBusqueda) throws SQLException {
        List<ProblematicaAcademica> problematicasAcademicas = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematica_academica where descripcion like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + descripcionBusqueda + "%");
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    problematicasAcademicas.add(getProblematicaAcademica(resultSet));
                }
            }catch (SQLException ex){
            LOG.warn(getClass().getName(), new SQLException());
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return problematicasAcademicas;
    }

    @Override
    public List<ProblematicaAcademica> obtenerProblematicasAcademicas() throws SQLException {
        List<ProblematicaAcademica> experienciasEducativas = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematica_academica";
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
            String query = "SELECT * from problematica_academica where id like ?";
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
    //cuando agregamos una problematica academica por default su solucion sera 0
    @Override
    public int agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException {
        ConexionBD baseDeDatos = new ConexionBD();
        int id = 0;
        try (Connection conexion = baseDeDatos.abrirConexion()){
            String consulta = "INSERT INTO problematica_academica(titulo, descripcion, idExperienciaEducativa,idProfesor,idSesionDeTutoriaAcademica) VALUES (?,?,?,?,?)";
            PreparedStatement sentencia = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, problematicaAcademica.getTitulo());
            sentencia.setString(2, problematicaAcademica.getDescripcion());
            sentencia.setInt(3,problematicaAcademica.getIdExperienciaEducativa());
            sentencia.setInt(4,problematicaAcademica.getIdProfesor());
            sentencia.setInt(5,problematicaAcademica.getIdSesionDeTutoriaAcademica());
            int columnasAfectadas = sentencia.executeUpdate();
            if (columnasAfectadas != 0){
                ResultSet resultado = sentencia.getGeneratedKeys();
                resultado.next();
                id = resultado.getInt(1);
            }
        }catch (SQLException excepcionSQL){
            LOG.warn(getClass().getName(), new SQLException());
            throw excepcionSQL;
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return id;
    }

    @Override
    public boolean eliminarProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "DELETE FROM problematica_academica WHERE (id = ?)";
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

    @Override
    public List<ProblematicaAcademica> obtenerProblematicasAcademicasDeUnTutor(int idTutorAcademico)throws SQLException{
        List<ProblematicaAcademica> experienciasEducativas = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  pa.* from " +
                    "problematica_academica pa " +
                    "inner join estudiantes_problematicasacademicas epa ON epa.idProblematicaAcademica = pa.id " +
                    "inner join estudiante e ON e.id = epa.idEstudiante " +
                    "where  e.idTutorAcademico = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
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

    private ProblematicaAcademica getProblematicaAcademica(ResultSet resultSet) throws SQLException {
        ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica();
        int idProblematicaAcademica;
        String titulo;
        String descripcion;
        int idExperienciaEducativa;
        int idSolucionProblematicaAcademica;
        int idSesionDeTutoriaAcademica;
        int idProfesor;



        idProblematicaAcademica = resultSet.getInt("id");
        descripcion = resultSet.getString("descripcion");
        titulo = resultSet.getString("titulo");
        idExperienciaEducativa = resultSet.getInt("idExperienciaEducativa");
        idSolucionProblematicaAcademica = resultSet.getInt("idSolucionProblematicaAcademica");
        idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
        idProfesor = resultSet.getInt("idProfesor");

        problematicaAcademica.setIdProblematicaAcademica(idProblematicaAcademica);
        problematicaAcademica.setDescripcion(descripcion);
        problematicaAcademica.setIdExperienciaEducativa(idExperienciaEducativa);
        problematicaAcademica.setTitulo(titulo);
        problematicaAcademica.setIdSolucionProblematicaAcademica(idSolucionProblematicaAcademica);
        problematicaAcademica.setIdSesionDeTutoriaAcademica(idSesionDeTutoriaAcademica);
        problematicaAcademica.setIdProfesor(idProfesor);

        return problematicaAcademica;

    }
}
