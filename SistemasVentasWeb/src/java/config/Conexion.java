package config;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private final String base = "bd_ventas";
    private final String user = "root";
    private final String password = "123456";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection con = null;

    public Connection Conexion() { 
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            JOptionPane.showMessageDialog(null, "CONEXION EXITOSA A LA BD");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONECTAR CON LA BASE" + e);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONECTAR CON LA BASE" + ex);
        }
        return con;
    }
}