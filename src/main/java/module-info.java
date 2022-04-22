module uv.fei.tutorias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.testng;
    requires log4j;


    exports uv.fei.tutorias.bussinesslogic to test.uv.fei.tutorias;
    exports uv.fei.tutorias.dataaccess to  test.uv.fei.tutorias;
    exports uv.fei.tutorias.domain to  test.uv.fei.tutorias;
    opens uv.fei.tutorias.bussinesslogic;
    opens uv.fei.tutorias.main to javafx.fxml;
    exports uv.fei.tutorias.main;

}