package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ProgramaEducativo;

import java.util.List;
import java.util.ArrayList;

// author @liu

public class ProgramaEducativoDAO implements IProgramaEducativoDAO {

    @Override
    public List<ProgramaEducativo> findProgramasEducativosById(String searchName) {
        List<ProgramaEducativo> programasEducativos = new ArrayList<>();
        return programasEducativos;
    }

    @Override
    public ProgramaEducativo findProgramaEducativoById(int idProgramaEducativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProgramaEducativo(ProgramaEducativo programaEducativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteProgramaEducativoById(int idProgramaEducativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
