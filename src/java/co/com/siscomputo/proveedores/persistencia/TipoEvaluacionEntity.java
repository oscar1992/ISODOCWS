/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.proveedores.persistencia;

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
@Table(name = "PRO_TEVAL")
public class TipoEvaluacionEntity extends ObjetoRetornaEntity implements Serializable{
    
    @Id
    @Column(name = "EVAL_EVAL")
    private Integer idTipoEvaluacion;
    
    @Column(name = "EVAL_NOMB")
    private String nombreTipoEvaluacion;
    
    @Column(name = "EVAL_TIPO")
    private String tipoEvaluacion;
    
    @Column(name = "EVAL_PERI")
    private String periocidadEvaluacion;
    
    @Column(name = "EVAL_RANGOBAJO")
    private Integer rangoBajoEvaluacion;
    
    @Column(name = "EVAL_RANGOMEDIO")
    private Integer rangoMedioEvaluacion;
    
    @Column(name = "EVAL_RANGOALTO")
    private Integer rangoAltoEvaluacion;
    
    @Column(name = "EVAL_METAINFER")
    private String evaluacionMetaInferior;
    
    @Column(name = "EVAL_METAMEDIA")
    private String evaluacionMetaMedia;
    
    @Column(name = "EVAL_METASUPERI")
    private String evaluacionMetaSuperior;

    public Integer getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(Integer idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getNombreTipoEvaluacion() {
        return nombreTipoEvaluacion;
    }

    public void setNombreTipoEvaluacion(String nombreTipoEvaluacion) {
        this.nombreTipoEvaluacion = nombreTipoEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getPeriocidadEvaluacion() {
        return periocidadEvaluacion;
    }

    public void setPeriocidadEvaluacion(String periocidadEvaluacion) {
        this.periocidadEvaluacion = periocidadEvaluacion;
    }

    public Integer getRangoBajoEvaluacion() {
        return rangoBajoEvaluacion;
    }

    public void setRangoBajoEvaluacion(Integer rangoBajoEvaluacion) {
        this.rangoBajoEvaluacion = rangoBajoEvaluacion;
    }

    public Integer getRangoMedioEvaluacion() {
        return rangoMedioEvaluacion;
    }

    public void setRangoMedioEvaluacion(Integer rangoMedioEvaluacion) {
        this.rangoMedioEvaluacion = rangoMedioEvaluacion;
    }

    public Integer getRangoAltoEvaluacion() {
        return rangoAltoEvaluacion;
    }

    public void setRangoAltoEvaluacion(Integer rangoAltoEvaluacion) {
        this.rangoAltoEvaluacion = rangoAltoEvaluacion;
    }

    public String getEvaluacionMetaInferior() {
        return evaluacionMetaInferior;
    }

    public void setEvaluacionMetaInferior(String evaluacionMetaInferior) {
        this.evaluacionMetaInferior = evaluacionMetaInferior;
    }

    public String getEvaluacionMetaMedia() {
        return evaluacionMetaMedia;
    }

    public void setEvaluacionMetaMedia(String evaluacionMetaMedia) {
        this.evaluacionMetaMedia = evaluacionMetaMedia;
    }

    public String getEvaluacionMetaSuperior() {
        return evaluacionMetaSuperior;
    }

    public void setEvaluacionMetaSuperior(String evaluacionMetaSuperior) {
        this.evaluacionMetaSuperior = evaluacionMetaSuperior;
    }
    
    
    
    
}
