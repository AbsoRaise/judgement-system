package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.CGroupScoreMapper;
import org.example.pojo.CGroupScoreRecord;
import org.example.service.CGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CGroupScoreServiceImpl extends ServiceImpl<CGroupScoreMapper, CGroupScoreRecord> implements CGroupScoreService {
    @Autowired
    private CGroupScoreMapper cGroupScoreMapper;

    @Override
    public void submit(Integer judgeId, Integer athleteId, Integer actionNumber, Double score, Boolean responded) {
        CGroupScoreRecord cGroupScoreRecord = new CGroupScoreRecord(judgeId, athleteId, actionNumber, score, responded);
        List<CGroupScoreRecord> cGroupScoreRecordList = cGroupScoreMapper.selectList(Wrappers.<CGroupScoreRecord>lambdaQuery()
                .eq(CGroupScoreRecord::getAthleteId, athleteId)
                .eq(CGroupScoreRecord::getActionNumber, actionNumber)
        );
        for (CGroupScoreRecord record : cGroupScoreRecordList) {
            if (!record.getJudgeId().equals(cGroupScoreRecord.getJudgeId()) && record.getActionNumber().equals(cGroupScoreRecord.getActionNumber())) {
                cGroupScoreMapper.updateById(cGroupScoreRecord);
                return;
            }

        }

    }
}
