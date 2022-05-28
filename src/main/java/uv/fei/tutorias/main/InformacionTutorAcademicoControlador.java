package uv.fei.tutorias.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.TutorAcademico;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformacionTutorAcademicoControlador implements Initializable {
    public TableView tablaEstudiantes;
    public TableColumn estudianteMatricula;
    public TableColumn estudianteNombre;
    public TableColumn estudianteApellidoPaterno;
    public TableColumn estudianteApellidoMaterno;
    public Label nombreTutor;
    public Label correoInstitucionalTutor;
    public ObservableList<Estudiante> listaObservableEstudiantes;


    public void inicializarValores(TutorAcademico tutorSeleccionado){
        nombreTutor.setText(tutorSeleccionado.getNombre() + " " + tutorSeleccionado.getApellidoPaterno() + " " + tutorSeleccionado.getApellidoMaterno());
        int idTutor = tutorSeleccionado.getId();
        TutorAcademicoDAO tutorDAO = new TutorAcademicoDAO();
        TutorAcademico datosTutor = tutorDAO.findTutorAcademicoById(idTutor);
        correoInstitucionalTutor.setText(datosTutor.getUsuario().getCorreoInstitucional());
        mostrarEstudianteTabla(idTutor);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }

    private void mostrarEstudianteTabla(int idTutor) {
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        listaEstudiantes = estudianteDAO.recuperarTodosEstudiantesPorIDTutor(idTutor);
        if (listaEstudiantes != null) {
            listaObservableEstudiantes.addAll(listaEstudiantes);
            tablaEstudiantes.setItems(listaObservableEstudiantes);
        }else{
            mostrarAlerta("Error de Conexion", "No se pudieron obtener los datos", Alert.AlertType.ERROR);
        }

    }

    private void configurarTabla() {
        listaObservableEstudiantes = FXCollections.observableArrayList();
        this.estudianteMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        this.estudianteNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.estudianteApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        this.estudianteApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
    }

    public void cerrarBoton(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void descargarBoton(MouseEvent mouseEvent) {
    }

    public void imprimirBoton(MouseEvent mouseEvent) {
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
