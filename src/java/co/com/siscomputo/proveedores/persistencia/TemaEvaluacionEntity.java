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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name = "PRO_TEMA")
public class TemaEvaluacionEntity extends ObjetoRetornaEntity implements Serializable{
    
    @Id
    @Column(name = "TEMA_TEMAE")
    private Integer idTemaEvaluacion;
    
    @Column(name = "TEMA_NOMB")
    private String nombreTemaEvaluacion;
    
    @Column(name = "TEMA_MAXRE")
    private Integer maxRespuestaEvaluacion;
    
    @Column(name = "TEMA_PORCE")
    private Integer PorcentajeEvaluacion;
    
    @JoinColumn(name = "TEMA_EVAL")
    @ManyToOne
    private TipoEvaluacionEntity temaEvaluacion;

    public Integer getIdTemaEvaluacion() {
        return idTemaEvaluacion;
    }

    public void setIdTemaEvaluacion(Integer idTemaEvaluacion) {
        this.idTemaEvaluacion = idTemaEvaluacion;
    }

    public String getNombreTemaEvaluacion() {
        return nombreTemaEvaluacion;
    }

    public void setNombreTemaEvaluacion(String nombreTemaEvaluacion) {
        this.nombreTemaEvaluacion = nombreTemaEvaluacion;
    }

    public Integer getMaxRespuestaEvaluacion() {
        return maxRespuestaEvaluacion;
    }

    public void setMaxRespuestaEvaluacion(Integer maxRespuestaEvaluacion) {
        this.maxRespuestaEvaluacion = maxRespuestaEvaluacion;
    }

    public Integer getPorcentajeEvaluacion() {
        return PorcentajeEvaluacion;
    }

    public void setPorcentajeEvaluacion(Integer PorcentajeEvaluacion) {
        this.PorcentajeEvaluacion = PorcentajeEvaluacion;
    }

    public TipoEvaluacionEntity getTemaEvaluacion() {
        return temaEvaluacion;
    }

    public void setTemaEvaluacion(TipoEvaluacionEntity temaEvaluacion) {
        this.temaEvaluacion = temaEvaluacion;
    }
    
    
}
