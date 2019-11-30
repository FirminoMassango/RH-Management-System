/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Utilizadores;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.NewHibernateUtil;

/**
 *
 * @author Firmino Massango
 */
public class CtrlUser {
    
    
    /**
     * Método que retorna os dados armazenados no banco de dados
     * @return 
     */
      public List<Utilizadores> listar(String chave) {
                SessionFactory sf = NewHibernateUtil.getSessionFactory();
                Session sec = sf.openSession();

                 Query c = sec.createQuery("from utilizadores");
                 List<Utilizadores> list = c.list();
                 
                    if (list.size() > 0) {
                            return c.list();
                    } else {
                     JOptionPane.showMessageDialog(null, "Não encontrado!");
                return null;
        }
    }
      
}
