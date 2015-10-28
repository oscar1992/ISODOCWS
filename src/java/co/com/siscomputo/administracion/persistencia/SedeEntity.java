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
@Table(name = "ADM_SEDE")
public class SedeEntity extends ObjetoRetornaEntity implements  Serializable{
    @Id
    @Column(name = "SEDE_SEDE")
    private  int idSede;
    @Column(name = "SEDE_NOMB")
    private String nombreSede;
    @Column(name = "SEDE_CODI")
    private int codigoSede;
    @Column(name = "SEDE_ESTA")
    private String estadoSede;
    @Column(name = "SEDE_CREA")
    private int creadorSede;
    @Column(name = "SEDE_UPDA")
    private int actualizadorSede;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getEstadoSede() {
        return estadoSede;
    }

    public void setEstadoSede(String estadoSede) {
        this.estadoSede = estadoSede;
    }

    public int getCreadorSede() {
        return creadorSede;
    }

    public void setCreadorSede(int creadorSede) {
        this.creadorSede = creadorSede;
    }

    public int getActualizadorSede() {
        return actualizadorSede;
    }

    public void setActualizadorSede(int actualizadorSede) {
        this.actualizadorSede = actualizadorSede;
    }
    
    
}
