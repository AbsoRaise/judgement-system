package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.CompetitionTypeEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_team_competition")
public class User {
    private String user;    //运动员
    private Integer team;   //队伍
    private String competition; //比赛
    private Double score;   //总分
    private CompetitionTypeEnum type;   //比赛小项
    private String groupp;  //组别
    private Integer sort;   //出场顺序
    private String actionsScores;   //套路动作及分数
    private String payment;
    private Boolean isPay;
}
