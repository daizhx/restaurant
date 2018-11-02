package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.order.ui.DlgAuth;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//点菜程序作为独立程序入口
public class Order {

    public static SessionFactory sessionFactory1;

    @Autowired
    private DlgAuth dlgAuth;


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DlgAuth dlg = (DlgAuth) context.getBean("dlgAuth");
        dlg.display();

        //1，借助hibernate初始化数据库,更新数据库
//        sessionFactory1 = new Configuration().configure().addAnnotatedClass(Food.class).buildSessionFactory();
        //TODO 2，开启UDP消息接收服务程序用于接收需要处理的消息

        //3，进入主界面
//        DlgAuth dlgAuth = (DlgAuth) DlgManager.getInstance().getDlg(DlgAuth.class);

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
