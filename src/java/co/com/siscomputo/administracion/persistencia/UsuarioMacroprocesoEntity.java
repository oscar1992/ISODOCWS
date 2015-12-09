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
@Table(name = "ADM_TUSMA")
public class UsuarioMacroprocesoEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "USMA_USMA")
    private int idUsuarioMacroproceso;
    @JoinColumn(name = "USMA_MACR")
    @ManyToOne
    private MacroprocesosEntity idMacroProceso;
    @JoinColumn(name = "USMA_USUA")
    @ManyToOne
    private UsuarioEntity idUsuario;
    @JoinColumn(name = "USMA_ACCI")
    @ManyToOne
    private AccionEntity idAccion;

    public int getIdUsuarioMacroproceso() {
        return idUsuarioMacroproceso;
    }

    public void setIdUsuarioMacroproceso(int idUsuarioMacroproceso) {
        this.idUsuarioMacroproceso = idUsuarioMacroproceso;
    }

    public MacroprocesosEntity getIdMacroProceso() {
        return idMacroProceso;
    }

    public void setIdMacroProceso(MacroprocesosEntity idMacroProceso) {
        this.idMacroProceso = idMacroProceso;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public AccionEntity getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(AccionEntity idAccion) {
        this.idAccion = idAccion;
    }
    
}
