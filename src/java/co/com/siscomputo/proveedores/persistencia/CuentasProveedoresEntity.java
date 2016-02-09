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
public class CuentasProveedoresEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "CUEN_CUEN")
    private int idCuentasProveedores;
    @Column(name = "CUEN_TIPO")
    private String nombreCuentasProveedores;
    @Column(name = "CUEN_ESTA")
    private String estadoCuentasProveedores;

    public int getIdCuentasProveedores() {
        return idCuentasProveedores;
    }

    public void setIdCuentasProveedores(int idCuentasProveedores) {
        this.idCuentasProveedores = idCuentasProveedores;
    }

    public String getNombreCuentasProveedores() {
        return nombreCuentasProveedores;
    }

    public void setNombreCuentasProveedores(String nombreCuentasProveedores) {
        this.nombreCuentasProveedores = nombreCuentasProveedores;
    }

    public String getEstadoCuentasProveedores() {
        return estadoCuentasProveedores;
    }

    public void setEstadoCuentasProveedores(String estadoCuentasProveedores) {
        this.estadoCuentasProveedores = estadoCuentasProveedores;
    }

}
