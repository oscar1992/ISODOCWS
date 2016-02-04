/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.UsuarioEntity;
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
@Table(name = "GDO_TUSDO")
public class UsuarioDocumentoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "USDO_USDO")
    private int idUsuarioDocumento;
    @JoinColumn(name = "USDO_USUA")
    @ManyToOne
    private UsuarioEntity usuarioUsuarioDocumento;
    @JoinColumn(name = "USDO_DOCU")
    @ManyToOne
    private DocumentoEntity documentoUsuarioDocumento;

    public int getIdUsuarioDocumento() {
        return idUsuarioDocumento;
    }

    public void setIdUsuarioDocumento(int idUsuarioDocumento) {
        this.idUsuarioDocumento = idUsuarioDocumento;
    }

    public UsuarioEntity getUsuarioUsuarioDocumento() {
        return usuarioUsuarioDocumento;
    }

    public void setUsuarioUsuarioDocumento(UsuarioEntity usuarioUsuarioDocumento) {
        this.usuarioUsuarioDocumento = usuarioUsuarioDocumento;
    }

    public DocumentoEntity getDocumentoUsuarioDocumento() {
        return documentoUsuarioDocumento;
    }

    public void setDocumentoUsuarioDocumento(DocumentoEntity documentoUsuarioDocumento) {
        this.documentoUsuarioDocumento = documentoUsuarioDocumento;
    }

}
