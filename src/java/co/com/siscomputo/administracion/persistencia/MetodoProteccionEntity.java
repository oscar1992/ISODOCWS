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
@Table(name = "ADM_TPROT")
public class MetodoProteccionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "PROT_PROT")
    private int idMetodoProteccion;
    @Column(name = "PROT_NOMB")
    private String nombreMetodoProteccion;
    @Column(name = "PROT_CREA")
    private int creadorMetodoProteccion;
    @Column(name = "PROT_UPDA")
    private int actualizadorMetodoProteccion;
    @Column(name = "PROT_ESTA")
    private String estadoMetodoProteccion;

    public int getIdMetodoProteccion() {
        return idMetodoProteccion;
    }

    public void setIdMetodoProteccion(int idMetodoProteccion) {
        this.idMetodoProteccion = idMetodoProteccion;
    }

    public String getNombreMetodoProteccion() {
        return nombreMetodoProteccion;
    }

    public void setNombreMetodoProteccion(String nombreMetodoProteccion) {
        this.nombreMetodoProteccion = nombreMetodoProteccion;
    }

    public int getCreadorMetodoProteccion() {
        return creadorMetodoProteccion;
    }

    public void setCreadorMetodoProteccion(int creadorMetodoProteccion) {
        this.creadorMetodoProteccion = creadorMetodoProteccion;
    }

    public int getActualizadorMetodoProteccion() {
        return actualizadorMetodoProteccion;
    }

    public void setActualizadorMetodoProteccion(int actualizadorMetodoProteccion) {
        this.actualizadorMetodoProteccion = actualizadorMetodoProteccion;
    }

    public String getEstadoMetodoProteccion() {
        return estadoMetodoProteccion;
    }

    public void setEstadoMetodoProteccion(String estadoMetodoProteccion) {
        this.estadoMetodoProteccion = estadoMetodoProteccion;
    }

}
