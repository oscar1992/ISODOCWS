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
@Table(name = "ADM_TALMA")
public class TipoAlmacenamientoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ALMA_ALMA")
    private int idTipoAlmacenamiento;
    @Column(name = "ALMA_NOMB")
    private String nombreTipoAlmacenamiento;
    @Column(name = "ALMA_CREA")
    private int creadorTipoAlmacenamiento;
    @Column(name = "ALMA_UPDA")
    private int actualizadorTipoAlmacenamiento;
    @Column(name = "ALMA_ESTA")
    private String estadoTipoAlmacenamiento;

    public int getIdTipoAlmacenamiento() {
        return idTipoAlmacenamiento;
    }

    public void setIdTipoAlmacenamiento(int idTipoAlmacenamiento) {
        this.idTipoAlmacenamiento = idTipoAlmacenamiento;
    }

    public String getNombreTipoAlmacenamiento() {
        return nombreTipoAlmacenamiento;
    }

    public void setNombreTipoAlmacenamiento(String nombreTipoAlmacenamiento) {
        this.nombreTipoAlmacenamiento = nombreTipoAlmacenamiento;
    }

    public int getCreadorTipoAlmacenamiento() {
        return creadorTipoAlmacenamiento;
    }

    public void setCreadorTipoAlmacenamiento(int creadorTipoAlmacenamiento) {
        this.creadorTipoAlmacenamiento = creadorTipoAlmacenamiento;
    }

    public int getActualizadorTipoAlmacenamiento() {
        return actualizadorTipoAlmacenamiento;
    }

    public void setActualizadorTipoAlmacenamiento(int actualizadorTipoAlmacenamiento) {
        this.actualizadorTipoAlmacenamiento = actualizadorTipoAlmacenamiento;
    }

    public String getEstadoTipoAlmacenamiento() {
        return estadoTipoAlmacenamiento;
    }

    public void setEstadoTipoAlmacenamiento(String estadoTipoAlmacenamiento) {
        this.estadoTipoAlmacenamiento = estadoTipoAlmacenamiento;
    }

}
