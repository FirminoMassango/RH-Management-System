/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.DaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Remuneracao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *
 * @author whisfirm
 */
public class CtrlRemuneracao {
    
    DaoGenerico<Remuneracao> dao = new DaoGenerico<>();
    
    /**
     * Método que pega os dados da classe Remuneração e executa os métodos salvar e actualizar da classe Dao Genérico 
     * @param re
     */
    public void gravarRemuneracao(Remuneracao re) {
        
            dao.salvar(re);
            
}
    /**
     * Método para remover os dados da base de dados com base no método remover da classe Dao Genérico
     * @param re
     * @return 
     */
    public boolean removerRemuneracao(Remuneracao re) {

          return dao.remover(re);
    }
    
    /**
     * Método para actualizar os dados da base de dados a partir do método actualizar da classe Dao Genérico
     * @param re
     */
    public void Actualizar(Remuneracao re) {

        
            dao.actualizar(re);

    }
    
    
    
     /**
     * Método que retorna os dados armazenados no banco de dados
     * @return 
     */
               public List<Remuneracao> remunn(String chave) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session sec = sf.openSession();

                 Query c = sec.createQuery("from remuneracao");
                 List<Remuneracao> list = c.list();
                 
                    if (list.size() > 0) {
                            return c.list();
                    } else {
                     JOptionPane.showMessageDialog(null, "Não encontrado!");
                return null;
        }
    }
    
//               public static void main(String[] args) {
//                   
//                 Remuneracao remuneracao = new Remuneracao();
//                 CtrlRemuneracao daoRem = new CtrlRemuneracao();
//
//            for (Remuneracao rem : daoRem.remunn("")) {
//
//                //remuneracao.getBonus();
//                remuneracao.setSubsidioDeAlimentacao(rem.getSubsidioDeAlimentacao());
//                remuneracao.setSubsidioDeTransporte(rem.getSubsidioDeTransporte());
//
//            
//
//        }
//            
//                   System.out.println(remuneracao.getSubsidioDeAlimentacao()+remuneracao.getSubsidioDeAlimentacao());
//        
//    }

}
