/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
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
@Table(name = "ADM_TDISP")
public class DisposicionesEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "DISP_DISP")
    private int idDisposirciones;
    @Column(name = "DISP_NOMB")
    private String nombreDisposiciones;
    @Column(name = "DISP_CREA")
    private int creadorDisposiciones;
    @Column(name = "DISP_UPDA")
    private int actualizadorDispocisiones;
    @Column(name = "DISP_ESTA")
    private String estadoDisposiciones;

    public int getIdDisposirciones() {
        return idDisposirciones;
    }

    public void setIdDisposirciones(int idDisposirciones) {
        this.idDisposirciones = idDisposirciones;
    }

    public String getNombreDisposiciones() {
        return nombreDisposiciones;
    }

    public void setNombreDisposiciones(String nombreDisposiciones) {
        this.nombreDisposiciones = nombreDisposiciones;
    }

    public int getCreadorDisposiciones() {
        return creadorDisposiciones;
    }

    public void setCreadorDisposiciones(int creadorDisposiciones) {
        this.creadorDisposiciones = creadorDisposiciones;
    }

    public int getActualizadorDispocisiones() {
        return actualizadorDispocisiones;
    }

    public void setActualizadorDispocisiones(int actualizadorDispocisiones) {
        this.actualizadorDispocisiones = actualizadorDispocisiones;
    }

    public String getEstadoDisposiciones() {
        return estadoDisposiciones;
    }

    public void setEstadoDisposiciones(String estadoDisposiciones) {
        this.estadoDisposiciones = estadoDisposiciones;
    }
    
    
}
