package com.trs.zhq.service;

import com.trs.zhq.util.ResultDataUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public abstract interface ConfigService {
    ResultDataUtil importData(MultipartFile file, File savePath);
}
