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
@Table(name = "ADM_TEMPR")
public class EmpresaEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "EMPR_EMPR")
    private int idEmpresa;
    @Column(name = "EMPR_NOMB")
    private String nombreEmpresa;
    @Column(name = "EMPR_CODI")
    private int codigoEmpresa;
    @Column(name = "EMPR_ESTA")
    private String estadoEmpresa;
    @Column(name = "EMPR_CREA")
    private int creadorEmpresa;
    @Column(name = "EMPR_UPDA")
    private int actualizadorEmpresa;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(String estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }

    public int getCreadorEmpresa() {
        return creadorEmpresa;
    }

    public void setCreadorEmpresa(int creadorEmpresa) {
        this.creadorEmpresa = creadorEmpresa;
    }

    public int getActualizadorEmpresa() {
        return actualizadorEmpresa;
    }

    public void setActualizadorEmpresa(int actualizadorEmpresa) {
        this.actualizadorEmpresa = actualizadorEmpresa;
    }
    
    
}
