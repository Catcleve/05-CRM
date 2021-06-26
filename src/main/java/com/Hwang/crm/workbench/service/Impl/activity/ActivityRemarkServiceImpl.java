package com.Hwang.crm.workbench.service.Impl.activity;

import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.activity.ActivityRemark;
import com.Hwang.crm.workbench.mapper.ActivityRemarkMapper;
import com.Hwang.crm.workbench.service.activity.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper remarkMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ActivityRemark insertRemark(ActivityRemark remark) {
        remark.setId(UUIDUtil.getUUID());
        remark.setCreateTime(DateTimeUtil.getSysTime());
        int i = remarkMapper.insertSelective(remark);
        if (i == 0) {
            throw new CrmException(CrmEnum.REMARK_INSERT);
        }
        return remark;
    }

    @Override
    public void deleteRemark(ActivityRemark remark) {

        int i = remarkMapper.deleteByPrimaryKey(remark.getId());
        if (i == 0) {
            throw new CrmException(CrmEnum.REMARK_DELETE);
        }
    }

    @Override
    public ActivityRemark editRemark(ActivityRemark remark) {
        remark.setEditTime(DateTimeUtil.getSysTime());
        remark.setEditFlag("1");
        int i = remarkMapper.updateByPrimaryKeySelective(remark);

        if (i == 0) {
            throw new CrmException(CrmEnum.REMARK_UPDATE);
        }

        remark = remarkMapper.selectByPrimaryKey(remark.getId());
        User user = userMapper.selectByPrimaryKey(remark.getOwner());
        remark.setImg(user.getImg());
        return remark;
    }
}
