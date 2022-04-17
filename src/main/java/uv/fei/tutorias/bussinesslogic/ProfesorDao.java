package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;



public class ProfesorDao implements IProfesorDao{
    private final Logger log = Logger.getLogger(PersonaDAO.class);

    @Override
    public boolean addProfesor(Persona profesor) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            if (personaDao.addPerson(profesor)) {
                String query = "INSERT INTO profesor (Persona_idPersona) VALUES ( ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, personaDao.findIdPersona(profesor));
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return false;
    }

    @Override
    public Profesor findProfesorById(int idProfesor) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select PROFE.idProfesor, P.*  from profesor PROFE left join persona P on P.idPersona = PROFE.Persona_idPersona where idProfesor = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProfesor);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Profesor not found");
            }
            return getProfesor(resultSet);
        } catch(SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return null;
    }


    @Override
    public List<Profesor> findProfesoresByName(String searchName) {
        List<Profesor> profesors = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT p.*, profe.idProfesor From profesor profe Left Join persona p on p.idPersona = profe.Persona_idPersona Where nombre sounds like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, searchName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false){
                throw new SQLException("Profesores not found");
            } else {
                do {
                    profesors.add(getProfesor(resultSet));
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);
        }


        return profesors;
    }


    @Override
    public boolean deleteProfesorById(int idProfesor) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {

            String query = "DELETE FROM profesor WHERE (idProfesor = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProfesor);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ningun profesor");
            } else {
                System.out.println("Profesor eliminada satisfactoriamente");
            }
        } catch (SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return true;
    }


    private Profesor getProfesor(ResultSet resultSet) {
        Profesor profesor = new Profesor();
        String nombrePersona = "";
        String apellidoPaternoPersona = "";
        String apellidoMaternoPersona = "";
        String correoInstitucionalPersona = "";
        String correoPersonalPersona = "";
        try {
            nombrePersona = resultSet.getString("nombre");
            profesor.getPersona().setNombre(nombrePersona);
            apellidoMaternoPersona = resultSet.getString("apellidoMaterno");
            profesor.getPersona().setApellidoMaterno(apellidoMaternoPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.getPersona().setApellidoPaterno(apellidoPaternoPersona);
            correoInstitucionalPersona = resultSet.getString("correoInstitucional");
            profesor.getPersona().setCorreoInstitucional(correoInstitucionalPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.getPersona().setApellidoPaterno(apellidoPaternoPersona);
            correoPersonalPersona = resultSet.getString("correoPersonal");
            profesor.getPersona().setCorreoPersonal(correoPersonalPersona);

        } catch(SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return profesor;
    }

}
