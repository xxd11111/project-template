package com.xxd.controller;

import com.xxd.ehcache.EhcacheManagerHelper;
import com.xxd.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author xxd
 * @Date 2021/12/13
 * @Version 1.0
 */
@CacheConfig(cacheManager = "ehCacheCacheManager")
@RestController
public class EhcacheController {

    // 非注解使用
    /**
     * 通过CacheManagerHelper获取缓存信息
     * */
    @GetMapping("ehcache1/{id}")
    public String getUserById(@PathVariable String id) {
        String result = (String) EhcacheManagerHelper.getInstance().get(id);
        if (result == null) {
            User user = new User();
            user.setId(Long.parseLong(id));
            user.setName( "张三");
            user.setPassword(String.valueOf(Math.random() * 100));
            result = user.toString();
            EhcacheManagerHelper.getInstance().put(id, result);
        }
        return result;
    }

    //使用注解的话要配置config类，及开启application上的enableCaching注解

    /**
     * @param userId
     * @return
     * @Cacheable： 1.方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     * 2.去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     * 3.没有查到缓存就调用目标方法
     * 4.将目标方法返回的结果，放进缓存中
     * 5.condition：指定符合条件的情况下才缓存；
     */
    @GetMapping("ehcache2/{userId}")
    @Cacheable(value = "localCache", key = "#userId")
    public String getUserByIdWithInject(@PathVariable String userId) {
        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setName( "张三");
        user.setPassword(String.valueOf(Math.random() * 100));
        return user.toString();
    }


    /**
     * @param userId
     * @return
     * @Cacheable： 1.方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     * 2.去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     * 3.没有查到缓存就调用目标方法
     * 4.将目标方法返回的结果，放进缓存中
     * 5.condition：指定符合条件的情况下才缓存；
     */
    @Cacheable(value = "UserCache", key = "#userId", condition = "#userId!=''")
    public User findById(String userId) {
        System.out.println(new Date().getTime() + "进入UserService.findById,当前userId为:" + userId);
        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setName( "张三");
        user.setPassword(String.valueOf(Math.random() * 100));
        return user;
    }

    /**
     * @param user
     * @return
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存 运行时机：
     * 1、先调用目标方法
     * 2、将目标方法的结果缓存起来
     */
    @CachePut(value = "UserCache", key = "#user.userId")
    public User updateUser(User user) {
        System.out.println(new Date().getTime() + "进入UserService.updateUser,当前userId为:" + user.getId());
        user.setId(user.getId());
        user.setName( "张三");
        user.setPassword(String.valueOf(Math.random() * 100));
        return user;
    }

    /**
     * @param userId
     * @CacheEvict：缓存清除 key：指定要清除的数据
     * beforeInvocation = true：代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(value = "UserCache", key = "#userId", beforeInvocation = true)
    public void deleteUser(String userId) {
        System.out.println(new Date().getTime() + "进入UserService.deleteUser,当前userId为:" + userId);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null,null);
    }

}
