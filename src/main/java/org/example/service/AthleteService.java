package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.Enum.CompetitionTypeEnum;
import org.example.pojo.Athlete;

import java.util.List;

public interface AthleteService extends IService<Athlete> {
    //当场比赛的运动员列表
    List<Athlete> list(String competitiveType, String groupValue);
    //下一个运动员
    Athlete nextAthlete(String competitiveType, String groupValue, Integer athleteId);
    //获取运动员的排名
    Integer getRank(Integer athleteId);
    //将运动员列表从signprj复制到athlete_list中
    void copyTo(String competitionName, CompetitionTypeEnum competitiveType);
}
