package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.ProblematicaAcademica;

public interface IProblematicaAcademicaDAO {
    List<ProblematicaAcademica> obtenerProblematicaAcademicaPorDescripcion(String descripcionBusqueda) throws SQLException;

    public List<ProblematicaAcademica> obtenerProblematicasAcademicas() throws SQLException;
    public ProblematicaAcademica obtenerProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) throws SQLException;
    public int agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException;
    public boolean eliminarProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) throws SQLException;
    public List<ProblematicaAcademica> obtenerProblematicasAcademicasConSolucion() throws SQLException;
}
