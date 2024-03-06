package org.example.controller;

import org.example.pojo.Result;
import org.example.service.TotalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/totalscore")
public class TotalScoreController {
    @Autowired
    private TotalScoreService totalScoreService;

    @PostMapping("/create")
    public Result createAthleteScore(Integer athleteId) {
        totalScoreService.create(athleteId);
        return Result.success();
    }

    @PostMapping("/deduction/overtime_score")
    public Result overtimeScoreDeduction(Integer athleteId, Double scoreValue) {
        totalScoreService.overtimeScoreDeduction(athleteId, scoreValue);
        return Result.success();
    }

    @PostMapping("/deduction/routine_check")
    public Result routineCheckDeduction(Integer athleteId, Double scoreValue) {
        totalScoreService.routineCheckDeduction(athleteId, scoreValue);
        return Result.success();
    }
}
