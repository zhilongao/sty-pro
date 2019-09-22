package com.study;

import com.study.dao.GrideMapper;
import com.study.entity.Gride;
import com.study.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author long
 * @Date 2019/4/5 0:18
 */
public class App {

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream is = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession session = null;
        try {
            is = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            session = sessionFactory.openSession();
            // 第一种方式：使用完全限定名查询
            /*User uu = (User)session.selectOne("com.study.dao.UserMapper.selectByPrimaryKey", 5);
            System.out.println(uu.getId() + "--" + uu.getAge() + "--" + uu.getUname() + "--" + uu.getPhonenum());*/
            // 第二种方式
            /*UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectByPrimaryKey(2);
            System.out.println(user.getId() + "--" + user.getAge() + "--" + user.getUname());*/

            // 设置一对多的查询  班级 学生
            GrideMapper grideMapper = session.getMapper(GrideMapper.class);
            Gride gride = new Gride();
            gride.setGid(301);
            List<Gride> singleGrideList = grideMapper.selectAllList(gride);
            System.out.println(singleGrideList.size());
            System.out.println("======================");
            List<Gride> grides = grideMapper.selectAllListWithStudents(gride);
            for (Gride temp : grides) {
                System.out.println(temp.getName() + "--" + temp.getGid());
                List<Student> students = temp.getStudents();
                for (Student stu : students) {
                    System.out.println(stu.getGid() + "--" + stu.getSid() + "--" + stu.getSname());
                }
                System.out.println("\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public static void createByJava() {
        Configuration configuration = new Configuration();

    }



}
