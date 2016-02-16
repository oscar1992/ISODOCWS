/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoTraza;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TUSUA")

public class UsuarioEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "USUA_USUA ")
    private int idUsuario;
    @Column(name = "USUA_NOMB ")   
    private String nombre;
    @Column(name = "USUA_PASS ")    
    private String clave;    
    @Column(name = "USUA_CREA ")
    private int idCreador;
    @Column(name = "USUA_UPDA ")
    private int idActualizador;
    @Column(name = "USUA_ESTA ")
    private String estado;
    @Column(name = "USUA_FUIN ")
    private Date ultimoIngreso;
    @Column(name = "USUA_APELL")
    private String apellido;
    @Column(name = "USUA_NUSU")
    private String nombreUsuario;
    @Column(name = "USUA_EMAI")
    private String email;
    @Column(name = "USUA_TELE")
    private String telefono;
    @Column(name = "USUA_NIDE")
    private String numeroIdentificacion;

    public UsuarioEntity() {
        
    }
    

    public UsuarioEntity(int id_usuario, String nombre, String clave, int area, int id_creador, int id_actualizador, String estado, Date ultimo_ingreso, String apellido, String nombreUsuario, String email, String telefono, String numeroIdentificacion) {
        this.idUsuario = id_usuario;
        this.nombre = nombre;
        this.clave = clave;
        
        this.idCreador = id_creador;
        this.idActualizador = id_actualizador;
        this.estado = estado;
        this.ultimoIngreso = ultimo_ingreso;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.telefono = telefono;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public int getIdActualizador() {
        return idActualizador;
    }

    public void setIdActualizador(int idActualizador) {
        this.idActualizador = idActualizador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

  
    
      
    
    

}