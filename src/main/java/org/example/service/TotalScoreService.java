package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Score;

public interface TotalScoreService extends IService<Score> {
    //新建 初始化运动员分数
    void create(Integer athleteId);
    //裁判长 扣分
    void overtimeScoreDeduction(Integer athleteId, Double scoreValue);

    void routineCheckDeduction(Integer athleteId, Double scoreValue);
}
