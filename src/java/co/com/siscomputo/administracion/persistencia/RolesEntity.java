/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoTraza;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TROLE")
public class RolesEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "ROLE_ROLE")  
    private int id_rol;
    @Column(name = "ROLE_NOMB")
    private String nombre_rol;
    @Column(name = "ROLE_ESTA")
    private String estado_rol;
    @Column(name = "ROLE_CREA")
    private int id_creador;
    @Column(name = "ROLE_UPDA")
    private int id_actualizador;

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getEstado_rol() {
        return estado_rol;
    }

    public void setEstado_rol(String estado_rol) {
        this.estado_rol = estado_rol;
    }

    public int getId_creador() {
        return id_creador;
    }

    public void setId_creador(int id_creador) {
        this.id_creador = id_creador;
    }

    public int getId_actualizador() {
        return id_actualizador;
    }

    public void setId_actualizador(int id_actualizador) {
        this.id_actualizador = id_actualizador;
    }
    

    
}
