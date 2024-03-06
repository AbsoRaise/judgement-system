package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Data
@TableName("a_group_score_record")
public class AGroupScoreRecord {
    private Integer recordId;
    private Integer judgeId;
    private Integer athleteId;
    private String errorCode;
    private DateTimeLiteralExpression.DateTime timestamp;
    private Boolean responded;
}
