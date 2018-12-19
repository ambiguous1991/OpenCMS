package com.jba.contoller;

import com.jba.dao.property.type.Property;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/")
public class Home {

    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping
    public String home(){

        Session sess = sessionFactory.getCurrentSession();

        List<Property> propertyList = sess.createQuery("from Property ", Property.class).getResultList();

        propertyList.forEach(
                property -> log.info(property.toString())
        );

        return "index";
    }
}
