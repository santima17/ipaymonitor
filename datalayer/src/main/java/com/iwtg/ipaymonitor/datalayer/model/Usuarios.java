package com.iwtg.ipaymonitor.datalayer.model;
// Generated 24/03/2018 02:37:22 AM by Hibernate Tools 4.3.1



/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private Integer idUsuario;
     private String usuario;
     private String pass;
     private String nombre;
     private String apellido;
     private Integer admin;
     private Boolean baja;
     private String motivo;

    public Usuarios() {
    }

	
    public Usuarios(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }
    public Usuarios(String usuario, String pass, String nombre, String apellido, Integer admin, Boolean baja, String motivo) {
       this.usuario = usuario;
       this.pass = pass;
       this.nombre = nombre;
       this.apellido = apellido;
       this.admin = admin;
       this.baja = baja;
       this.motivo = motivo;
    }
   
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Integer getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
    public Boolean getBaja() {
        return this.baja;
    }
    
    public void setBaja(Boolean baja) {
        this.baja = baja;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }




}


