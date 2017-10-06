package com.company.app.dao;

import com.company.app.SessionFactoryBuilder;
import com.company.app.dao.interfaces.Dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao<T,ID extends Serializable> implements Dao<T,ID> {

  private Class<T> persistentClass;
  private Session session;

  @SuppressWarnings("unchecked")
  public AbstractDao(){
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T findById(ID id) {
    return getSession().load(this.getPersistentClass(), id);
  }

  @Override
  public List<T> findAll() {
    return this.findByCriteria();
  }

  @SuppressWarnings("unchecked")
  protected List<T> findByCriteria(Criterion... criterion){
    Criteria crit = this.getSession().createCriteria(this.getPersistentClass());

    for(Criterion c: criterion){
      crit.add(c);
    }
    return (List<T>) crit.list();

  }

  @Override
  public T save(T entity) {
    this.getSession().saveOrUpdate(entity);
    return entity;
  }

  @Override
  public void delete(T entity) {
    this.getSession().delete(entity);
  }

  @Override
  public void flush() {
    this.getSession().flush();
  }

  @Override
  public void clear() {
    this.getSession().clear();
  }

  @Override
  public void setSession(Session session) {
    this.session = session;
  }

  protected Session getSession(){
    if(this.session == null){
      this.session = SessionFactoryBuilder.get().getCurrentSession();
    }
    return this.session;
  }
}
