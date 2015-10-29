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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_TCIUD")
public class CiudadEntity extends ObjetoTraza implements Serializable{
    @Id
    @Column(name = "CIUD_CIUD")
    private int idCiudad;
    @Column(name = "CIUD_CODI")
    private int codigoCiudad;
    @Column(name = "CIUD_NOMB")
    private String nombreCiudad;
    @JoinColumn(name = "CIUD_DEPT")
    @ManyToOne
    private DepartamentoEntity ciudadDepartamento;
    @Column(name = "CIUD_ESTA")
    private String estadoCiudad;
    @Column(name = "CIUD_CREA")
    private int creadorCiudad;
    @Column(name = "CIUD_UPDA")
    private int actualizadorCiudad;

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(int codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public DepartamentoEntity getCiudadDepartamento() {
        return ciudadDepartamento;
    }

    public void setCiudadDepartamento(DepartamentoEntity ciudadDepartamento) {
        this.ciudadDepartamento = ciudadDepartamento;
    }

    public String getEstadoCiudad() {
        return estadoCiudad;
    }

    public void setEstadoCiudad(String estadoCiudad) {
        this.estadoCiudad = estadoCiudad;
    }

    public int getCreadorCiudad() {
        return creadorCiudad;
    }

    public void setCreadorCiudad(int creadorCiudad) {
        this.creadorCiudad = creadorCiudad;
    }

    public int getActualizadorCiudad() {
        return actualizadorCiudad;
    }

    public void setActualizadorCiudad(int actualizadorCiudad) {
        this.actualizadorCiudad = actualizadorCiudad;
    }
    
    
}
