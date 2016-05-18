
import com.entity.Fizioterapeut;
import com.entity.Karton;
import com.entity.Lekar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;
import com.service.FizioterapeutService;
import com.service.KartonService;
import com.service.LekarService;
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
             new ClassPathXmlApplicationContext("Bean2.xml");
       
       KartonService kService= (KartonService) context.getBean("kartonService");
      
       LekarService lService = (LekarService) context.getBean("lekarService");
       FizioterapeutService lfService = (FizioterapeutService) context.getBean("fizioterapeutService");
       Karton k = kService.vratiPacijenta("2712");
       System.out.println(k.getAdresa());
       Lekar lekar = lService.vratiLekara("12345");
      
       System.out.println(lekar.getType());
       
       System.out.println(k.getType());
   
    }
}
