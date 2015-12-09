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
@Table(name = "ADM_TUSPR")
public class UsuarioProcesoEntity extends ObjetoTraza implements Serializable {
    @Id
    @Column(name = "USPR_USPR")
    private int idUsuarioProceso;
    @JoinColumn(name = "USPR_USUA")
    @ManyToOne
    private UsuarioEntity idUsuario;
    @JoinColumn(name = "USPR_PROC")
    @ManyToOne
    private ProcesosEntity idProceso;
    @JoinColumn(name = "USPR_ACCI")
    @ManyToOne
    private AccionEntity idAccion;

    public int getIdUsuarioProceso() {
        return idUsuarioProceso;
    }

    public void setIdUsuarioProceso(int idUsuarioProceso) {
        this.idUsuarioProceso = idUsuarioProceso;
    }

    public UsuarioEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ProcesosEntity getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(ProcesosEntity idProceso) {
        this.idProceso = idProceso;
    }

    public AccionEntity getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(AccionEntity idAccion) {
        this.idAccion = idAccion;
    }
    
}
