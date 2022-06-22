package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.TablaProblematicaAcademica_Detalles;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultarProblematicasAcademicasControlador implements Initializable {
    @FXML  private TableView<TablaProblematicaAcademica_Detalles> tblProblematicaAcademica;
    @FXML  private TableColumn<TablaProblematicaAcademica_Detalles,String> colExperienciaEducativa;
    @FXML  private TableColumn<TablaProblematicaAcademica_Detalles,String> colFechaDETutoria;
    @FXML  private TableColumn<TablaProblematicaAcademica_Detalles,String> colTitulo;
    @FXML  private Button btnEliminar;
    @FXML  private Button btnConsultar;

    public void actEliminar(ActionEvent actionEvent) {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        int idProblematicaAcademica = tblProblematicaAcademica.getSelectionModel().getSelectedItem().problematicaAcademica.getIdProblematicaAcademica();
        try {
            problematicaAcademicaDAO.eliminarProblematicaAcademicaPorId(idProblematicaAcademica);
        } catch (SQLException e) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
        UtilidadVentana.mostrarAlertaConfirmacion("Modificaciones","La problematica academica se ha eliminado exitosamente", Alert.AlertType.CONFIRMATION);
    }

    public void actConsultar(ActionEvent actionEvent) {
    }

    public void actRegresar(ActionEvent actionEvent) {
    }

    public void crearTabla() throws SQLException {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        ExperienciaEducativa experienciaEducativa;
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
        List<ProblematicaAcademica> problematicasAcademicas = problematicaAcademicaDAO.obtenerProblematicasAcademicasDeUnTutor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico());
        List<TablaProblematicaAcademica_Detalles> problematicaAcademica_detalles = new ArrayList<>();

        for (ProblematicaAcademica p :
                problematicasAcademicas) {
            experienciaEducativa = experienciaEducativaDAO.obtenerExperienciaEducativaPorId(p.getIdExperienciaEducativa());
            sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(p.getIdSesionDeTutoriaAcademica());
            TablaProblematicaAcademica_Detalles tablaProblematicaAcademica_detalles = new TablaProblematicaAcademica_Detalles();
            tablaProblematicaAcademica_detalles.setNombreEE(experienciaEducativa.getNombre());
            tablaProblematicaAcademica_detalles.setFechaDeTutoria(sesionDeTutoriaAcademica.getFechaConFormato());
            tablaProblematicaAcademica_detalles.setTituloProblematicaAcademica(p.getTitulo());
            tablaProblematicaAcademica_detalles.setProblematicaAcademica(p);
            problematicaAcademica_detalles.add(tablaProblematicaAcademica_detalles);
        }
        ObservableList<TablaProblematicaAcademica_Detalles> listaObservable =
                FXCollections.observableArrayList();
        listaObservable.addAll(problematicaAcademica_detalles);
        configurarListObservable(listaObservable);

    }

    private void configurarListObservable(ObservableList<TablaProblematicaAcademica_Detalles> observableList){
        this.colExperienciaEducativa.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getNombreEE()));
        this.colTitulo.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getTituloProblematicaAcademica()));
        this.colFechaDETutoria.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getFechaDeTutoria()));
        this.tblProblematicaAcademica.setItems(observableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnConsultar.setDisable(true);
        btnEliminar.setDisable(true);
    }


    public void clicEnProblematicaAcademica(MouseEvent mouseEvent) {
        btnConsultar.setDisable(tblProblematicaAcademica.getSelectionModel().getSelectedItem() == null);
        btnEliminar.setDisable(tblProblematicaAcademica.getSelectionModel().getSelectedItem() == null);
    }
}
