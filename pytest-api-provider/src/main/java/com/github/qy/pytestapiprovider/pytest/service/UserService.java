package com.github.qy.pytestapiprovider.pytest.service;

import com.github.qy.pytestapiprovider.pytest.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserService {
    private final ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();


    //删除user; 路径参数与查询参数
    public Integer deleteUser(Integer id) {
        User user = map.remove(id);
        //1为删除成功 否则为0（不存在）
        return user == null ? 0 : 1;
    }

    //更新user put请求 传json
    public String updateUser(User user) {
        map.put(user.getId(), user);
        return "success";
    }

    public String addUserWIthParams(Integer id, Integer age, String name) {
        User user = new User();
        user.setId(id).setAge(age).setName(name);
        boolean containsKey = map.containsKey(id);
        if (containsKey) return "fail,该用户已存在";
        else {
            map.put(id, user);
            return "success";
        }
    }


    //新增User post请求; 传json
    public String addUserWIthJson(User user) {
        Integer id = user.getId();
        boolean containsKey = map.containsKey(id);
        if (containsKey) return "fail,该用户已存在";
        else {
            map.put(id, user);
            return "success";
        }
    }


    public User searchById(Integer id) {
        return map.get(id);
    }

    //获取所有User
    public List<User> list() {
        List<User> list = map.values().stream().toList();
        log.info("获取所有User:{}", list);
        return list;
    }

    @PostConstruct
    private void dataInit() {
        User user1 = new User();
        user1.setId(1).setName("Mybatis").setAge(18);
        User user2 = new User();
        user2.setId(2).setName("Mybatis").setAge(18);
        User user3 = new User();
        user3.setId(3).setName("Mybatis").setAge(18);
        map.put(1, user1);
        map.put(2, user2);
        map.put(3, user3);
    }
}
