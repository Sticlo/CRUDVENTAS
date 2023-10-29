package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import jakarta.servlet.http.HttpSession;
import javax.swing.JOptionPane;


public class Validar extends HttpServlet {

    EmpleadoDAO edao = new EmpleadoDAO();
    Empleado em = new Empleado();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    private String asegurarClave(String textoclaro) {
        String clavesha = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(textoclaro.getBytes());
            clavesha = Base64.getEncoder().encodeToString(sha256.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR EN LA ENCRIPTACION: " + e.getMessage());
        }
        return clavesha;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            request.setAttribute("clave_normal", pass);
            String ClaveEncriptada = asegurarClave(pass);

            System.out.println("CLAVE ENCRIPTADA: " + ClaveEncriptada);

            em = edao.validar(user, ClaveEncriptada);
            if (em.getUser() != null) {
                request.setAttribute("usuario", em);
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", em);
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response); // Redirección después de la autenticación
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            HttpSession session = request.getSession(false);
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
