package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao{
	 // create
    public void save(Object t);

    // delete
    public void delete(Object t);
    
    public void deleteById(Class clazz, int id);
    
   // public void deleteByProperty(Class<T> clazz,String property,String value);
  
    // update
    public void update(Object t);

    // query
    // query according to id
    public Object queryById(Class clazz, int id);
    public List<Object> queryAll(Class clazz);
  /*  
    // query according to property(seems only to support String)
    public List<T> queryByProperty(Class<T> clazz, String property,String value);
    
 // query according to property(seems only to support String)
    public List<T> queryByProperty(Class<T> clazz, String property,Integer value);
    
 // query according to property(seems only to support String)
    public List<T> fuzzyQueryByProperty(Class<T> clazz, String property, String value);
    
    //query all
    public long rowCount(Class<T> clazz);
    
    public List<T> queryAll(Class<T> clazz);

    *//**
    * query objects according to the condition
    *
    * @param hql
    * @return
    *//*
    public List<T> queryAllBySql(Class<T> clazz, String sql);

    *//**
    * query object according to condition
    *
    * @param hql
    * @return
    *//*
    public T queryObjectBySql(Class<T> clazz, String sql);
    
    *//**
     * query objects according to pagination
     *
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    public List<T> queryByPage(int pageNum, int pageSize, Class<T> clazz);*/
}
