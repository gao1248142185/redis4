package com.atguigu.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author Gao
 * @Date 2021-09-14-16:53
 * @Description
 */
public class CodeTset {
    public static void main(String[] args) {
        verifyCode("13836219614");

//        getRedisCode("13836219614","77");
    }

    public static void getRedisCode(String phone,String code){
        Jedis jedis = new Jedis("192.168.111.128", 6379);
        String codeKey = "verifyCode" + phone + ":code";
        String rediscode = jedis.get(codeKey);
        System.out.println(rediscode);
        if (rediscode.equals(code)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        jedis.close();

    }


    public static void verifyCode(String phone){
        Jedis jedis = new Jedis("192.168.111.128", 6379);
        String countKey = "verifyCode" + phone + ":count";
        String codeKey = "verifyCode" + phone + ":code";
        String count = jedis.get(countKey);
        if (count == null){
            jedis.setex(countKey,24*60*60,"1");

        }else if (Integer.parseInt(count)<=2){
            jedis.incr(countKey);
        }else {
            System.out.println("不能发送了");
            jedis.close();
        }

        String code = getCode();
        jedis.setex(codeKey,120,code);
        jedis.close();

    }

    public static String getCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int ran = random.nextInt(10);
            code += ran;
        }
        return code;
    }
}
