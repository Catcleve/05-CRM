package com.Hwang.crm.settings.bean;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.workbench.bean.tran.Tran;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.*;

public class CrmTest02 {
    @Test
    public void test01() {
        Tran tran = new Tran();
        PropertyDescriptor id = BeanUtil.getPropertyDescriptor(Tran.class, "id");
        String s = IdUtil.fastUUID();
        SubPersonWithAlias person = new SubPersonWithAlias();
        person.setSubName("sub名字");
        person.setSlow(true);

        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(person);
        stringObjectMap.put("subName", "123");

        BeanUtil.fillBeanWithMap(stringObjectMap,person,true);
        CopyOptions copyOptions = new CopyOptions();
    }

    @Test
    public void test02(){
        Map<String, String> build = MapUtil.builder(new HashMap<String, String>())
                .put("key1", "value1")
                .put("key3", "value3")
                .put("key2", "value2").build();
        String hello = MapUtil.join(build, ",", ":", false, "hello");
        System.out.println("hello = " + hello);

    }

    @Test
    public void test03(){

        DynaBean dynaBean = DynaBean.create(User.class);
        dynaBean.set("id","12344");
        Object getId = dynaBean.invoke("getId");
        Assert.assertEquals("12344",getId);
    }

    @Test
    public void test04(){

        HashMap<String, String> map = MapUtil.newHashMap(true);
        map.put("123", "456");
        map.put("223", "789");
        String format = StrUtil.format("{123}-->{223}", map);
        System.out.println("format = " + format);
        String s = IdUtil.fastSimpleUUID();
        System.out.println(s.length());
        System.out.println("s = " + s);

    }

    @Test
    public void test05(){
        Object[] a = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, a);
        //从4.1.11开始可以这么用
        List<?> list1 = Convert.toList(a);
        List<? extends List<?>> lists = Collections.singletonList(list);
        lists.forEach(System.out::println);
        System.out.println("list1 = " + list1);
        System.out.println(Arrays.toString(a));
    }
}

// Lombok注解
@Data
class SubPersonWithAlias {
//    @Alias("aliasSubName")
    private String subName;
    private Boolean slow;
}


