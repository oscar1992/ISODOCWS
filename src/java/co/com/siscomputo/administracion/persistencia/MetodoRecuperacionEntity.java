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
@Table(name = "ADM_TRECU")
public class MetodoRecuperacionEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "RECU_RECU")
    private int idMetodoRecuperacion;
    @Column(name = "RECU_NOMB")
    private String nombreMetodoRecuperacion;
    @Column(name = "RECU_CREA")
    private int creadorMetodoRecuperacion;
    @Column(name = "RECU_UPDA")
    private int actualizadorMetdodoRecuperacion;
    @Column(name = "RECU_ESTA")
    private String estadoMetodoRecuperacion;

    public int getIdMetodoRecuperacion() {
        return idMetodoRecuperacion;
    }

    public void setIdMetodoRecuperacion(int idMetodoRecuperacion) {
        this.idMetodoRecuperacion = idMetodoRecuperacion;
    }

    public String getNombreMetodoRecuperacion() {
        return nombreMetodoRecuperacion;
    }

    public void setNombreMetodoRecuperacion(String nombreMetodoRecuperacion) {
        this.nombreMetodoRecuperacion = nombreMetodoRecuperacion;
    }

    public int getCreadorMetodoRecuperacion() {
        return creadorMetodoRecuperacion;
    }

    public void setCreadorMetodoRecuperacion(int creadorMetodoRecuperacion) {
        this.creadorMetodoRecuperacion = creadorMetodoRecuperacion;
    }

    public int getActualizadorMetdodoRecuperacion() {
        return actualizadorMetdodoRecuperacion;
    }

    public void setActualizadorMetdodoRecuperacion(int actualizadorMetdodoRecuperacion) {
        this.actualizadorMetdodoRecuperacion = actualizadorMetdodoRecuperacion;
    }

    public String getEstadoMetodoRecuperacion() {
        return estadoMetodoRecuperacion;
    }

    public void setEstadoMetodoRecuperacion(String estadoMetodoRecuperacion) {
        this.estadoMetodoRecuperacion = estadoMetodoRecuperacion;
    }
    
    
}
