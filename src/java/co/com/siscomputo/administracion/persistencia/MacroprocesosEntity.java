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
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TMACR")
public class MacroprocesosEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "MACR_MACR")
    private int idMacroproceso;
    @Column(name = "MACR_NOMB")
    private String nombreMacroproceso;
    @Column(name = "MACR_ESTA")
    private String estadoMacroproceso;
    @Column(name = "MACR_CREA")
    private int creadorMacroproceso;
    @Column(name = "MACR_UPDA")
    private int actualizadorMacroproceso;

    public int getIdMacroproceso() {
        return idMacroproceso;
    }

    public void setIdMacroproceso(int idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
    }

    public String getNombreMacroproceso() {
        return nombreMacroproceso;
    }

    public void setNombreMacroproceso(String nombreMacroproceso) {
        this.nombreMacroproceso = nombreMacroproceso;
    }

    public String getEstadoMacroproceso() {
        return estadoMacroproceso;
    }

    public void setEstadoMacroproceso(String estadoMacroproceso) {
        this.estadoMacroproceso = estadoMacroproceso;
    }

    public int getCreadorMacroproceso() {
        return creadorMacroproceso;
    }

    public void setCreadorMacroproceso(int creadorMacroproceso) {
        this.creadorMacroproceso = creadorMacroproceso;
    }

    public int getActualizadorMacroproceso() {
        return actualizadorMacroproceso;
    }

    public void setActualizadorMacroproceso(int actualizadorMacroproceso) {
        this.actualizadorMacroproceso = actualizadorMacroproceso;
    }
    
    
}
