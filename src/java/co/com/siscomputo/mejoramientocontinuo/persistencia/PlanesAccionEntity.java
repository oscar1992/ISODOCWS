/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.mejoramientocontinuo.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;
import co.com.siscomputo.administracion.persistencia.OrigenEntity;
import co.com.siscomputo.administracion.persistencia.UnidadDeNegocioEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.glassfish.gmbal.ManagedAttribute;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "MJ_TPLAN")
public class PlanesAccionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "PLAN_PLAN")
    private Integer idPlanesAccion;

    @Column(name = "PLAN_SOLI")
    private String solicitantePlanAccion;

    @Column(name = "PLAN_ESTA")
    private String estadoPlanAccion;

    @JoinColumn(name = "PLAN_ORIG")
    @ManyToOne
    private OrigenEntity origen;

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

    @JoinColumn(name = "PLAN_UNI")
    @ManyToOne
    private UnidadDeNegocioEntity unidadDeNegocio;

    @JoinColumn(name = "PLAN_EMPR")
    @ManyToOne
    private EmpresaEntity empresaPlanAccion;

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

    public OrigenEntity getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEntity origen) {
        this.origen = origen;
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

    public UnidadDeNegocioEntity getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(UnidadDeNegocioEntity unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    public EmpresaEntity getEmpresaPlanAccion() {
        return empresaPlanAccion;
    }

    public void setEmpresaPlanAccion(EmpresaEntity empresaPlanAccion) {
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
