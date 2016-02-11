package co.com.siscomputo.proveedores.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "PRO_TCUEN")
public class TipoCuentaEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "CUEN_CUEN")
    private int idTipoCuenta;
    @Column(name = "CUEN_TIPO")
    private String tipoTipoCuenta;
    @Column(name = "CUEN_ESTA")
    private String estadoTipoCuenta;

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getTipoTipoCuenta() {
        return tipoTipoCuenta;
    }

    public void setTipoTipoCuenta(String tipoTipoCuenta) {
        this.tipoTipoCuenta = tipoTipoCuenta;
    }

    public String getEstadoTipoCuenta() {
        return estadoTipoCuenta;
    }

    public void setEstadoTipoCuenta(String estadoTipoCuenta) {
        this.estadoTipoCuenta = estadoTipoCuenta;
    }

}
