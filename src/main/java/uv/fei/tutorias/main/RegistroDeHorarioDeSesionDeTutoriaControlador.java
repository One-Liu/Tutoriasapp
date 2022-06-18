package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ListaDeAsistencia;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.utilidades.TablaEstudiante_Horario;

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
    
    public void cargarDatos() throws SQLException {
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
    
    private boolean validarDatos() throws SQLException {
        boolean datosValidos = true;
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        
        if(estudiantesDelTutorAcademico.isEmpty()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Estudiantes", 
                "Aún no tiene ningún estudiante asignado", 
                Alert.AlertType.ERROR);
            datosValidos = false;

        } else if(listaDeAsistenciaDAO.validarRegistroDeListasDeAsistencia(
            DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico(),
            this.sesionDeTutoriaAcademica.getId())) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Horarios registrados",
                "La sesión de tutoría académica seleccionada ya tiene horarios registrados",
                Alert.AlertType.ERROR);
            datosValidos = false;
        }

        return datosValidos;
    }
    
    public void cargarCamposGUI() throws SQLException {
        if(validarDatos()) {
            this.lblFechaDeSesionDeTutoria.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());

            this.colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.colHoras.setCellValueFactory(new PropertyValueFactory("hora"));
            this.colMinutos.setCellValueFactory(new PropertyValueFactory("minuto"));

            this.tblTutorado_Horario.setItems(estudiantesDelTutorAcademico);
            this.tblTutorado_Horario.getSelectionModel().clearSelection();
        } else {
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
    
    private boolean validarHorariosIngresados() {
        boolean horariosValidos = true;
        
        for(TablaEstudiante_Horario tablaEstudiante_Horario : estudiantesDelTutorAcademico) {
            String hora = tablaEstudiante_Horario.getHora().getValue();
            String minuto = tablaEstudiante_Horario.getMinuto().getValue();
            
            if(hora.equals("00") && minuto.equals("00")) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Horarios de sesión", 
                    "Debe asignar un horario para todos los estudiantes", 
                    Alert.AlertType.WARNING);
                horariosValidos = false;
                break;
                
            } else if(!tablaEstudiante_Horario.getHORAS().contains(hora)
                || !tablaEstudiante_Horario.getMINUTOS().contains(minuto)) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Valores inválidos", 
                    "Asegúrese de haber ingresado valores válidos en los horarios de sesión de tutoría", 
                    Alert.AlertType.WARNING);
                horariosValidos = false;
                break;
            }
        }
        
        return horariosValidos;
    }
    
    @FXML
    private void clicRegistrar(ActionEvent evento) {
        if(validarHorariosIngresados()) {
            ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
            
            try {
                for(TablaEstudiante_Horario tablaEstudiante_Horario : estudiantesDelTutorAcademico) {
                    Estudiante estudiante = tablaEstudiante_Horario.getEstudiante();
                    String horas = tablaEstudiante_Horario.getHora().getValue();
                    String minutos = tablaEstudiante_Horario.getMinuto().getValue();

                    String horarioDeSesion = horas + ":" + minutos;
                    
                    ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia(horarioDeSesion, false, this.sesionDeTutoriaAcademica.getId(), estudiante.getIdEstudiante());
                    listaDeAsistenciaDAO.agregarListaDeAsistencia(listaDeAsistencia);
                }
                
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de registro", 
                    "Los horarios para la sesión de tutoría académica han sido reigstrados correctamente", 
                    Alert.AlertType.INFORMATION);
                UtilidadVentana.cerrarVentana(evento);
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        }
    }
}
