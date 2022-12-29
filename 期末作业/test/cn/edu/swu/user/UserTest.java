package cn.edu.swu.user;

<<<<<<< HEAD

import java.sql.SQLException;
=======
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> ff52b43 (期末修改2)
import java.util.List;
import java.util.UUID;

public class UserTest {

<<<<<<< HEAD
    //添加用户
    public static void main(String[] args) throws SQLException {
        User admin = new User();
        admin.setId(UUID.randomUUID().toString());//java提供的工具类，用算法随机生成字符串
        admin.setName("管理员");
        admin.setUser("admin");
        admin.setPassword("admin");

        User guest = new User();
        guest.setId(UUID.randomUUID().toString());//java提供的工具类，用算法随机生成字符串
        guest.setName("匿名用户");
        guest.setUser("guest");
        guest.setPassword("guest");

        UserRepo.getInstance().save(admin);
        UserRepo.getInstance().save(guest);

        /*List<User> users = UserRepo.getInstance().getAll();//全部取出
        for(User user : users){
            System.out.println("删除"+user.getName());
            UserRepo.getInstance().delete(user);//删除
        }*/

=======
    public static void main(String[] args) throws SQLException {
        User admin = new User();
        admin.setId(UUID.randomUUID().toString());
        admin.setName("管理员");
        admin.setUser("admin");
        admin.setPassword("swu.edu.cn");

        User guest = new User();
        guest.setId(UUID.randomUUID().toString());
        guest.setName("匿名用户");
        guest.setUser("guest");
        guest.setPassword("guest");
        UserRepo.getInstance().save(admin);
        UserRepo.getInstance().save(guest);

        List<User> users = UserRepo.getInstance().getAll();
        for (User user : users) {
            System.out.println("删除" + user.getName());
            UserRepo.getInstance().delete(user);
        }
>>>>>>> ff52b43 (期末修改2)
    }
}
