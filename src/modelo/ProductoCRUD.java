/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import modelo.Producto;

/**
 *
 * @author david
 */
public class ProductoCRUD {

    int r;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();
    Producto pro = new Producto();

    public int actualizarStock(int cant, int idp) {
        String sql = "update producto set Stock=? where IdProducto=?";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idp);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return r;
    }

    public Producto listarID(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where IdProducto=?";
        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPre(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return p;
    }

    public List listarProducto() {

        String sql = "SELECT * FROM producto";
        List<Producto> listaprod = new ArrayList<>();

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();

                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPre(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
                listaprod.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return listaprod;
    }

    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into producto(Nombres, Precio, Stock, Estado) Values(?,?,?,?)";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return r;
    }

    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return r;
    }

    public boolean actualizarProducto(Producto pro) {
        System.out.println("IdProducto enviado al método: " + pro.getId());

        String sql = "UPDATE producto SET Nombres=?, Precio=?, Stock=?, Estado=? WHERE IdProducto=?";
        try {
            con = cn.Conecta(); // Verificar que 'cn.Conecta()' devuelve una conexión válida.
            if (con == null) {
                System.out.println("Error: La conexión a la base de datos es nula.");
                return false;
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPre());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(5, pro.getId());

            System.out.println("SQL ejecutada: " + ps.toString()); // Ver el SQL con parámetros.
            int rowsAffected = ps.executeUpdate(); // Ejecutar la actualización y verificar las filas afectadas.

            if (rowsAffected > 0) {
                System.out.println("Producto actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró ningún producto con el ID especificado.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;

    }

    public boolean eliminarProducto(int id) {

        String sql = "DELETE FROM producto where IdProducto=?";

        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public boolean RegistrarProducto(Producto pro) {
        String sql = "insert into producto(Nombres,Precio,Stock,Estado)values(?,?,?,?)";
        try {
            con = cn.Conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPre());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

}
