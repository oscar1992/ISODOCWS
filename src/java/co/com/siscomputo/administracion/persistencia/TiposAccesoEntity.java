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
@Table(name = "ADM_TACCE")
public class TiposAccesoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ACCE_ACCE")
    private int idTiposAcceso;
    @Column(name = "ACCE_NOMB")
    private String nombreTiposAcceso;
    @Column(name = "ACCE_CREA")
    private int creadorTiposAcceso;
    @Column(name = "ACCE_UPDA")
    private int actualizadorTiposAcceso;
    @Column(name = "ACCE_ESTA")
    private String estadoTiposAcceso;

    public int getIdTiposAcceso() {
        return idTiposAcceso;
    }

    public void setIdTiposAcceso(int idTiposAcceso) {
        this.idTiposAcceso = idTiposAcceso;
    }

    public String getNombreTiposAcceso() {
        return nombreTiposAcceso;
    }

    public void setNombreTiposAcceso(String nombreTiposAcceso) {
        this.nombreTiposAcceso = nombreTiposAcceso;
    }

    public int getCreadorTiposAcceso() {
        return creadorTiposAcceso;
    }

    public void setCreadorTiposAcceso(int creadorTiposAcceso) {
        this.creadorTiposAcceso = creadorTiposAcceso;
    }

    public int getActualizadorTiposAcceso() {
        return actualizadorTiposAcceso;
    }

    public void setActualizadorTiposAcceso(int actualizadorTiposAcceso) {
        this.actualizadorTiposAcceso = actualizadorTiposAcceso;
    }

    public String getEstadoTiposAcceso() {
        return estadoTiposAcceso;
    }

    public void setEstadoTiposAcceso(String estadoTiposAcceso) {
        this.estadoTiposAcceso = estadoTiposAcceso;
    }

}
