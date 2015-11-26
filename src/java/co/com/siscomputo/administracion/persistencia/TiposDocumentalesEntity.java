/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

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
@Table(name = "ADM_TTIDO")
public class TiposDocumentalesEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "TIDO_TIDO")
    private int idTipoDocumental;
    @Column(name = "TIDO_NOMB")
    private String nombreTipoDocumental;
    @Column(name = "TIDO_GENE")
    private String genracionTipoDocumental;
    @Column(name = "TIDO_INIC")
    private String inicialesTipoDocuemntal;
    @Column(name = "TIDO_CONS")
    private int consecutivoTipoDocuemntal;
    @Column(name = "TIDO_CREA")
    private int creadorTpoDocumental;
    @Column(name = "TIDO_UPDA")
    private int actualizaTipoDocumental;
    @Column(name = "TIDO_ESTA")
    private String estadoTipoDocuemtal;
    
    public int getIdTipoDocumental() {
        return idTipoDocumental;
    }

    public void setIdTipoDocumental(int idTipoDocumental) {
        this.idTipoDocumental = idTipoDocumental;
    }

    public String getNombreTipoDocumental() {
        return nombreTipoDocumental;
    }

    public void setNombreTipoDocumental(String nombreTipoDocumental) {
        this.nombreTipoDocumental = nombreTipoDocumental;
    }

    public String getGenracionTipoDocumental() {
        return genracionTipoDocumental;
    }

    public void setGenracionTipoDocumental(String genracionTipoDocumental) {
        this.genracionTipoDocumental = genracionTipoDocumental;
    }

    public String getInicialesTipoDocuemntal() {
        return inicialesTipoDocuemntal;
    }

    public void setInicialesTipoDocuemntal(String inicialesTipoDocuemntal) {
        this.inicialesTipoDocuemntal = inicialesTipoDocuemntal;
    }

    public int getConsecutivoTipoDocuemntal() {
        return consecutivoTipoDocuemntal;
    }

    public void setConsecutivoTipoDocuemntal(int consecutivoTipoDocuemntal) {
        this.consecutivoTipoDocuemntal = consecutivoTipoDocuemntal;
    }

    public int getCreadorTpoDocumental() {
        return creadorTpoDocumental;
    }

    public void setCreadorTpoDocumental(int creadorTpoDocumental) {
        this.creadorTpoDocumental = creadorTpoDocumental;
    }

    public int getActualizaTipoDocumental() {
        return actualizaTipoDocumental;
    }

    public void setActualizaTipoDocumental(int actualizaTipoDocumental) {
        this.actualizaTipoDocumental = actualizaTipoDocumental;
    }

    public String getEstadoTipoDocuemtal() {
        return estadoTipoDocuemtal;
    }

    public void setEstadoTipoDocuemtal(String estadoTipoDocuemtal) {
        this.estadoTipoDocuemtal = estadoTipoDocuemtal;
    }
    
    
}
