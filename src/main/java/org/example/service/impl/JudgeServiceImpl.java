package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.JudgeMapper;
import org.example.pojo.Judge;
import org.example.service.JudgeService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImpl extends ServiceImpl<JudgeMapper, Judge> implements JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;

    @Override
    public void login(Judge judge) {
        judgeMapper.insert(judge);
    }

    @Override
    public void logout(String uuid) {
        this.remove(Wrappers.<Judge>lambdaQuery().eq(Judge::getUuid, uuid));
        ThreadLocalUtil.remove();
    }

    @Override
    public Judge info(String uuid) {
        Judge judge = this.getOne(Wrappers.<Judge>lambdaQuery().eq(Judge::getUuid, uuid));
        return judge;
    }

}
