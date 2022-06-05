package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAO implements IProgramaEducativoDAO {

    private final Logger LOGGER = Logger.getLogger(ProgramaEducativoDAO.class);

    @Override
    public ArrayList<ProgramaEducativo> obtenerProgramasEducativos() throws SQLException {
        ArrayList<ProgramaEducativo> programasEducativos = new ArrayList<>();
        String consulta = "SELECT * FROM programa_educativo";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se han encontrado programas educativos");
            } else {
                do {
                    programasEducativos.add(getProgramaEducativo(resultado));
                } while(resultado.next());
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return programasEducativos;
    }

    @Override
    public ProgramaEducativo obtenerProgramaEducativoPorId(int idProgramaEducativo) throws SQLException {
        ProgramaEducativo programaEducativo = new ProgramaEducativo();
        String consulta = "SELECT * FROM programa_educativo WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idProgramaEducativo);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se ha encontrado el programa educativo con el id " + idProgramaEducativo);
            } else {
                programaEducativo = getProgramaEducativo(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return programaEducativo;
    }

    private ProgramaEducativo getProgramaEducativo(ResultSet resultado) throws SQLException {
        int idProgramaEducativo;
        String nombre;
        
        idProgramaEducativo = resultado.getInt("id");
        nombre = resultado.getString("nombreProgramaEducativo");
        
        ProgramaEducativo programaEducativo = new ProgramaEducativo(idProgramaEducativo,nombre);
        return programaEducativo;
    }

    @Override
    public boolean agregarProgramaEducativo(ProgramaEducativo programaEducativo) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO programa_educativo (nombreProgramaEducativo) VALUES (?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, programaEducativo.getNombre());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: El programa educativo no se ha agregado");
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarProgramaEducativoPorId(int idProgramaEducativo) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM programa_educativo WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idProgramaEducativo);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                throw new SQLException("ERROR: No se ha eliminado el programa educativo con el id " + idProgramaEducativo);
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarProgramaEducativo(ProgramaEducativo programaEducativo) throws SQLException {
        boolean validacion = false;
        String consulta = 
                "UPDATE programa_educativo " + 
                "SET nombreProgramaEducativo = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, programaEducativo.getNombre());
            sentencia.setInt(2, programaEducativo.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado el programa educativo con el id " + programaEducativo.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}
