package iot.ah.demo.core.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 CREATE TABLE `user` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 `gmt_create` datetime NOT NULL COMMENT '创建时间',
 `gmt_modified` datetime NOT NULL COMMENT '修改时间',
 `name` varchar(256) NOT NULL COMMENT 'name',
 `phone` varchar(64) NOT NULL DEFAULT '' COMMENT 'phone',
 PRIMARY KEY (`id`),
 UNIQUE KEY `name` (`name`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户表'
 */
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    @Qualifier("mysql-jdbctemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(User user) {
        jdbcTemplate.update(
            "insert into user(gmt_create,gmt_modified,name,phone) values (?,?,?,?)",
            new Date(),
            new Date(),
            user.getName(),
            user.getPhone());
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update("delete from user where name = ?", name);
    }

    @Override
    public List<User> list() {
        return jdbcTemplate.query("select * from user limit 100", new UserMapper());
    }




    class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User record = new User();
            record.setId(resultSet.getLong("id"));
            record.setName(resultSet.getString("name"));
            record.setPhone(resultSet.getString("phone"));
            return record;
        }
    }
}
