package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.AthleteMapper;
import org.example.pojo.Athlete;
import org.example.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AthleteServiceImpl extends ServiceImpl<AthleteMapper, Athlete> implements AthleteService {
    @Autowired
    private AthleteMapper athleteMapper;
    @Override
    public List<Athlete> list(String competitiveType,String groupValue) {
        List<Athlete> athleteList = athleteMapper.selectList(Wrappers.<Athlete>lambdaQuery()
                .eq(Athlete::getCompetitiveType, competitiveType)
                .eq(Athlete::getMatchGroup, groupValue)
        );
        Collections.sort(athleteList, Comparator.comparing(Athlete::getOrderNum));
        return athleteList;
    }
}
