package com.zjn.algorithm.hash;

import java.util.*;

/**
 * TestHashCircle   测试类
 *背景：
 * 开始：
 * 引入一个redis集群【高可用集群，即每台redis数据一样，冗余数据】作为缓存，后来需要缓存的数据量增大，需要扩容缓存
 * 层，怎么办？
 *
 * 然后：
 * 做数据分片，根据数据 的key按照某种逻辑进行hash计算，再根据redis集群的个数【比如3个集群】进行取模，这样不同的集群上
 * 保存不同的数据，扩容问题解决。
 * 但是不能动态扩容，如果后续继续增加集群个数，会导致很多key不能定位到原有的集群【每个集群都有一部分数据key不能再被命中】，
 * 导致缓存失效可能引发 缓存雪崩问题，怎么办？
 *
 * 改进：
 * hash环【2^32-1 范围，即int无符合取值范围，顺时针增大】的概念被提出，不再根据数据key来进行hash计算，而是根据每个
 * redis集群特征【如ip、名字等】进行hash计算，计算的值落到环上某个点上，然后再计算数据key的hash值，如果计算结果正好
 * 落到某个集群的hash值重合那就定位到这个集群，如果没有和集群hash值重合那就在hash环上顺时针查找下一个集群的hash点然后
 * 定位到这个集群，这样再添加集群【只会影响某一个集群的key】，问题解决。
 * 但是新问题出现了，比如a、b、c 三个集群，计算出出来的hash点不均匀，比如a的hash点比b的hash小，但是a距离b特别近，导致
 *（a，b）区间很小，即定位到b集群上的key很少，怎么办？
 *
 *
 * 改进：
 * 在加入一层虚拟层，在hash环上，每个集群计算多个hash点，【即多个虚拟hash点】，比如每个集群计算1000个hash点【这1000个hash
 * 点可能不是连续的中间可能有其他集群的虚拟hash点】，然后增加一步从虚拟hash点到集群的映射，就能做到数据均由分布【理论上虚拟
 * 点越多扩容带来的影响越小】
 *
 *
 *
 *
 *
 * 【缓存雪崩-很多key缓存失效，压力转移到数据库】
 * 【缓存击穿-某一个key缓存失效，但是这个key的访问并发特别高，压力转移到数据库】
 * 【缓存穿透-访问的key是不存在的（数据库中也是不存在的），很多key缓存失效】
 *
 *
 * @author zjn
 * @date 2021/4/15
 **/
public class TestHashCircle {
    // 机器节点IP前缀
    private static final String IP_PREFIX = "192.168.0.";

    public static void main(String[] args) {
        // 每台真实机器节点上保存的记录条数
        Map<String, Integer> map = new HashMap<String, Integer>();

        // 真实机器节点, 模拟10台
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        for (int i = 1; i <= 10; i++) {
            map.put(IP_PREFIX + i, 0); // 初始化记录
            Node<String> node = new Node<String>(IP_PREFIX + i, "node" + i);
            nodes.add(node);
        }

        IHashService iHashService = new HashService();
        // 每台真实机器引入500个虚拟节点
        ConsistentHash<Node<String>> consistentHash = new ConsistentHash<Node<String>>(iHashService, 500, nodes);

        // 将5000条记录尽可能均匀的存储到10台机器节点上
        for (int i = 0; i < 5000; i++) {
            // 产生随机一个字符串当做一条记录，可以是其它更复杂的业务对象,比如随机字符串相当于对象的业务唯一标识
            String data = UUID.randomUUID().toString() + i;
            // 通过记录找到真实机器节点
            Node<String> node = consistentHash.get(data);
            // 再这里可以能过其它工具将记录存储真实机器节点上，比如MemoryCache等
            // ...
            // 每台真实机器节点上保存的记录条数加1
            map.put(node.getIp(), map.get(node.getIp()) + 1);
        }

        // 打印每台真实机器节点保存的记录条数
        for (int i = 1; i <= 10; i++) {
            System.out.println(IP_PREFIX + i + "节点记录条数：" + map.get(IP_PREFIX + i));
        }
    }
}
