/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.entites;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class MenuPermisosEntity {
    private int id_permiso;
    private String nombre_permiso;
    private ArrayList<MenuPermisosEntity> subNivel;
    private String estado;
    private int id_creador;
    private int id_actualizador;
    private String ruta;
    private int asociadoMenu;
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

    public ArrayList<MenuPermisosEntity> getSubNivel() {
        return subNivel;
    }

    public void setSubNivel(ArrayList<MenuPermisosEntity> subNivel) {
        this.subNivel = subNivel;
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
