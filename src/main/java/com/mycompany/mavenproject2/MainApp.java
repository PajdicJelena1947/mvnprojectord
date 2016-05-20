package com.mycompany.mavenproject2;



import com.entity.Fizioterapeut;
import com.entity.Pacijent;
import com.entity.Fizijatar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;
import com.service.FizioterapeutService;
import com.service.PacijentService;
import com.service.FizijatarService;
import java.sql.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jelena.pajdic
 */
public class MainApp {
    public static void main(String[] args) throws MessagingException {
       ApplicationContext context = 
             new ClassPathXmlApplicationContext("/com/mycompany/mavenproject2/Bean2.xml");
       
       PacijentService kService= (PacijentService) context.getBean("kartonService");
      
       FizijatarService lService = (FizijatarService) context.getBean("lekarService");
       FizioterapeutService lfService = (FizioterapeutService) context.getBean("fizioterapeutService");
       Pacijent k = kService.vratiPacijenta("2712");
       System.out.println(k.getAdresa());
       Fizijatar lekar = lService.vratiLekara("12345");
  
      
       System.out.println(kService.emailFizijatra("2712"));
    }
}
