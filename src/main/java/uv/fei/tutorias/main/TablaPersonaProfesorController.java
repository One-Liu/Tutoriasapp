package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TablaPersonaProfesorController implements Initializable {

    public AnchorPane raiz;
    @FXML private TableColumn<Profesor,String> colIdProfesor;
    @FXML private Button btnSeleccionarProfesor;
    @FXML private TableView<Profesor> tblPersona;
    @FXML private TableColumn<Persona,String> colNombre;
    @FXML private TableColumn<Persona,String> colApellidoPaterno;
    @FXML private TableColumn<Persona,String> colApellidoMaterno;
    private ObservableList<Profesor> listaObservableProfesores= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        List<Profesor> profesores = null;
        try {
            profesores = profesorDAO.findProfesoresByName("");
        } catch (SQLException e) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
        assert profesores != null;
        listaObservableProfesores.addAll(profesores);
        configurarTabla(listaObservableProfesores);


    }

    public void configurarTabla(ObservableList<Profesor> profesores){
        this.colIdProfesor.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getIdProfesor()));
        this.colNombre.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getNombre()));
        this.colApellidoPaterno.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getApellidoPaterno()));
        this.colApellidoMaterno.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getApellidoMaterno()));
        this.tblPersona.setItems(profesores);
        btnSeleccionarProfesor.setDisable(true);

    }

    public void actSelectPersona(MouseEvent mouseEvent) {
        btnSeleccionarProfesor.setDisable(false);

    }

    public void actSeleccionarProfesor(ActionEvent actionEvent) {
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        //recuperamos la informacion de la ventana anterior
        Stage stage = (Stage) this.raiz.getScene().getWindow();
        ExperienciaEducativa experienciaEducativa = (ExperienciaEducativa) stage.getUserData();
        //Juntamos la informacion con la ventana nueva
        experienciaEducativa.setIdProfesor(tblPersona.getSelectionModel().getSelectedItem().getIdProfesor());
        //lo agregamos a la base de datos
        experienciaEducativaDAO.modificarExperienciaEducativa(experienciaEducativa);

    }
}
