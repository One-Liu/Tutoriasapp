module uv.fei.tutorias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.testng;
    requires slf4j.simple;


    exports uv.fei.tutorias.test to junit;
    exports uv.fei.tutorias.bussinesslogic to org.slf4j;
    opens uv.fei.tutorias.bussinesslogic;
    opens uv.fei.tutorias.main to javafx.fxml;
    exports uv.fei.tutorias.main;
}