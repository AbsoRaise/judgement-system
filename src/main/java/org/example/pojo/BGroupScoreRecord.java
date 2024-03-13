package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("b_group_score_record")
@NoArgsConstructor
@AllArgsConstructor
public class BGroupScoreRecord {
    @TableId
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private Double score;

    public BGroupScoreRecord(Integer judgeId, Integer athleteId, Double score) {
        this.judgeId = judgeId;
        this.athleteId = athleteId;
        this.score = score;
    }
}
