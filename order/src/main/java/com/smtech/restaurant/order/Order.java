package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//点菜程序作为独立程序入口
public class Order {

    public static SessionFactory sessionFactory1;
    public static SessionFactory sessionFactory2;

    public static void main(String[] args) {
        //1，借助hibernate初始化数据库,更新数据库
        sessionFactory1 = new Configuration().configure().addAnnotatedClass(Food.class).buildSessionFactory();
        //TODO 2，开启UDP消息接收服务程序用于接收需要处理的消息

        //3，进入主界面
        Order o = new Order();
        o.addFoodJpa("aaaa", new Float(1.2));

//
//        DlgStart dlg = (DlgStart) DlgManager.getInstance().getDlg(DlgStart.class);
//        dlg.setSize(500,500);
//        dlg.setVisible(true);
    }

    public void addFoodJpa(String name, float price){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Food f = new Food();
        f.setName(name);
        f.setPrice(price);
//        f.setGuQing(true);

        em.persist(f);

        em.getTransaction().commit();
        em.close();
        emf.close();
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
