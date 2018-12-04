package iot.ah.demo.core.mysql;

import java.util.List;

public interface UserMapper {


    void insert(User user);

    void delete(String name);

    List<User> list();

}
