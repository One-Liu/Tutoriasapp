package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IProblematicaAcademicaDAO {
    public ObservableList<ProblematicaAcademica> obtenerProblematicaAcademicaPorDescripcion(String descripcionBusqueda) throws SQLException;
    public ObservableList<ProblematicaAcademica> obtenerProblematicasAcademicas() throws SQLException;
    public ProblematicaAcademica obtenerProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda);
    public boolean agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException;
    public boolean eliminarProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda);
}
