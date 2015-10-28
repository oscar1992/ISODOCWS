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
@Table(name = "ADM_TROPE")
public class RolPermisoEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "TROPE_TROPE")
    private int id_rol_permiso;
    @JoinColumn(name = "TROPE_TPERM")
    @ManyToOne
    private ModuloEntity id_permiso;
    @JoinColumn(name = "TROPE_TROLE")
    @ManyToOne
    private RolesEntity id_rol;

    public RolPermisoEntity() {
    }
    
    public int getId_rol_permiso() {
        return id_rol_permiso;
    }

    public void setId_rol_permiso(int id_rol_permiso) {
        this.id_rol_permiso = id_rol_permiso;
    }

    public ModuloEntity getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(ModuloEntity id_permiso) {
        this.id_permiso = id_permiso;
    }

    public RolesEntity getId_rol() {
        return id_rol;
    }

    public void setId_rol(RolesEntity id_rol) {
        this.id_rol = id_rol;
    }
}
