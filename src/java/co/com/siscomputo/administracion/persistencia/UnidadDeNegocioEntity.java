/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

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
@Table(name = "ADM_TUNEG")
public class UnidadDeNegocioEntity extends ObjetoRetornaEntity implements Serializable{
    
    
    @Id
    @Column(name = "UNEG_UNEG")
    private Integer idUnidadNegocio;
    
    @Column(name = "UNEG_NOMB")
    private String nombreNegocio;
    
    @Column(name = "UNEG_ESTA")
    private String estadoNegocio;

    public Integer getIdUnidadNegocio() {
        return idUnidadNegocio;
    }

    public void setIdUnidadNegocio(Integer idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getEstadoNegocio() {
        return estadoNegocio;
    }

    public void setEstadoNegocio(String estadoNegocio) {
        this.estadoNegocio = estadoNegocio;
    }
    
    
}
