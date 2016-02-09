package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.administracion.persistencia.EmpresaEntity;

import co.com.siscomputo.administracion.persistencia.PlantillaEntity;
import co.com.siscomputo.administracion.persistencia.ProcesoEntity;

import co.com.siscomputo.administracion.persistencia.TiposDocumentalesEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @JoinColumn(name = "DOCU_ACCI")
    @ManyToOne
    private AccionEntity accionDocumento;
    @JoinColumn(name = "DOCU_EMPR")
    @ManyToOne
    private EmpresaEntity empresaDocumento;
    @JoinColumn(name = "DOCU_PROC2")
    @OneToOne
    private ProcesoEntity procesoDocumento;
    @Column(name = "DOCU_FCRE")
    private String fechaDocumento;
    @Column(name = "DOCU_RUTA")
    private String rutaDocumento;
    
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

    public AccionEntity getAccionDocumento() {
        return accionDocumento;
    }

    public void setAccionDocumento(AccionEntity accionDocumento) {
        this.accionDocumento = accionDocumento;
    }

    public EmpresaEntity getEmpresaDocumento() {
        return empresaDocumento;
    }

    public void setEmpresaDocumento(EmpresaEntity empresaDocumento) {
        this.empresaDocumento = empresaDocumento;
    }

    public ProcesoEntity getProcesoDocumento() {
        return procesoDocumento;
    }

    public void setProcesoDocumento(ProcesoEntity procesoDocumento) {
        this.procesoDocumento = procesoDocumento;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }
    
    
}
