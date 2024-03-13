package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.BGroupScoreMapper;
import org.example.pojo.BGroupScoreRecord;
import org.example.service.BGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class BGroupScoreServiceImpl extends ServiceImpl<BGroupScoreMapper, BGroupScoreRecord> implements BGroupScoreService {
    @Autowired
    private BGroupScoreMapper bGroupScoreMapper;

    @Override
    public void submit(Integer judgeId, Integer athleteId, Double score) {
        BGroupScoreRecord curAthleteBGroupScore = bGroupScoreMapper.selectOne(Wrappers.<BGroupScoreRecord>lambdaQuery()
                .eq(BGroupScoreRecord::getAthleteId, athleteId)
                .eq(BGroupScoreRecord::getJudgeId, judgeId)
        );
        if (curAthleteBGroupScore == null) {
            BGroupScoreRecord bGroupScoreRecord = new BGroupScoreRecord(judgeId, athleteId, score);
            bGroupScoreMapper.insert(bGroupScoreRecord);
        } else {
            curAthleteBGroupScore.setScore(score);
            bGroupScoreMapper.updateById(curAthleteBGroupScore);
        }
    }

    @Override
    public Double calc(Integer athleteId) {
        List<BGroupScoreRecord> athleteScoreList = bGroupScoreMapper.selectList(Wrappers.<BGroupScoreRecord>lambdaQuery()
                .eq(BGroupScoreRecord::getAthleteId, athleteId)
        );
        //如果有5个评分
        if (athleteScoreList.size() == 5) {
            //去除最低分和最高分
            Double maxScore = 0.0;
            Double minScore = 3.0;
            Double sum = 0.0;
            for (BGroupScoreRecord record : athleteScoreList) {
                if (record.getScore() > maxScore) {
                    maxScore = record.getScore();
                }
                if (record.getScore() < minScore) {
                    minScore = record.getScore();
                }
                sum += record.getScore();
            }
            sum -= maxScore;
            sum -= minScore;
            //取平均分
            DecimalFormat df = new DecimalFormat("#.###");
            return Double.parseDouble(df.format(sum / (athleteScoreList.size() - 2)));
        } else {
            return null;
        }
    }
}
