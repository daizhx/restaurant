package com.smtech.restaurant.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.enilu.guns"},basePackageClasses = {SpringContextHolder.class})
@EnableJpaRepositories(basePackages = {"com.smtech.restaurant.server.dao","cn.enilu.guns.dao"})
@EntityScan({"com.smtech.restaurant.entities","cn.enilu.guns.bean.entity"})
public class RestaurantServer implements ApplicationRunner{

    protected final static Logger logger = LoggerFactory.getLogger(RestaurantServer.class);

    @PersistenceUnit
    EntityManagerFactory emf;

    public static void main(String[] args){
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        //启动
        SpringApplication.run(RestaurantServer.class, args);
        //启动线程
        new ReceiveUDP().start();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("项目启动后执行初始化任务!");
        //TODO 通过JPA执行原生sql写入初始数据
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin(); //这个不加会报错
//
//        Query query = null;
//        query = em.createNativeQuery("INSERT INTO `t_sys_cfg` VALUES ('1', 'JS_API_TICKET', 'test', '微信JSAPI_TICKET(produt:kgt8ON7yVITDhtdwci0qeYBa_xOxkaEccepDVZel0heq1M9pKgrfFWOlX2MfHEt122psCpElf4V5eePHPouJPg)')");
//        query.executeUpdate();
//
//        em.getTransaction().commit();
//        em.close();
    }
}