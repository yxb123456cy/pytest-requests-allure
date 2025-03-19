package com.github.qy.pytestapiprovider.pytest.ctl;

import com.github.qy.pytestapiprovider.pytest.domain.User;
import com.github.qy.pytestapiprovider.pytest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/python/user")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
public class PytestController {
    private final UserService service;

    //获取全部user;
    @GetMapping("/list")
    public List<User> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public User searchById(@PathVariable("id") Integer id) {
        return service.searchById(id);
    }

    //新增User json参数;
    @PostMapping("/addWithJson")
    public String addUserWIthJson(@RequestBody User user) {
        return service.addUserWIthJson(user);
    }

    @PostMapping("/add")
    public String addUserWIthParams(@RequestParam("id") Integer id, @RequestParam("age") Integer age, @RequestParam("name") String name) {
        return service.addUserWIthParams(id, age, name);
    }

    @DeleteMapping("/delete")
    public Integer deleteUser(@RequestParam("id") Integer id) {
        return service.deleteUser(id);
    }

    @DeleteMapping("/del/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return service.deleteUser(id);
    }

    @PutMapping("/update")
    //更新user put请求 传json
    public String updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }


}
