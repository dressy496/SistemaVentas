package modelo;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();  // Crea una instancia de tu clase de conexión
        Connection con = conexion.Conecta(); // Llama al método Conecta() para establecer la conexión

        if (con != null) {
            System.out.println("Conexión exitosa a la base de datos");
        } else {
            System.out.println("Fallo en la conexión a la base de datos");
        }
    }
}
