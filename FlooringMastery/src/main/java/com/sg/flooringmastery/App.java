/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.flooringController;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.orderDao;
import com.sg.flooringmastery.dao.orderDaoImpl;
import com.sg.flooringmastery.service.NotFoundException;
import com.sg.flooringmastery.service.orderService;
import com.sg.flooringmastery.service.orderServiceImpl;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;
import com.sg.flooringmastery.ui.flooringView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author dsmelser
 */
public class App {

    public static void main(String[] args) throws PersistenceException, NotFoundException {
//        UserIO io = new UserIOConsoleImpl();
//        flooringView view = new flooringView(io);
//        orderDao dao = new orderDaoImpl("data");
//        orderService service = new orderServiceImpl(dao);
//        flooringController controller = new flooringController(view,service);
//        
         ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        flooringController controller
                = ctx.getBean("controller", flooringController.class);
        controller.run();
    }
    
 
}
