package com.youu.youu.restapi.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cc
 * @create 2017-08-11 14:34
 * @desc  用于控制EUditor的部分功能
 **/
@Controller
public class UEditorControlller {

    /**
     * 保存图片
     * @author cc
     * @param upfile
     * @param request
     * @return  Map<String,String> 返回结果信息(UEditor需要)
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<String,String> uploadImage(@RequestParam("upfile") CommonsMultipartFile upfile, HttpServletRequest request)
            throws IOException {
        //获取服务器根目录
        ServletContext servletContext = request.getSession().getServletContext();
        String realPath = servletContext.getRealPath("/");
        System.out.println("存图片realPath="+realPath);

        //这里upfile是config.json中图片提交的表单名称
        //文件原名称
        String fileName=upfile.getOriginalFilename();
        System.out.println(fileName);
        //为了避免重复简单处理
        String nowName=new Date().getTime()+"_" + fileName;

        if(!upfile.isEmpty()){
            //上传位置路径
            String path0 = realPath+"upload\\"+nowName;
            //按照路径新建文件
            File newFile = new File(path0);
            //复制
            FileCopyUtils.copy(upfile.getBytes(), newFile);
        }

        //返回结果信息(UEditor需要)
        Map<String,String> map = new HashMap<String,String >();

        //是否上传成功
        map.put("state", "SUCCESS");

        //现在文件名称
        map.put("title", nowName);

        //文件原名称
        map.put("original", fileName);

        //文件类型 .+后缀名
        map.put("type", fileName.substring(upfile.getOriginalFilename().lastIndexOf(".")));
        //文件路径
        map.put("url", "/youu-restapi/"+nowName+"/getImage");

        //文件大小（字节数）
        map.put("size", upfile.getSize()+"");
        //System.out.println("走controller上传成功");
        return map;

    }


    /**
     * 读取图片
     * @author cc
     * @param imgName
     * @param response
     * @throws Exception
     */
    @RequestMapping("{imgName}/getImage")
    public void readImg(@PathVariable("imgName") String imgName, HttpServletResponse response, HttpServletRequest request)
            throws Exception {

        //获取服务器根目录
        ServletContext servletContext = request.getSession().getServletContext();
        String realPath = servletContext.getRealPath("/");
        System.out.println("取图片realPath="+realPath);

        //设置文件的返回类型
        response.setContentType("image/*");
        //文件路径(必须是绝对路径)
        String imgPath = realPath+"upload\\"+imgName;

        //java中用File类来表示一个文件
        File image = new File(imgPath);

        //测试这个文件路径是否存在（也就是这个文件是否存在）
        if (!image.exists()) {
            return;
        }

        //FileUtils.readFileToByteArray(File file)把一个文件转换成字节数组返回
        response.getOutputStream().write(FileUtils.readFileToByteArray(image));

        //java在使用流时,都会有一个缓冲区,按一种它认为比较高效的方法来发数据:
        //把要发的数据先放到缓冲区,缓冲区放满以后再一次性发过去,而不是分开一次一次地发.
        //而flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.
        response.getOutputStream().flush();
        response.getOutputStream().close();

        //System.out.println("用controller读地址");

    }
}
