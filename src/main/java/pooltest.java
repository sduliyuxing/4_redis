import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Title pooltest
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\9\21 0021 15:41
 */
public class pooltest {
    public static void main(String[] args) {
        JedisPool jedisPool1=pool.getinstance();
        JedisPool jedisPool2=pool.getinstance();
        System.out.println(jedisPool1==jedisPool2);
        Jedis jedis=null;
        jedis=jedisPool1.getResource();
        jedis.auth("zhongguo");
        jedis.set("boot.run","boot.run");
        System.out.println(jedis.get("boot.run"));
        pool.release(jedisPool1,jedis);
    }
}
