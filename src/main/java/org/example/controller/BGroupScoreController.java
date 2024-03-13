package org.example.controller;

import org.example.pojo.Result;
import org.example.service.BGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b_group_score")
public class BGroupScoreController {
    @Autowired
    private BGroupScoreService bGroupScoreService;

    @PostMapping("/submit")
    public Result submit(Integer judgeId, Integer athleteId, Double score){
        bGroupScoreService.submit(judgeId, athleteId, score);
        return Result.success();
    }
    @GetMapping("/calc")
    public Result<Double> calc(Integer athleteId){
        Double score = bGroupScoreService.calc(athleteId);
        if (score == null) {
            return Result.error("评分未完成");
        } else {
            return Result.success(score);
        }
    }
}
