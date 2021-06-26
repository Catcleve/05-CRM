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
import java.util.*;

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
        for (DicType dicType : dicTypes) {
//            排序查询
            Example example = new Example(DicValue.class);
            example.orderBy("orderNo");
            example.createCriteria().andEqualTo("typeCode", dicType.getCode());
            List<DicValue> dicValueList = valueMapper.selectByExample(example);
            dicMap.put(dicType.getCode(), dicValueList);
        }


//        加载阶段对应的可能性的文件
        ResourceBundle stage = ResourceBundle.getBundle("mybatis.Stage");

        Set<String> keySet = stage.keySet();
//        这里需要使用keymap，可以保持自然排序
        Map<String, String> stageState = new TreeMap<>();
        keySet.forEach(key -> stageState.put(key, stage.getString(key)));


//        放入到作用域
        context.setAttribute("dicMap",dicMap);
        context.setAttribute("stageState",stageState);
    }
}
