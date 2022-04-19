package uv.fei.tutorias.bussinesslogic;

import java.util.List;
import java.sql.ResultSet;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public interface IProgramaEducativoDAO {
    public List<ProgramaEducativo> findProgramasEducativosByName(String searchName);
    public ProgramaEducativo findProgramaEducativoById(int idProgramaEducativo);
    public ProgramaEducativo getProgramaEducativo(ResultSet resultSet);
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo);
    public boolean deleteProgramaEducativoById(int idProgramaEducativo);
}
