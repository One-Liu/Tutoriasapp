package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ProgramaEducativo;

import java.util.List;

// author @liu

public interface IProgramaEducativoDAO {
    public List<ProgramaEducativo> findProgramasEducativosByName(String searchName);
    public ProgramaEducativo findProgramaEducativoById(int idProgramaEducativo);
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo);
    public boolean deleteProgramaEducativoById(int idProgramaEducativo);
}
