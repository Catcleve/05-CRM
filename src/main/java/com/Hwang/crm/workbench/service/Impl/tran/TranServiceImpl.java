package com.Hwang.crm.workbench.service.Impl.tran;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.bean.StageImg;
import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.activity.Activity;
import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.Hwang.crm.workbench.bean.customer.Customer;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
import com.Hwang.crm.workbench.mapper.*;
import com.Hwang.crm.workbench.service.tran.TranService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Autowired
    private ActivityMapper activityMapper;



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
        Activity activity;
        for (Tran tran1 : pageInfo.getList()) {
            user = userMapper.selectByPrimaryKey(tran1.getOwner());
            tran1.setOwner(user.getName());
            customer = customerMapper.selectByPrimaryKey(tran1.getCustomerId());
            tran1.setCustomerId(customer.getName());
            contacts = contactsMapper.selectByPrimaryKey(tran1.getContactsId());
            tran1.setContactsId(contacts.getFullName());
            activity = activityMapper.selectByPrimaryKey(tran1.getActivityId());
            tran1.setActivityId(activity.getName());
            user = userMapper.selectByPrimaryKey(tran1.getCreateBy());
            tran1.setCreateBy(user.getName());

        }
        return pageInfo;
    }

//  客户名称自动补全
    @Override
    public List<String> queryCustomerName(String customerName) {

        Example example = new Example(Customer.class);
        example.createCriteria().andLike("name", "%" + customerName + "%");
        List<Customer> customers = customerMapper.selectByExample(example);
        ArrayList<String> names = new ArrayList<>();
        customers.forEach(customer -> names.add(customer.getName()));
        return names;
    }

//    获取阶段历史列表
    @Override
    public List<TranHistory> getHistory(Tran tran) {

        String tranId = tran.getId();

        Example example = new Example(TranHistory.class);
        example.orderBy("createTime");
        example.createCriteria().andEqualTo("tranId", tranId);

        return tranHistoryMapper.selectByExample(example);

    }

//    显示阶段图
    @Override
    public ArrayList<StageImg> stage(Tran tran, HttpSession session) {

        Map<String, String> stageState = (Map<String, String>) session.
                getServletContext().getAttribute("stageState");

        User user = (User) session.getAttribute("user");


//        判断是第一次到页面还是要修改阶段状态
        Tran newTran = tranMapper.selectByPrimaryKey(tran.getId());
        if (!tran.getStage().equals(newTran.getStage())) {
//            设置新的交易可能性
            newTran.setPossibility(tran.getPossibility());
            newTran.setStage(tran.getStage());
//            更新交易信息
            int i = tranMapper.updateByPrimaryKey(newTran);
            if (i == 0 ) {
                throw new CrmException(CrmEnum.TRAN_STAGE);
            }
//            创建交易历史
            TranHistory tranHistory = new TranHistory();
            BeanUtils.copyProperties(tran,tranHistory);
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateTime(DateTimeUtil.getSysTime());
            tranHistory.setCreateBy(user.getName());
            tranHistory.setTranId(newTran.getId());
            tranHistoryMapper.insert(tranHistory);
        }

        String stage = tran.getStage();
        String substring = stage.substring(0, 2);
        int stageNo = Integer.parseInt(substring);


        Set<String> strings = stageState.keySet();

//        判断一共有多少个状态
        int index = strings.size();
//        point用来记录分割点,就是从哪里开始变成失败
        int point = 0;
        for (String string : strings) {
            point++;
            if (stageState.get(string).equals("0")) {
                break;
            }
        }

//      存放状态名字的集合
        ArrayList<String> stageName = new ArrayList<>(strings);

        ArrayList<StageImg> stageInfo = new ArrayList<>();

//      循环,次数为状态的次数
        for (int i = 1; i <= index; i++) {

            //      用来存放状态和对应的图标
            StageImg stageImg = new StageImg();

//          如果当前状态和次数都小于分割点
            if (i < point && stageNo < point) {
//                如果循环次数小于状态,全是绿圈
                if (i < stageNo) {
                    stageImg.setStage(stageName.get(i-1));
                    stageImg.setIcon("绿圈");
                    stageImg.setPossibility(stageState.get(stageImg.getStage()));
//                    如果等于状态,是锚点
                } else if (i == stageNo) {
                    stageImg.setStage(stageName.get(i-1));
                    stageImg.setIcon("锚点");
                    stageImg.setPossibility(stageState.get(stageState.get(stageImg.getStage())));
//                    如果大于当前状态,是黑圈
                } else {
                    stageImg.setStage(stageName.get(i-1));
                    stageImg.setIcon("黑圈");
                    stageImg.setPossibility(stageState.get(stageImg.getStage()));

                }

//                当前状态和次数都不小于分割点,说明是失败状态
//                小于分割点的都是黑圈
            } else if (i < point ) {
                stageImg.setStage(stageName.get(i-1));
                stageImg.setIcon("黑圈");
                stageImg.setPossibility(stageState.get(stageImg.getStage()));
//                等于当前状态的为红叉
            } else if (stageNo == i) {
                stageImg.setStage(stageName.get(i-1));
                stageImg.setIcon("红叉");
                stageImg.setPossibility(stageState.get(stageImg.getStage()));
//                其他为黑叉
            } else  {
                stageImg.setStage(stageName.get(i-1));
                stageImg.setIcon("黑叉");
                stageImg.setPossibility(stageState.get(stageImg.getStage()));
            }

            stageInfo.add(stageImg);
        }
        return stageInfo;
    }
}
