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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TPERM")
public class PermisosEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "PERM_PERM")
    private int id_permiso;
    @Column(name = "PERM_NOMB")
    private String nombre_permiso;
    @JoinColumn(name = "PERM_MODU")
    @ManyToOne
    private ModuloEntity id_modulo;
    @Column(name = "PERM_ESTA")
    private String estado;
    @Column(name = "PERM_CREA")
    private int id_creador;
    @Column(name = "PERM_UPDA")
    private int id_actualizador;
    @Column(name = "PERM_RUTA")
    private String ruta;
    @Column(name = "PERM_ASOC")
    private int asociadoMenu;
    @Column(name = "PERM_NIVE")
    private int asociadoNivel;
    
    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getNombre_permiso() {
        return nombre_permiso;
    }

    public void setNombre_permiso(String nombre_permiso) {
        this.nombre_permiso = nombre_permiso;
    }

    public ModuloEntity getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(ModuloEntity id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getAsociadoMenu() {
        return asociadoMenu;
    }

    public void setAsociadoMenu(int asociadoMenu) {
        this.asociadoMenu = asociadoMenu;
    }

    public int getAsociadoNivel() {
        return asociadoNivel;
    }

    public void setAsociadoNivel(int asociadoNivel) {
        this.asociadoNivel = asociadoNivel;
    }
}
