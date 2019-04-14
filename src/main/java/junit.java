import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * @Title junit
 * @Description
 * @Copyright: ��Ȩ���� (c) 2018 - 2019
 * @Company: ������������
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\9\21 0021 9:02
 */
public class junit {

    @Test
    public void test1()
    {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.auth("zhongguo");
        System.out.println(jedis.ping());
        jedis.set("k1","1");
        jedis.set("k2","2");
        Set<String> set= jedis.keys("*");
        System.out.println(set);
    }

    @Test
    public void test2() throws InterruptedException {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.auth("zhongguo");
        jedis.select(0);
        System.out.println(jedis.ping());
        Integer bal;
        Integer debt;
        Integer money=10;
        Integer x = Integer.parseInt(jedis.get("bal"));
        jedis.watch("bal");
        Thread.sleep(8000);
        Integer y = Integer.parseInt(jedis.get("bal"));
        if(x!=y)
        {
            jedis.unwatch();
            System.out.println("ˢ������");
        }
        else {
            Transaction transaction = jedis.multi();
            transaction.decrBy("bal",money);
            transaction.incrBy("debt",money);
            transaction.exec();
            jedis.unwatch();
            bal=Integer.parseInt(jedis.get("bal"));
            debt=Integer.parseInt(jedis.get("debt"));
            System.out.println("ˢ���ɹ�");
            System.out.println("ʣ����"+bal);
            System.out.println("��ǰǷ��"+debt);
        }
    }
}
