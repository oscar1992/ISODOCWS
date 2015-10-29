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
@Table(name = "ADM_TPROC")
public class ProcesosEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "PROC_PROC")
    private int idProcesos;
    @Column(name = "PROC_NOMB")
    private String nombrePreoceso;
    @Column(name = "PROC_ESTA")
    private String estadoProceso;
    @JoinColumn(name = "PROC_MACR")
    @ManyToOne
    private MacroprocesosEntity idMacroProceso;
    @Column(name = "PROC_CREA")
    private int creadorProceso;
    @Column(name = "PROC_UPDA")
    private int actualizadorProceso;

    public int getIdProcesos() {
        return idProcesos;
    }

    public void setIdProcesos(int idProcesos) {
        this.idProcesos = idProcesos;
    }

    public String getNombrePreoceso() {
        return nombrePreoceso;
    }

    public void setNombrePreoceso(String nombrePreoceso) {
        this.nombrePreoceso = nombrePreoceso;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public MacroprocesosEntity getIdMacroProceso() {
        return idMacroProceso;
    }

    public void setIdMacroProceso(MacroprocesosEntity idMacroProceso) {
        this.idMacroProceso = idMacroProceso;
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
