package dmt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author 刘烁
 */
public class PhoenixKnowledgeHandler {
    private final SessionFactory sessionFactory;

    public PhoenixKnowledgeHandler() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate-phoenix_knowledge.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }
    public List run() {
        List list = null;
        String hql = "from TbKnowledgeBase";
        try {
            Session session = sessionFactory.openSession();
            list = session.createQuery(hql).list();
            System.out.println(list.size());
            session.close();
        } catch(Exception ex) {
        }
        return list;
    }
    
    public List run1() {
        List list = null;
        String hql = "from TbUserCategory";
        try {
            Session session = sessionFactory.openSession();
            list = session.createQuery(hql).list();
            System.out.println(list.size());
            session.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }    
}
