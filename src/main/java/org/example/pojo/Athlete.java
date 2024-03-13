package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.CompetitionTypeEnum;

import java.io.Serializable;

@Data
@TableName("athlete_list")
@NoArgsConstructor
@AllArgsConstructor
public class Athlete implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer athleteId;  //运动员id
    private String athleteName; //运动员姓名
    private String competitiveType; //比赛类型
    private String matchGroup;   //组别
    private Integer orderNum;   //出场顺序
    private String actions;   //动作


}
