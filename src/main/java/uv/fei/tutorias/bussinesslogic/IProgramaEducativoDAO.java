package uv.fei.tutorias.bussinesslogic;

import java.util.List;
import java.sql.SQLException;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public interface IProgramaEducativoDAO {
    public List<ProgramaEducativo> obtenerProgramasEducativos() throws SQLException;
    public ProgramaEducativo obtenerProgramaEducativoPorId(int idProgramaEducativo) throws SQLException;
}
