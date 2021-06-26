package com.Hwang.crm.workbench.service.Impl.clue;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.activity.Activity;
import com.Hwang.crm.workbench.bean.clue.Clue;
import com.Hwang.crm.workbench.bean.relation.ClueActivityRelation;
import com.Hwang.crm.workbench.bean.clue.ClueRemark;
import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.Hwang.crm.workbench.bean.contacts.ContactsRemark;
import com.Hwang.crm.workbench.bean.customer.Customer;
import com.Hwang.crm.workbench.bean.customer.CustomerRemark;
import com.Hwang.crm.workbench.bean.relation.ContactsActivityRelation;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
import com.Hwang.crm.workbench.bean.tran.TranRemark;
import com.Hwang.crm.workbench.mapper.*;
//import com.Hwang.crm.workbench.mapper.ClueRemarkMapper;
import com.Hwang.crm.workbench.service.clue.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;




    //    多条件模糊查询
    @Override
    public PageInfo<Clue> list(PageInfo<Clue> pageInfo, Clue clue) {

//        创建example对象
        Example example = new Example(Clue.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();

//        判断，当名称不为空时，加入模糊查询
        if (StrUtil.isNotEmpty(clue.getFullName())) {
            criteria.andLike("fullName", "%" + clue.getFullName() + "%");
        }

        if (StrUtil.isNotEmpty(clue.getCompany())) {
            criteria.andLike("company", "%" + clue.getCompany() + "%");
        }

        if (StrUtil.isNotEmpty(clue.getMPhone())) {
            criteria.andLike("mPhone", "%" + clue.getMPhone() + "%");
        }

        //        公司座机查询
        if (StrUtil.isNotEmpty(clue.getPhone())) {
            criteria.andLike("phone", "%" + clue.getPhone() + "%");
        }

        //
        if (StrUtil.isNotEmpty(clue.getSource())) {
            criteria.andEqualTo("source", clue.getSource());
        }

        //        公司座机查询
        if (StrUtil.isNotEmpty(clue.getState())) {
            criteria.andEqualTo("state", clue.getState());
        }

//         当owner不为空时，通过owner模糊查询出符合条件的user对象，拿到id，然后进行clue查询
        if (StrUtil.isNotEmpty(clue.getOwner())) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + clue.getOwner() + "%");
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
        List<Clue> activities = clueMapper.selectByExample(example);
        pageInfo = new PageInfo<>(activities);
        for (Clue custom : pageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey(custom.getOwner());
//            getClueRemark(custom);
            custom.setOwner(user.getName());
        }
        return pageInfo;
    }


    //    通过clue获取对应的clueRemark
