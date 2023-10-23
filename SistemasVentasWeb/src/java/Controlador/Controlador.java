package Controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Principal":
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
                break;
            case "Producto":
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
                break;
            case "Cliente":
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                break;
            case "Empleado":
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                break;   
            case "NuevaVenta":
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
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
