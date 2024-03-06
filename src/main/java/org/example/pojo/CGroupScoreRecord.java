package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_group_score_record")
public class CGroupScoreRecord {
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private Integer actionNumber;
    private Boolean responded;
}
