/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriasapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Tutoriasapp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btnGetPersonById = new Button();
        btnGetPersonById.setText("Get Person By Id");
        btnGetPersonById.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PersonaDAO personaDao = new PersonaDAO();
                Persona persona = personaDao.findPersonaById(1);
                System.out.println(String.format("Person found: %s %s %s %d",persona.getApellidoPaterno(), persona.getNombre(), persona.getIdPersona()));
            }
        });
        
        Button btnGetTutorAcademicoById = new Button();
        btnGetTutorAcademicoById.setText("Get Tutor Academico By Id");
        btnGetTutorAcademicoById.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        Button btnGetTutorAcademicoByName = new Button();
        btnGetTutorAcademicoByName.setText("Get Tutor Academico By Name");
        btnGetTutorAcademicoByName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        Button btnAddTutorAcademico = new Button();
        btnAddTutorAcademico.setText("Add Tutor Academico");
        btnAddTutorAcademico.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
                Persona tutorAcademico = new Persona("TUTOR","DE","PRUEBA1","123456","tutor1@uv.mx");
                if(tutorAcademicoDao.addTutorAcademico(tutorAcademico)) {
                    System.out.println("Tutor registrado");
                } else {
                    System.out.println("No se ha registrado el tutor");
                }
            }
        });
        
        Button btnDeleteTutorAcademico = new Button();
        btnDeleteTutorAcademico.setText("Delete Tutor Academico");
        btnDeleteTutorAcademico.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
                if(tutorAcademicoDao.deleteTutorAcademicoById(3)) {
                    System.out.println("Tutor Academico borrado");
                } else {
                    System.out.println("No se ha borrado/encontrado el tutor");
                }
            }
        });
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().add(btnGetPersonById);
        root.getChildren().add(btnGetTutorAcademicoById);
        root.getChildren().add(btnGetTutorAcademicoByName);
        root.getChildren().add(btnAddTutorAcademico);
        root.getChildren().add(btnDeleteTutorAcademico);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Sistema de Tutorias Academicas");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    
    // @param args the command line arguments
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
