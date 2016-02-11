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
@Table(name = "ADM_TMONE")
public class TipoMonedaEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "MONE_MONE")
    private Integer idMoneda;
    
    @Column(name = "MONE_TIPO")
    private String tipoMoneda;
    
    @Column(name = "MONE_VALO")
    private String valorMoneda;
    
    @Column(name = "MONE_ESTA")
    private String estadoMoneda;

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getValorMoneda() {
        return valorMoneda;
    }

    public void setValorMoneda(String valorMoneda) {
        this.valorMoneda = valorMoneda;
    }

    public String getEstadoMoneda() {
        return estadoMoneda;
    }

    public void setEstadoMoneda(String estadoMoneda) {
        this.estadoMoneda = estadoMoneda;
    }
    
    
    
}
