package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.AGroupScoreRecord;

public interface AGroupScoreService extends IService<AGroupScoreRecord>{
    // A组裁判 提交评分
    void submit(Integer judgeId, Integer athleteId, String code);
}
