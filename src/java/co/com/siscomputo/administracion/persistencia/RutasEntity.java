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
@Table(name = "ADM_TRUTA")
public class RutasEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "RUTA_RUTA")
    private int idRutas;
    @Column(name = "RUTA_TIPO")
    private String tipoRutas;
    @Column(name = "RUTA_CARP")
    private String carpetaRutas;

    public int getIdRutas() {
        return idRutas;
    }

    public void setIdRutas(int idRutas) {
        this.idRutas = idRutas;
    }

    public String getTipoRutas() {
        return tipoRutas;
    }

    public void setTipoRutas(String tipoRutas) {
        this.tipoRutas = tipoRutas;
    }

    public String getCarpetaRutas() {
        return carpetaRutas;
    }

    public void setCarpetaRutas(String carpetaRutas) {
        this.carpetaRutas = carpetaRutas;
    }
    
    
}
