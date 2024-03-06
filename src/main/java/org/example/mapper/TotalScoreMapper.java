package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Judge;
import org.example.pojo.Score;
@Mapper
public interface TotalScoreMapper extends BaseMapper<Score> {
}
