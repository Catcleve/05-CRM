package com.Hwang.crm.workbench.service.Impl.clue;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.Activity;
import com.Hwang.crm.workbench.bean.Clue;
import com.Hwang.crm.workbench.bean.ClueActivityRelation;
import com.Hwang.crm.workbench.mapper.ActivityMapper;
import com.Hwang.crm.workbench.mapper.ClueActivityRelationMapper;
import com.Hwang.crm.workbench.mapper.ClueMapper;
//import com.Hwang.crm.workbench.mapper.ClueRemarkMapper;
import com.Hwang.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
    private ClueActivityRelationMapper relationMapper;

//    @Autowired
//    private ClueRemarkMapper clueRemarkMapper;


    //    多条件模糊查询
    @Override
    public PageInfo<Clue> list(PageInfo<Clue> pageInfo, Clue clue) {

//        创建example对象
        Example example = new Example(Clue.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();


//        判断，当名称不为空时，加入模糊查询
        if (StrUtil.isNotEmpty(clue.getFullName())) {
            criteria.andLike("name", "%" + clue.getFullName() + "%");
        }

        if (StrUtil.isNotEmpty(clue.getCompany())) {
            criteria.andLike("name", "%" + clue.getCompany() + "%");
        }

        if (StrUtil.isNotEmpty(clue.getMPhone())) {
            criteria.andLike("name", "%" + clue.getMPhone() + "%");
        }

        //        公司座机查询
        if (StrUtil.isNotEmpty(clue.getPhone())) {
            criteria.andLike("phone", "%" + clue.getPhone() + "%");
        }

        //
        if (StrUtil.isNotEmpty(clue.getSource())) {
            criteria.andLike("phone", "%" + clue.getSource() + "%");
        }

        //        公司座机查询
        if (StrUtil.isNotEmpty(clue.getState())) {
            criteria.andLike("phone", "%" + clue.getState() + "%");
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
//    public void getClueRemark(Clue custom){
//        Example remarkExample = new Example(ClueRemark.class);
//        remarkExample.createCriteria().andEqualTo("clueId", custom.getId());
//        List<ClueRemark> clueRemarks = clueRemarkMapper.selectByExample(remarkExample);
//        clueRemarks.forEach(clueRemark -> {
//            User user = userMapper.selectByPrimaryKey(clueRemark.getCreateBy());
//            clueRemark.setImg(user.getImg());
//            clueRemark.setCreateBy(user.getName());
//        });
//        custom.setClueRemarks(clueRemarks);
//    }


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


    //        通过线索id获取对应的活动
    @Override
    public List<Activity> getActivity(Clue clue, PageInfo<Activity> pageInfo) {

        //      通过线索获取线索 活动关联表中的活动id
        ClueActivityRelation relation = new ClueActivityRelation();

        //        通过线索id新建线索对象
        relation.setClueId(clue.getId());

        //        通过线索对象获取关联表对象集合
        List<ClueActivityRelation> relations = relationMapper.select(relation);

        //        拿出其中的市场活动id
        List<String> activityIds = new ArrayList<>();
        activityIds.add("");
        relations.forEach(select -> activityIds.add(select.getActivityId()));

        //        通过市场活动id集合，获取对应的市场活动集合
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();

        //        如果有名称信息，说明时模态框的查询
        String activityName = clue.getFullName();
        if (activityName != null) {
            if (!activityName.equals("")) {
                criteria.andLike("name", "%" + activityName + "%");
            }
            criteria.andNotIn("id", activityIds);

        } else {
            criteria.andIn("id", activityIds);
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Activity> activities = activityMapper.selectByExample(example);

        //        设置市场活动所有者名字
        activities.forEach(activity ->
                activity.setOwner(userMapper.selectByPrimaryKey(activity.getOwner()).getName()));

        return activities;
    }

    @Override
    public void deleteRelation(ClueActivityRelation relation) {

        Example example = new Example(relation.getClass());
        example.createCriteria().andEqualTo("clueId", relation.getClueId())
                .andEqualTo("activityId", relation.getActivityId());
        int i = relationMapper.deleteByExample(example);
    }

    @Override
    public void insertRelation(String clueId, String[] ids) {

        for (String activityId : ids) {
            ClueActivityRelation relation = new ClueActivityRelation();
            relation.setClueId(clueId);
            relation.setActivityId(activityId);
            relation.setId(UUIDUtil.getUUID());
            relationMapper.insert(relation);
        }


    }
}


