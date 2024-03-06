package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Athlete;

@Mapper
public interface AthleteMapper extends BaseMapper<Athlete> {

}
