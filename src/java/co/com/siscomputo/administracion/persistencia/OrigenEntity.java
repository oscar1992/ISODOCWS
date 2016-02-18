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
 * @author Personal
 */
@Entity
@Table(name = "ADM_TORIG")
public class OrigenEntity extends ObjetoRetornaEntity implements Serializable{
    
    @Id
    @Column(name = "ORIG_ORIG")
    private Integer idOrigen;
    
    @Column(name = "ORIG_TIPO")
    private String tipoOrigen;
    
    @Column(name = "ORIG_ALIA")
    private String aliasOrigen;
    
    @Column(name = "ORIG_ESTA")
    private String estadoOrigen;

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(String tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public String getAliasOrigen() {
        return aliasOrigen;
    }

    public void setAliasOrigen(String aliasOrigen) {
        this.aliasOrigen = aliasOrigen;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }
    
    
    
    
}
