package co.com.siscomputo.administracion.persistencia;

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
@Table(name = "ADM_TEXTE")
public class ExtensionesEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "EXTE_EXTE")
    private int idExtensiones;
    @Column(name = "EXTE_TIPO")
    private String tipoExtensiones;

    public int getIdExtensiones() {
        return idExtensiones;
    }

    public void setIdExtensiones(int idExtensiones) {
        this.idExtensiones = idExtensiones;
    }

    public String getTipoExtensiones() {
        return tipoExtensiones;
    }

    public void setTipoExtensiones(String tipoExtensiones) {
        this.tipoExtensiones = tipoExtensiones;
    }

}
