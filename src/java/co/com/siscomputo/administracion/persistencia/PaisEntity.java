/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.siscomputo.administracion.persistencia;

import co.com.siscomputo.administracion.entites.ObjetoTraza;
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
@Table(name = "ADM_PAIS")
public class PaisEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "PAIS_PAIS")
    private int idPais;
    @Column(name = "PAIS_CODI")
    private int codPais;
    @Column(name = "PAIS_NOMB")
    private String nombrePais;
    @Column(name = "PAIS_ESTA")
    private String estadoPais;
    @Column(name = "PAIS_CREA")
    private int creadorPais;
    @Column(name = "PAIS_UPDA")
    private int actualizadorPais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getEstadoPais() {
        return estadoPais;
    }

    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }

    public int getCreadorPais() {
        return creadorPais;
    }

    public void setCreadorPais(int creadorPais) {
        this.creadorPais = creadorPais;
    }

    public int getActualizadorPais() {
        return actualizadorPais;
    }

    public void setActualizadorPais(int actualizadorPais) {
        this.actualizadorPais = actualizadorPais;
    }
    
    
    
}
