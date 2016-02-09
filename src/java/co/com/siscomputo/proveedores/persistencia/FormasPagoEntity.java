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
@Table(name = "PRO_TFORM")
public class FormasPagoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "FORM_FORM")
    private int idFormasPagos;

    @Column(name = "FORM_TIPO")
    private String tipoFormaPago;

    @Column(name = "FORM_ESTA ")
    private String estadoFormaPago;

    public int getIdFormasPagos() {
        return idFormasPagos;
    }

    public void setIdFormasPagos(int idFormasPagos) {
        this.idFormasPagos = idFormasPagos;
    }

    public String getTipoFormaPago() {
        return tipoFormaPago;
    }

    public void setTipoFormaPago(String tipoFormaPago) {
        this.tipoFormaPago = tipoFormaPago;
    }

    public String getEstadoFormaPago() {
        return estadoFormaPago;
    }

    public void setEstadoFormaPago(String estadoFormaPago) {
        this.estadoFormaPago = estadoFormaPago;
    }
    
    

}
