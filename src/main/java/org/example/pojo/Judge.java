package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("judge_list")
public class Judge{
    @TableId
    private Integer judgeId;    //裁判id
    private String judgeGroup;  //裁判组别
    private String uuid;    //UUID
}
