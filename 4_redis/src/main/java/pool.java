import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title pool
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\9\21 0021 15:24
 */
public class pool {
    private static JedisPool jedisPool=null;
    private pool(){}
    public static JedisPool getinstance()
    {
       if (jedisPool==null)
       {
           synchronized (pool.class)
           {
               if (jedisPool==null)
               {
                   JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
                   jedisPoolConfig.setMaxTotal(1000);
                   jedisPoolConfig.setMaxIdle(32);
                   jedisPoolConfig.setTestOnBorrow(true);
                   jedisPool=new JedisPool(jedisPoolConfig,"127.0.0.1",6379,100*1000,"zhongguo");
               }
           }
       }
       return jedisPool;
    }
    public static void release(JedisPool jedisPool,Jedis jedis)
    {
       jedisPool.returnResourceObject(jedis);
    }
}
