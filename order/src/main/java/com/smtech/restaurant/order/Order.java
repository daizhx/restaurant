package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//点菜程序作为独立程序入口
public class Order {

    public static SessionFactory sessionFactory1;
    public static SessionFactory sessionFactory2;

    public static void main(String[] args) {
        //1,初始化数据库
        sessionFactory1 = new Configuration().configure().addAnnotatedClass(Food.class).buildSessionFactory();

//
//        DlgStart dlg = (DlgStart) DlgManager.getInstance().getDlg(DlgStart.class);
//        dlg.setSize(500,500);
//        dlg.setVisible(true);
    }

    /* Method to CREATE an employee in the database */
    public Integer addFood(String name, float price){
        SessionFactory factory = sessionFactory1;
        Session session = factory.openSession();
        Transaction tx = null;
        Integer ID = null;

        try {
            tx = session.beginTransaction();
            Food f = new Food();
            f.setName(name);
            f.setPrice(price);
            f.setGuQing(true);
            ID = (Integer) session.save(f);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ID;
    }
}
