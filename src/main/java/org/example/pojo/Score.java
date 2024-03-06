package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("athlete_scores")
public class Score {
    private Integer athleteId;
    private Double totalScore;
    private Double overtimeScore;
    private Double aGroupScore;
    private Double bGroupScore;
    private Double cGroupScore;
    private Double routineCheckDeduction;
}
