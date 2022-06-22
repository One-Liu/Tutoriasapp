package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class ModificarSolucionProblematicaAcademicaControlador implements Initializable {
    
    @FXML
    private TableView<?> tblProblematicasAcademicas;
    @FXML
    private TableColumn<?,String> clmFechaDeReporte;
    @FXML
    private TableColumn<?,String> clmProblematicaAcademica;
    @FXML
    private TableColumn<?,String> clmExperienciaEducativa;
    @FXML
    private TableColumn<?,String> clmProfesor;
    
    private ObservableList<ProblematicaAcademica> problematicasAcademicasConSolucion = FXCollections.observableArrayList();
    
    public void cargarDatos() throws SQLException {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        problematicasAcademicasConSolucion.addAll(problematicaAcademicaDAO.obtenerProblematicasAcademicasConSolucion());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void clicSeleccionar(ActionEvent evento) {
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
