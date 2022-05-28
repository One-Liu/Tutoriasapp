package uv.fei.tutorias.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private Connection conexion;

    public Connection abrirConexion() throws SQLException {
        conectar();
        return conexion;
    }

    private void conectar() throws SQLException {
        try {
            FileInputStream archivoConfiguracion = new FileInputStream(new File("src\\main\\java\\uv\\fei\\tutorias\\dataaccess\\dbconfig.txt"));
            Properties atributos = new Properties();
            atributos.load(archivoConfiguracion);
            archivoConfiguracion.close();
            String direccionBD = atributos.getProperty("DireccionBD");
            String usuario = atributos.getProperty("Usuario");
            String contrasenia = atributos.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(direccionBD, usuario, contrasenia);
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (IOException e2){
            System.out.println(e2.getMessage());
        }
    }
//TODO aplicar log4j
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}