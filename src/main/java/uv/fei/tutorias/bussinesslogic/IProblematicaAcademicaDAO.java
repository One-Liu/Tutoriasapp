package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IProblematicaAcademicaDAO {
    public ArrayList<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName);
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId);
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica);
    public boolean deleteProblematicaAcademicaById(int searchId);
}
