package com.Hwang.crm.workbench.service.Impl;

import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.workbench.bean.ActivityRemark;
import com.Hwang.crm.workbench.mapper.ActivityRemarkMapper;
import com.Hwang.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper remarkMapper;

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
        return remark;
    }
}
