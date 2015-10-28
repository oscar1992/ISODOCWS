/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.entites;

import co.com.siscomputo.administracion.persistencia.ModuloEntity;
import co.com.siscomputo.administracion.persistencia.PermisosEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ListaAsignaPermisosModulo extends ObjetoTraza implements Serializable{
    private ModuloEntity modulo;
    private ArrayList<PermisosEntity> permisoNivel1;
    private ArrayList<PermisosEntity> permisoNivel2;
    private ArrayList<PermisosEntity> permisoNivel3;

    public ModuloEntity getModulo() {
        return modulo;
    }

    public void setModulo(ModuloEntity modulo) {
        this.modulo = modulo;
    }

    public ArrayList<PermisosEntity> getPermisoNivel1() {
        return permisoNivel1;
    }

    public void setPermisoNivel1(ArrayList<PermisosEntity> permisoNivel1) {
        this.permisoNivel1 = permisoNivel1;
    }

    public ArrayList<PermisosEntity> getPermisoNivel2() {
        return permisoNivel2;
    }

    public void setPermisoNivel2(ArrayList<PermisosEntity> permisoNivel2) {
        this.permisoNivel2 = permisoNivel2;
    }

    public ArrayList<PermisosEntity> getPermisoNivel3() {
        return permisoNivel3;
    }

    public void setPermisoNivel3(ArrayList<PermisosEntity> permisoNivel3) {
        this.permisoNivel3 = permisoNivel3;
    }
    
    
}
