package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IProblematicaAcademicaDAO {
    public List<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName);
    public List<ProblematicaAcademica> findAllProblematicasAcademicas();
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId);
    public boolean agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException;
    public boolean deleteProblematicaAcademicaById(int searchId);
}
