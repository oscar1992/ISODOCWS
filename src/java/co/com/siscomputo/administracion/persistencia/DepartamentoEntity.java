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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ADM_DEPT")
public class DepartamentoEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "DEPT_DEPT")
    private int idDepartamento;
    @Column(name = "DEPT_CODI")
    private int codigoDepartamento;
    @Column(name = "DEPT_NOMB")
    private String nombreDepartamento;
    @JoinColumn(name = "DEPT_PAIS")
    @ManyToOne
    private PaisEntity idPais;
    @Column(name = "DEPT_ESTA")
    private String estadoDepartamento;
    @Column(name = "DEPT_CREA")
    private int creadorDepartamento;
    @Column(name = "DEPT_UPDA")
    private int actualizadorDepartamento;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    } 

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public PaisEntity getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisEntity idPais) {
        this.idPais = idPais;
    }

    public String getEstadoDepartamento() {
        return estadoDepartamento;
    }

    public void setEstadoDepartamento(String estadoDepartamento) {
        this.estadoDepartamento = estadoDepartamento;
    }

    public int getCreadorDepartamento() {
        return creadorDepartamento;
    }

    public void setCreadorDepartamento(int creadorDepartamento) {
        this.creadorDepartamento = creadorDepartamento;
    }

    public int getActualizadorDepartamento() {
        return actualizadorDepartamento;
    }

    public void setActualizadorDepartamento(int actualizadorDepartamento) {
        this.actualizadorDepartamento = actualizadorDepartamento;
    }
    
    
}
