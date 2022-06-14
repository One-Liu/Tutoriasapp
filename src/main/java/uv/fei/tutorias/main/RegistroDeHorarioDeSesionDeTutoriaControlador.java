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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class RegistroDeHorarioDeSesionDeTutoriaControlador implements Initializable {
    @FXML
    private Label lblFechaDeSesionDeTutoria;
    @FXML
    private TableView<?> tblTutorado_Horario;
    @FXML
    private TableColumn<?,?> colTutorado;
    @FXML
    private TableColumn<?,?> colHorario;
    
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    
    private void inicializarEstudiantes() throws SQLException {
        this.estudiantes.addAll(estudianteDAO.obtenerEstudiantesDeTutor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico()));
    }
    
    private void cargarCamposGUI() {
        try {
            inicializarEstudiantes();
            this.lblFechaDeSesionDeTutoria.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
            
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
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
