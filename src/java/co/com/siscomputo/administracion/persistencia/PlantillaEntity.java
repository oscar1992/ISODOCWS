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
@Table(name = "ADM_TPLAN")
public class PlantillaEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "PLAN_PLAN")
    private int idPlantilla;
    @Column(name = "PLAN_NOMB")
    private String nombrePlantilla;
    @Column(name = "PLAN_RUTA")
    private String rutaPlantilla;
    @Column(name = "PLAN_CREA")
    private int creadorPlantilla;
    @Column(name = "PLAN_UPDA")
    private int actualizadorPlantilla;
    @Column(name = "PLAN_ESTA")
    private String estadoPlantilla;

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public void setNombrePlantilla(String nombrePlantilla) {
        this.nombrePlantilla = nombrePlantilla;
    }

    public String getRutaPlantilla() {
        return rutaPlantilla;
    }

    public void setRutaPlantilla(String rutaPlantilla) {
        this.rutaPlantilla = rutaPlantilla;
    }

    public int getCreadorPlantilla() {
        return creadorPlantilla;
    }

    public void setCreadorPlantilla(int creadorPlantilla) {
        this.creadorPlantilla = creadorPlantilla;
    }

    public int getActualizadorPlantilla() {
        return actualizadorPlantilla;
    }

    public void setActualizadorPlantilla(int actualizadorPlantilla) {
        this.actualizadorPlantilla = actualizadorPlantilla;
    }

    public String getEstadoPlantilla() {
        return estadoPlantilla;
    }

    public void setEstadoPlantilla(String estadoPlantilla) {
        this.estadoPlantilla = estadoPlantilla;
    }

}
