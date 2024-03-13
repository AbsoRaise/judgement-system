package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("athlete_scores")
public class Score {
    @TableId
    private Integer athleteId;  //运动员id
    private Double totalScore;  //总分
    private Double overtimeScore;   //超时扣分
    private Double aGroupScore; //A组扣分
    private Double bGroupScore; //B组扣分
    private Double cGroupScore; //C组扣分
    private Double routineCheckDeduction;   //套路检查扣分
}
