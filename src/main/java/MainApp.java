
import com.entity.Fizioterapeut;
import com.entity.Pacijent;
import com.entity.Fizijatar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;
import com.excepitons.PostojiPregled;
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
    	ClassPathXmlApplicationContext context = 
             new ClassPathXmlApplicationContext("Bean2.xml");
       
       PacijentService kService= (PacijentService) context.getBean("pacijentService");
      
       FizijatarService lService = (FizijatarService) context.getBean("fizijatarService");
       FizioterapeutService fService= (FizioterapeutService) context.getBean("fizioterapeutService");
     List<Pregled> p =kService.sviPreglediPacijenta("2712");
     for(Pregled pe:p)
    	 System.out.println(pe.getBolest());
}
}
