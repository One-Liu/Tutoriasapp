package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TablaEstudiante_Horario;

public class RegistroDeHorarioDeSesionDeTutoriaControlador implements Initializable {
    @FXML
    private Label lblFechaDeSesionDeTutoria;
    @FXML
    private TableView<TablaEstudiante_Horario> tblTutorado_Horario;
    @FXML
    private TableColumn<TablaEstudiante_Horario,String> colEstudiante;
    @FXML
    private TableColumn<TablaEstudiante_Horario,Spinner> colHoras;
    @FXML
    private TableColumn<TablaEstudiante_Horario,Spinner> colMinutos;
    
    private ObservableList<TablaEstudiante_Horario> estudiantesDelTutorAcademico = FXCollections.observableArrayList();
    
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    private void cargarDatos() throws SQLException {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
        estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantesDeTutor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico()));
        TablaEstudiante_Horario visualizacionEstudiante;
        
        for(Estudiante estudiante : estudiantesObtenidos) {
            visualizacionEstudiante = new TablaEstudiante_Horario();
            visualizacionEstudiante.setEstudiante(estudiante);
            this.estudiantesDelTutorAcademico.add(visualizacionEstudiante);
            System.out.println(estudiante);
        }
    }
    
    private void cargarCamposGUI() {
        this.lblFechaDeSesionDeTutoria.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
        
        this.colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colHoras.setCellValueFactory(new PropertyValueFactory("hora"));
        this.colMinutos.setCellValueFactory(new PropertyValueFactory("minuto"));
        
        this.tblTutorado_Horario.setItems(estudiantesDelTutorAcademico);
        this.tblTutorado_Horario.getSelectionModel().clearSelection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
    
    @FXML
    private void clicRegistrar(ActionEvent evento) {
        
        UtilidadVentana.cerrarVentana(evento);
    }
}
