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
@Table(name = "PRO_TTIRB")
public class TipoTributarioEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "TRIB_TRIB")
    private int idTipoTributario;
    @Column(name = "TRIB_TIPO")
    private String nombreTipoTributario;
    @Column(name = "TRIB_ESTA")
    private String estadoTipoTributario;

    public int getIdTipoTributario() {
        return idTipoTributario;
    }

    public void setIdTipoTributario(int idTipoTributario) {
        this.idTipoTributario = idTipoTributario;
    }

    public String getNombreTipoTributario() {
        return nombreTipoTributario;
    }

    public void setNombreTipoTributario(String nombreTipoTributario) {
        this.nombreTipoTributario = nombreTipoTributario;
    }

    public String getEstadoTipoTributario() {
        return estadoTipoTributario;
    }

    public void setEstadoTipoTributario(String estadoTipoTributario) {
        this.estadoTipoTributario = estadoTipoTributario;
    }

}
