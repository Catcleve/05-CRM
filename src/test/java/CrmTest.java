import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class CrmTest {

    @Test
    public void test01() {
        String uuid = UUID.randomUUID().toString().replace("-","");

        System.out.println("uuid = " + uuid);
    }

    @Test
    public void test02() {

    }

    @Test
    public void test03() {
        BeanFactory context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
//        System.out.println("user = " + userMapper);
        User user = new User();
        user.setId("5");
        userMapper.insertSelective(user);
    }


}
