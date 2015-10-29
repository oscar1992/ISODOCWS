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
@Table(name = "ADM_TSEEM")
public class SedeEmpresaEntity extends ObjetoRetornaEntity implements Serializable{
    @Id
    @Column(name = "SEEM_SEEM")
    private int idSedeEmpresa;
    @JoinColumn(name = "SEEM_SEDE")
    @ManyToOne
    private SedeEntity idSede;
    @JoinColumn(name = "SEEM_EMPR")
    @ManyToOne
    private EmpresaEntity idEmpresa;

    public int getIdSedeEmpresa() {
        return idSedeEmpresa;
    }

    public void setIdSedeEmpresa(int idSedeEmpresa) {
        this.idSedeEmpresa = idSedeEmpresa;
    }

    public SedeEntity getIdSede() {
        return idSede;
    }

    public void setIdSede(SedeEntity idSede) {
        this.idSede = idSede;
    }

    public EmpresaEntity getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(EmpresaEntity idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    
}
