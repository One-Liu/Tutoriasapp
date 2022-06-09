package uv.fei.tutorias.bussinesslogic;

import java.util.List;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IProblematicaAcademicaDAO {
    public List<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName);
    public List<ProblematicaAcademica> findAllProblematicasAcademicas();
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId);
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica);
    public boolean deleteProblematicaAcademicaById(int searchId);
}
