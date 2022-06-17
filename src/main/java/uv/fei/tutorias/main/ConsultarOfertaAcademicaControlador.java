package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarOfertaAcademicaControlador implements Initializable {

    @FXML private CheckBox chBok;
    @FXML private TableColumn<ExperienciaEducativa,String> colIdEe;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colNrc;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colProfesor;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colNombreEE;
    @FXML
    private Button btnRegresa;
    @FXML
    private Button btnAsignarEEaProfesor;
    @FXML
    private TableView<ExperienciaEducativa> tblEE;


    ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();




    public void actSeleccionEE(MouseEvent mouseEvent) {
        if (tblEE.getSelectionModel().getSelectedItem().getIdProfesor() == 0){
            btnAsignarEEaProfesor.setDisable(false);
        }else btnAsignarEEaProfesor.setDisable(true);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ExperienciaEducativa> experienciasEducativasObservables = FXCollections.observableArrayList();
        try {
            experienciasEducativasObservables.addAll(experienciaEducativaDAO.obtenerExperienciasEducativas());
        } catch (SQLException e) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }

        configurarLista(experienciasEducativasObservables);
        btnAsignarEEaProfesor.setDisable(true);

    }


    public void clicEeSinProfesorAsignado(ActionEvent actionEvent) throws SQLException {
        ObservableList<ExperienciaEducativa> experienciasEducativasObservables = FXCollections.observableArrayList();
        if (chBok.isSelected()){
            experienciasEducativasObservables.addAll(experienciaEducativaDAO.buscarExperienciaEducativasSinTutor()) ;
            configurarLista(experienciasEducativasObservables);
        }else {
            experienciasEducativasObservables.addAll(experienciaEducativaDAO.obtenerExperienciasEducativas());
            configurarLista(experienciasEducativasObservables);
        }
    }


    private void configurarLista(ObservableList<ExperienciaEducativa> experienciasEducativas) {
        this.colIdEe.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getIdExperienciaEducativa()));
        this.colNrc.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getNrc()));
        this.colNombreEE.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getNombre()));
        this.colProfesor.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getIdProfesor()));

        this.tblEE.setItems(experienciasEducativas);
    }

    public void clicAsignarEeAProfesor(ActionEvent actionEvent) throws IOException {
        //creamos un objeto con lo que selecciona el usuario en la tabla
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setIdExperienciaEducativa(tblEE.getSelectionModel().getSelectedItem().getIdExperienciaEducativa());
        experienciaEducativa.setNrc(tblEE.getSelectionModel().getSelectedItem().getNrc());
        experienciaEducativa.setIdProfesor(tblEE.getSelectionModel().getSelectedItem().getIdProfesor());
        experienciaEducativa.setNombre(tblEE.getSelectionModel().getSelectedItem().getNombre());
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIAsignarExperienciaEducativaAProfesor.fxml"));
            Parent raiz = cargadorFXML.load();
            AsignarExperienciaEducativaAProfesorControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setExperienciaEducativa(experienciaEducativa);
            Scene escenaFormulario = new Scene(raiz);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setResizable(false);
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.setTitle("Asignar Experiencia educativa a profesor");
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(IOException | IllegalStateException ioException){
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }


    }

    public void clicCancelar(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }
}
