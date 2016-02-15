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
@Table(name = "PRO_TANEX")
public class AnexoContratoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANEX_ANEX")
    private Integer idAnexo;

    @Column(name = "ANEX_CONT")
    private Integer idContratoAnexo;

    @Column(name = "ANEX_RUTA")
    private String ubicacionAnexo;

    public Integer getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public Integer getIdContratoAnexo() {
        return idContratoAnexo;
    }

    public void setIdContratoAnexo(Integer idContratoAnexo) {
        this.idContratoAnexo = idContratoAnexo;
    }

    public String getUbicacionAnexo() {
        return ubicacionAnexo;
    }

    public void setUbicacionAnexo(String ubicacionAnexo) {
        this.ubicacionAnexo = ubicacionAnexo;
    }

}
