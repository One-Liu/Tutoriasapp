package uv.fei.tutorias.main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.utilidades.TablaProblematica_FechaReporte_EE_Profesor;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class SeleccionDeSolucionAProblematicaAcademica_ModificacionSolucionControlador implements Initializable {
    
    @FXML
    private TableView<TablaProblematica_FechaReporte_EE_Profesor> tblProblematicasAcademicas;
    @FXML
    private TableColumn<TablaProblematica_FechaReporte_EE_Profesor,String> colFechaDeReporte;
    @FXML
    private TableColumn<TablaProblematica_FechaReporte_EE_Profesor,String> colProblematicaAcademica;
    @FXML
    private TableColumn<TablaProblematica_FechaReporte_EE_Profesor,String> colExperienciaEducativa;
    @FXML
    private TableColumn<TablaProblematica_FechaReporte_EE_Profesor,String> colProfesor;
    
    private ObservableList<TablaProblematica_FechaReporte_EE_Profesor> problematicasAcademicasConSolucion;
    
    public void cargarDatos() throws SQLException {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        
        ObservableList<ProblematicaAcademica> problematicasAcademicas = FXCollections.observableArrayList();
        problematicasAcademicas.addAll(problematicaAcademicaDAO.obtenerProblematicasAcademicasConSolucion());
        
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        TablaProblematica_FechaReporte_EE_Profesor visualizacionProblematica;
        
        for(ProblematicaAcademica problematicaAcademica : problematicasAcademicas) {
            visualizacionProblematica = new TablaProblematica_FechaReporte_EE_Profesor();
            SesionDeTutoriaAcademica sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(problematicaAcademica.getIdSesionDeTutoriaAcademica());
            ExperienciaEducativa experienciaEducativa = experienciaEducativaDAO.obtenerExperienciaEducativaPorId(problematicaAcademica.getIdExperienciaEducativa());
            Profesor profesor = profesorDAO.findProfesorById(experienciaEducativa.getIdProfesor());
            
            visualizacionProblematica.setProblematicaAcademica(problematicaAcademica);
            visualizacionProblematica.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);
            visualizacionProblematica.setExperienciaEducativa(experienciaEducativa);
            visualizacionProblematica.setProfesor(profesor);
            this.problematicasAcademicasConSolucion.add(visualizacionProblematica);
        }
    }
    
    public void cargarCamposGUI() {
        this.colProblematicaAcademica.setCellValueFactory(new PropertyValueFactory("tituloProblematicaAcademica"));
        this.colFechaDeReporte.setCellValueFactory(new PropertyValueFactory("fechaDeReporte"));
        this.colExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreEE"));
        this.colProfesor.setCellValueFactory(new PropertyValueFactory("nombreProfesor"));
        
        this.tblProblematicasAcademicas.setItems(this.problematicasAcademicasConSolucion);
        this.tblProblematicasAcademicas.getSelectionModel().clearSelection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void clicSeleccionar(ActionEvent evento) {
        TablaProblematica_FechaReporte_EE_Profesor problematicaSeleccionada = this.tblProblematicasAcademicas.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIModificacionDeSolucionAProblematicaAcademica.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeSolucionAProblematicaAcademicaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setProblematicaAcademica(problematicaSeleccionada.getProblematicaAcademica());
            controladorGUI.setSesionDeTutoriaAcademica(problematicaSeleccionada.getSesionDeTutoriaAcademica());
            controladorGUI.setExperienciaEducativa(problematicaSeleccionada.getExperienciaEducativa());
            controladorGUI.setProfesor(problematicaSeleccionada.getProfesor());
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de solución a problemática académica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
            UtilidadVentana.cerrarVentana(evento);
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
