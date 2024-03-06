package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Athlete;

import java.util.List;

public interface AthleteService extends IService<Athlete> {
    //当场比赛的运动员列表
    List<Athlete> list(String competitiveType);
}
