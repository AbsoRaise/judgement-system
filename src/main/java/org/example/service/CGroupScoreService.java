package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.CGroupScoreRecord;

public interface CGroupScoreService extends IService<CGroupScoreRecord> {
    // C组裁判 提交评分
    void submit(Integer judgeId, Integer athleteId, Integer actionNumber, Double score, Boolean responded);
}
