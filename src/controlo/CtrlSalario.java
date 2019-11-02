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
import modelo.Funcionario;
import modelo.Salario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.NewHibernateUtil;

/**
 *
 * @author whisfirm
 */
public class CtrlSalario {
 
DaoGenerico dao = new DaoGenerico();
    
    /**
     * Método que pega os dados da classe Salario e executa os métodos salvar e actualizar da classe Dao Genérico 
     * @param sa
     */
    public void gravarSalario(Salario sa) {
        
       // Salario salarioExistente = pegarSalario(sa.getId());
        
       // if (salarioExistente == null)
            dao.salvar(sa);
        //else
        //    dao.actualizar(sa);
            
}
    /**
     * Método para remover os dados da base de dados com base no método remover da classe Dao Genérico
     * @param sa
     * @return 
     */
    public boolean removerSalario(Salario sa) {

          return dao.remover(sa);
    }
    
    /**
     * Método para actualizar os dados da base de dados a partir do método actualizar da classe Dao Genérico
     * @param sa
     */
    public void Actualizar(Salario sa) {

        
            dao.actualizar(sa);

    }
    
    
    /**
     * Método que retorna os dados armazenados no banco de dados
     * @return 
     */
               public List<Salario> salarioo(String chave) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session sec = sf.openSession();

                 Query c = sec.createQuery("from salario");
                 List<Funcionario> list = c.list();
                 
                    if (list.size() > 0) {
                            return c.list();
                    } else {
                     JOptionPane.showMessageDialog(null, "Não encontrado!");
                return null;
        }
    }
    

    /**
     * Método para a utilização dos métodos salvar ou actulizar da classe DAO Genérico quando necessário
     * @param id
     * @return 
     */
//      public Salario pegarSalario(int id) {
//        
//        Salario obj = null;
//        Session session = null;
//        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
//        
//        try {
//            
//            session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//            obj = (Salario) session.get(Salario.class, id);
//            transaction.commit();
//            
//            
//        } catch (HibernateException e) {
//            System.out.println(e.getStackTrace());
//        }finally{
//            if (session != null)
//                session.close();
//        }
//            
//        return obj;
//    }
}