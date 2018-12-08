package com.litbo.hospital.user.service;

import com.github.pagehelper.PageInfo;
import com.litbo.hospital.user.bean.SysGgxz;
import org.springframework.web.multipart.MultipartFile;

public interface GgxzService {
    int addGgxz(MultipartFile multipartFile, SysGgxz ggxz);

    PageInfo listGgxzs(int pageNum, int pageSize);

    int ggxzPass(Integer id);

    int ggxzFail(Integer id);

    PageInfo listWaits(int pageNum, int pageSize);
}
