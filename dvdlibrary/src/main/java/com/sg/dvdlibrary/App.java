/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author siyaa
 */
public class App {

    public static void main(String[] arg) {
//        UserIO myIO = new UserIOConsoleImpl();
//        DvdLibrarydao myDao=new DvdLibraryImpl();
//        DvdLibraryView View = new DvdLibraryView(myIO);
//        DvdLibraryController controller = new DvdLibraryController(myDao,View);

ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    DvdLibraryController controller=ctx.getBean("controller",DvdLibraryController.class);
        controller.run();
    }

}
