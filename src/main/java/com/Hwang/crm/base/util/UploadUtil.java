package com.Hwang.crm.base.util;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.UserEnum;
import com.Hwang.crm.base.exception.UserException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class UploadUtil {

    public static ResultVo fileUpload(MultipartFile[] img, HttpSession session) {
        ResultVo resultVo = new ResultVo();
        //        先搞个文件上传的位置
        String path = session.getServletContext().getRealPath("/upload");
        //        创建文件夹对象
        File file = new File(path);
        //        创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }


        for (MultipartFile multipartFile : img) {

            //      获取文件的名字
            String name = multipartFile.getOriginalFilename();
            //      判断文件后缀是否正确
            assert name != null;
            try {
                //判断文件类型是否合法
                verifySuffix(name);
                //       判断文件大小是否正确
                verifyMaxSize(multipartFile);

            } catch (UserException e1) {
                resultVo.setMessage(e1.getMessage());
                return resultVo;
            }
            //        给文件起名字，以免重复
            name = System.currentTimeMillis() + name;
            //        把文件放入硬盘
            try {
                multipartFile.transferTo(new File(path + File.separator + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultVo;
    }

    private static void verifyMaxSize(MultipartFile multipartFile) {
        if (multipartFile.getSize() > 4*1024*1024) {
            throw new UserException(UserEnum.UPLOAD_SIZE);
        }
    }

    private static void verifySuffix(String name) {
        String[] imgs = {".jpg", ".png", ".jpeg", ".gif"};
        String substring = name.substring(name.lastIndexOf("."));
        if (!Arrays.asList(imgs).contains(substring)) {
            throw new UserException(UserEnum.UPLOAD_SUFFIX);
        }
    }
}
