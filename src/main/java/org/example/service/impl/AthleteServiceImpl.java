package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.AthleteMapper;
import org.example.pojo.Athlete;
import org.example.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteServiceImpl extends ServiceImpl<AthleteMapper, Athlete> implements AthleteService {
    @Autowired
    private AthleteMapper athleteMapper;
    @Override
    public List<Athlete> list(String competitiveType) {
//        athleteMapper.selectList(Wrappers.<Judge>lambdaQuery().eq(Athlete::getCompetitiveType, CompetitiveType))
        return null;
    }
}
