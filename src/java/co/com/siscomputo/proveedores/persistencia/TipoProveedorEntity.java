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
@Table(name = "PRO_TTIPO")
public class TipoProveedorEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "TIPO_TIPO")
    private int idTipoProveedor;
    @Column(name = "TIPO_NOMB")
    private String nombreTipoProveedor;
    @Column(name = "TIPO_ESTA")
    private String estadoTipoProveedor;

    public int getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(int idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public String getNombreTipoProveedor() {
        return nombreTipoProveedor;
    }

    public void setNombreTipoProveedor(String nombreTipoProveedor) {
        this.nombreTipoProveedor = nombreTipoProveedor;
    }

    public String getEstadoTipoProveedor() {
        return estadoTipoProveedor;
    }

    public void setEstadoTipoProveedor(String estadoTipoProveedor) {
        this.estadoTipoProveedor = estadoTipoProveedor;
    }

}
