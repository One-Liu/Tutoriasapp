
package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.*;
import uv.fei.tutorias.utilidades.TablaProblematica_FechaReporte_EE_Profesor;
import uv.fei.tutorias.utilidades.UtilidadVentana;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;

public class ProblematicasSinSolucionControlador implements Initializable {

    @FXML private Button btnRegistraarSolucion;
    @FXML private TableView<TablaProblematica_FechaReporte_EE_Profesor> tblProblematicaAcademica;
    @FXML private TableColumn<TablaProblematica_FechaReporte_EE_Profesor,String> colTituloPA;
    @FXML private TableColumn<TablaProblematica_FechaReporte_EE_Profesor, String> colFechaDeReporte;
    @FXML private TableColumn<TablaProblematica_FechaReporte_EE_Profesor, String> colExperienciaEducativa;
    @FXML private TableColumn <TablaProblematica_FechaReporte_EE_Profesor,String>colProfesor;

    private ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    private ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
    private Profesor profesor = new Profesor();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        List<ProblematicaAcademica> listaDao = null;
        ObservableList<TablaProblematica_FechaReporte_EE_Profesor> listaObservable = FXCollections.observableArrayList();
        List<TablaProblematica_FechaReporte_EE_Profesor> profesores = null;

        try {
            listaDao = problematicaAcademicaDAO.obtenerProblematicasAcademicasSinSolucion();
        } catch (SQLException e) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }

        try {
            profesores = CargarProblematicas(listaDao);
        } catch (SQLException e) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
        listaObservable.addAll(profesores);
        configurarTabla(listaObservable);
        btnRegistraarSolucion.setDisable(true);

    }

    public void clicRegistrarSolucion(ActionEvent actionEvent) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIRegistrarSolucionProblematicaAcademica.fxml"));
            Parent raiz = cargadorFXML.load();
            RegistrarSolucionProblematicaAcademicaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setProblematicaAcademica(this.problematicaAcademica);
            controladorGUI.setExperienciaEducativa(this.experienciaEducativa);
            controladorGUI.setSesionDeTutoriaAcademica(this.sesionDeTutoriaAcademica);
            controladorGUI.setProfesor(this.profesor);
            controladorGUI.recuperarDatos();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Probleamaticas academicas sin solucion");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }


    }

    public void clicCancelar(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }

    public List<TablaProblematica_FechaReporte_EE_Profesor> CargarProblematicas(List<ProblematicaAcademica> problematicaAcademicaLista) throws SQLException {
        List<TablaProblematica_FechaReporte_EE_Profesor> tablas = new ArrayList<>();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        for (ProblematicaAcademica p :
                problematicaAcademicaLista) {
            TablaProblematica_FechaReporte_EE_Profesor ta = new TablaProblematica_FechaReporte_EE_Profesor();
            ta.setProblematicaAcademica(p);
            ta.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(p.getIdSesionDeTutoriaAcademica()));
            ta.setProfesor(profesorDAO.obtenerProfesorPorId(p.getIdProfesor()));
            ta.setExperienciaEducativa(experienciaEducativaDAO.obtenerExperienciaEducativaPorId(p.getIdExperienciaEducativa()));
            tablas.add(ta);
        }
        return tablas;
    }
    private void configurarTabla(ObservableList<TablaProblematica_FechaReporte_EE_Profesor> observableList){
        this.colExperienciaEducativa.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getExperienciaEducativa().getNombre()));
        this.colProfesor.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getProfesor().getNombre()));
        this.colFechaDeReporte.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getFechaDeReporte()));
        this.colTituloPA.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getProblematicaAcademica().getTitulo()));
        this.tblProblematicaAcademica.setItems(observableList);
    }

    public void clicEnProblematicaAcademica(MouseEvent mouseEvent) {
        btnRegistraarSolucion.setDisable(tblProblematicaAcademica.getSelectionModel().getSelectedItem() == null);
        this.problematicaAcademica = tblProblematicaAcademica.getSelectionModel().getSelectedItem().getProblematicaAcademica();
        this.sesionDeTutoriaAcademica = tblProblematicaAcademica.getSelectionModel().getSelectedItem().getSesionDeTutoriaAcademica();
        this.experienciaEducativa = tblProblematicaAcademica.getSelectionModel().getSelectedItem().getExperienciaEducativa();
        this.profesor = tblProblematicaAcademica.getSelectionModel().getSelectedItem().getProfesor();

    }
}
