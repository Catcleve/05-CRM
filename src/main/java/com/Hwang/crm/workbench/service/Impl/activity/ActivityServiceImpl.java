package com.Hwang.crm.workbench.service.Impl.activity;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.test.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.activity.Activity;
import com.Hwang.crm.workbench.bean.activity.ActivityRemark;
import com.Hwang.crm.workbench.mapper.ActivityMapper;
import com.Hwang.crm.workbench.mapper.ActivityRemarkMapper;
import com.Hwang.crm.workbench.service.activity.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;


    //    多条件模糊查询
    @Override
    public PageInfo<Activity> list(PageInfo<Activity> pageInfo, Activity activity) {

//        创建example对象
        Example example = new Example(Activity.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();


//        判断，当名称不为空时，加入模糊查询
        if (StrUtil.isNotEmpty(activity.getName())) {
            criteria.andLike("name", "%" + activity.getName() + "%");
        }

//         当owner不为空时，通过owner模糊查询出符合条件的user对象，拿到id，然后进行activity查询
        if (StrUtil.isNotEmpty(activity.getOwner())) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + activity.getOwner() + "%");
            List<User> users = userMapper.selectByExample(example1);
            ArrayList<String> owners = new ArrayList<>();
            owners.add("");
            for (User user : users) {
                owners.add(user.getId());
            }
            criteria.andIn("owner", owners);
        }

//        开始日期查询
        if (StrUtil.isNotEmpty(activity.getStartDate())) {
            criteria.andGreaterThanOrEqualTo("startDate", activity.getStartDate());
        }
//          结束日期查询
        if (StrUtil.isNotEmpty(activity.getEndDate())) {
            criteria.andLessThanOrEqualTo("endDate", activity.getEndDate());
        }


//        分页助手需要写在这里，如果写在controller，会导致给上面的user查询分页
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Activity> activities = activityMapper.selectByExample(example);
        pageInfo = new PageInfo<>(activities);
        for (Activity active : pageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey(active.getOwner());
            getActivityRemark(active);
            active.setOwner(user.getName());
        }
        return pageInfo;
    }


    //    通过activity获取对应的activityRemark
    public void getActivityRemark(Activity active) {
        Example remarkExample = new Example(ActivityRemark.class);
        remarkExample.createCriteria().andEqualTo("activityId", active.getId());
        List<ActivityRemark> activityRemarks = activityRemarkMapper.selectByExample(remarkExample);
        activityRemarks.forEach(activityRemark -> {
            User user = userMapper.selectByPrimaryKey(activityRemark.getOwner());
            activityRemark.setImg(user.getImg());
        });
        active.setActivityRemarks(activityRemarks);
    }

    //    获取所有user给下拉框使用
    @Override
    public List<User> getUser() {
        Example example = new Example(User.class);
        example.selectProperties("id", "name");
        return userMapper.selectByExample(example);
    }

    //    添加市场活动
    @Override
    public void saveActivity(Activity activity) {
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        int i = activityMapper.insertSelective(activity);
        if (i == 0) {
            throw new CrmException(CrmEnum.ACTIVITY_ADD);
        }
    }

    //    修改市场活动
    @Override
    public void updateActivityById(Activity activity) {
        activity.setEditTime(DateTimeUtil.getSysTime());
        int i = activityMapper.updateByPrimaryKeySelective(activity);
        if (i == 0) {
            throw new CrmException(CrmEnum.ACTIVITY_EDIT);
        }
    }

    //    删除市场活动，一个或者多个
    @Override
    public void deleteActivity(List<String> ids) {
        Example example = new Example(Activity.class);
        example.createCriteria().andIn("id", ids);
        int i = activityMapper.deleteByExample(example);
        if (i <= 0) {
            throw new CrmException(CrmEnum.ACTIVITY_DELETE);
        }
    }

    @Override
    public void output(HttpServletResponse response) {


        // 通过工具类创建writer，默认创建xls格式,true为xlsx格式
        ExcelWriter writer = ExcelUtil.getWriter(true);

//        通过example可以获取对象的属性有几个，也就是excel中有几列
        Example example = new Example(Activity.class);
        List<Activity> activities = activityMapper.selectByExample(example);
        Map<String, EntityColumn> propertyMap = example.getPropertyMap();
        int row = propertyMap.size();

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(row, "市场活动");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(activities, true);

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);

    }
}
