package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.AGroupScoreRecord;
import org.springframework.stereotype.Repository;

@Mapper
public interface AGroupScoreMapper extends BaseMapper<AGroupScoreRecord> {
}
