package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Judge;
import org.example.pojo.Score;
import org.springframework.stereotype.Repository;

@Mapper
public interface TotalScoreMapper extends BaseMapper<Score> {
}
