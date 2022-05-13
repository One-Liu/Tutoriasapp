package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public class ListaDeAsistenciaDAO implements IListaDeAsistenciaDAO {

    private final Logger LOGGER = Logger.getLogger(ListaDeAsistenciaDAO.class);

    @Override
    public ListaDeAsistencia findListaDeAsistenciaById(int idListaDeAsistencia) {
        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM lista_de_asistencia WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencia);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado la lista de asistencia con el id " + listaDeAsistencia);
            }
            listaDeAsistencia = getListaDeAsistencia(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return listaDeAsistencia;
        }
    }

    @Override
    public ArrayList<ListaDeAsistencia> findListasDeAsistenciaByIdEstudiante(int idEstudiante) {
        ArrayList<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM lista_de_asistencia WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado listas de asistencia con el idEstudiante " + idEstudiante);
            } else {
                do {
                    listasDeAsistencia.add(getListaDeAsistencia(resultSet));
                } while (resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return listasDeAsistencia;
        }
    }

    @Override
    public ArrayList<ListaDeAsistencia> findListasDeAsistenciaByIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        ArrayList<ListaDeAsistencia> listasDeAsistencia = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM lista_de_asistencia WHERE idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado listas de asistencia con el idSesionDeTutoriaAcademica " + idSesionDeTutoriaAcademica);
            } else {
                do {
                    listasDeAsistencia.add(getListaDeAsistencia(resultSet));
                } while (resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return listasDeAsistencia;
        }
    }

    @Override
    public ListaDeAsistencia getListaDeAsistencia(ResultSet resultSet) {
        int idListaDeAsistencia = 0;
        String hora = "";
        int idEstudiante = 0;
        int idSesionDeTutoriaAcademica = 0;
        try {
            idListaDeAsistencia = resultSet.getInt("id");
            hora = resultSet.getString("hora");
            idEstudiante = resultSet.getInt("idEstudiante");
            idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        }
        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia(idListaDeAsistencia,hora,idSesionDeTutoriaAcademica,idEstudiante);
        return listaDeAsistencia;
    }

    @Override
    public boolean addListaDeAsistencia(ListaDeAsistencia listaDeAsistencia) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO lista_de_asistencia (hora,idEstudiante,idSesionDeTutoriaAcademica) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,listaDeAsistencia.getHora());
            statement.setInt(2, listaDeAsistencia.getIdEstudiante());
            statement.setInt(3, listaDeAsistencia.getIdSesionDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: La lista de asistencia no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteListaDeAsistenciaById(int idListaDeAsistencia) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM lista_de_asistencia WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencia);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado la lista de asistencia con el id " + idListaDeAsistencia);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciaDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
