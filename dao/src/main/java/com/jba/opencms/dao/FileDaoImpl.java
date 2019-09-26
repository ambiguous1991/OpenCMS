package com.jba.opencms.dao;

import com.jba.opencms.dao.ifs.FileDao;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.internal.predicate.LikePredicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileDaoImpl extends HibernateDao<File> implements FileDao {

    public FileDaoImpl(Class<File> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    @Override
    public File get(String path) {
        List<File> files = getCurrentSession().createQuery("from " + File.class.getName() + " where path=:path", File.class)
                .setParameter("path", path).getResultList();
        if(files.isEmpty()) return null;
        else if (files.size()==1) return files.get(0);
        else throw new NonUniqueResultException(files.size());
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

    @Override
    public List<FileProjection> getImagesMetadata() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<FileProjection> query = builder.createQuery(FileProjection.class);
        Root<File> file = query.from(File.class);
        query.multiselect(file.get("name"), file.get("path"), file.get("mime"), file.get("description"));
        CriteriaBuilder.In<Object> mime = builder.in(file.get("mime"));
        mime.value("image/png");
        mime.value("image/jpg");
        mime.value("image/jpeg");
        mime.value("image/bmp");

        query.where(mime);

        return getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public List<FileProjection> getFileMetadata() {
        return null;
    }
}
