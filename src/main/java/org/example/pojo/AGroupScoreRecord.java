package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("a_group_score_record")
@NoArgsConstructor
@AllArgsConstructor
public class AGroupScoreRecord {
    @TableId
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private String errorCode;
    private LocalDateTime timestamp;
    private Boolean responded;

    public AGroupScoreRecord(Integer judgeId, Integer athleteId, String errorCode, LocalDateTime timestamp, Boolean responded) {
        this.judgeId = judgeId;
        this.athleteId = athleteId;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.responded = responded;
    }
}
