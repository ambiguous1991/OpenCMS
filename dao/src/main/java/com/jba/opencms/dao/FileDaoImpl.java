package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.FileDao;
import com.jba.opencms.type.file.File;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileDaoImpl extends HibernateDao<File> implements FileDao {

    public FileDaoImpl(Class<File> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    @Override
    public File get(String path) {
        return getCurrentSession().createQuery("from " + File.class.getName() + " where path=:path", File.class)
                .setParameter("path", path).getSingleResult();
    }

    @Override
    public List<String> list(String path) {
        List<File> resultList = getCurrentSession().createQuery("from " + File.class.getName() + " where File.path like :path%", File.class)
                .setParameter("path", path).getResultList();
        if(resultList!=null){
            return resultList.stream().map(File::getName).collect(Collectors.toList());
        }
        else return Collections.emptyList();
    }
}
