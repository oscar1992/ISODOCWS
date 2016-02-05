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
@Table(name = "PRO_TESTA")
public class EstadoProveedorEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ESTA_ESTA")
    private int idEstadoProveedor;
    @Column(name = "ESTA_TIPO")
    private String tipoEstadoProveedor;
    @Column(name = "ESTA_ESTD")
    private String estadoEstadoProveedor;

    public int getIdEstadoProveedor() {
        return idEstadoProveedor;
    }

    public void setIdEstadoProveedor(int idEstadoProveedor) {
        this.idEstadoProveedor = idEstadoProveedor;
    }

    public String getTipoEstadoProveedor() {
        return tipoEstadoProveedor;
    }

    public void setTipoEstadoProveedor(String tipoEstadoProveedor) {
        this.tipoEstadoProveedor = tipoEstadoProveedor;
    }

    public String getEstadoEstadoProveedor() {
        return estadoEstadoProveedor;
    }

    public void setEstadoEstadoProveedor(String estadoEstadoProveedor) {
        this.estadoEstadoProveedor = estadoEstadoProveedor;
    }

}
