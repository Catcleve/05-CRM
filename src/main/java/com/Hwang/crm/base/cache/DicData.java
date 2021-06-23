package com.Hwang.crm.base.cache;

import com.Hwang.crm.base.bean.DicType;
import com.Hwang.crm.base.bean.DicValue;
import com.Hwang.crm.base.mapper.DicTypeMapper;
import com.Hwang.crm.base.mapper.DicValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;

@Component
public class DicData {

//    字典类1
    @Autowired
    private DicTypeMapper typeMapper;
//字典类2
    @Autowired
    private DicValueMapper valueMapper;
//  servletContext对象，字典表放入其中，全服务器都可以获得
    @Autowired
    private ServletContext context;

//    这个注解表示服务器加载之后就会启动这个方法
    @PostConstruct
    public void getDicMap (){

        HashMap<String,List<DicValue> > dicMap = new HashMap<>();

//        获取键
        Example typeExample = new Example(DicType.class);
        typeExample.selectProperties("code");
        List<DicType> dicTypes = typeMapper.selectByExample(typeExample);

//        获取值，放入map中
        DicValue dicValue = new DicValue();
        for (DicType dicType : dicTypes) {
            dicValue.setTypeCode(dicType.getCode());
            List<DicValue> dicValueList = valueMapper.select(dicValue);
            dicMap.put(dicType.getCode(), dicValueList);
        }

//        放入到作用域
        context.setAttribute("dicMap",dicMap);
    }
}
