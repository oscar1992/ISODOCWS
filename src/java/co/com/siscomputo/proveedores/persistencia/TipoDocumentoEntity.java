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
@Table(name = "PRO_TTIDO")
public class TipoDocumentoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "TIDO_TIDO")
    private int idTipoDocumento;
    @Column(name = "TIDO_TIPO")
    private String tipoTipoDocumento;
    @Column(name = "TIDO_ESTA")
    private String estadoTipoDocumento;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoTipoDocumento() {
        return tipoTipoDocumento;
    }

    public void setTipoTipoDocumento(String tipoTipoDocumento) {
        this.tipoTipoDocumento = tipoTipoDocumento;
    }

    public String getEstadoTipoDocumento() {
        return estadoTipoDocumento;
    }

    public void setEstadoTipoDocumento(String estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

}
