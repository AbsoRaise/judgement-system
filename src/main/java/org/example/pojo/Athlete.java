package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("athlete_list")
public class Athlete {
    private Integer athleteId;  //运动员id
    private String athleteName; //运动员姓名
    private String competitiveType; //比赛类型
    private String matchGroup;   //组别
    private String orderNum;   //出场顺序


}
