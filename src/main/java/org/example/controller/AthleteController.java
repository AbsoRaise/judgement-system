package org.example.controller;

import org.example.Enum.CompetitionTypeEnum;
import org.example.pojo.Athlete;
import org.example.pojo.Result;
import org.example.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/athlete")
public class AthleteController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping("/list")
    public Result<List<Athlete>> list(String competitiveType, String groupValue) {
        List<Athlete> athleteList = athleteService.list(competitiveType, groupValue);
        return Result.success(athleteList);


    }

    @GetMapping("/next_athlete")
    public Result<Athlete> nextAthlete(String competitiveType, String groupValue, Integer athleteId) {
        Athlete athlete = athleteService.nextAthlete(competitiveType, groupValue, athleteId);
        if (athlete == null) {
            return Result.error("已经是最后一个运动员");
        } else {
            return Result.success(athlete);
        }

    }
    @GetMapping("/get_rank")
    public Result<Integer> getRank(Integer athleteId) {
        Integer rank = athleteService.getRank(athleteId);
        return Result.success(rank);
    }

    @PostMapping("/copy")
    public Result<String> copy(String competitionName,CompetitionTypeEnum competitiveType) {
        athleteService.copyTo(competitionName,competitiveType);
        return Result.success();
    }

}
