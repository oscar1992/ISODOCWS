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
@Table(name = "ADM_TCONT")
public class TipoControlDistribucionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "CONT_CONT")
    private int idTipoControlDistribucion;
    @Column(name = "CONT_NOMB")
    private String nombreTipoControlDistribucion;
    @Column(name = "CONT_CREA")
    private int creadorTipoControlDistribucion;
    @Column(name = "CONT_UPDA")
    private int actualizadorTipoControlDistribucion;
    @Column(name = "CONT_ESTA")
    private String estadoTipoControlDistribucion;

    public int getIdTipoControlDistribucion() {
        return idTipoControlDistribucion;
    }

    public void setIdTipoControlDistribucion(int idTipoControlDistribucion) {
        this.idTipoControlDistribucion = idTipoControlDistribucion;
    }

    public String getNombreTipoControlDistribucion() {
        return nombreTipoControlDistribucion;
    }

    public void setNombreTipoControlDistribucion(String nombreTipoControlDistribucion) {
        this.nombreTipoControlDistribucion = nombreTipoControlDistribucion;
    }

    public int getCreadorTipoControlDistribucion() {
        return creadorTipoControlDistribucion;
    }

    public void setCreadorTipoControlDistribucion(int creadorTipoControlDistribucion) {
        this.creadorTipoControlDistribucion = creadorTipoControlDistribucion;
    }

    public int getActualizadorTipoControlDistribucion() {
        return actualizadorTipoControlDistribucion;
    }

    public void setActualizadorTipoControlDistribucion(int actualizadorTipoControlDistribucion) {
        this.actualizadorTipoControlDistribucion = actualizadorTipoControlDistribucion;
    }

    public String getEstadoTipoControlDistribucion() {
        return estadoTipoControlDistribucion;
    }

    public void setEstadoTipoControlDistribucion(String estadoTipoControlDistribucion) {
        this.estadoTipoControlDistribucion = estadoTipoControlDistribucion;
    }

}
