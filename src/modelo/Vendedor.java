/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author david
 */
public class Vendedor {

    PreparedStatement ps;
    ResultSet rs;

    Conexion con = new Conexion();
    Connection acceso;

    public EntidadVendedor ValidarVendedor(String dni, String user) {
        EntidadVendedor ev = new EntidadVendedor();
        String sql = "select * from empleado where Dni=? and User=?";
        try {
            acceso = con.Conecta();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, user);
            rs = ps.executeQuery();

            while (rs.next()) {
                ev.setId(rs.getInt(1));
                ev.setDni(rs.getString(2));
                ev.setNom(rs.getString(3));
                ev.setTel(rs.getString(4));
                ev.setEstado(rs.getString(5));
                ev.setUser(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ev;

    }
}
