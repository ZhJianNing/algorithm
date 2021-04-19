package com.zjn.algorithm.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRUCache
 * 参考：https://blog.csdn.net/qq_26440803/article/details/83795122
 *
 * @author zjn
 * @date 2021/4/19
 **/
public class LRUCache<V> {

    //容量
    private int capacity = 1024;
    //Node记录表
    private Map<String, TwoNode<String, V>> table = new ConcurrentHashMap<>();
    //双向链表头部
    private TwoNode<String, V> head;
    //双向链表尾部
    private TwoNode<String, V> tail;

    public LRUCache(int capacity) {
        this();
        this.capacity = capacity;
    }

    //初始化双向链表
    public LRUCache() {
        head = new TwoNode<>();
        tail = new TwoNode<>();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;

    }

    //双向链表内部类
    public static class TwoNode<K, V> {
        private K key;
        private V value;
        TwoNode<K, V> prev;
        TwoNode<K, V> next;

        public TwoNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public TwoNode() {
        }
    }

    //获取数据【最新访问数据放到头部】
    public V get(String key) {
        //先hash计算定位到双向链表
        TwoNode<String, V> node = table.get(key);
        if (null == node) {
            return null;
        }
        //如果存在，需要把对应的node放到头部
        //第一步，截断节点，让node的前一个节点和后一个节点相连
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //第二步，把node节点放到链表头部（把node放到head和原head.next中间）（最热数据），需要修改三个几点的属性
        //node节点
        node.prev = head;
        node.next = head.next;
        //head节点
        head.next.prev = node;
        //head原下一个节点
        head.next = node;
        //todo 第三步，为什么还要再put一遍,感觉这一步多余
        table.put(key, node);
        return node.value;
    }

    //存取数据【新放入数据放到头部，如果容量不够需要删掉尾部数据】
    public void put(String key, V value) {
        TwoNode<String, V> node = table.get(key);
        //如果没有需要存入数据
        if (null == node) {
            //判断是否超容量
            if (table.size() == capacity) {
                //todo 这里和原作者有出入，需要后期验证
                //如果超了就删掉尾部节点
                tail.prev.prev.next = tail;//tail的上上个节点的next指向tail
                tail.prev = tail.prev.prev;//tail的prev指向tail的上上个节点
                table.remove(tail.prev.key);//最后删除tail的上一个节点
            }
            //添加新节点
            node = new TwoNode<>();
            node.key = key;
            node.value = value;
            table.put(key, node);
            //todo 这里原作者只把新的node放入table中，但是没有和链表中其他节点进行关联，在if分支之外做了关联【解决】
            //todo 可以把设置头部节点和删除末位节点封装，用到的地方还挺多
        }
        //如果存在新节点，那就把node移动到表头
        //node节点
        node.prev = head;
        node.next = head.next;
        //head节点
        head.next.prev = node;
        //head原下一个节点
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache<TwoNode> cache = new LRUCache<>(4);
        TwoNode<String, Integer> node1 = new TwoNode<>("key1", 1);
        TwoNode<String, Integer> node2 = new TwoNode<>("key2", 1);
        TwoNode<String, Integer> node3 = new TwoNode<>("key3", 1);
        TwoNode<String, Integer> node4 = new TwoNode<>("key4", 1);
        TwoNode<String, Integer> node5 = new TwoNode<>("key5", 1);
        cache.put("key1", node1);
        cache.put("key2", node2);
        cache.put("key3", node3);
        cache.put("key4", node4);
        cache.get("key2");
        cache.put("key5", node5);
        cache.get("key2");


    }


}



