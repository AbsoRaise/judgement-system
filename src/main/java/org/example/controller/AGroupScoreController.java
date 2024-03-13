package org.example.controller;

import org.example.pojo.Result;
import org.example.service.AGroupScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a_group_score")
public class AGroupScoreController {
    @Autowired
    private AGroupScoreService aGroupScoreService;

    @PostMapping("/submit")
    public Result submit(Integer judgeId, Integer athleteId, String code){
        aGroupScoreService.submit(judgeId, athleteId, code);
        return Result.success();
    }

}