/*
    public void getClueRemark(Clue custom){
        Example remarkExample = new Example(ClueRemark.class);
        remarkExample.createCriteria().andEqualTo("clueId", custom.getId());
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectByExample(remarkExample);
        clueRemarks.forEach(clueRemark -> {
            User user = userMapper.selectByPrimaryKey(clueRemark.getCreateBy());
            clueRemark.setImg(user.getImg());
            clueRemark.setCreateBy(user.getName());
        });
        custom.setClueRemarks(clueRemarks);
    }
*/


    //    添加线索
    @Override
    public void saveClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        int i = clueMapper.insertSelective(clue);
        if (i == 0) {
            throw new CrmException(CrmEnum.CLUE_ADD);
        }
    }

    //    修改线索
    @Override
    public void updateClueById(Clue clue) {
        clue.setEditTime(DateTimeUtil.getSysTime());
        int i = clueMapper.updateByPrimaryKeySelective(clue);
        if (i == 0) {
            throw new CrmException(CrmEnum.CUSTOMER_EDIT);
        }
    }

    //    删除线索，一个或者多个
    @Override
    public void deleteClue(List<String> ids) {
        Example example = new Example(Clue.class);
        example.createCriteria().andIn("id", ids);
        int i = clueMapper.deleteByExample(example);
        if (i <= 0) {
            throw new CrmException(CrmEnum.ACTIVITY_DELETE);
        }
    }


    //        通过线索id获取对应的活动，分包含和不包含
    @Override
    public List<Activity> getActivity(Clue clue, PageInfo<Activity> pageInfo) {

        //      通过线索获取线索 活动关联表中的活动id
        ClueActivityRelation relation = new ClueActivityRelation();

        //        通过线索id新建线索对象
        relation.setClueId(clue.getId());

        //        通过线索对象获取关联表对象集合
        List<ClueActivityRelation> relations = clueActivityRelationMapper.select(relation);

        //        拿出其中的市场活动id
        List<String> activityIds = new ArrayList<>();
        activityIds.add("");
        relations.forEach(select -> activityIds.add(select.getActivityId()));

        //        通过市场活动id集合，获取对应的市场活动集合
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();

        //        如果有名称信息，说明是模态框的查询
        String activityName = clue.getFullName();
        if (activityName != null) {
            if (!activityName.equals("")) {
                criteria.andLike("name", "%" + activityName + "%");
            }
            criteria.andNotIn("id", activityIds);

        } else {
            if (StrUtil.isNotEmpty(clue.getJob())) {
                criteria.andLike("name", "%" + clue.getJob() + "%");
            }
            criteria.andIn("id", activityIds);
        }

//        分页
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Activity> activities = activityMapper.selectByExample(example);

        //        设置市场活动所有者名字
        activities.forEach(activity ->
                activity.setOwner(userMapper.selectByPrimaryKey(activity.getOwner()).getName()));

        return activities;
    }


    //      解除关联
    @Override
    public void deleteRelation(ClueActivityRelation relation) {

        Example example = new Example(relation.getClass());
        example.createCriteria().andEqualTo("clueId", relation.getClueId())
                .andEqualTo("activityId", relation.getActivityId());
        int i = clueActivityRelationMapper.deleteByExample(example);
    }


    //      添加关联
    @Override
    public void insertRelation(String clueId, String[] ids) {

        for (String activityId : ids) {
            ClueActivityRelation relation = new ClueActivityRelation();
            relation.setClueId(clueId);
            relation.setActivityId(activityId);
            relation.setId(UUIDUtil.getUUID());
            clueActivityRelationMapper.insert(relation);
        }


    }


    //      转换
    @Override
    public void insertConversion(Tran tran, HttpSession session) {

//        clueId
        String clueId = tran.getId();
//        登录的user对象
        User user = (User) session.getAttribute("user");
//        提取属性
        String creatBy = user.getName();
//        创建时间
        String sysTime = DateTimeUtil.getSysTime();


//        1、根据线索的主键查询线索的信息(线索包含自身的信息，包含客户的信息，包含联系人信息)
        Clue clue = clueMapper.selectByPrimaryKey(clueId);

//        2、先将线索中的客户信息取出来保存在客户表中，当该客户不存在的时候，新建客户(按公司名称精准查询)
        Customer customer = new Customer();
        customer.setName(clue.getCompany());
        List<Customer> select = customerMapper.select(customer);
        if (select.size() == 0) {
            BeanUtils.copyProperties(clue, customer,"editBy","editTime");
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateTime(sysTime);
        customerMapper.insertSelective(customer);
        } else {
            customer = select.get(0);
        }



//        3、将线索中联系人信息取出来保存在联系人表中
        Contacts contacts = new Contacts();
        BeanUtils.copyProperties(clue, contacts,"editBy","editTime");
        contacts.setId(UUIDUtil.getUUID());
        contacts.setCreateTime(sysTime);
        contacts.setCustomerId(customer.getId());
        contactsMapper.insert(contacts);


//        4、线索中的备注信息取出来保存在客户备注和联系人备注中
        ClueRemark oldClueRemark = new ClueRemark();
        oldClueRemark.setClueId(clueId);
//       取出对应的备注信息集合
        List<ClueRemark> clueRemarks = clueRemarkMapper.select(oldClueRemark);

//        遍历，创建对应的客户和联系人备注
        ContactsRemark contactsRemark = new ContactsRemark();
        CustomerRemark customerRemark = new CustomerRemark();
        for (ClueRemark clueRemark : clueRemarks) {

            BeanUtils.copyProperties(clueRemark, contactsRemark,"editBy","editTime");
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateTime(sysTime);
            contactsRemarkMapper.insertSelective(contactsRemark);

            BeanUtils.copyProperties(clueRemark, customerRemark,"editBy","editTime");
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateTime(sysTime);
            customerRemarkMapper.insertSelective(customerRemark);
        }

//        5、将"线索和市场活动的关系"转换到"联系人和市场活动的关系中"
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        List<ClueActivityRelation> select1 = clueActivityRelationMapper.select(clueActivityRelation);
        ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
        for (ClueActivityRelation activityRelation : select1) {
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(activityRelation.getActivityId());
            contactsActivityRelationMapper.insertSelective(contactsActivityRelation);
        }


//        6、如果转换过程中发生了交易，创建一条新的交易，交易信息不全，后面可以通过修改交易来完善交易信息

//        等于1说明需要创建交易
        if (tran.getCreateTime().equals("1")) {
            BeanUtils.copyProperties(clue,tran,"editBy","editTime");
            tran.setId(UUIDUtil.getUUID());
            tran.setCreateBy(creatBy);
            tran.setCreateTime(sysTime);
            tran.setCustomerId(customer.getId());
            tran.setContactsId(contacts.getId());
            tran.setType("新业务");
            tranMapper.insertSelective(tran);
        }

//        7、创建该条交易对应的交易历史以及备注
        TranRemark tranRemark = new TranRemark();
        tranRemark.setId(UUIDUtil.getUUID());
        tranRemark.setTranId(tran.getId());
        tranRemark.setCreateBy(creatBy);
        tranRemark.setCreateTime(sysTime);
        tranRemarkMapper.insertSelective(tranRemark);

//      交易历史
        TranHistory tranHistory = new TranHistory();
        BeanUtils.copyProperties(tran,tranHistory,"editBy","editTime");
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setCreateTime(sysTime);
        tranHistory.setCreateBy(creatBy);
        tranHistory.setTranId(tran.getId());
        tranHistoryMapper.insertSelective(tranHistory);

//      8、删除线索的备注信息
        clueRemarkMapper.delete(oldClueRemark);

//      9、删除线索和市场活动的关联关系(tbl_clue_activity_relation)
        clueActivityRelationMapper.delete(clueActivityRelation);

//      10、删除线索
        clueMapper.delete(clue);


    }


}


