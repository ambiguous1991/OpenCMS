package com.jba.opencms.web.controller;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.dao.HibernateDao;
import com.jba.opencms.dao.ifs.EntryDao;
import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private EntryDao entryDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<Entry> menu = new ArrayList<>();
        menu.add(entryDao.findOne(10));
        menu.add(entryDao.findOne(11));
        menu.add(entryDao.findOne(12));

        model.addAttribute("menu", menu);

        return "index";
    }

}
