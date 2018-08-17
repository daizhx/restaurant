package com.smtech.restaurant.app;


import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.MainFrame;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.BorderLayoutEx;
import com.smtech.swing.common.view.ViewGroup;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimeZone;

public class RestaurantSystem {
    private static Logger logger = LoggerFactory.getLogger(RestaurantSystem.class);
    private static ViewGroup contentPanel;
    private static Process serverProcess;

    private RestaurantSystem(){}

    public static void main(String[] args) {
        // 初始化日志系统
        initLogSystem();

        // 设置界面风格
        setLookAndFeel();

        // 显示加载进度条
        createAndShowGUI();

        //start serverIDEA,after server started go to home page
        startServer();


        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });


    }

    private static void startServer() {
        //这种启动方式只用在idea中启动
        try {
            String cp = "E:\\restaurant\\server\\out\\production\\classes;E:\\restaurant\\server\\out\\production\\resources;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-web\\1.5.2.RELEASE\\27f29876980c079b46be16b759eafe92720f3338\\spring-boot-starter-web-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-data-jpa\\1.5.2.RELEASE\\9e2dfd470f0df4a7ba00a71ca462aeefbd382a4d\\spring-boot-starter-data-jpa-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.xerial\\sqlite-jdbc\\3.15.1\\acb44214a86abebf2f3a8969ecbb697850dd9c8e\\sqlite-jdbc-3.15.1.jar;E:\\restaurant\\entities\\out\\production\\classes;E:\\restaurant\\common\\out\\production\\classes;E:\\restaurant\\common\\out\\production\\resources;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter\\1.5.2.RELEASE\\c72938c86ed3beadb385f29bc2954c8ca3aa47ab\\spring-boot-starter-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-tomcat\\1.5.2.RELEASE\\354320ef371fc4fc126d1bb8564cd73085a48823\\spring-boot-starter-tomcat-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-validator\\5.3.4.Final\\2f6c8c0b646afe18e3ad205726729d3c4a85fe2e\\hibernate-validator-5.3.4.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-databind\\2.8.7\\6c3257ef458ac58a8da69a6dca3d2a15286d88c8\\jackson-databind-2.8.7.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-web\\4.3.7.RELEASE\\7b69fc68cdb74c1c92f72905af6995696fcb56aa\\spring-web-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-webmvc\\4.3.7.RELEASE\\d25b11a605589f176e3ecf1dcae62a991a18b377\\spring-webmvc-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-aop\\1.5.2.RELEASE\\9a21295c00a62bfaaa930ba5f7909fe26eea2562\\spring-boot-starter-aop-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-jdbc\\1.5.2.RELEASE\\bf431678f6e201fadac9b06f4627358c1554d4e0\\spring-boot-starter-jdbc-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-core\\5.0.12.Final\\e58bf1c660e6706d8e2cbb53bae110f574366102\\hibernate-core-5.0.12.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-entitymanager\\5.0.12.Final\\302a526f5058290e9cbd719a5caf9f248d344719\\hibernate-entitymanager-5.0.12.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\javax.transaction\\javax.transaction-api\\1.2\\d81aff979d603edd90dcd8db2abc1f4ce6479e3e\\javax.transaction-api-1.2.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.data\\spring-data-jpa\\1.11.1.RELEASE\\fa362aecd78883991f57a5d64e19f34b57a2c34d\\spring-data-jpa-1.11.1.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-aspects\\4.3.7.RELEASE\\fa0671826a42f6bac5145ffbc78075493dcb4e8b\\spring-aspects-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate.javax.persistence\\hibernate-jpa-2.1-api\\1.0.0.Final\\5e731d961297e5a07290bfaf3db1fbc8bbbf405a\\hibernate-jpa-2.1-api-1.0.0.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.alibaba\\fastjson\\1.2.46\\1510c47faaf3d484221ab20e4ec9d42cfa4984cb\\fastjson-1.2.46.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.squareup.okhttp3\\okhttp\\3.10.0\\7ef0f1d95bf4c0b3ba30bbae25e0e562b05cf75e\\okhttp-3.10.0.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot\\1.5.2.RELEASE\\46bb5d8c9ab5d3ef9e158ca5906ee7d3569befc1\\spring-boot-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-autoconfigure\\1.5.2.RELEASE\\8a9b8c747bc2c86eefde10330cb2984541bcb9d1\\spring-boot-autoconfigure-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-logging\\1.5.2.RELEASE\\32210889e1f9bb09ac134b9f43a117ed62f0bed2\\spring-boot-starter-logging-1.5.2.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-core\\4.3.7.RELEASE\\54fa2db94cc7222edc90ec71354e47cd1dc07f7b\\spring-core-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.yaml\\snakeyaml\\1.17\\7a27ea250c5130b2922b86dea63cbb1cc10a660c\\snakeyaml-1.17.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-core\\8.5.11\\72761f51fc7cef3ee19d4aafc7adc605df9f611f\\tomcat-embed-core-8.5.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-el\\8.5.11\\60253815b897166903bf5ec41219c5bb15333a69\\tomcat-embed-el-8.5.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-websocket\\8.5.11\\dfa65e7857d46630761c0571758a56f7cbd1e9ba\\tomcat-embed-websocket-8.5.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\javax.validation\\validation-api\\1.1.0.Final\\8613ae82954779d518631e05daa73a6a954817d5\\validation-api-1.1.0.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.jboss.logging\\jboss-logging\\3.3.0.Final\\3616bb87707910296e2c195dc016287080bba5af\\jboss-logging-3.3.0.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml\\classmate\\1.3.3\\864c8e370a691e343210cc7c532fc198cee460d8\\classmate-1.3.3.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-annotations\\2.8.0\\45b426f7796b741035581a176744d91090e2e6fb\\jackson-annotations-2.8.0.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-core\\2.8.7\\8b46f39c78476fb848c81a49fa807a9e9506dddd\\jackson-core-2.8.7.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-aop\\4.3.7.RELEASE\\3f243d685e4a8a78a0c291445c6d85560ec4d339\\spring-aop-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-beans\\4.3.7.RELEASE\\2de9f59f3202965438f3a02057d6ad8274636044\\spring-beans-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-context\\4.3.7.RELEASE\\34b66b0b7910122ef95ba4fff6da9238ef80a5de\\spring-context-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-expression\\4.3.7.RELEASE\\5257b6486e43d8c05674323fea5b415d4da72f38\\spring-expression-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.aspectj\\aspectjweaver\\1.8.9\\db28774f477f07220eac18d5ec9c4e01f48589d7\\aspectjweaver-1.8.9.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat\\tomcat-jdbc\\8.5.11\\4d156969f12963b5f9232e9fe68ab710c3318ad5\\tomcat-jdbc-8.5.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-jdbc\\4.3.7.RELEASE\\305c8db0f9552948aec093528cd01393cc98a646\\spring-jdbc-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.javassist\\javassist\\3.21.0-GA\\598244f595db5c5fb713731eddbb1c91a58d959b\\javassist-3.21.0-GA.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\antlr\\antlr\\2.7.7\\83cd2cd674a217ade95a4bb83a8a14f351f48bd0\\antlr-2.7.7.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.jboss\\jandex\\2.0.0.Final\\3e899258936f94649c777193e1be846387ed54b3\\jandex-2.0.0.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\dom4j\\dom4j\\1.6.1\\5d3ccc056b6f056dbf0dddfdf43894b9065a8f94\\dom4j-1.6.1.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate.common\\hibernate-commons-annotations\\5.0.1.Final\\71e1cff3fcb20d3b3af4f3363c3ddb24d33c6879\\hibernate-commons-annotations-5.0.1.Final.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.data\\spring-data-commons\\1.13.1.RELEASE\\4e4257f2eb3f191613b4b000d43e8d0c3ff4457e\\spring-data-commons-1.13.1.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-orm\\4.3.7.RELEASE\\d9b193994609086ea1f067af07e0af5f53303d92\\spring-orm-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-tx\\4.3.7.RELEASE\\b761cc783e49b5aa998ac63a721495a9f0f69f9c\\spring-tx-4.3.7.RELEASE.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\slf4j-api\\1.7.24\\3f6b4bd4f8dbe8d4bea06d107a3826469b85c3e9\\slf4j-api-1.7.24.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\jcl-over-slf4j\\1.7.24\\e6a8629079856a2aa7862c6327ccf6dd1988d7fc\\jcl-over-slf4j-1.7.24.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\com.squareup.okio\\okio\\1.14.0\\102d7be47241d781ef95f1581d414b0943053130\\okio-1.14.0.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\ch.qos.logback\\logback-classic\\1.1.11\\ccedfbacef4a6515d2983e3f89ed753d5d4fb665\\logback-classic-1.1.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\jul-to-slf4j\\1.7.24\\25a2be668cb2ad1d05d76c0773df73b4b53617fd\\jul-to-slf4j-1.7.24.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\log4j-over-slf4j\\1.7.24\\6ab46c51a3848286a0db3ba7b22037b3834c3c44\\log4j-over-slf4j-1.7.24.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat\\tomcat-juli\\8.5.11\\fa0b261ce002175b65ebb6ae8eb4345cb7e57da3\\tomcat-juli-8.5.11.jar;C:\\Users\\Administrator\\.gradle\\caches\\modules-2\\files-2.1\\ch.qos.logback\\logback-core\\1.1.11\\88b8df40340eed549fb07e2613879bf6b006704d\\logback-core-1.1.11.jar;";

//            String cp = "H:\\springprojects\\restaurant\\server\\out\\production\\classes;H:\\springprojects\\restaurant\\server\\out\\production\\resources;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-web\\1.5.2.RELEASE\\27f29876980c079b46be16b759eafe92720f3338\\spring-boot-starter-web-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-data-jpa\\1.5.2.RELEASE\\9e2dfd470f0df4a7ba00a71ca462aeefbd382a4d\\spring-boot-starter-data-jpa-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.xerial\\sqlite-jdbc\\3.15.1\\acb44214a86abebf2f3a8969ecbb697850dd9c8e\\sqlite-jdbc-3.15.1.jar;H:\\springprojects\\restaurant\\entities\\out\\production\\classes;H:\\springprojects\\restaurant\\common\\out\\production\\classes;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter\\1.5.2.RELEASE\\c72938c86ed3beadb385f29bc2954c8ca3aa47ab\\spring-boot-starter-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-tomcat\\1.5.2.RELEASE\\354320ef371fc4fc126d1bb8564cd73085a48823\\spring-boot-starter-tomcat-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-validator\\5.3.4.Final\\2f6c8c0b646afe18e3ad205726729d3c4a85fe2e\\hibernate-validator-5.3.4.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-databind\\2.8.7\\6c3257ef458ac58a8da69a6dca3d2a15286d88c8\\jackson-databind-2.8.7.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-web\\4.3.7.RELEASE\\7b69fc68cdb74c1c92f72905af6995696fcb56aa\\spring-web-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-webmvc\\4.3.7.RELEASE\\d25b11a605589f176e3ecf1dcae62a991a18b377\\spring-webmvc-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-aop\\1.5.2.RELEASE\\9a21295c00a62bfaaa930ba5f7909fe26eea2562\\spring-boot-starter-aop-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-jdbc\\1.5.2.RELEASE\\bf431678f6e201fadac9b06f4627358c1554d4e0\\spring-boot-starter-jdbc-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-core\\5.0.12.Final\\e58bf1c660e6706d8e2cbb53bae110f574366102\\hibernate-core-5.0.12.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate\\hibernate-entitymanager\\5.0.12.Final\\302a526f5058290e9cbd719a5caf9f248d344719\\hibernate-entitymanager-5.0.12.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\javax.transaction\\javax.transaction-api\\1.2\\d81aff979d603edd90dcd8db2abc1f4ce6479e3e\\javax.transaction-api-1.2.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.data\\spring-data-jpa\\1.11.1.RELEASE\\fa362aecd78883991f57a5d64e19f34b57a2c34d\\spring-data-jpa-1.11.1.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-aspects\\4.3.7.RELEASE\\fa0671826a42f6bac5145ffbc78075493dcb4e8b\\spring-aspects-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate.javax.persistence\\hibernate-jpa-2.1-api\\1.0.0.Final\\5e731d961297e5a07290bfaf3db1fbc8bbbf405a\\hibernate-jpa-2.1-api-1.0.0.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.alibaba\\fastjson\\1.2.46\\1510c47faaf3d484221ab20e4ec9d42cfa4984cb\\fastjson-1.2.46.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.squareup.okhttp3\\okhttp\\3.10.0\\7ef0f1d95bf4c0b3ba30bbae25e0e562b05cf75e\\okhttp-3.10.0.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot\\1.5.2.RELEASE\\46bb5d8c9ab5d3ef9e158ca5906ee7d3569befc1\\spring-boot-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-autoconfigure\\1.5.2.RELEASE\\8a9b8c747bc2c86eefde10330cb2984541bcb9d1\\spring-boot-autoconfigure-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.boot\\spring-boot-starter-logging\\1.5.2.RELEASE\\32210889e1f9bb09ac134b9f43a117ed62f0bed2\\spring-boot-starter-logging-1.5.2.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-core\\4.3.7.RELEASE\\54fa2db94cc7222edc90ec71354e47cd1dc07f7b\\spring-core-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.yaml\\snakeyaml\\1.17\\7a27ea250c5130b2922b86dea63cbb1cc10a660c\\snakeyaml-1.17.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-core\\8.5.11\\72761f51fc7cef3ee19d4aafc7adc605df9f611f\\tomcat-embed-core-8.5.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-el\\8.5.11\\60253815b897166903bf5ec41219c5bb15333a69\\tomcat-embed-el-8.5.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat.embed\\tomcat-embed-websocket\\8.5.11\\dfa65e7857d46630761c0571758a56f7cbd1e9ba\\tomcat-embed-websocket-8.5.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\javax.validation\\validation-api\\1.1.0.Final\\8613ae82954779d518631e05daa73a6a954817d5\\validation-api-1.1.0.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.jboss.logging\\jboss-logging\\3.3.0.Final\\3616bb87707910296e2c195dc016287080bba5af\\jboss-logging-3.3.0.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml\\classmate\\1.3.3\\864c8e370a691e343210cc7c532fc198cee460d8\\classmate-1.3.3.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-annotations\\2.8.0\\45b426f7796b741035581a176744d91090e2e6fb\\jackson-annotations-2.8.0.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.fasterxml.jackson.core\\jackson-core\\2.8.7\\8b46f39c78476fb848c81a49fa807a9e9506dddd\\jackson-core-2.8.7.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-aop\\4.3.7.RELEASE\\3f243d685e4a8a78a0c291445c6d85560ec4d339\\spring-aop-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-beans\\4.3.7.RELEASE\\2de9f59f3202965438f3a02057d6ad8274636044\\spring-beans-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-context\\4.3.7.RELEASE\\34b66b0b7910122ef95ba4fff6da9238ef80a5de\\spring-context-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-expression\\4.3.7.RELEASE\\5257b6486e43d8c05674323fea5b415d4da72f38\\spring-expression-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.aspectj\\aspectjweaver\\1.8.9\\db28774f477f07220eac18d5ec9c4e01f48589d7\\aspectjweaver-1.8.9.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat\\tomcat-jdbc\\8.5.11\\4d156969f12963b5f9232e9fe68ab710c3318ad5\\tomcat-jdbc-8.5.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-jdbc\\4.3.7.RELEASE\\305c8db0f9552948aec093528cd01393cc98a646\\spring-jdbc-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.javassist\\javassist\\3.21.0-GA\\598244f595db5c5fb713731eddbb1c91a58d959b\\javassist-3.21.0-GA.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\antlr\\antlr\\2.7.7\\83cd2cd674a217ade95a4bb83a8a14f351f48bd0\\antlr-2.7.7.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.jboss\\jandex\\2.0.0.Final\\3e899258936f94649c777193e1be846387ed54b3\\jandex-2.0.0.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\dom4j\\dom4j\\1.6.1\\5d3ccc056b6f056dbf0dddfdf43894b9065a8f94\\dom4j-1.6.1.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.hibernate.common\\hibernate-commons-annotations\\5.0.1.Final\\71e1cff3fcb20d3b3af4f3363c3ddb24d33c6879\\hibernate-commons-annotations-5.0.1.Final.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework.data\\spring-data-commons\\1.13.1.RELEASE\\4e4257f2eb3f191613b4b000d43e8d0c3ff4457e\\spring-data-commons-1.13.1.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-orm\\4.3.7.RELEASE\\d9b193994609086ea1f067af07e0af5f53303d92\\spring-orm-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.springframework\\spring-tx\\4.3.7.RELEASE\\b761cc783e49b5aa998ac63a721495a9f0f69f9c\\spring-tx-4.3.7.RELEASE.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\slf4j-api\\1.7.24\\3f6b4bd4f8dbe8d4bea06d107a3826469b85c3e9\\slf4j-api-1.7.24.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\jcl-over-slf4j\\1.7.24\\e6a8629079856a2aa7862c6327ccf6dd1988d7fc\\jcl-over-slf4j-1.7.24.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\com.squareup.okio\\okio\\1.14.0\\102d7be47241d781ef95f1581d414b0943053130\\okio-1.14.0.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\ch.qos.logback\\logback-classic\\1.1.11\\ccedfbacef4a6515d2983e3f89ed753d5d4fb665\\logback-classic-1.1.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\jul-to-slf4j\\1.7.24\\25a2be668cb2ad1d05d76c0773df73b4b53617fd\\jul-to-slf4j-1.7.24.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.slf4j\\log4j-over-slf4j\\1.7.24\\6ab46c51a3848286a0db3ba7b22037b3834c3c44\\log4j-over-slf4j-1.7.24.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\org.apache.tomcat\\tomcat-juli\\8.5.11\\fa0b261ce002175b65ebb6ae8eb4345cb7e57da3\\tomcat-juli-8.5.11.jar;C:\\Users\\daizhx\\.gradle\\caches\\modules-2\\files-2.1\\ch.qos.logback\\logback-core\\1.1.11\\88b8df40340eed549fb07e2613879bf6b006704d\\logback-core-1.1.11.jar";
            Runtime runtime = Runtime.getRuntime();
//            runtime.addShutdownHook(new Thread(){
//                @Override
//                public void run() {
//                    System.out.println("11111111111111111111111111111111111111111111111");
//                    if(serverProcess != null){
//                        System.out.println("222222222222222222222222222222222222222");
//                        serverProcess.destroy();
//                    }
//                }
//            });
            serverProcess = runtime.exec("java -classpath " + cp + " com.smtech.restaurant.server.RestaurantServer");
            BufferedReader br = new BufferedReader(new InputStreamReader(serverProcess.getInputStream(), "UTF-8"));
            String line = null;

            while ((line = br.readLine()) != null) {
//            log.info(line);
                System.out.println(line);
                if("serverstarted".equals(line)){
                    System.out.println("server has started");
                    showHomeView();
                    break;
                }
            }

            System.out.println("======================================================");
            br = new BufferedReader(new InputStreamReader(serverProcess.getErrorStream(),"GBK"));
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            int ev = serverProcess.exitValue();
            System.out.println("server process exit:"+ev);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initLogSystem() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        PropertyConfigurator.configure(RestaurantSystem.class.getResource("/log.properties"));
        logger.error("Initialize log system success");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        showSetupView();
    }


    /**
     * 设置界面风格
     */
    private static void setLookAndFeel() {
        // 解决JAVA输入文本框出现提示框的问题
        System.setProperty("java.awt.im.style", "on-the-spot");
        // 防止激活输入法时白屏
        System.setProperty("sun.java2d.noddraw", "true");

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
//            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            logger.error(StackTraceToString.getExceptionTrace(e));
        }

    }


    // show start window
    private static void showSetupView() {
        contentPanel = new ViewGroup();
        contentPanel.setBackgroundImage("bg.png");
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setImageDisplayMode(ViewGroup.SCALED);
        contentPanel.setLayout(new BorderLayoutEx());

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(200,40));

        contentPanel.add(progressBar,BorderLayout.CENTER);

        MainFrame frame = MainFrame.getInstance();
//        JCalendarChooser.parent = frame;
        frame.setContentPane(contentPanel);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);
    }

    private static void showHomeView(){
        DlgBase dlg = DlgManager.getInstance().getDlg(DlgHome.class);
        dlg.pack();
        dlg.setVisible(true);
    }
}
