/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Firmino Massango
 * @author Silja Ferro
 * @author Wínia Macaringue
 */

/**
 * Classe responável pela definição de métodos que constituem o processo de gestão de subsídios
 */
public interface Subsidios {
    
    /**
     * Método responsável pela tribuição de salário ao funcionário
     */
    public abstract void atribuirSubsidio();
    
    /**
     * Método responsável por descontar o salário do funcionário de acordo o número de faltas
     */
    public abstract void descontoSalarial();
    
   // public abstract void promocao();
    
}
