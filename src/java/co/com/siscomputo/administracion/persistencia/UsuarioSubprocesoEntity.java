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
@Table(name = "ADM_TUSSU")
public class UsuarioSubprocesoEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "USSU_USSU")
    private int idUsuarioSubproceso;
    @JoinColumn(name = "USSU_USUA")
    @ManyToOne
    private UsuarioEntity idusuario;
    @JoinColumn(name = "USSU_SUBP")
    @ManyToOne
    private SubprocesoEntity idSubprocesoEntity;

    public int getIdUsuarioSubproceso() {
        return idUsuarioSubproceso;
    }

    public void setIdUsuarioSubproceso(int idUsuarioSubproceso) {
        this.idUsuarioSubproceso = idUsuarioSubproceso;
    }

    public UsuarioEntity getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(UsuarioEntity idusuario) {
        this.idusuario = idusuario;
    }

    public SubprocesoEntity getIdSubprocesoEntity() {
        return idSubprocesoEntity;
    }

    public void setIdSubprocesoEntity(SubprocesoEntity idSubprocesoEntity) {
        this.idSubprocesoEntity = idSubprocesoEntity;
    }
    
}
