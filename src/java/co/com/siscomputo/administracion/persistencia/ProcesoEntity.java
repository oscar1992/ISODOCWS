package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TPROC2")
public class ProcesoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "PROC_PROC")
    private int idProceso;
    @Column(name = "PROC_NOMB")
    private String nombreProceso;
    @JoinColumn(name = "PROC_NIVE")
    @ManyToOne
    private NivelEntity nivelProceso;
    @Column(name = "PROC_ASOC")
    private int asociadoProceso;
    @Column(name = "PROC_ESTA")
    private String estadoProceso;
    @Column(name = "PROC_CREA")
    private int creaProceso;
    @Column(name = "PROC_UPDA")
    private int actualizaProceso;

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public NivelEntity getNivelProceso() {
        return nivelProceso;
    }

    public void setNivelProceso(NivelEntity nivelProceso) {
        this.nivelProceso = nivelProceso;
    }

    public int getAsociadoProceso() {
        return asociadoProceso;
    }

    public void setAsociadoProceso(int asociadoProceso) {
        this.asociadoProceso = asociadoProceso;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public int getCreaProceso() {
        return creaProceso;
    }

    public void setCreaProceso(int creaProceso) {
        this.creaProceso = creaProceso;
    }

    public int getActualizaProceso() {
        return actualizaProceso;
    }

    public void setActualizaProceso(int actualizaProceso) {
        this.actualizaProceso = actualizaProceso;
    }

}
