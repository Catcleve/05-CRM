package com.Hwang.crm.workbench.service.Impl;

import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.Hwang.crm.workbench.bean.CustomerRemark;
import com.Hwang.crm.workbench.mapper.CustomerRemarkMapper;
import com.Hwang.crm.workbench.service.CustomerRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRemarkServiceImpl implements CustomerRemarkService {
    
    @Autowired
    private CustomerRemarkMapper remarkMapper;

//    添加
    @Override
    public CustomerRemark insertRemark(CustomerRemark remark) {
        remark.setId(UUIDUtil.getUUID());
        remark.setCreateTime(DateTimeUtil.getSysTime());
        int i = remarkMapper.insertSelective(remark);
        if (i == 0) {
            throw new CrmException(CrmEnum.REMARK_INSERT);
        }
        return remark;
    }

//    删除
    @Override
    public void deleteRemark(CustomerRemark remark) {

        int i = remarkMapper.deleteByPrimaryKey(remark.getId());
        if (i == 0) {
            throw new CrmException(CrmEnum.REMARK_DELETE);
        }
    }

//    修改
    @Override
    public CustomerRemark editRemark(CustomerRemark remark) {
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
