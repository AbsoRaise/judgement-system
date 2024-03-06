package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("b_group_score_record")
public class BGroupScore {
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private Double score;
}
