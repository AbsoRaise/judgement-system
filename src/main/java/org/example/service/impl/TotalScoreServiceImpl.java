package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.TotalScoreMapper;
import org.example.pojo.Score;
import org.example.service.TotalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalScoreServiceImpl extends ServiceImpl<TotalScoreMapper, Score> implements TotalScoreService {
    @Autowired
    private TotalScoreMapper totalScoreMapper;

    @Override
    public void create(Integer athleteId) {
        Score score = new Score(athleteId, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        if (totalScoreMapper.selectOne(Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId)) != null) {
            totalScoreMapper.update(score, Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId));
        } else {
            totalScoreMapper.insert(score);
        }

    }

    @Override
    public void overtimeScoreDeduction(Integer athleteId, Double scoreValue) {
        Score updatedScore = totalScoreMapper.selectOne(Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId));
        updatedScore.setOvertimeScore(updatedScore.getOvertimeScore() - scoreValue);
        totalScoreMapper.update(updatedScore, Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId));

    }

    @Override
    public void routineCheckDeduction(Integer athleteId, Double scoreValue) {
        Score updatedScore = totalScoreMapper.selectOne(Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId));
        updatedScore.setRoutineCheckDeduction(updatedScore.getRoutineCheckDeduction() - scoreValue);
        totalScoreMapper.update(updatedScore, Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athleteId));

    }
}
