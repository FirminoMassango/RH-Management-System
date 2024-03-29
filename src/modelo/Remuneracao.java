package modelo;
// Generated 8/out/2019 1:22:44 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Remuneracao generated by hbm2java
 *
 * Classe responsável pela atribuição de subsídios
 *
 *
 * @author Firmino MAssango
 * @author Silja Ferro
 * @author Wínia Macarigue
 */
@Entity(name = "remuneracao")
public class Remuneracao{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    
    @Column(name = "salarioBase")
    private Double salarioBase;
    
    @Column(name = "subsidioDeFerias")
    private Double subsidioDeFerias;
    
    @Column(name = "subsidioDeAlimentacao")//, nullable = false)
    private Double subsidioDeAlimentacao;
    
    @Column(name = "subsidioDeTransporte")//, nullable = false)
    private Double subsidioDeTransporte;
    
    @Column(name = "subsidioDeCargo")
    private Double subsidioDeCargo;
    
    @Column(name = "subsidioDecimoTerceiro")
    private Double subsidioDecimoTerceiro;
    
    @Column(name = "bonus")
    private Double bonus;
    
    @Column(name = "aumentoSalarial")
    private Double aumentoSalarial;
    
    @Column(name = "descontoSalarial")
    private Double descontoSalarial;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Double getDescontoSalarial() {
        return descontoSalarial;
    }

    public void setDescontoSalarial(Double descontoSalarial) {
        this.descontoSalarial = descontoSalarial;
    }
    
    

    public Double getSubsidioDeFerias() {
        return this.subsidioDeFerias;
    }

    public void setSubsidioDeFerias(Double subsidioDeFerias) {
        this.subsidioDeFerias = subsidioDeFerias;
    }

    public Double getSubsidioDeAlimentacao() {
        return this.subsidioDeAlimentacao;
    }

    public void setSubsidioDeAlimentacao(Double subsidioDeAlimentacao) {
        this.subsidioDeAlimentacao = subsidioDeAlimentacao;
    }

    public Double getSubsidioDeTransporte() {
        return this.subsidioDeTransporte;
    }

    public void setSubsidioDeTransporte(Double subsidioDeTransporte) {
        this.subsidioDeTransporte = subsidioDeTransporte;
    }

    public Double getSubsidioDeCargo() {
        return this.subsidioDeCargo;
    }

    public void setSubsidioDeCargo(Double subsidioDeCargo) {
        this.subsidioDeCargo = subsidioDeCargo;
    }

    public Double getSubsidioDecimoTerceiro() {
        return this.subsidioDecimoTerceiro;
    }

    public void setSubsidioDecimoTerceiro(Double subsidioDecimoTerceiro) {
        this.subsidioDecimoTerceiro = subsidioDecimoTerceiro;
    }

    public Double getBonus() {
        return this.bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getAumentoSalarial() {
        return this.aumentoSalarial;
    }

    public void setAumentoSalarial(Double aumentoSalarial) {
        this.aumentoSalarial = aumentoSalarial;
    }

    public Double getPenalizacaoFerias() {
        return this.descontoSalarial;
    }

    public void setPenalizacaoFerias(Double penalizacaoFerias) {
        this.descontoSalarial = penalizacaoFerias;
    }

    public double atribuirSalarioLiquido() {
        return  getSalarioBase()+ 
                getSubsidioDeAlimentacao()
                + getSubsidioDeCargo()
                + getSubsidioDeFerias()
                + getSubsidioDeTransporte()
                + getSubsidioDecimoTerceiro()
                + getBonus() 
                ;
    }

    /**

    @Override
    public void descontoSalarial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
   
}
