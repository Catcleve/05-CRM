package com.Hwang.crm.workbench.service.Impl.contacts;

import cn.hutool.core.util.StrUtil;
import com.test.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.Hwang.crm.workbench.bean.customer.Customer;
import com.Hwang.crm.workbench.mapper.ContactsMapper;
import com.Hwang.crm.workbench.mapper.CustomerMapper;
import com.Hwang.crm.workbench.service.contacts.ContactsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {


    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;
    
    //    多条件模糊查询
    @Override
    public PageInfo<Contacts> list(PageInfo<Contacts> pageInfo, Contacts contacts) {


//        获取customer的ID和name的键值对
        List<Customer> allCustomers = customerMapper.selectAll();
        HashMap<String, String> customerMap = new HashMap<>();
        allCustomers.forEach(customer -> customerMap.put(customer.getId(), customer.getName()));


//        创建example对象
        Example example = new Example(Contacts.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();

//        姓名查询
        if (StrUtil.isNotEmpty(contacts.getFullName())) {
            criteria.andLike("fullName", "%" + contacts.getFullName() + "%");
        }

//        生日模糊查询
        if (StrUtil.isNotEmpty(contacts.getBirth())) {
            criteria.andLike("birth", "%" + contacts.getBirth() + "%");
        }

//        来源查询
        if (StrUtil.isNotEmpty(contacts.getSource())) {
            criteria.andEqualTo("source", contacts.getSource());
        }

//        客户名称模糊查询
        if (StrUtil.isNotEmpty(contacts.getCustomerId())) {
            Example CustomerExample = new Example(Customer.class);
            CustomerExample.createCriteria().andLike("name", "%" + contacts.getOwner() + "%");
            List<Customer> customers = customerMapper.selectByExample(CustomerExample);
            ArrayList<String> owners = new ArrayList<>();
            for (Customer customer : customers) {
                owners.add(customer.getId());
            }
//            添加空字符串，防止出现列表为空报错
            owners.add("");
            criteria.andIn("owner", owners);
        }
        

//         当owner不为空时，通过owner模糊查询出符合条件的user对象，拿到id，然后进行contacts查询
        if (StrUtil.isNotEmpty(contacts.getOwner())) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + contacts.getOwner() + "%");
            List<User> users = userMapper.selectByExample(example1);
            ArrayList<String> owners = new ArrayList<>();
            for (User user : users) {
                owners.add(user.getId());
            }
//            添加空字符串，防止出现列表为空报错
            owners.add("");
            criteria.andIn("owner", owners);
        }


//        分页助手需要写在这里，如果写在controller，会导致给上面的user查询分页
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Contacts> activities = contactsMapper.selectByExample(example);
        pageInfo = new PageInfo<>(activities);
        for (Contacts custom : pageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey(custom.getOwner());
            custom.setCustomerId(customerMap.get(custom.getCustomerId()));
//            getContactsRemark(custom);
            custom.setOwner(user.getName());
        }
        return pageInfo;
    }
}
