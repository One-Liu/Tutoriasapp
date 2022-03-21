package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.Persona;

import java.util.ArrayList;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Get person");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                PersonaDAO personaDAO = new PersonaDAO();

                Persona persona = new Persona("dasd","dasd","Madasdsadcho",19,"1212","dasdsad@ESTUDINTES.UV.MX");
                Persona persona1 = new Persona("Fer","Gutierrez","Hernandez",19,"2123","fdsfsdf@sdasd.com");

//                tutorAcademicoDAO.addTutorAcademico(persona);
//                personaDAO.addPersona(persona);

                personaDAO.deletePersonById(25);

                ArrayList<Persona> personas= (ArrayList<Persona>) personaDAO.findPersonsByName("Paulo");
                for(Persona persona2 : personas) {
                    System.out.println(String.format("Person found: %s %s", persona2.getApellidoPaterno(), persona2.getNombre()));
                }

            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
