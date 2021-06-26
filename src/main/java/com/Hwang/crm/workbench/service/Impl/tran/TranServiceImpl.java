package com.Hwang.crm.workbench.service.Impl.tran;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.Hwang.crm.workbench.bean.customer.Customer;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.mapper.ContactsMapper;
import com.Hwang.crm.workbench.mapper.CustomerMapper;
import com.Hwang.crm.workbench.mapper.TranMapper;
import com.Hwang.crm.workbench.service.tran.TranService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranServiceImpl implements TranService {

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;



    //    多条件模糊查询
    @Override
    public PageInfo<Tran> list(PageInfo<Tran> pageInfo, Tran tran) {

//        创建example对象
        Example example = new Example(Tran.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();


//        判断，当名称不为空时，加入模糊查询
        if (StrUtil.isNotEmpty(tran.getName())) {
            criteria.andLike("name", "%" + tran.getName() + "%");
        }

//         当owner不为空时，通过owner模糊查询出符合条件的user对象，拿到id，然后进行tran查询
        if (StrUtil.isNotEmpty(tran.getOwner())) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + tran.getOwner() + "%");
            List<User> users = userMapper.selectByExample(example1);
            ArrayList<String> owners = new ArrayList<>();
            owners.add("");
            for (User user : users) {
                owners.add(user.getId());
            }
            criteria.andIn("owner", owners);
        }


//        客户名称查询
        if (StrUtil.isNotEmpty(tran.getCustomerId())) {
            Example customerExample = new Example(Customer.class);
            customerExample.createCriteria().andLike("name", "%" + tran.getCustomerId() + "%");
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            ArrayList<String> names = new ArrayList<>();
            names.add("");
            for (Customer customer : customers) {
                names.add(customer.getId());
            }
            criteria.andIn("customerId", names);
        }

//         阶段查询
        if (StrUtil.isNotEmpty(tran.getStage())) {
            criteria.andLike("stage", "%" + tran.getStage() + "%");
        }

        //         类型查询
        if (StrUtil.isNotEmpty(tran.getType())) {
            criteria.andLike("type", "%" + tran.getType() + "%");
        }

        //         来源查询
        if (StrUtil.isNotEmpty(tran.getSource())) {
            criteria.andLike("source", "%" + tran.getSource() + "%");
        }
        
//        联系人名称查询
        if (StrUtil.isNotEmpty(tran.getContactsId())) {
            Example contactsExample = new Example(Contacts.class);
            contactsExample.createCriteria().andLike("fullName", "%" + tran.getContactsId() + "%");
            List<Contacts> contactss = contactsMapper.selectByExample(contactsExample);
            ArrayList<String> names = new ArrayList<>();
            names.add("");
            for (Contacts contacts : contactss) {
                names.add(contacts.getId());
            }
            criteria.andIn("contactsId", names);
        }



//        分页助手需要写在这里，如果写在controller，会导致给上面的user查询分页
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Tran> trans = tranMapper.selectByExample(example);
        pageInfo = new PageInfo<>(trans);


        User user ;
        Customer customer;
        Contacts contacts ;
        for (Tran tran1 : pageInfo.getList()) {
            user = userMapper.selectByPrimaryKey(tran1.getOwner());
            tran1.setOwner(user.getName());
            customer = customerMapper.selectByPrimaryKey(tran1.getCustomerId());
            tran1.setCustomerId(customer.getName());
            contacts = contactsMapper.selectByPrimaryKey(tran1.getContactsId());
            tran1.setContactsId(contacts.getFullName());
        }
        return pageInfo;
    }

    @Override
    public List<String> queryCustomerName(String customerName) {

        Example example = new Example(Customer.class);
        example.createCriteria().andLike("name", "%" + customerName + "%");
        List<Customer> customers = customerMapper.selectByExample(example);
        ArrayList<String> names = new ArrayList<>();
        customers.forEach(customer -> names.add(customer.getName()));
        return names;
    }

}
