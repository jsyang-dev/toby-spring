package springbook.user.dao;

import springbook.user.domain.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDao {
    void add(User user);

    User get(String id);

    void deleteAll();

    Integer getCount();

    List<User> getAll();
}
