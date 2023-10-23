/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Controlador extends HttpServlet {

    Empleado em=new Empleado();
    EmpleadoDAO edao=new EmpleadoDAO();
    Cliente cl=new Cliente();
    ClienteDAO cdao=new ClienteDAO();
    Producto p=new Producto();
    ProductoDAO pdao=new ProductoDAO();
    int ide;
    int idc;
    int idp;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            if(menu.equals("Clientes")){
                switch(accion){
                    case "Listar":
                        break;
                    case "Agregar":
                        break;
                    case "Editar":
                        break;
                    case "Delete":
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            }
            if(menu.equals("Principal")){
                 switch(accion){
                    case "Listar":
                        break;
                    case "Agregar":
                        break;
                    case "Editar":
                        break;
                    case "Delete":
                        break;
                    default:
                }
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if(menu.equals("Empleado")){
                 switch(accion){
                    case "Listar":
                        List lista=edao.listar();
                        request.setAttribute("empleados",lista);
                        break;
                    case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNombres");
                        String tel=request.getParameter("txtTel");
                        String est=request.getParameter("txtEstado");
                        String user=request.getParameter("txtUsuario");
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        edao.agregar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request,response);
                        break;
                    case "Editar":
                        ide=Integer.parseInt(request.getParameter("id"));
                        Empleado e=edao.listarId(ide);    
                        request.setAttribute("empleado",e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request,response); 
                        break;
                      case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNombres");
                        String tel1=request.getParameter("txtTel");
                        String est1=request.getParameter("txtEstado");
                        String user1=request.getParameter("txtUsuario");
                        em.setDni(dni1);
                        em.setNom(nom1);
                        em.setTel(tel1);
                        em.setEstado(est1);
                        em.setUser(user1);
                        em.setId(ide);
                        edao.actualizar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request,response); 
                        break;
                    case "Delete":
                        ide=Integer.parseInt(request.getParameter("id"));
                        edao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request,response);
                        break;
                    default:
                        throw new AssertionError();
                }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
            if(menu.equals("Producto")){
                 switch(accion){
                    case "Listar":
                        break;
                    case "Agregar":
                        break;
                    case "Editar":
                        break;
                    case "Delete":
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if(menu.equals("NuevaVenta")){
                switch (accion) {
                    case "BuscarCliente":
                        String dni=request.getParameter("codigocliente");
                        cl.setDni(dni);
                        cl=cdao.buscar(dni);
                        request.setAttribute("c", cl);
                        break;
                    default:
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}