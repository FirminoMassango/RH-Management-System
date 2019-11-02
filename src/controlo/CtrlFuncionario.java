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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import util.NewHibernateUtil;

/**
 *
 * @author Firmino Massango
 */
public class CtrlFuncionario {
 
    DaoGenerico<Funcionario> dao = new DaoGenerico();
    
    /**
     * Método que pega os dados da classe Funcionário e executa os métodos salvar e actualizar da classe Dao Genérico 
     * @param fu
     */
    public void gravarFuncionario(Funcionario fu) {
        
        Funcionario funcionarioExistente = pegarFuncionario(fu.getId());
        
        if (funcionarioExistente == null)
            dao.salvar(fu);
        else
            dao.actualizar(fu);
            
}
    /**
     * Método para remover os dados da base de dados com base no método remover da classe Dao Genérico
     * @param fu
     * @return 
     */
    public boolean removerFuncionario(Funcionario fu) {

          return dao.remover(fu);
    }
    
    /**
     * Método para actualizar os dados da base de dados a partir do método actualizar da classe Dao Genérico
     * @param fu
     */
    public void Actualizar(Funcionario fu) {

        
            dao.actualizar(fu);

    }
    
    
 /**
     * Método que retorna os dados armazenados no banco de dados
     * @return 
     */
    public static List<Funcionario> getTodosDAO() {

        List<Funcionario> lista = new ArrayList<>();

        Session session = null;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        try {

            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Funcionario.class);
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
    
    public static List<Funcionario> getTodosDAOdecrescente() {

        List<Funcionario> lista = new ArrayList<>();

        Session session = null;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();

        try {

            session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Funcionario.class);
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
     
    
            public List<Funcionario> listarActivos(String chave) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session sec = sf.openSession();

                 Query c = sec.createQuery("from funcionario where estado = 'activo'");
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
      public Funcionario pegarFuncionario(int id) {
        
        Funcionario obj = null;
        Session session = null;
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        
        try {
            
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            obj = (Funcionario) session.get(Funcionario.class, id);
            transaction.commit();
            
            
        } catch (HibernateException e) {
            System.out.println(e.getStackTrace());
        }finally{
            if (session != null)
                session.close();
        }
            
        return obj;
    }
    
}
