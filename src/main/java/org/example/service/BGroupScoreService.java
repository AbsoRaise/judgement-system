package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.BGroupScoreRecord;

public interface BGroupScoreService extends IService<BGroupScoreRecord> {
    // B组裁判 提交评分
    void submit(Integer judgeId, Integer athleteId, Double score);
    // B组裁判 计算运动员总分
    Double calc(Integer athleteId);
}
