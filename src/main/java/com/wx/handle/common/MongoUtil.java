package com.wx.handle.common;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * MongoUtil工具类
 * @author wangyunlong
 * @mark 待测试
 *
 */
public class MongoUtil {

    private static MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     *
     * @param t
     * @param collectionName
     * @param <T>
     */
    public static <T> void insert(String collectionName, T t){
        /******   save与insert的区别  save为insertOrUpdate   insert仅为insert   *****/
        // mongoTemplate.save(t, collectionName);
        mongoTemplate.insert(t, collectionName);
    }

    /**
     *
     * @param list
     * @param collectionName
     * @param <T>
     */
    public static <T> void insertBatch(String collectionName, List<T> list){
        mongoTemplate.insert(list, collectionName);
    }

    /**
     *
     * @param field
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String collectionName, String field, Object value, Class<T> clazz){
        return mongoTemplate.findOne(new Query(Criteria.where(field).is(value)),clazz,collectionName);
    }

    /**
     *
     * @param collectionName
     * @param field
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> getList(String collectionName, String field, Object value, Class<E> clazz){
        return mongoTemplate.find(new Query(Criteria.where(field).is(value)),clazz,collectionName);
    }

    /**
     *
     * @param collectionName
     * @param paramsMap
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String collectionName, Map<String,Object> paramsMap, Class<T> clazz){
        return mongoTemplate.findOne(new Query(getCriteria(paramsMap)),clazz,collectionName);
    }

    /**
     *
     * @param collectionName
     * @param paramsMap
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> getList(String collectionName, Map<String,Object> paramsMap, Class<E> clazz){
        return mongoTemplate.find(new Query(getCriteria(paramsMap)),clazz,collectionName);
    }

    /**
     *
     * @param collectionName
     * @param field
     * @param value
     */
    public static void delete(String collectionName, String field, Object value){
        WriteResult result= mongoTemplate.remove(new Query(Criteria.where(field).is(value)),collectionName);
        System.out.format("MongoDB删除数据：%d条",result.getN());
    }

    /**
     *
     * @param collectionName
     * @param paramsMap
     */
    public static void delete(String collectionName, Map<String,Object> paramsMap){
        WriteResult result= mongoTemplate.remove(new Query(getCriteria(paramsMap)),collectionName);
        System.out.format("MongoDB删除数据：%d条",result.getN());
    }

    /**
     *
     * @param collectionName
     * @param field
     * @param value
     * @param t
     * @param <T>
     */
    public static <T> void update(String collectionName, String field, Object value, T t){
        mongoTemplate.updateFirst(new Query(Criteria.where(field.equals("id") ? "_id" : field).is(value)), getUpdate(t), collectionName);
    }

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> DBObject getDBObject(T t){
        DBObject object = new BasicDBObject();
        Field[] fields = t.getClass().getDeclaredFields();
        for(Field field : fields){
            if ("serialVersionUID".equals(field.getName())) continue;
            field.setAccessible(true);
            try {
                object.put(field.getName(), field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     *
     * @param list
     * @param <T>
     * @return
     */
    private static <T> List<DBObject> getDBObjects(List<T> list){
        List<DBObject> dbList = new ArrayList<>();
        DBObject object = null;
        for(T t : list){
            object = getDBObject(t);
            if(object != null) dbList.add(object);
        }

        return dbList;
    }

    /**
     *
     * 多条件查询拼装方法
     *
     */
    private static Criteria getCriteria(Map<String,Object> paramsMap){
        Set<String> keys = paramsMap.keySet();
        Iterator<String> keysIte = keys.iterator();
        Criteria criteria = new Criteria();
        String key = null;
        while (keysIte.hasNext()){
            key = keysIte.next();
            criteria.where(key).is(paramsMap.get(key));
        }
        return criteria;
    }

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Update getUpdate(T t){
        Update update = new Update();
        Field[] fields = t.getClass().getDeclaredFields();
        // Method 1:调用get方法
//        StringBuffer methodName = null;
//        for(Field field : fields){
//            methodName.append("get").append(field.getName().substring(0,1).toUpperCase()).append(field.getName().substring(1));
//            try {
//                Method method = clazz.getMethod(methodName.toString(), clazz);
//                Object object = method.invoke(t);
//                update.push(field.getName(), object);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            methodName = null;
//        }
        // Method 2:filed.get(对象)直接获取该属性的值
        for(Field field : fields){
            if ("serialVersionUID".equals(field.getName())) continue;
            field.setAccessible(true);
            try {
                update.set(field.getName(), field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return update;
    }
}
