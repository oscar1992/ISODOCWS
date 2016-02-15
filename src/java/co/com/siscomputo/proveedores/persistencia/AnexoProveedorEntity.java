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
@Table(name = "PRO_TANPR")
public class AnexoProveedorEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANPR_")
    private int idAnexoProveedor;
    @Column(name = "ANPR_PROV")
    private int proveedorAnexoProveedor;
    @Column(name = "ANPR_RUTA")
    private String rutaAnexoProveedor;

    public int getIdAnexoProveedor() {
        return idAnexoProveedor;
    }

    public void setIdAnexoProveedor(int idAnexoProveedor) {
        this.idAnexoProveedor = idAnexoProveedor;
    }

    public int getProveedorAnexoProveedor() {
        return proveedorAnexoProveedor;
    }

    public void setProveedorAnexoProveedor(int proveedorAnexoProveedor) {
        this.proveedorAnexoProveedor = proveedorAnexoProveedor;
    }

    public String getRutaAnexoProveedor() {
        return rutaAnexoProveedor;
    }

    public void setRutaAnexoProveedor(String rutaAnexoProveedor) {
        this.rutaAnexoProveedor = rutaAnexoProveedor;
    }

}
