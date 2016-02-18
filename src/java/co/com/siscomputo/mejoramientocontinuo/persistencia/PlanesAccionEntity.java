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
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name = "MJ_TPLAN")
public class PlanesAccionEntity extends ObjetoRetornaEntity implements Serializable{
    
    @Id
    @Column(name = "PLAN_PLAN")
    private Integer idPlanesAccion;
    
    @Column(name = "PLAN_SOLI")
    private String solicitantePlanAccion;

    @Column(name = "PLAN_ESTA")
    private String estadoPlanAccion;
    
    @Column(name = "PLAN_ORIG")
    private String origenTipoHallazgo;
    
    @Column(name = "PLAN_TIPA")
    private String tipoDeAccion;
    
    @Column(name = "PLAN_TIPH")
    private String tipoDeHallazgo;
    
    @Column(name = "PLAN_MAG")
    private String magnitudPlanAccion;
    
    @Column(name = "PLAN_TIPN")
    private String tipoNorma;
    
    @Column(name = "PLAN_PRO")
    private String procesoPlanAccion;
    
    @Column(name = "PLAN_UNI")
    private String unidadDeNegocio;
    
    @Column(name = "PLAN_EMPR")
    private String empresaPlanAccion;
    
    @Column(name = "PLAN_DESC")
    private String descripcionPlanAccion;
    
    @Column(name = "PLAN_RUTA")
    private String rutaArchivoPlanAccion;

    public Integer getIdPlanesAccion() {
        return idPlanesAccion;
    }

    public void setIdPlanesAccion(Integer idPlanesAccion) {
        this.idPlanesAccion = idPlanesAccion;
    }

    public String getSolicitantePlanAccion() {
        return solicitantePlanAccion;
    }

    public void setSolicitantePlanAccion(String solicitantePlanAccion) {
        this.solicitantePlanAccion = solicitantePlanAccion;
    }

    public String getEstadoPlanAccion() {
        return estadoPlanAccion;
    }

    public void setEstadoPlanAccion(String estadoPlanAccion) {
        this.estadoPlanAccion = estadoPlanAccion;
    }

    public String getOrigenTipoHallazgo() {
        return origenTipoHallazgo;
    }

    public void setOrigenTipoHallazgo(String origenTipoHallazgo) {
        this.origenTipoHallazgo = origenTipoHallazgo;
    }

    public String getTipoDeAccion() {
        return tipoDeAccion;
    }

    public void setTipoDeAccion(String tipoDeAccion) {
        this.tipoDeAccion = tipoDeAccion;
    }

    public String getTipoDeHallazgo() {
        return tipoDeHallazgo;
    }

    public void setTipoDeHallazgo(String tipoDeHallazgo) {
        this.tipoDeHallazgo = tipoDeHallazgo;
    }

    public String getMagnitudPlanAccion() {
        return magnitudPlanAccion;
    }

    public void setMagnitudPlanAccion(String magnitudPlanAccion) {
        this.magnitudPlanAccion = magnitudPlanAccion;
    }

    public String getTipoNorma() {
        return tipoNorma;
    }

    public void setTipoNorma(String tipoNorma) {
        this.tipoNorma = tipoNorma;
    }

    public String getProcesoPlanAccion() {
        return procesoPlanAccion;
    }

    public void setProcesoPlanAccion(String procesoPlanAccion) {
        this.procesoPlanAccion = procesoPlanAccion;
    }

    public String getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(String unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public String getEmpresaPlanAccion() {
        return empresaPlanAccion;
    }

    public void setEmpresaPlanAccion(String empresaPlanAccion) {
        this.empresaPlanAccion = empresaPlanAccion;
    }

    public String getDescripcionPlanAccion() {
        return descripcionPlanAccion;
    }

    public void setDescripcionPlanAccion(String descripcionPlanAccion) {
        this.descripcionPlanAccion = descripcionPlanAccion;
    }

    public String getRutaArchivoPlanAccion() {
        return rutaArchivoPlanAccion;
    }

    public void setRutaArchivoPlanAccion(String rutaArchivoPlanAccion) {
        this.rutaArchivoPlanAccion = rutaArchivoPlanAccion;
    }
    
            
    
}
