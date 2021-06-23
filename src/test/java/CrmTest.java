import com.Hwang.crm.base.cache.DicData;
import com.Hwang.crm.base.util.MD5Util;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

public class CrmTest {

    BeanFactory context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    UserMapper userMapper = (UserMapper) context.getBean("userMapper");

    @Test
    public void test01() {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        System.out.println("uuid = " + uuid);
    }


    //    测试密码加密  md5 + 加盐
    @Test
    public void test02() {
        String password = "admin";
        String md5 = MD5Util.getMD5(password);
        System.out.println("md5 = " + md5);

    }

    //    测试添加
    @Test
    public void test03() {

//        System.out.println("user = " + userMapper);
        String loginPwd = MD5Util.getMD5("hahaha");
        User user = new User();
        user.setId(UUIDUtil.getUUID());
        user.setLoginAct("zhangwei");
        user.setLoginPwd(loginPwd);
        userMapper.insertSelective(user);
    }

    //    测试查找
    @Test
    public void test04() {
        User user = new User();
        user.setLoginAct("zhangwei");
        user.setLoginPwd("101a6ec9f938885df0a44f20458d2eb4");
        List<User> select = userMapper.select(user);
        System.out.println("select = " + select);
    }

//    测试模糊查找
    @Test
    public void test05(){
        Example example = new Example(User.class);
        example.createCriteria().andLike("loginAct", "%zhang%");
        userMapper.selectByExample(example);

    }

//    测试修改
    @Test
    public void test06(){
        User user = new User();
        user.setId("f2a6d0930df944159d504f655a073a4c");
        user.setLoginAct("qinkai");
        user.setLoginPwd(MD5Util.getMD5("admin"));
        int i = userMapper.updateByPrimaryKeySelective(user);
        System.out.println("i = " + i);
    }

    @Test
    public void test07(){

        DicData dicData = new DicData();
        dicData.getDicMap();

    }


}
