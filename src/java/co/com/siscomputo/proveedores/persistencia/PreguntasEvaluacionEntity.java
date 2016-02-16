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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name = "PRO_PREGU")
public class PreguntasEvaluacionEntity extends ObjetoRetornaEntity implements Serializable{
    
    @Id
    @Column(name = "PREGU_PREG")
    private Integer idPregunta;
    
    @JoinColumn(name = "PREGU_EVAL")
    @OneToOne
    private Integer idEvaluacionPregunta;
    
    @JoinColumn(name = "PREGU_TEMA")
    @OneToOne
    private Integer idTemaPregunta;
    
    @Column(name="PREGU_PREGU")
    private String pregunta;
    
    @Column(name = "PREGU_DESCR")
    private String descripcionDetallada;
    
    @Column(name = "PREGU_PESO")
    private String pesoItem;

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Integer getIdEvaluacionPregunta() {
        return idEvaluacionPregunta;
    }

    public void setIdEvaluacionPregunta(Integer idEvaluacionPregunta) {
        this.idEvaluacionPregunta = idEvaluacionPregunta;
    }

    public Integer getIdTemaPregunta() {
        return idTemaPregunta;
    }

    public void setIdTemaPregunta(Integer idTemaPregunta) {
        this.idTemaPregunta = idTemaPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    public String getPesoItem() {
        return pesoItem;
    }

    public void setPesoItem(String pesoItem) {
        this.pesoItem = pesoItem;
    }
    
    
    
    
    
}
