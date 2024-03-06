package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Judge;
import org.springframework.stereotype.Service;


public interface JudgeService extends IService<Judge> {
    //新增裁判
    void login(Judge judge);
    //登出裁判 删除
    void logout(String uuid);
    //裁判信息
    Judge info(String uuid);
}
