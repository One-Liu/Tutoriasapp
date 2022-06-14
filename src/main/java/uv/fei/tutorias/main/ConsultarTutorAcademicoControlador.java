package uv.fei.tutorias.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.TutorAcademico;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConsultarTutorAcademicoControlador implements Initializable {

    public TableColumn tablaTutorID;
    public TableColumn tablaTutorNumero;
    public ObservableList<TutorAcademico> listaObservableTutores;
    public TableView tablaTutores;
    public TableColumn tablaTutorApellidoPaterno;
    public TableColumn tablaTutorApellidoMaterno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        try {
            mostrarTutoresTabla();
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    public void configurarTabla(){
        listaObservableTutores = FXCollections.observableArrayList();
        this.tablaTutorNumero.setCellValueFactory(new PropertyValueFactory("idTutorAcademico"));
        this.tablaTutorID.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tablaTutorApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        this.tablaTutorApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
    }

    public void mostrarTutoresTabla() throws SQLException {
        ArrayList<TutorAcademico> listaTutores = new ArrayList<>();
        TutorAcademicoDAO tutorDAO = new TutorAcademicoDAO();
        listaTutores = (ArrayList<TutorAcademico>) tutorDAO.obtenerTutoresAcademicos();
        if (listaTutores != null) {
            listaObservableTutores.addAll(listaTutores);
            tablaTutores.setItems(listaObservableTutores);
        }else{
            mostrarAlerta("Error de Conexion", "No se pudieron obtener los datos", Alert.AlertType.ERROR);
        }
    }

    public void regresarBotonClic(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void consultarBotonClic(MouseEvent mouseEvent) {
        int filaSeleccionada = tablaTutores.getSelectionModel().getSelectedIndex();
        if (filaSeleccionada >= 0){
            TutorAcademico tutorSeleccionado = listaObservableTutores.get(filaSeleccionada);
            mostrarGUIInformacionTutorAcademico(tutorSeleccionado);
        }else{
            mostrarAlerta("Advertencia","Debes seleccionar un Tutor de la Lista", Alert.AlertType.WARNING);
        }
    }
    public void mostrarGUIInformacionTutorAcademico(TutorAcademico tutorSeleccionado ){
        try{
            FXMLLoader recursoFXML = new FXMLLoader(getClass().getResource("GUIInformacionTutorAcademico.fxml"));
            Parent root = recursoFXML.load();
            InformacionTutorAcademicoControlador controlador = recursoFXML.getController();
            controlador.inicializarValores(tutorSeleccionado);
            Scene escena = new Scene(root);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch(IOException ex){
            mostrarAlerta("Error de Invocar Ventana","No se pudo abrir la ventada de Información del Tutor Académico", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
