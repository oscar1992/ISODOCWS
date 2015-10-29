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
@Table(name = "ADM_TSUBP")
public class SubprocesoEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "SUBP_SUBP")
    private int idSubproceso;
    @Column(name = "SUBP_NOMB")
    private String nombreSubproceso;
    @Column(name = "SUBP_ESTA")
    private String estadoSubproceso;
    @JoinColumn(name = "SUBP_PROC")
    @ManyToOne
    private ProcesosEntity idProcesos;
    @Column(name = "SUBP_CREA")
    private int creadorProceso;
    @Column(name = "SUBP_UPDA")
    private int actualizadorProceso;

    public int getIdSubproceso() {
        return idSubproceso;
    }

    public void setIdSubproceso(int idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public String getNombreSubproceso() {
        return nombreSubproceso;
    }

    public void setNombreSubproceso(String nombreSubproceso) {
        this.nombreSubproceso = nombreSubproceso;
    }

    public String getEstadoSubproceso() {
        return estadoSubproceso;
    }

    public void setEstadoSubproceso(String estadoSubproceso) {
        this.estadoSubproceso = estadoSubproceso;
    }

    public ProcesosEntity getIdProcesos() {
        return idProcesos;
    }

    public void setIdProcesos(ProcesosEntity idProcesos) {
        this.idProcesos = idProcesos;
    }

    public int getCreadorProceso() {
        return creadorProceso;
    }

    public void setCreadorProceso(int creadorProceso) {
        this.creadorProceso = creadorProceso;
    }

    public int getActualizadorProceso() {
        return actualizadorProceso;
    }

    public void setActualizadorProceso(int actualizadorProceso) {
        this.actualizadorProceso = actualizadorProceso;
    }
        
}
