package co.com.siscomputo.proveedores.persistencia;

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
@Table(name = "PRO_ANEX")
public class AnexoEvalucionEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANEX_ANEX")
    private int IdAnexoEvalucion;
    @JoinColumn(name = "ANEX_EVAL")
    @ManyToOne
    private EvaluacionesEntity IdEvalucion;
    @Column(name = "ANEX_RUTA")
    private String rutaAnexoEvalucion;

    public int getIdAnexoEvalucion() {
        return IdAnexoEvalucion;
    }

    public void setIdAnexoEvalucion(int IdAnexoEvalucion) {
        this.IdAnexoEvalucion = IdAnexoEvalucion;
    }

    public EvaluacionesEntity getIdEvalucion() {
        return IdEvalucion;
    }

    public void setIdEvalucion(EvaluacionesEntity IdEvalucion) {
        this.IdEvalucion = IdEvalucion;
    }
    
    public String getRutaAnexoEvalucion() {
        return rutaAnexoEvalucion;
    }

    public void setRutaAnexoEvalucion(String rutaAnexoEvalucion) {
        this.rutaAnexoEvalucion = rutaAnexoEvalucion;
    }

}
