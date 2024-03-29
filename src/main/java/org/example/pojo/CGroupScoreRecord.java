package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("c_group_score_record")
@NoArgsConstructor
@AllArgsConstructor
public class CGroupScoreRecord {
    @TableId
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private Integer actionNumber;
    private Double score;
    private Boolean responded;

    public CGroupScoreRecord(Integer judgeId, Integer athleteId, Integer actionNumber, Double score, Boolean responded) {
        this.judgeId = judgeId;
        this.athleteId = athleteId;
        this.actionNumber = actionNumber;
        this.score = score;
        this.responded = responded;
    }
}
