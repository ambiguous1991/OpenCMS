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
import java.util.*;
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
        CriteriaQuery<FileProjection> query = byMime(Arrays.asList("image/png", "image/jpg", "image/jpeg", "image/bmp"));

        return getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public List<FileProjection> getScriptMetadata() {
        CriteriaQuery<FileProjection> query = byMime(Collections.singletonList("text/javascript"));

        return getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public List<FileProjection> getStylesheetMetadata() {
        CriteriaQuery<FileProjection> query = byMime(Collections.singletonList("text/css"));

        return getCurrentSession().createQuery(query).getResultList();
    }

    private CriteriaQuery<FileProjection> fileProjectionCriteriaQuery(Map<String, List<String>> in){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();;
        CriteriaQuery<FileProjection> query = builder.createQuery(FileProjection.class);
        Root<File> file = query.from(File.class);
        query.multiselect(file.get("name"), file.get("path"), file.get("mime"), file.get("description"));

        for(String key: in.keySet()){
            CriteriaBuilder.In<Object> inQuery = builder.in(file.get(key));
            for(String value: in.get(key)){
                inQuery.value(value);
            }
            query.where(inQuery);
        }
        return query;
    }

    private CriteriaQuery<FileProjection> byMime(List<String> mimes){
        HashMap<String, List<String>> mime = new HashMap<>();
        mime.put("mime", mimes);
        return fileProjectionCriteriaQuery(mime);
    }

    private CriteriaQuery<FileProjection> all(){
        return fileProjectionCriteriaQuery(new HashMap<>());
    }

    @Override
    public List<File> findAll(List<String> mimes) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<File> query = builder.createQuery(File.class);
        Root<File> file = query.from(File.class);
        CriteriaBuilder.In<Object> mime = builder.in(file.get("mime"));

        for(String mimeValue: mimes){
            mime.value(mimeValue);
        }

        query.where(mime);
        return getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public List<FileProjection> getFileMetadata() {
        CriteriaQuery<FileProjection> all = all();
        return getCurrentSession().createQuery(all).getResultList();
    }
}
