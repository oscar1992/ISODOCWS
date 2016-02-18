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
 * @author Personal
 */
@Entity
@Table(name = "MJ_TANPL")
public class AnexoPlanesAccionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANPL_ANPL")
    private Integer idTablaAnexo;

    @JoinColumn(name = "ANPL_IDPL")
    @ManyToOne
    private PlanesAccionEntity idPlanesAccion;
    
    @Column(name = "ANPL_RUTA")
    private String ubicacionAnexo;

    public Integer getIdTablaAnexo() {
        return idTablaAnexo;
    }

    public void setIdTablaAnexo(Integer idTablaAnexo) {
        this.idTablaAnexo = idTablaAnexo;
    }

    public PlanesAccionEntity getIdPlanesAccion() {
        return idPlanesAccion;
    }

    public void setIdPlanesAccion(PlanesAccionEntity idPlanesAccion) {
        this.idPlanesAccion = idPlanesAccion;
    }

    public String getUbicacionAnexo() {
        return ubicacionAnexo;
    }

    public void setUbicacionAnexo(String ubicacionAnexo) {
        this.ubicacionAnexo = ubicacionAnexo;
    }
    
    
    

}
