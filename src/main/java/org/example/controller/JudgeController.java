package org.example.controller;

import org.example.pojo.Judge;
import org.example.pojo.Result;
import org.example.service.JudgeService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;


    @PostMapping("/login")
    public Result login(String judgeGroup) {
        Judge judge = new Judge();
        judge.setJudgeGroup(judgeGroup);
        judge.setUuid(UUID.randomUUID().toString());
        judgeService.login(judge);
        return Result.success(judge.getUuid());
    }

    @PostMapping("/logout")
    public Result logout(String judgeUuid){
        judgeService.logout(judgeUuid);
        return Result.success();
    }
    @GetMapping("/info")
    public Result<Judge> info(String judgeUuid){
        Judge judge = judgeService.info(judgeUuid);
        if (judge != null){
            return Result.success(judge);
        }else{
            return Result.error("裁判信息不存在");
        }

    }

}
