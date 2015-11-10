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
    private ArrayList<ListaAsignaPermisosPermiso> permisoNivel1;
    

    public ModuloEntity getModulo() {
        return modulo;
    }

    public void setModulo(ModuloEntity modulo) {
        this.modulo = modulo;
    }

    public ArrayList<ListaAsignaPermisosPermiso> getPermisoNivel1() {
        return permisoNivel1;
    }

    public void setPermisoNivel1(ArrayList<ListaAsignaPermisosPermiso> permisoNivel1) {
        this.permisoNivel1 = permisoNivel1;
    }
    
    
}
