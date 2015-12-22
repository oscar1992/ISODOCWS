/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.gestiondocumental.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoRetornaEntity;
import co.com.siscomputo.administracion.persistencia.AccionEntity;
import co.com.siscomputo.administracion.persistencia.GrupoUsuariosEntity;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "GDO_TGRDO")
public class GrupoDocumentoEntity extends ObjetoRetornaEntity implements Serializable {

    @Id
    @Column(name = "GRDO_GRDO")
    private int idGrupoDocumento;
    @JoinColumn(name = "GRDO_DOCU")
    @ManyToOne
    private DocumentoEntity documentoGrupoDocumento;
    @JoinColumn(name = "GRDO_GRUP")
    @ManyToOne
    private GrupoUsuariosEntity grupousuariosGrupoDocumento;
    @JoinColumn(name = "GRDO_ACCI")
    @ManyToOne
    private AccionEntity accionGrupoDocumento;
    @Column(name = "GRDO_FECH")
    private String fecha;

    public int getIdGrupoDocumento() {
        return idGrupoDocumento;
    }

    public void setIdGrupoDocumento(int idGrupoDocumento) {
        this.idGrupoDocumento = idGrupoDocumento;
    }

    public DocumentoEntity getDocumentoGrupoDocumento() {
        return documentoGrupoDocumento;
    }

    public void setDocumentoGrupoDocumento(DocumentoEntity documentoGrupoDocumento) {
        this.documentoGrupoDocumento = documentoGrupoDocumento;
    }

    public GrupoUsuariosEntity getGrupousuariosGrupoDocumento() {
        return grupousuariosGrupoDocumento;
    }

    public void setGrupousuariosGrupoDocumento(GrupoUsuariosEntity grupousuariosGrupoDocumento) {
        this.grupousuariosGrupoDocumento = grupousuariosGrupoDocumento;
    }

    public AccionEntity getAccionGrupoDocumento() {
        return accionGrupoDocumento;
    }

    public void setAccionGrupoDocumento(AccionEntity accionGrupoDocumento) {
        this.accionGrupoDocumento = accionGrupoDocumento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

     
}
