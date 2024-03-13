package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Score;

public interface TotalScoreService extends IService<Score> {
    //新建 初始化运动员分数
    void create(Integer athleteId);
    //裁判长 扣分
    void overtimeScoreDeduction(Integer athleteId, Double scoreValue);
    //套路检查扣分
    void routineCheckDeduction(Integer athleteId, Double scoreValue);
    //获取运动员所有分数
    Score getAllScores(Integer athleteId);
}
