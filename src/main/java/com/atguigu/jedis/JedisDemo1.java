package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Set;

/**
 * @author Gao
 * @Date 2021-09-14-15:50
 * @Description
 */
public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.111.128",6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }
    @Test
    public void tess(){
        Jedis jedis = new Jedis("192.168.111.128", 6379);
        jedis.set("k1","lucy");
        jedis.mset("k2","ss","k3","ee");
        List<String> mget = jedis.mget("k1", "k2", "k3");
        System.out.println(mget);
        Set<String> keys = jedis.keys("*");
        for (String key : keys){
            System.out.println(key);
        }
    }

}
