package org.example.controller;

import org.example.pojo.Result;
import org.example.service.CGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c_group_score")
public class CGroupScoreController {
    @Autowired
    private CGroupScoreService cGroupScoreService;

    @PostMapping("/submit")
    public Result submit(Integer judgeId, Integer athleteId, Integer actionNumber, Double score, Boolean responded) {
        cGroupScoreService.submit(judgeId, athleteId, actionNumber, score, responded);
        return Result.success();
    }
}
