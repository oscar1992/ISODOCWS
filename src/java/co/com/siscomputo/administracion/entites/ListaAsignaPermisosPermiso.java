/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.entites;

import co.com.siscomputo.administracion.persistencia.PermisosEntity;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ListaAsignaPermisosPermiso {
    private PermisosEntity permiso;
    private ArrayList<PermisosEntity> listaS;

    public PermisosEntity getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisosEntity permiso) {
        this.permiso = permiso;
    }

    public ArrayList<PermisosEntity> getListaS() {
        return listaS;
    }

    public void setListaS(ArrayList<PermisosEntity> listaS) {
        this.listaS = listaS;
    }
    
    
}
