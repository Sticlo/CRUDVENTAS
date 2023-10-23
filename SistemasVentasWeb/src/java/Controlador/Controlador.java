package Controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("menu");
        String accion = request.getParameter("accion");
        if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if(menu.equals("Empleado")){
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if(menu.equals("Cliente")){
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if(menu.equals("Producto")){
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if(menu.equals("NuevaVenta")){
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
