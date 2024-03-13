package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.AGroupScoreMapper;
import org.example.mapper.TotalScoreMapper;
import org.example.pojo.AGroupScoreRecord;
import org.example.pojo.Score;
import org.example.service.AGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.time.Duration;

@Service
public class AGroupScoreServiceImpl extends ServiceImpl<AGroupScoreMapper, AGroupScoreRecord> implements AGroupScoreService {
    @Autowired
    private AGroupScoreMapper aGroupScoreMapper;
    @Autowired
    private TotalScoreMapper totalScoreMapper;

    @Override
    public void submit(Integer judgeId, Integer athleteId, String code) {
        AGroupScoreRecord aGroupScoreRecord = new AGroupScoreRecord(judgeId, athleteId, code, LocalDateTime.now(), false);
        //查询其他裁判提交的相同错误码且未响应的记录
        List<AGroupScoreRecord> aGroupScoreRecordList = aGroupScoreMapper.selectList(Wrappers.<AGroupScoreRecord>lambdaQuery()
                .eq(AGroupScoreRecord::getAthleteId, athleteId)
                .eq(AGroupScoreRecord::getErrorCode, code)
                .eq(AGroupScoreRecord::getResponded, false)
        );

        Integer overtimeSeconds = 100;
        for (AGroupScoreRecord record : aGroupScoreRecordList) {
            //非同一个裁判提交了相同的错误码
            if (!record.getJudgeId().equals(aGroupScoreRecord.getJudgeId()) && record.getErrorCode().equals(aGroupScoreRecord.getErrorCode())) {
                //两条记录的时间戳相差小于5秒
                Duration duration = Duration.between(record.getTimestamp(), aGroupScoreRecord.getTimestamp());

                if (Math.abs(duration.getSeconds()) < overtimeSeconds) {
                    aGroupScoreRecord.setResponded(true);
                    //更新两条记录的responded字段
                    aGroupScoreMapper.update(null, Wrappers.<AGroupScoreRecord>lambdaUpdate()
                            .set(AGroupScoreRecord::getResponded, true)
                            .eq(AGroupScoreRecord::getRecordId, record.getRecordId())
                    );
                    aGroupScoreRecord.setResponded(true);
                    aGroupScoreMapper.insert(aGroupScoreRecord);
                    //更新总分表
                    //查询该运动员的总分记录
                    Score score = totalScoreMapper.selectOne(Wrappers.<Score>lambdaQuery()
                            .eq(Score::getAthleteId, athleteId));
                    if (score != null) {
                        score.setAGroupScore(score.getAGroupScore() - 0.1);
                        totalScoreMapper.update(score, Wrappers.<Score>lambdaQuery()
                                .eq(Score::getAthleteId, athleteId));
                    }
                    return;
                }
            }
        }
        aGroupScoreMapper.insert(aGroupScoreRecord);

    }
}
