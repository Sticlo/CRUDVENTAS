package Modelo;

/**
 *
 * @author PC
 */
public class Cliente {
    String id;
    String dni;
    String nom;
    String direccion;
    String estado;
    
    public Cliente(){
    }

    public Cliente(String id, String dni, String nom, String direccion, String estado) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}