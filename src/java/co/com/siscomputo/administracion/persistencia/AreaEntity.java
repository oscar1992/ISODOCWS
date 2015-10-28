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
@Table(name = "ADM_TAREA")
public class AreaEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "AREA_AREA")
    private int idArea;
    @Column(name = "AREA_NOMB")
    private String nombreArea;
    @Column(name = "AREA_CODI")
    private int codigoArea;
    @Column(name = "AREA_ESTA")
    private String estadoArea;
    @Column(name = "AREA_CREA")
    private int creadorArea;
    @Column(name = "AREA_UPDA")
    private int actualizadorArea;
    @JoinColumn(name = "AREA_SEDE")
    @ManyToOne
    private SedeEntity idSede;
    
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public int getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getEstadoArea() {
        return estadoArea;
    }

    public void setEstadoArea(String estadoArea) {
        this.estadoArea = estadoArea;
    }

    public int getCreadorArea() {
        return creadorArea;
    }

    public void setCreadorArea(int creadorArea) {
        this.creadorArea = creadorArea;
    }

    public int getActualizadorArea() {
        return actualizadorArea;
    }

    public void setActualizadorArea(int actualizadorArea) {
        this.actualizadorArea = actualizadorArea;
    }

    public SedeEntity getIdSede() {
        return idSede;
    }

    public void setIdSede(SedeEntity idSede) {
        this.idSede = idSede;
    }
    
    
}
