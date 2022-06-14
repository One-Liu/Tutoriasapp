package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;

import uv.fei.tutorias.domain.ExperienciaEducativa;

public interface IExperienciaEducativaDAO {
    public ExperienciaEducativa obtenerExperienciaEducativaPorId(int searchId) throws SQLException;
    public boolean agregarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException;
    public boolean eliminarExperienciaEducativa(int searchId) throws SQLException;

    boolean modificarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws SQLException;

    public List<ExperienciaEducativa> obtenerExperienciasEducativas() throws SQLException;
}
