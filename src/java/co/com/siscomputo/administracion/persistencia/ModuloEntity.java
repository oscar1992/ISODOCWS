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
@Table(name = "ADM_TMODU") 
public class ModuloEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "MODU_MODU")
    private int id_modulo;
    @Column(name = "MODU_NOMB")
    private String nombre;
    @Column(name = "MODU_RUTA")
    private String ruta_modulo;
    @Column(name = "MODU_ICON")
    private String ruta_icono;
    @Column(name = "MODU_CREA")
    private int id_creador;
    @Column(name = "MODU_UPDA")
    private int id_actualizador;
    @Column(name = "MODU_ESTADO")
    private String estado;
    @Column(name = "MODU_ORDE")
    private int orden;

    public ModuloEntity() {
    }

    public ModuloEntity(int id_modulo, String nombre, String ruta_modulo, String ruta_icono, int id_creador, int id_actualizador, String estado, int orden) {
        this.id_modulo = id_modulo;
        this.nombre = nombre;
        this.ruta_modulo = ruta_modulo;
        this.ruta_icono = ruta_icono;
        this.id_creador = id_creador;
        this.id_actualizador = id_actualizador;
        this.estado = estado;
        this.orden = orden;
    }
    
    
    public int getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta_modulo() {
        return ruta_modulo;
    }

    public void setRuta_modulo(String ruta_modulo) {
        this.ruta_modulo = ruta_modulo;
    }

    public String getRuta_icono() {
        return ruta_icono;
    }

    public void setRuta_icono(String ruta_icono) {
        this.ruta_icono = ruta_icono;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
