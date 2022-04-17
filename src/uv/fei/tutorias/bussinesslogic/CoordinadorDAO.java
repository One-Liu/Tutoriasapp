package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class CoordinadorDAO implements ICoordinadorDAO {
    @Override
    public List<Coordinador> findCoordinadorByName(String searchName) {
        List<Coordinador> coordinadores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT  P.nombre AS nombreCoordinador, P.apellidoPaterno AS apellidoPaternoCoordinador,\n" +
                "P.apellidoMaterno AS apellidoMaternoCoordinador, P.correoInstitucional AS correoInstitucionalCoordinador,\n" +
                "P.correoPersonal AS correoPersonalCoordinador, PE.nombre AS nombreProgramaEducativo\n" +
                "FROM Coordinador C\n" +
                "LEFT JOIN Persona P ON P.idPersona = C.idPersona\n" +
                "LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = C.idProgramaEducativo\n" +
                "WHERE CONCAT(P.nombre,\" \", P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Coordinador not found");
            } else {
                do {
                    coordinadores.add(getCoordinador(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinadores;
        }
    }

    @Override
    public Coordinador findCoordinadorById(int idCoordinador) {
        Coordinador coordinador = new Coordinador();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT  P.nombre AS nombreCoordinador, P.apellidoPaterno AS apellidoPaternoCoordinador,\n" +
                "P.apellidoMaterno AS apellidoMaternoCoordinador, P.correoInstitucional AS correoInstitucionalCoordinador,\n" +
                "P.correoPersonal AS correoPersonalCoordinador, PE.nombre AS nombreProgramaEducativo\n" +
                "FROM Coordinador C\n" +
                "LEFT JOIN Persona P ON P.idPersona = C.idPersona\n" +
                "LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = C.idProgramaEducativo\n" +
                "WHERE idCoordinador = 1;SELECT  P.nombre AS nombreCoordinador, P.apellidoPaterno AS apellidoPaternoCoordinador,\n" +
                "P.apellidoMaterno AS apellidoMaternoCoordinador, P.correoInstitucional AS correoInstitucionalCoordinador,\n" +
                "P.correoPersonal AS correoPersonalCoordinador, PE.nombre AS nombreProgramaEducativo\n" +
                "FROM Coordinador C\n" +
                "LEFT JOIN Persona P ON P.idPersona = C.idPersona\n" +
                "LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = C.idProgramaEducativo\n" +
                "WHERE idCoordinador = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Coordinador not found");
            }
            coordinador = getCoordinador(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinador;
        }
    }

    @Override
    public boolean addCoordinador(Coordinador coordinador) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO Coordinador (idProgramaEducativo, idPersona) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinador.getProgramaEducativo().getIdProgramaEducativo());
            statement.setInt(2, personaDao.addPersonaReturnId(coordinador.getPersona()));
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Coordinador not added");
            } else {
                System.out.println("Coordinador added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteCoordinadorById(int idCoordinador) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM Coordinador WHERE idCoordinador = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Coordinador not deleted");
            } else {
                System.out.println("Coordinador deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Coordinador getCoordinador(ResultSet resultSet) {
        Coordinador coordinador = new Coordinador();
        Persona personaCoordinador = new Persona();
        ProgramaEducativo programaEducativo = new ProgramaEducativo();
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        String nombreCoordinador = "";
        String apellidoPaternoCoordinador = "";
        String apellidoMaternoCoordinador = "";
        String correoInstitucionalCoordinador = "";
        String correoPersonalCoordinador = "";
        try {
            nombreCoordinador = resultSet.getString("nombreCoordinador");
            personaCoordinador.setNombre(nombreCoordinador);
            apellidoPaternoCoordinador = resultSet.getString("apellidoPaternoCoordinador");
            personaCoordinador.setApellidoPaterno(apellidoPaternoCoordinador);
            apellidoMaternoCoordinador = resultSet.getString("apellidoMaternoCoordinador");
            personaCoordinador.setApellidoMaterno(apellidoMaternoCoordinador);
            correoInstitucionalCoordinador = resultSet.getString("correoInstitucionalCoordinador");
            personaCoordinador.setCorreoInstitucional(correoInstitucionalCoordinador);
            correoPersonalCoordinador = resultSet.getString("correoPersonalCoordinador");
            personaCoordinador.setCorreoPersonal(correoPersonalCoordinador);
            programaEducativo = programaEducativoDao.getProgramaEducativo(resultSet);
            coordinador.setPersona(personaCoordinador);
            coordinador.setProgramaEducativo(programaEducativo);
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return coordinador;
        }
    }
}
