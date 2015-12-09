package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "GDO_TDOPR")
public class DocumentoProcesoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "DOPR_DOPR")
    private int idDocumentoProceso;
    @JoinColumn(name = "DOPR_ACCI")
    @ManyToOne
    private AccionEntity accionDocumentoProceso;
    @JoinColumn(name = "DOPR_DOCU")
    @ManyToOne
    private DocumentoEntity documentoDocumentoProceso;
    @Column(name = "DOPR_FECH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDocumentoProceso;
    @JoinColumn(name = "DOPR_USUA")
    @ManyToOne
    private UsuarioEntity usuarioDocumentoProceso;

    public int getIdDocumentoProceso() {
        return idDocumentoProceso;
    }

    public void setIdDocumentoProceso(int idDocumentoProceso) {
        this.idDocumentoProceso = idDocumentoProceso;
    }

    public AccionEntity getAccionDocumentoProceso() {
        return accionDocumentoProceso;
    }

    public void setAccionDocumentoProceso(AccionEntity accionDocumentoProceso) {
        this.accionDocumentoProceso = accionDocumentoProceso;
    }

    public DocumentoEntity getDocumentoDocumentoProceso() {
        return documentoDocumentoProceso;
    }

    public void setDocumentoDocumentoProceso(DocumentoEntity documentoDocumentoProceso) {
        this.documentoDocumentoProceso = documentoDocumentoProceso;
    }

    public Date getFechaDocumentoProceso() {
        return fechaDocumentoProceso;
    }

    public void setFechaDocumentoProceso(Date fechaDocumentoProceso) {
        this.fechaDocumentoProceso = fechaDocumentoProceso;
    }

    public UsuarioEntity getUsuarioDocumentoProceso() {
        return usuarioDocumentoProceso;
    }

    public void setUsuarioDocumentoProceso(UsuarioEntity usuarioDocumentoProceso) {
        this.usuarioDocumentoProceso = usuarioDocumentoProceso;
    }

    

}
