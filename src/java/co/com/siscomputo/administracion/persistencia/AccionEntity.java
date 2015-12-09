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
@Table(name = "ADM_TACCI")
public class AccionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ACCI_ACCI")
    private int idAccion;
    @Column(name = "ACCI_NOMB")
    private String nombreAccion;
    @Column(name = "ACCI_DESC")
    private String descripcionAccion;
    @Column(name = "ACCI_CREA")
    private int creadorAccion;
    @Column(name = "ACCI_UPDA")
    private int actualizadorAccion;
    @Column(name = "ACCI_ESTA")
    private String estadoAccion;

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public String getDescripcionAccion() {
        return descripcionAccion;
    }

    public void setDescripcionAccion(String descripcionAccion) {
        this.descripcionAccion = descripcionAccion;
    }

    public int getCreadorAccion() {
        return creadorAccion;
    }

    public void setCreadorAccion(int creadorAccion) {
        this.creadorAccion = creadorAccion;
    }

    public int getActualizadorAccion() {
        return actualizadorAccion;
    }

    public void setActualizadorAccion(int actualizadorAccion) {
        this.actualizadorAccion = actualizadorAccion;
    }

    public String getEstadoAccion() {
        return estadoAccion;
    }

    public void setEstadoAccion(String estadoAccion) {
        this.estadoAccion = estadoAccion;
    }

}
