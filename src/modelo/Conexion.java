/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author david
 */
public class Conexion {

    Connection con;
    String url = "jdbc:mysql://localhost:3306/bd_ventas";
    String usuario = "root";
    String contra = "";

    public Connection Conecta() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, contra);
        } catch (Exception e) {
            System.out.println("Error de conexion" + e);
        }
        return con;
    }
}
