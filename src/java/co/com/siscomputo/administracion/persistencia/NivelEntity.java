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
@Table(name = "ADM_TNIVE")
public class NivelEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "NIVE_NIVE")
    private int idNivel;
    @Column(name = "NIVE_NOMB")
    private String nombreNivel;
    @Column(name = "NIVE_CREA")
    private int creadorNivel;
    @Column(name = "NIVE_UPDA")
    private int actualizadorNivel;
    @Column(name = "NIVE_ESTA")
    private String estadoNivel;
    @Column(name = "NIVE_SECU")
    private int secuenciaNivel;

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public int getCreadorNivel() {
        return creadorNivel;
    }

    public void setCreadorNivel(int creadorNivel) {
        this.creadorNivel = creadorNivel;
    }

    public int getActualizadorNivel() {
        return actualizadorNivel;
    }

    public void setActualizadorNivel(int actualizadorNivel) {
        this.actualizadorNivel = actualizadorNivel;
    }

    public String getEstadoNivel() {
        return estadoNivel;
    }

    public void setEstadoNivel(String estadoNivel) {
        this.estadoNivel = estadoNivel;
    }

    public int getSecuenciaNivel() {
        return secuenciaNivel;
    }

    public void setSecuenciaNivel(int secuenciaNivel) {
        this.secuenciaNivel = secuenciaNivel;
    }

}
