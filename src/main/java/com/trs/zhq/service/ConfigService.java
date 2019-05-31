package com.trs.zhq.service;

import com.trs.zhq.util.ResultDataUtil;
import org.springframework.web.multipart.MultipartFile;

public abstract interface ConfigService {
    ResultDataUtil inportData(MultipartFile file);
}
