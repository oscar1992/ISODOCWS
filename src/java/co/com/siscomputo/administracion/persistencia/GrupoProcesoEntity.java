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
@Table(name = "ADM_TGRPR")
public class GrupoProcesoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "GRPR_GRPR")
    private int idGrupoProceso;
    @JoinColumn(name = "GRPR_GRUP")
    @ManyToOne
    private GrupoUsuariosEntity grupoUsuarioProceso;
    @JoinColumn(name = "GRPR_PROC")
    @ManyToOne
    private ProcesoEntity procesoGrupoProceso;
    @JoinColumn(name = "GRPR_ACCI")
    @ManyToOne
    private AccionEntity accionGrupoProceso;

    public int getIdGrupoProceso() {
        return idGrupoProceso;
    }

    public void setIdGrupoProceso(int idGrupoProceso) {
        this.idGrupoProceso = idGrupoProceso;
    }

    public GrupoUsuariosEntity getGrupoUsuarioProceso() {
        return grupoUsuarioProceso;
    }

    public void setGrupoUsuarioProceso(GrupoUsuariosEntity grupoUsuarioProceso) {
        this.grupoUsuarioProceso = grupoUsuarioProceso;
    }


    public ProcesoEntity getProcesoGrupoProceso() {
        return procesoGrupoProceso;
    }

    public void setProcesoGrupoProceso(ProcesoEntity procesoGrupoProceso) {
        this.procesoGrupoProceso = procesoGrupoProceso;
    }

    public AccionEntity getAccionGrupoProceso() {
        return accionGrupoProceso;
    }

    public void setAccionGrupoProceso(AccionEntity accionGrupoProceso) {
        this.accionGrupoProceso = accionGrupoProceso;
    }


}
