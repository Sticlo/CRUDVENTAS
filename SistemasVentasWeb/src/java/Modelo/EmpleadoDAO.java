
package Modelo;

import java.sql.*;
import config.Conexion;

public class EmpleadoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Empleado validar (String user,String dni){
        Empleado em=new Empleado();
        String sql="select * from empleado where user=? and dni=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs=ps.executeQuery();
            while(rs.next()){
                //em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("user"));
                em.setCedula(rs.getString("dni"));
                em.setNom(rs.getString("Nombres"));
            }
        }catch (Exception e){
        }
        return em;
    }
}
