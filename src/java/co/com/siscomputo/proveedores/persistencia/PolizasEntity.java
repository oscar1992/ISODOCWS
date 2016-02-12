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
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "PRO_TPOLI")
public class PolizasEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "POLI_POLI")
    private Integer idPoliza;

    @Column(name = "POLI_NUME")
    private String numeroPoliza;

    @JoinColumn(name = "POLI_CONT")
    private ContratosEntity  idContratoPoliza;

    public Integer getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(Integer idPoliza) {
        this.idPoliza = idPoliza;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public ContratosEntity getIdContratoPoliza() {
        return idContratoPoliza;
    }

    public void setIdContratoPoliza(ContratosEntity idContratoPoliza) {
        this.idContratoPoliza = idContratoPoliza;
    }
}