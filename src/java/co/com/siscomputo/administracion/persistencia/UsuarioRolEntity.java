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
@Table(name = "ADM_TUSRO")
public class UsuarioRolEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "USRO_USRO")
    private int id_usuario_rol;
    @JoinColumn(name = "USRO_USUA")
    @ManyToOne
    private UsuarioEntity usuario;
    @JoinColumn(name = "USRO_ROLE")
    @ManyToOne
    private RolesEntity rol;
    @JoinColumn(name = "USRO_AREA")
    @ManyToOne
    private AreaEntity area;
    
    
    public int getId_usuario_rol() {
        return id_usuario_rol;
    }

    public void setId_usuario_rol(int id_usuario_rol) {
        this.id_usuario_rol = id_usuario_rol;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public RolesEntity getRol() {
        return rol;
    }

    public void setRol(RolesEntity rol) {
        this.rol = rol;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }
    
}
