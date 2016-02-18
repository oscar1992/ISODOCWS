/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MJ_TANAC")
public class AnexosAccionesRapidasEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANAC_ANAC")
    private Integer idAnexoAcc;

    @JoinColumn(name = "ANAC_IDAC")
    @ManyToOne
    private AccionesRapidasEntity idAccionR;
    
    
    @Column(name = "ANAC_RUTA")
    private String rutaAnexoAccion;

    public Integer getIdAnexoAcc() {
        return idAnexoAcc;
    }

    public void setIdAnexoAcc(Integer idAnexoAcc) {
        this.idAnexoAcc = idAnexoAcc;
    }

    public AccionesRapidasEntity getIdAccionR() {
        return idAccionR;
    }

    public void setIdAccionR(AccionesRapidasEntity idAccionR) {
        this.idAccionR = idAccionR;
    }

    public String getRutaAnexoAccion() {
        return rutaAnexoAccion;
    }

    public void setRutaAnexoAccion(String rutaAnexoAccion) {
        this.rutaAnexoAccion = rutaAnexoAccion;
    }
    
    
    
}
