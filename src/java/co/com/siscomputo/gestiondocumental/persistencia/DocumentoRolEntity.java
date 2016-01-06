package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.RolesEntity;
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
@Table(name = "GDO_TDORO")
public class DocumentoRolEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "DORO_DORO")
    private int idDocumentoRol;
    @Column(name = "DORO_COPI")
    private String copiaDocumentoRol;
    @Column(name = "DORO_CONT")
    private String controlDocumentoRol;
    @JoinColumn(name = "DORO_ROLE")
    @ManyToOne
    private RolesEntity rolesentityDocumentoRol;
    @JoinColumn(name = "DORO_DOCU")
    @ManyToOne
    private DocumentoEntity documentoentityDocumentoRol;
    

    public int getIdDocumentoRol() {
        return idDocumentoRol;
    }

    public void setIdDocumentoRol(int idDocumentoRol) {
        this.idDocumentoRol = idDocumentoRol;
    }

    public RolesEntity getRolesentityDocumentoRol() {
        return rolesentityDocumentoRol;
    }

    public void setRolesentityDocumentoRol(RolesEntity rolesentityDocumentoRol) {
        this.rolesentityDocumentoRol = rolesentityDocumentoRol;
    }

    public DocumentoEntity getDocumentoentityDocumentoRol() {
        return documentoentityDocumentoRol;
    }

    public void setDocumentoentityDocumentoRol(DocumentoEntity documentoentityDocumentoRol) {
        this.documentoentityDocumentoRol = documentoentityDocumentoRol;
    }

    public String getCopiaDocumentoRol() {
        return copiaDocumentoRol;
    }

    public void setCopiaDocumentoRol(String copiaDocumentoRol) {
        this.copiaDocumentoRol = copiaDocumentoRol;
    }

    public String getControlDocumentoRol() {
        return controlDocumentoRol;
    }

    public void setControlDocumentoRol(String controlDocumentoRol) {
        this.controlDocumentoRol = controlDocumentoRol;
    }
    
}
