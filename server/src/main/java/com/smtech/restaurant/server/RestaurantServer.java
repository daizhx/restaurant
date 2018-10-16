package com.smtech.restaurant.server;

import cn.enilu.guns.bean.entity.system.Cfg;
import cn.enilu.guns.dao.system.CfgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.io.*;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.enilu.guns"},basePackageClasses = {SpringContextHolder.class})
@EnableJpaRepositories(basePackages = {"com.smtech.restaurant.server.dao","cn.enilu.guns.dao"})
@EntityScan({"com.smtech.restaurant.entities","cn.enilu.guns.bean.entity"})
public class RestaurantServer implements ApplicationRunner{

    protected final static Logger logger = LoggerFactory.getLogger(RestaurantServer.class);

    @PersistenceUnit
    EntityManagerFactory emf;

    @Autowired
    CfgRepository cfgRepository;

    public static void main(String[] args){
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        //启动
        SpringApplication.run(RestaurantServer.class, args);
        //启动线程
        new ReceiveUDP().start();
    }

    @Override
    public void run(ApplicationArguments args){
        logger.info("项目启动后执行初始化任务!");
        //通过JPA执行原生sql写入初始数据
        //写入guns-lite框架的初始化数据
        Cfg cfg = cfgRepository.findByCfgName("init");
        if(cfg == null){
            logger.info("第一次启动系统，准备初始化数据!");
            //第一次启动，写入初始化数据
            String dir = System.getProperty("user.dir");
            try {
                File f = new File(dir + File.separator + "DB" + File.separator + "init.sql");
//                FileInputStream fis = new FileInputStream(dir + File.separator + "DB" + File.separator + "init.sql");
                BufferedReader br = new BufferedReader(new FileReader(f));
                StringBuilder sqlSB = new StringBuilder();
                String str;
//                while ((str = br.readLine()) != null){
//                    sqlSB.append(str);
//                }

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin(); //这个不加会报错

                while ((str = br.readLine()) != null){
                    Query query = em.createNativeQuery(str);
                    query.executeUpdate();
                }
//                Query query = em.createNativeQuery(sqlSB.toString());
//                query.executeUpdate();

                em.getTransaction().commit();
                em.close();

            } catch (FileNotFoundException e) {
                logger.error("数据库初始化文件不存在");
                return;
            } catch (IOException e) {
                logger.error("数据库初始化失败");
                return;
            }

            cfg = new Cfg();
            cfg.setCfgName("init");
            cfg.setCfgValue("true");
            cfgRepository.save(cfg);
            logger.info("第一次启动系统，初始化数据完成!");

        }


    }
}