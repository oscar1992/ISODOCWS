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
@Table(name = "PRO_TLINE")
public class LineaEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "LINE_LINE")
    private int idLinea;
    @Column(name = "LINE_TIPO")
    private String tipoLinea;
    @Column(name = "LINE_ESTA")
    private String estadoLinea;

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getTipoLinea() {
        return tipoLinea;
    }

    public void setTipoLinea(String tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public String getEstadoLinea() {
        return estadoLinea;
    }

    public void setEstadoLinea(String estadoLinea) {
        this.estadoLinea = estadoLinea;
    }
    
    
}
