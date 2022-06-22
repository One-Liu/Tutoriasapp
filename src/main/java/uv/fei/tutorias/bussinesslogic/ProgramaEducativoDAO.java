package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAO implements IProgramaEducativoDAO {

    private final Logger LOGGER = Logger.getLogger(ProgramaEducativoDAO.class);

    @Override
    public List<ProgramaEducativo> obtenerProgramasEducativos() throws SQLException {
        List<ProgramaEducativo> programasEducativos = new ArrayList<>();
        String consulta = "SELECT * FROM programa_educativo";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                programasEducativos.add(getProgramaEducativo(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
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
            if(resultado.next()) {
                programaEducativo = getProgramaEducativo(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
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
}
