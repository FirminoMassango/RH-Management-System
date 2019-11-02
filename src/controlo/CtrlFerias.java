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
import modelo.Ferias;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import util.NewHibernateUtil;

/**
 *
 * @author Firmino Massango
 */
public class CtrlFerias {
    
    DaoGenerico<Ferias> dao = new DaoGenerico();
    
    /**
     * Método que pega os dados da classe Férias e executa os métodos salvar e actualizar da classe Dao Genérico 
     * @param fe
     */
    public void gravarFerias(Ferias fe) {
        

            dao.salvar(fe);

}
    /**
     * Método para remover os dados da base de dados com base no método remover da classe Dao Genérico
     * @param fe
     * @return 
     */
    public boolean removerFerias(Ferias fe) {

          return dao.remover(fe);
    }
    
    /**
     * Método para actualizar os dados da base de dados a partir do método actualizar da classe Dao Genérico
     * @param fe
     */
    public void Actualizar(Ferias fe) {

        
            dao.actualizar(fe);

    }
    
    
    /**
     * Método que retorna os dados armazenados no banco de dados
     * @return 
     */
    public static List<Ferias> getTodosDAO() {

        List<Ferias> lista = new ArrayList<>();

        Session session = null;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        try {

            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Ferias.class);
            lista = criteria.list();
            return lista;

        } catch (HibernateException ex) {
            ex.getStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return lista;
    }
    
    public static List<Ferias> getTodosDAOdecrescente() {

        List<Ferias> lista = new ArrayList<>();

        Session session = null;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        try {

            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Ferias.class);
            criteria.addOrder(Order.desc("id"));
            lista = criteria.list();
            return lista;

        } catch (HibernateException ex) {
            ex.getStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return lista;
    }
    
    
    

                public List<Ferias> listarFer(String chave) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session sec = sf.openSession();

                 Query c = sec.createQuery("from ferias");
                 List<Ferias> list = c.list();
                 
                    if (list.size() > 0) {
                            return c.list();
                    } else {
                     JOptionPane.showMessageDialog(null, "Não encontrado!");
                return null;
        }
    }
}
