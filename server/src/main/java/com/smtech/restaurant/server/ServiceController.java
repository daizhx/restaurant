package com.smtech.restaurant.server;

import com.smtech.restaurant.entities.Table;
import com.smtech.restaurant.server.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class ServiceController {

//    TableDao tableDao;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        Table table = new Table();

        return table.toString();
    }

    @RequestMapping("/table/all")
    @ResponseBody
    List<Table> getTables() {
//        return tableDao.findAll();
        return null;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceController.class, args);
    }
}