/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe responsável por armazenar os salários de acordo com o cargo
 * @author whisfirm
 */

@Entity(name = "salario")
public class Salario{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    
    @Column(name = "contabilista")//, nullable = false)
    private double contabilista;
    
    @Column(name = "cozinheiro")//, nullable = false)
    private double cozinheiro;
    
    @Column(name = "electrecista")//, nullable = false)
    private double electricista;
    
    @Column(name = "frentista")//, nullable = false)
    private double frentista;
    
    @Column(name = "guarda")//, nullable = false)
    private double guarda;
    
    @Column(name = "mecanico")//, nullable = false)
    private double mecanico;
    
    @Column(name = "motorista")//, nullable = false)
    private double motorista;
    
    @Column(name = "serrelheiro")//, nullable = false)
    private double serrelheiro;

   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getContabilista() {
        return contabilista;
    }

    public void setContabilista(double contabilista) {
        this.contabilista = contabilista;
    }

    public double getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(double cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public double getElectricista() {
        return electricista;
    }

    public void setElectricista(double electricista) {
        this.electricista = electricista;
    }

    public double getFrentista() {
        return frentista;
    }

    public void setFrentista(double frentista) {
        this.frentista = frentista;
    }

    public double getGuarda() {
        return guarda;
    }

    public void setGuarda(double guarda) {
        this.guarda = guarda;
    }

    public double getMecanico() {
        return mecanico;
    }

    public void setMecanico(double mecanico) {
        this.mecanico = mecanico;
    }

    public double getMotorista() {
        return motorista;
    }

    public void setMotorista(double motorista) {
        this.motorista = motorista;
    }

    public double getSerrelheiro() {
        return serrelheiro;
    }

    public void setSerrelheiro(double serrelheiro) {
        this.serrelheiro = serrelheiro;
    }
    


    @Override
    public String toString() {
        return "Salario{" + "id=" + id+ ", contabilista=" + contabilista + ", cozinheiro=" + cozinheiro + ", electricista=" + electricista + ", frentista=" + frentista + ", guarda=" + guarda + ", mecanico=" + mecanico + ", motorista=" + motorista + ", serrelheiro=" + serrelheiro + '}';
    }

    
     
    
//    public static void main(String[] args) {
//        CtrlSalario dao = new CtrlSalario();
//
//        Salario salario = new Salario();
//        for (Salario sal : dao.salarioo("")) {
//
//            System.out.println(sal.getContabilista());
//
//        }
//
//    }
}
