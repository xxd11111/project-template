package com.xxd.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Ehcache 缓存管理对象单例
 * 磁盘 + 内存
 * @Author xxd
 * @Date 2021/12/13
 * @Version 1.0
 */

public class EhcacheManagerHelper {

    private final String EHCACHE_PATH = "/ehcache.xml";
    private final String CACHE_NAME = "localCache";
    private CacheManager manager;
    private Cache cache;

    private EhcacheManagerHelper() {
        init();
    }

    private static class SingletonInstance {
        private static final EhcacheManagerHelper singleton = new EhcacheManagerHelper();
    }

    public static EhcacheManagerHelper getInstance() {
        return SingletonInstance.singleton;
    }

    /**
     * 每次开始使用缓存对象需要初始化
     */
    public void init() {
        manager = CacheManager.create(this.getClass().getResourceAsStream(EHCACHE_PATH));
        cache = manager.getCache(CACHE_NAME);
    }

    /**
     * 把key放入缓存中
     */
    public void put(String key, Object value) {
        cache.put(new Element(key, value));
        flush();
    }

    /**
     * 根据key获取缓存元素
     */
    public Object get(String key) {
        Element element = cache.get(key);
        return element != null ? element.getObjectValue() : null;
    }

    /**
     * 根据key移除缓存
     */
    public void remove(String key) {
        cache.remove(key);
        flush();
    }

    /**
     * 构建内存与磁盘的关系
     */
    public void flush() {
        cache.flush();
    }

    /**
     * 关闭缓存管理器
     */
    public void shutdown() {
        manager.shutdown();
    }
}
