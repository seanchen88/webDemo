package iot.ah.demo.controller;

import java.util.List;
import java.util.Map;

import iot.ah.demo.core.mysql.UserMapper;
import iot.ah.demo.core.mysql.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserMangeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/insert")
    public void insert(@RequestBody Map<String,Object> params) {
        String name = (String)params.get("name");
        String phone = (String)params.get("phone");
        User record = new User();
        record.setName(name);
        record.setPhone(phone);
        userMapper.insert(record);
        logger.info("insert user, name :{}, phone :{}", name, phone);
    }

    @RequestMapping(value = "/delete")
    public void delete(@RequestBody Map<String,Object> params) {
        String name = (String)params.get("name");
        userMapper.delete(name);
        logger.info("delete user, name :{}", name);
    }

    @RequestMapping(value = "/list")
    public List<User> list(){
        List<User> users = userMapper.list();
        return users;
    }

}
