package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AsignarExperienciaEducativaControlador implements Initializable {
    @FXML private CheckBox chBok;
    @FXML private TableColumn<ExperienciaEducativa,String> colIdEe;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colNrc;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colProfesor;
    @FXML
    private TableColumn<ExperienciaEducativa, String> colNombreEE;
    @FXML
    private ToggleButton tbtnEESinProfesorAsignado;
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
        ObservableList<ExperienciaEducativa> experienciasEducativas = experienciaEducativaDAO.findExperienciasEducativasByName("");

        observaList(experienciasEducativas);
        btnAsignarEEaProfesor.setDisable(true);



    }


    public void actEeSinProfesorAsignado(ActionEvent actionEvent) {

        if (chBok.isSelected()){
            ObservableList<ExperienciaEducativa> experienciasEducativas = experienciaEducativaDAO.findExperienciasEducativasWithoutProfesor();
            observaList(experienciasEducativas);
        }else {
            ObservableList<ExperienciaEducativa> experienciasEducativas = experienciaEducativaDAO.findExperienciasEducativasByName("");
            observaList(experienciasEducativas);
        }
    }


    private void observaList(ObservableList<ExperienciaEducativa> experienciasEducativas) {
        this.colIdEe.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getIdExperienciaEducativa()));
        this.colNrc.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getNrc()));
        this.colNombreEE.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getNombre()));
        this.colProfesor.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getIdProfesor()));

        this.tblEE.setItems(experienciasEducativas);
    }

    public void actAsignarEeAProfesor(ActionEvent actionEvent) throws IOException {
        //creamos un objeto con lo que selecciona el usuario en la tabla
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setIdExperienciaEducativa(tblEE.getSelectionModel().getSelectedItem().getIdExperienciaEducativa());
        experienciaEducativa.setNrc(tblEE.getSelectionModel().getSelectedItem().getNrc());
        experienciaEducativa.setIdProfesor(tblEE.getSelectionModel().getSelectedItem().getIdProfesor());
        experienciaEducativa.setNombre(tblEE.getSelectionModel().getSelectedItem().getNombre());

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();



        URL url = Paths.get("src\\main\\resources\\uv.fei.tutorias.main\\GUIMostrarProfesores.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(url ), 600, 400);

        stage.setUserData(experienciaEducativa);
        stage.setTitle("Profesores");
        stage.setScene(scene);
        stage.show();
    }
}
