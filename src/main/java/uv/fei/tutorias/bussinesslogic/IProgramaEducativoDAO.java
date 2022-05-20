package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.sql.ResultSet;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public interface IProgramaEducativoDAO {
    public ArrayList<ProgramaEducativo> findProgramasEducativosByName(String searchName);
    public ProgramaEducativo findProgramaEducativoById(int idProgramaEducativo);
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo);
    public boolean deleteProgramaEducativoById(int idProgramaEducativo);
}
