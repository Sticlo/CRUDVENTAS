package Controlador;

import Modelo.*;
import com.mysql.cj.protocol.Message;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.JOptionPane;

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
    
    Venta v=new Venta();
    List<Venta>lista=new ArrayList<>();
    int item=0;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroserie;
    VentaDAO vdao=new VentaDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            
            if(menu.equals("Clientes")){
                switch(accion){
                    case "Listar":
                        List lista=cdao.listar();
                        request.setAttribute("clientes",lista);
                        break;
                    case "Agregar":
                        String dni=request.getParameter("txtDnicliente");
                        String nom=request.getParameter("txtNombrescliente");
                        String dir=request.getParameter("txtDireccioncliente");
                        String est=request.getParameter("txtEstadocliene");
                        cl.setDni(dni);
                        cl.setNom(nom);
                        cl.setDireccion(dir);
                        cl.setEstado(est);
                        cdao.agregar(cl);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,response);
                        break;
                    case "Editar":
                        ide=Integer.parseInt(request.getParameter("id"));
                       // Cliente c=cdao.listarId(ide);    
                       // request.setAttribute("cliente",c);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,response);
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
                        String Contrasena=asegurarClave(request.getParameter("txtContrasena"));
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        em.setContrasena(Contrasena);
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
            request.getRequestDispatcher("empleado.jsp").forward(request, response);
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
                    case "BuscarProducto":
                        int id=Integer.parseInt(request.getParameter("codigoproducto"));
                        p=pdao.listarId(id);
                        request.setAttribute("c", cl);
                        request.setAttribute("producto", p);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalpagar", totalPagar);
                        break;
                    case "Agregar":
                        request.setAttribute("c", cl);
                        totalPagar=0.0;
                        item++;
                        cod=p.getId();
                        descripcion=request.getParameter("nomproducto");
                        precio=Double.parseDouble(request.getParameter("precio"));
                        cant=Integer.parseInt(request.getParameter("cant"));
                        subtotal=precio*cant;
                        v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        lista.add(v);
                        for(int i=0;i<lista.size();i++){
                            totalPagar+=lista.get(i).getSubtotal();
                        }
                        request.setAttribute("totalpagar", totalPagar);
                        request.setAttribute("lista", lista);
                        break;
                    case "GenerarVenta" :
                        for(int i=0;i<lista.size();i++){
                            Producto pr=new Producto();
                            int cantidad=lista.get(i).getCantidad();
                            int idproducto=lista.get(i).getIdproducto();
                            ProductoDAO ao=new ProductoDAO();
                            pr=ao.buscar(idproducto);
                            int sac=pr.getStock()-cantidad;
                            ao.Actualizarstock(idproducto, sac);
                        }
                        
                        v.setIdcliente(Integer.parseInt(cl.getId()));
                        v.setIdempleado(2);
                        v.setNumserie(numeroserie);
                        v.setFecha("2023-10-23");
                        v.setMonto(totalPagar);
                        v.setEstado("1");
                        vdao.guardarVenta(v);
                        //Guardar Detalle ventas
                        int idv=Integer.parseInt(vdao.IdVentas());
                        for(int i=0;i<lista.size();i++){
                            v=new Venta();
                            v.setId(idv);
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetalleventas(v);
                        }
                        
                        break;
                        
                    default:
                        numeroserie=vdao.GenerarSerie();
                        if(numeroserie==null){
                            numeroserie="00000001";
                            request.setAttribute("nserie", numeroserie);  
                        }else{
                            int incrementar=Integer.parseInt(numeroserie);
                            GenerarSerie gs=new GenerarSerie();
                            numeroserie=gs.NumeroSerie(incrementar);
                            request.setAttribute("nserie", numeroserie);
                        }
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                
            }
    }
       
    private String asegurarClave(String textoclaro){
        String clavesha="";
        try{
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(textoclaro.getBytes());
            clavesha=Base64.getEncoder().encodeToString(sha256.digest());        
        }catch(NoSuchAlgorithmException e){
            System.out.println("ERROR EN LA ENCRIPTACION: "+e.getMessage());
        }
        return clavesha;
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