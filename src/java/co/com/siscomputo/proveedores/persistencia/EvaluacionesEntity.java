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
@Table(name = "PRO_EVA")
public class EvaluacionesEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "EVA_EVA")
    private int IDEvaluaciones;

    @Column(name = "EVA_FECH")
    private String fechaEvaluaciones;

    @JoinColumn(name = "EVA_PRO")
    @ManyToOne
    private ProveedoresEntity proveedoresEntity;

    @JoinColumn(name = "EVA_TIPRO")
    @ManyToOne
    private TipoProveedorEntity tipoProveedorEntity;

    @JoinColumn(name = "EVA_TIEVA")
    @ManyToOne
    private TipoEvaluacionEntity tipoEvaluacionEntity;
    @Column(name = "EVA_ANIO")
    private String AnioEvaluaciones;
    @Column(name = "EVA_PERI")
    private String periodoEvaluaciones;
    @Column(name = "EVA_DOC")
    private String documentoEvaluaciones;
    @Column(name = "EVA_ESTA")
    private String estadoProveedorEvaluaciones;
    @Column(name = "EVA_UNI")
    private String UnidadNegocioEvaluaciones;
    @Column(name = "EVA_EMP")
    private String empresaEvaluaciones;
    @JoinColumn(name = "EVA_CONTR")
    @ManyToOne
    private ContratosEntity contratoEvaluaciones;

    @Column(name = "EVA_RESP")
    private String responsableEvaluaciones;
    @Column(name = "EVA_UBIC")
    private String ubicacionEvaluaciones;

    public int getIDEvaluaciones() {
        return IDEvaluaciones;
    }

    public void setIDEvaluaciones(int IDEvaluaciones) {
        this.IDEvaluaciones = IDEvaluaciones;
    }

    public String getFechaEvaluaciones() {
        return fechaEvaluaciones;
    }

    public void setFechaEvaluaciones(String fechaEvaluaciones) {
        this.fechaEvaluaciones = fechaEvaluaciones;
    }

    public ProveedoresEntity getProveedoresEntity() {
        return proveedoresEntity;
    }

    public void setProveedoresEntity(ProveedoresEntity proveedoresEntity) {
        this.proveedoresEntity = proveedoresEntity;
    }

    public TipoProveedorEntity getTipoProveedorEntity() {
        return tipoProveedorEntity;
    }

    public void setTipoProveedorEntity(TipoProveedorEntity tipoProveedorEntity) {
        this.tipoProveedorEntity = tipoProveedorEntity;
    }

    public TipoEvaluacionEntity getTipoEvaluacionEntity() {
        return tipoEvaluacionEntity;
    }

    public void setTipoEvaluacionEntity(TipoEvaluacionEntity tipoEvaluacionEntity) {
        this.tipoEvaluacionEntity = tipoEvaluacionEntity;
    }

    public String getAnioEvaluaciones() {
        return AnioEvaluaciones;
    }

    public void setAnioEvaluaciones(String AnioEvaluaciones) {
        this.AnioEvaluaciones = AnioEvaluaciones;
    }

    public String getPeriodoEvaluaciones() {
        return periodoEvaluaciones;
    }

    public void setPeriodoEvaluaciones(String periodoEvaluaciones) {
        this.periodoEvaluaciones = periodoEvaluaciones;
    }

    public String getDocumentoEvaluaciones() {
        return documentoEvaluaciones;
    }

    public void setDocumentoEvaluaciones(String documentoEvaluaciones) {
        this.documentoEvaluaciones = documentoEvaluaciones;
    }

    public String getEstadoProveedorEvaluaciones() {
        return estadoProveedorEvaluaciones;
    }

    public void setEstadoProveedorEvaluaciones(String estadoProveedorEvaluaciones) {
        this.estadoProveedorEvaluaciones = estadoProveedorEvaluaciones;
    }

    public String getUnidadNegocioEvaluaciones() {
        return UnidadNegocioEvaluaciones;
    }

    public void setUnidadNegocioEvaluaciones(String UnidadNegocioEvaluaciones) {
        this.UnidadNegocioEvaluaciones = UnidadNegocioEvaluaciones;
    }

    public String getEmpresaEvaluaciones() {
        return empresaEvaluaciones;
    }

    public void setEmpresaEvaluaciones(String empresaEvaluaciones) {
        this.empresaEvaluaciones = empresaEvaluaciones;
    }

    public ContratosEntity getContratoEvaluaciones() {
        return contratoEvaluaciones;
    }

    public void setContratoEvaluaciones(ContratosEntity contratoEvaluaciones) {
        this.contratoEvaluaciones = contratoEvaluaciones;
    }

    

    public String getResponsableEvaluaciones() {
        return responsableEvaluaciones;
    }

    public void setResponsableEvaluaciones(String responsableEvaluaciones) {
        this.responsableEvaluaciones = responsableEvaluaciones;
    }

    public String getUbicacionEvaluaciones() {
        return ubicacionEvaluaciones;
    }

    public void setUbicacionEvaluaciones(String ubicacionEvaluaciones) {
        this.ubicacionEvaluaciones = ubicacionEvaluaciones;
    }

}
