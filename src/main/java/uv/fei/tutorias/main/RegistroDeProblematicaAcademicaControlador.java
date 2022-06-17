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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.bussinesslogic.EstudiantesProblematicasAcademicasDAO;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.domain.DatosGlobalesDeSesion;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TablaEstudiante_Presenta;

public class RegistroDeProblematicaAcademicaControlador implements Initializable {

    @FXML
    private ComboBox<Profesor> cbProfesores;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciasEducativas;
    @FXML
    private TextField tfProblematicaAcademica;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TableView<TablaEstudiante_Presenta> tblEstudiante_Presenta;
    @FXML
    private TableColumn<TablaEstudiante_Presenta, String> colEstudiante;
    @FXML
    private TableColumn<TablaEstudiante_Presenta, CheckBox> colPresenta;

    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
    private ObservableList<TablaEstudiante_Presenta> estudiantesDelTutorAcademico = FXCollections.observableArrayList();

    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();

    private void cargarDatos() {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        try {
            this.profesores.addAll(profesorDAO.obtenerProfesores());
            this.experienciasEducativas.addAll(experienciaEducativaDAO.obtenerExperienciasEducativas());

            ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
            estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantesDeTutor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico()));
            TablaEstudiante_Presenta visualizacionEstudiante;
            for(Estudiante estudiante : estudiantesObtenidos) {
                visualizacionEstudiante = new TablaEstudiante_Presenta();
                visualizacionEstudiante.setEstudiante(estudiante);
                this.estudiantesDelTutorAcademico.add(visualizacionEstudiante);
            }

            if(profesores.isEmpty()) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "No hay profesores registrados",
                    "No se han encontrado profesores registrados en el sistema",
                    Alert.AlertType.ERROR);
                UtilidadVentana.cerrarVentana(new ActionEvent());
            } else if(experienciasEducativas.isEmpty()) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "No hay experiencias educativas registradas",
                    "No se han encontrado experiencias educativas registradas en el sistema",
                    Alert.AlertType.ERROR);
                UtilidadVentana.cerrarVentana(new ActionEvent());
            } else {
                cargarCamposGUI();
            }
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
    }

    private void inicializarColumnasDeTabla() {
        colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPresenta.setCellValueFactory(new PropertyValueFactory("presenta"));
    }

    private void cargarCamposGUI() {
        this.cbProfesores.setItems(profesores);
        this.cbProfesores.getSelectionModel().selectFirst();
        this.cbProfesores.setConverter(new StringConverter<Profesor>() {
            @Override
            public String toString(Profesor profesor) {
                return profesor == null ? null : "(" + profesor.getIdProfesor() + ") " + profesor.getNombreCompleto();
            }

            @Override
            public Profesor fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });

        this.cbExperienciasEducativas.setItems(experienciasEducativas);
        this.cbExperienciasEducativas.getSelectionModel().selectFirst();
        this.cbExperienciasEducativas.setConverter(new StringConverter<ExperienciaEducativa>() {
            @Override
            public String toString(ExperienciaEducativa experienciaEducativa) {
                return experienciaEducativa == null ? null : "(" + experienciaEducativa.getNrc() + ") " + experienciaEducativa.getNombre();
            }

            @Override
            public ExperienciaEducativa fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });

        inicializarColumnasDeTabla();
        this.tblEstudiante_Presenta.setItems(estudiantesDelTutorAcademico);
        this.tblEstudiante_Presenta.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }

    private boolean validarCamposLlenos() {
        boolean camposLlenos = false;

        if(this.tfProblematicaAcademica.getText().trim().isBlank()
            || this.taDescripcion.getText().trim().isBlank()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);

        } else {
            for(TablaEstudiante_Presenta tablaEstudiante_Presenta : estudiantesDelTutorAcademico) {
                CheckBox presentaProblematica = tablaEstudiante_Presenta.getPresenta();

                if(presentaProblematica.isSelected()) {
                    camposLlenos = true;
                    break;
                }
            }

            if(!camposLlenos) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "No hay estudiante seleccionado",
                    "Debe haber al menos un estudiante seleccionado para registrar la problemática académica",
                    Alert.AlertType.WARNING);
            }
        }

        return camposLlenos;
    }

    @FXML
    private void clicRegistrar(ActionEvent evento) {
        if(validarCamposLlenos()) {
            ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
            ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
            EstudiantesProblematicasAcademicasDAO estudiantesProblematicasAcademicasDAO = new EstudiantesProblematicasAcademicasDAO();

            ExperienciaEducativa experienciaEducativaSeleccionada = this.cbExperienciasEducativas.getSelectionModel().getSelectedItem();
            Profesor profesorSeleccionado = this.cbProfesores.getSelectionModel().getSelectedItem();

            problematicaAcademica.setTitulo(this.tfProblematicaAcademica.getText());
            problematicaAcademica.setDescripcion(this.taDescripcion.getText());
            problematicaAcademica.setIdSesionDeTutoriaAcademica(this.sesionDeTutoriaAcademica.getId());
            problematicaAcademica.setIdExperienciaEducativa(experienciaEducativaSeleccionada.getIdExperienciaEducativa());
            problematicaAcademica.setIdProfesor(profesorSeleccionado.getIdProfesor());

            try {
                problematicaAcademica.setIdProblematicaAcademica(problematicaAcademicaDAO.agregarProblematicaAcademica(problematicaAcademica));

                for(TablaEstudiante_Presenta tablaEstudiante_Presenta : estudiantesDelTutorAcademico) {
                    Estudiante estudiante = tablaEstudiante_Presenta.getEstudiante();
                    CheckBox presentaProblematica = tablaEstudiante_Presenta.getPresenta();

                    if(presentaProblematica.isSelected()) {
                        EstudiantesProblematicasAcademicas estudiantesProblematicasAcademicas = new EstudiantesProblematicasAcademicas(estudiante.getIdEstudiante(), problematicaAcademica.getIdProblematicaAcademica());
                        estudiantesProblematicasAcademicasDAO.agregarEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas);
                    }
                }

                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Confirmación de registro",
                    "La problemática académica se ha registrado correctamente",
                    Alert.AlertType.INFORMATION);
            } catch(SQLException ex) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        }
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
