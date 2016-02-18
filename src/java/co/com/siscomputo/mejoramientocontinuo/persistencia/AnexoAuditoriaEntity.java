package co.com.siscomputo.mejoramientocontinuo.persistencia;

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
@Table(name = "MJ_TANEX")
public class AnexoAuditoriaEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "ANEX_ANEX")
    private int IdAnexo;
    @Column(name = "ANEX_AUDI")
    private int IdAuditoria;
    @Column(name = "ANEX_RUTA")
    private int rutaAnexoAuditoria;

    public int getIdAnexo() {
        return IdAnexo;
    }

    public void setIdAnexo(int IdAnexo) {
        this.IdAnexo = IdAnexo;
    }

    public int getIdAuditoria() {
        return IdAuditoria;
    }

    public void setIdAuditoria(int IdAuditoria) {
        this.IdAuditoria = IdAuditoria;
    }

    public int getRutaAnexoAuditoria() {
        return rutaAnexoAuditoria;
    }

    public void setRutaAnexoAuditoria(int rutaAnexoAuditoria) {
        this.rutaAnexoAuditoria = rutaAnexoAuditoria;
    }

}
