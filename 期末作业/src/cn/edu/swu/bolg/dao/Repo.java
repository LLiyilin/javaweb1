package cn.edu.swu.bolg.dao;

import cn.edu.swu.bolg.model.po.Blog;
import cn.edu.swu.bolg.model.po.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Repo {

    private static Repo instance = new Repo();

    private Repo() {
    }

    public static Repo getInstance() {
        return instance;
    }

    public void saveBlog(Blog blog) throws SQLException {
        if (blog.getId() > 0) {
            this.updateBlog(blog);
        } else {
            this.insertBlog(blog);
        }
    }

    private void insertBlog(Blog blog) throws SQLException {
        String template =
                "INSERT INTO `blog`(`title`, `content`, `image`, `status`) " +
                        "VALUES (\"%s\", \"%s\", \"%s\", \"%s\")";
        String sql = String.format(
                template, blog.getTitle(), blog.getContent(), blog.getImage(), blog.getStatus());
        MysqlInstall.getInstance().execute(sql);
    }

    private void updateBlog(Blog blog) throws SQLException {
        String template =
                "UPDATE `blog` SET `title`=\"%s\", `content`=\"%s\", `image`=\"%s\", `status`=\"%s\" " +
                        "WHERE `id`=%s";
        String sql = String.format(template, blog.getTitle(),
                blog.getContent(), blog.getImage(), blog.getStatus(), blog.getId());
        MysqlInstall.getInstance().execute(sql);
    }

    public void deleteBlog(cn.edu.swu.bolg.model.po.Blog blog) throws SQLException {
        String template = "DELETE FROM `blog` WHERE `id` = %s";
        String sql = String.format(template, blog.getId());
        MysqlInstall.getInstance().execute(sql);
    }

    public void deleteBlog(Long id) throws SQLException {
        String template = "DELETE FROM `blog` WHERE `id` = %s";
        String sql = String.format(template, id);
        MysqlInstall.getInstance().execute(sql);
    }

    public List<Blog> getAll() throws SQLException {
        String sql = "SELECT `id`, `title`, `content`, `image`,`status`,`create_time` FROM `blog` where status=1";
        return MysqlInstall.getInstance().query(sql, new Jdbc<Blog>() {
            @Override
            public Blog visit(ResultSet rs) throws SQLException {
                return Repo.getBlogFromResultset(rs);
            }
        });
    }

    public Blog getById(String id) throws SQLException {
        String sql = "SELECT * FROM `blog` WHERE `id` = %s";
        List<Blog> blogs = MysqlInstall.getInstance().query(
                String.format(sql, id), new Jdbc<Blog>() {
                    @Override
                    public Blog visit(ResultSet rs) throws SQLException {
                        return Repo.getBlogFromResultset(rs);
                    }
                });
        return blogs.size() == 0 ? null : blogs.get(0);
    }

    public List<Blog> getByIds(List<Long> ids) throws SQLException {
        String sql = "SELECT * FROM `blog` WHERE `id` IN (%s)";
        String strId = "";
        for (int i = 0; i < ids.size(); i++) {
            strId += ((i > 0) ? "," : "") + ids.get(i);
        }

        System.out.println(String.format(sql, strId));
        List<Blog> blogs = MysqlInstall.getInstance().query(
                String.format(sql, strId), new Jdbc<Blog>() {
                    @Override
                    public Blog visit(ResultSet rs) throws SQLException {
                        return Repo.getBlogFromResultset(rs);
                    }
                });

        return blogs;
    }

    private static Blog getBlogFromResultset(ResultSet rs) throws SQLException {
        Blog blog = new Blog();
        blog.setId(rs.getLong("id"));
        blog.setTitle(rs.getString("title"));
        blog.setContent(rs.getString("content"));
        blog.setCreateTime(rs.getTime("create_time"));
        blog.setImage(rs.getString("image"));
        blog.setStatus(rs.getString("status"));
        return blog;
    }


    private static User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUser(rs.getString("user"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getInt("id"));
        return user;
    }
}
