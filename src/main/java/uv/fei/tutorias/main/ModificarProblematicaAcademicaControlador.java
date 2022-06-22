package uv.fei.tutorias.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.TablaEstudiante_Presenta;
import uv.fei.tutorias.utilidades.UtilidadVentana;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModificarProblematicaAcademicaControlador implements Initializable {
    @FXML private ComboBox<Profesor> cbProfesores;
    @FXML private ComboBox<ExperienciaEducativa> cbExperienciasEducativas;
    @FXML private TableView<TablaEstudiante_Presenta> tblEstudiante_Presenta;
    @FXML private TableColumn<TablaEstudiante_Presenta,String> colEstudiante;
    @FXML private TableColumn<TablaEstudiante_Presenta,CheckBox> colPresenta;
    @FXML private TextField tfProblematicaAcademica;
    @FXML private TextArea taDescripcion;

    @Setter
    private ProblematicaAcademica problematicaAcademica;




    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
    private ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
    private ObservableList<TablaEstudiante_Presenta> estudiantesDelTutorAcademico = FXCollections.observableArrayList();

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
                }else {
                    UtilidadVentana.mostrarAlertaSinConfirmacion(
                            "No hay estudiante seleccionado",
                            "Debe haber al menos un estudiante seleccionado para registrar la problemática académica",
                            Alert.AlertType.WARNING);
                    break;
                }
            }
        }

        return camposLlenos;
    }



    public void clicRegistrar(ActionEvent actionEvent) {
        if(validarCamposLlenos()) {
            ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
            EstudiantesProblematicasAcademicasDAO estudiantesProblematicasAcademicasDAO = new EstudiantesProblematicasAcademicasDAO();

            ExperienciaEducativa experienciaEducativaSeleccionada = this.cbExperienciasEducativas.getSelectionModel().getSelectedItem();
            Profesor profesorSeleccionado = this.cbProfesores.getSelectionModel().getSelectedItem();

            problematicaAcademica.setTitulo(this.tfProblematicaAcademica.getText());
            problematicaAcademica.setDescripcion(this.taDescripcion.getText());
            problematicaAcademica.setIdExperienciaEducativa(experienciaEducativaSeleccionada.getIdExperienciaEducativa());
            problematicaAcademica.setIdProfesor(profesorSeleccionado.getIdProfesor());

            try {
                problematicaAcademicaDAO.modificarProblematicaAcademica(problematicaAcademica);

                for(TablaEstudiante_Presenta tablaEstudiante_Presenta : estudiantesDelTutorAcademico) {
                    Estudiante estudiante = tablaEstudiante_Presenta.getEstudiante();
                    CheckBox presentaProblematica = tablaEstudiante_Presenta.getPresenta();
                    EstudiantesProblematicasAcademicas estudiantesProblematicasAcademicas = new EstudiantesProblematicasAcademicas(estudiante.getIdEstudiante(), problematicaAcademica.getIdProblematicaAcademica());

                    if(presentaProblematica.isSelected()) {
                        if (!estudiantesProblematicasAcademicasDAO.existeEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas)){
                            estudiantesProblematicasAcademicasDAO.agregarEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas);
                        }
                    }else {
                        if (estudiantesProblematicasAcademicasDAO.existeEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas)){
                            estudiantesProblematicasAcademicasDAO.eliminarAEstudianteDeLaProblematicaAcademica(estudiantesProblematicasAcademicas);
                        }
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

    public void clicCancelar(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }

    public void cargarDatos() throws SQLException {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();

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
    }
    public void cargarCamposGUI() {
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
            this.cbProfesores.setItems(profesores);
            this.cbProfesores.getSelectionModel().select(problematicaAcademica.getIdProfesor()-1);
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
            this.cbExperienciasEducativas.getSelectionModel().select(problematicaAcademica.getIdExperienciaEducativa()-1);
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

            colEstudiante.setCellValueFactory(new PropertyValueFactory("nombre"));
            colPresenta.setCellValueFactory(new PropertyValueFactory("presenta"));

            this.tblEstudiante_Presenta.setItems(estudiantesDelTutorAcademico);
            this.tblEstudiante_Presenta.getSelectionModel().clearSelection();
        }
    }

    public void cargarDatosProblematicaAcademica() throws SQLException {
        tfProblematicaAcademica.setText(problematicaAcademica.getTitulo());
        taDescripcion.setText(problematicaAcademica.getDescripcion());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
