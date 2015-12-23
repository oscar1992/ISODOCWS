package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.administracion.persistencia.MacroprocesosEntity;
import co.com.siscomputo.administracion.persistencia.PlantillaEntity;
import co.com.siscomputo.administracion.persistencia.ProcesosEntity;
import co.com.siscomputo.administracion.persistencia.SubprocesoEntity;
import co.com.siscomputo.administracion.persistencia.TiposDocumentalesEntity;
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
@Table(name = "GDO_TDOCU")
public class DocumentoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "DOCU_DOCU")
    private int idDocumento;
    @Column(name = "DOCU_TITU")
    private String tituloDocumento;
    @JoinColumn(name = "DOCU_TIDO")
    @ManyToOne
    private TiposDocumentalesEntity tipoDocumentalDocumento;
    @JoinColumn(name = "DOCU_PLAN")
    @ManyToOne
    private PlantillaEntity plantilla;
    @Column(name = "DOCU_VERS")
    private String versionDocumento;
    @Column(name = "DOCU_ESTA")
    private String estadoDocumento;
    @JoinColumn(name="DOCU_MACR")
    @ManyToOne
    private MacroprocesosEntity macroProcesoDocumento;
    @JoinColumn(name="DOCU_PROC")
    @ManyToOne
    private ProcesosEntity procesoProcesoDocumento;
    @JoinColumn(name="DOCU_SUBP")
    @ManyToOne
    private SubprocesoEntity subProcesoProcesoDocumento;
    @JoinColumn(name = "DOCU_ACCI")
    @ManyToOne
    private AccionEntity accionDocumento;
    
    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }

    public TiposDocumentalesEntity getTipoDocumentalDocumento() {
        return tipoDocumentalDocumento;
    }

    public void setTipoDocumentalDocumento(TiposDocumentalesEntity tipoDocumentalDocumento) {
        this.tipoDocumentalDocumento = tipoDocumentalDocumento;
    }

    public PlantillaEntity getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(PlantillaEntity plantilla) {
        this.plantilla = plantilla;
    }


    public String getVersionDocumento() {
        return versionDocumento;
    }

    public void setVersionDocumento(String versionDocumento) {
        this.versionDocumento = versionDocumento;
    }

    public String getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(String estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public MacroprocesosEntity getMacroProcesoDocumento() {
        return macroProcesoDocumento;
    }

    public void setMacroProcesoDocumento(MacroprocesosEntity macroProcesoDocumento) {
        this.macroProcesoDocumento = macroProcesoDocumento;
    }

    public ProcesosEntity getProcesoProcesoDocumento() {
        return procesoProcesoDocumento;
    }

    public void setProcesoProcesoDocumento(ProcesosEntity procesoProcesoDocumento) {
        this.procesoProcesoDocumento = procesoProcesoDocumento;
    }

    public SubprocesoEntity getSubProcesoProcesoDocumento() {
        return subProcesoProcesoDocumento;
    }

    public void setSubProcesoProcesoDocumento(SubprocesoEntity subProcesoProcesoDocumento) {
        this.subProcesoProcesoDocumento = subProcesoProcesoDocumento;
    }

    public AccionEntity getAccionDocumento() {
        return accionDocumento;
    }

    public void setAccionDocumento(AccionEntity accionDocumento) {
        this.accionDocumento = accionDocumento;
    }
    
}
