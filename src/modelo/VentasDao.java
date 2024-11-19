/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author david
 */
public class VentasDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public String NroSerieVentas() {
        String serie = "";
        String sql = "select max(NumeroSerie) from ventas";

        try {

            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serie;
    }

    public String IdVentas() {
        String idv = "";
        String sql = "select max(IdVentas) from ventas";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                idv = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return idv;
    }

    public int GuardarVentas(Venta v) {
        int r = 0;
        String sql = "INSERT INTO ventas(IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = cn.Conecta(); // Verifica que esta conexión sea válida
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdVendedor());
            ps.setString(3, v.getSerie());
            ps.setString(4, v.getFecha());
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());

            // Depuración: Imprimir los valores enviados
            System.out.println("Valores enviados a la base de datos:");
            System.out.println("IdCliente: " + v.getIdCliente());
            System.out.println("IdEmpleado: " + v.getIdVendedor());
            System.out.println("NumeroSerie: " + v.getSerie());
            System.out.println("FechaVentas: " + v.getFecha());
            System.out.println("Monto: " + v.getMonto());
            System.out.println("Estado: " + v.getEstado());

            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error en GuardarVentas: " + e.getMessage());
        }

        return r;
        /*
    //Venta venta = new Venta();
    int r=0;
    String sql = "insert into ventas(IdCliente, IdEmpleado, NumeroSerie,FechaVentas,Monto,Estado)values(?,?,?,?,?,?)";
    
    try{
   con=cn.Conecta();
   ps=con.prepareStatement(sql);
   ps.setInt(1, v.getIdCliente());
   ps.setInt(2, v.getIdVendedor());
   ps.setString(3, v.getSerie());
   ps.setString(4, v.getFecha());
   ps.setDouble(5, v.getMonto());
   ps.setString(6, v.getEstado());
   r=ps.executeUpdate();
        
    }catch (Exception e){
    
    }
     
   
    return r;
         */
    }

    public int GuardarDetalleVenta(DetalleVenta dv) {
        int r = 0;
        String sql = "INSERT INTO detalle_ventas(IdVentas,IdProducto,Cantidad,PrecioVenta)values(?,?,?,?)";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPreVenta());
            r = ps.executeUpdate();
            System.out.println("Filas insertadas: " + r);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return r;
    }

}
