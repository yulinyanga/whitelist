package com.trs.zhq.controller;

import com.trs.zhq.config.DBConfig;
import com.trs.zhq.service.ConfigService;
import com.trs.zhq.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@Controller
@RequestMapping({"/"})
public class IndexController {
    @Autowired
    private ConfigService configService;

    @RequestMapping("index")
    public String ImportFile() {
        System.out.println(111);
        return "index";
    }

    @RequestMapping(value = {"importWhiteList"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResultDataUtil importWhiteList(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
//        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //springboot环境下, 以jar包(项目根目录)所在目录作为文件资料的根目录, 这里定义 data/upload 作为文件上传的存放路径
        //!jar包运行时, 这里有 file: 前缀!
        //String savePath = rootFile.getAbsolutePath() + "data/upload";       //不要直接拼接路径  避免 分隔符多或少
        File savePath = null;
        try {
            savePath = new File(FileUtil.getJarRootPath(), "data/upload");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //判断上传文件的保存目录是否存在
        if (!savePath.exists() && !savePath.isDirectory()) {
            //创建目录
            boolean created = savePath.mkdirs();
            if (!created) {
                throw new RuntimeException("路径: '" + savePath.getAbsolutePath() + "'创建失败");
            }
        }
        return configService.importData(file, savePath);
    }

    @RequestMapping("exportData")
    public String exportData(String username,HttpServletRequest request,HttpServletResponse response) {
        DBConfig.DB_URL = "127.0.0.1";
        username = "ceshi";
        //统计列表
        try {
//            CountSensitiveWords.countBySize();
            CountSensitiveWords.detail(username);
            String filename = System.currentTimeMillis() + "疑似信息.xlsx";

            //解决firefox的文件名乱码
            String agent = request.getHeader("USER-AGENT");
//            response.setContentType("application/octet-stream");
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/vnd.ms-excel");
            if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
                String downloadName = new String(filename.getBytes("GB2312"), "ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=" + downloadName);
            } else {
                // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
                response.setHeader("Content-Disposition", "attachment;fileName="
                        + URLEncoder.encode(filename, "UTF-8"));
            }

            OutputStream os = response.getOutputStream();
            InputStream is = new FileInputStream(DBConfig.detailFile);
            byte[] bytes = new byte[1024 * 8];
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
