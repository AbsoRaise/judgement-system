package org.example.controller;

import org.example.pojo.Athlete;
import org.example.pojo.Result;
import org.example.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
