package com.wx.handle.common;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wangyunlong
 * @mark 待验证完善
 *
 */
@Component
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static void addUser(){
        //redisTemplate.opsForValue().set("aaa","abc");

        List<String> list = new ArrayList<>();
        list.add("info1");
        list.add("info2");
        list.add("info3");

        lset("list1", list);

        List<String> list1 = lget("list1", 0, -1, String.class);

        for(String s : list1){
            System.out.println("========" + s);
        }
    }

    /**
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     *
     * @param key
     * @return
     */
    public static String get(String key){
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     *
     * @param key
     * @param list
     * @param <T>
     */
    public static <T> void lset(String key, List<T> list){
        List<String> strList = new ArrayList<>(list.size());
        for (T t : list){
            strList.add(JSONUtils.toJSONString(t));
        }
        redisTemplate.opsForList().leftPushAll(key,strList);
    }

    /**
     *
     * @param key
     * @param start
     * @param end
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> lget(String key, long start, long end, Class<E> clazz){

        List<String> caches = redisTemplate.opsForList().range(key, start, end);

        List<E> result = new ArrayList<>(caches.size());

        for(String s : caches){
            E e1 = (E) JSONUtils.parse(s);
            result.add(e1);
        }

        return result;
    }
}
