/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 *Classe responsável pela comunicação entre o programa e o banco de dados
 * @author Firmino Massango
 */
public class DaoGenerico<Y> {
    
    /**
     * Método para grava os dados na base de dados
     * @param obj
     * @return 
     */
    public boolean salvar(Y obj) {

        
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();
  
        Transaction tx = s.beginTransaction();

        try {
            s.saveOrUpdate(obj);
            tx.commit();
             JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            JOptionPane.showMessageDialog(null, "Erro ao salvar");
            return false;

        } finally {
            s.close();

        }
    }

    
    /**
     * Método para actualizar
     * @param obj
     * @return 
     */
    public boolean actualizar(Y obj) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        Transaction tx = s.beginTransaction();

        try {
            s.update(obj);
            tx.commit();
            JOptionPane.showMessageDialog(null,"Actualizado com sucesso");
            return true;

        } catch (HibernateException e) {
            System.out.println(e.getStackTrace());
             JOptionPane.showMessageDialog(null,"Erro ao actualizar");
            return false;

        } finally {
            if (s != null)
                s.close();

        }

    }

    /**
     * Método para remover
     * @param obj
     * @return 
     */
    public boolean remover(Y obj) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        
        Transaction tx = s.beginTransaction();

        try {
            s.delete(obj);
            tx.commit();
            
            JOptionPane.showMessageDialog(null,"Removido com sucesso");
            return true;

        } catch (HibernateException  e) {
            System.out.println(e.getStackTrace());
            JOptionPane.showMessageDialog(null,"Erro ao salvar");

            return false;

        } finally {
            if (s != null)
                s.close();
        }
    }

}