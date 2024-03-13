package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Enum.CompetitionTypeEnum;
import org.example.mapper.AthleteMapper;
import org.example.mapper.TotalScoreMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Athlete;
import org.example.pojo.Score;
import org.example.pojo.User;
import org.example.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AthleteServiceImpl extends ServiceImpl<AthleteMapper, Athlete> implements AthleteService {
    @Autowired
    private AthleteMapper athleteMapper;
    @Autowired
    private TotalScoreMapper totalScoreMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Athlete> list(String competitiveType, String groupValue) {
        List<Athlete> athleteList = athleteMapper.selectList(Wrappers.<Athlete>lambdaQuery()
                .eq(Athlete::getCompetitiveType, competitiveType)
                .eq(Athlete::getMatchGroup, groupValue)
        );
        Collections.sort(athleteList, Comparator.comparing(Athlete::getOrderNum));
        return athleteList;
    }

    @Override
    public Athlete nextAthlete(String competitiveType, String groupValue, Integer athleteId) {
        List<Athlete> athleteList = athleteMapper.selectList(Wrappers.<Athlete>lambdaQuery()
                .eq(Athlete::getCompetitiveType, competitiveType)
                .eq(Athlete::getMatchGroup, groupValue)
        );
        Collections.sort(athleteList, Comparator.comparing(Athlete::getOrderNum));
        for (int i = 0; i < athleteList.size(); i++) {
            if (athleteList.get(i).getAthleteId().equals(athleteId)) {
                if (i == athleteList.size() - 1) {
                    return athleteList.get(0);
                } else {
                    return athleteList.get(i + 1);
                }
            }
        }
        return null;
    }

    @Override
    public Integer getRank(Integer athleteId) {
        //查询该运动员同比赛同小组的所有运动员
        List<Athlete> athleteList = athleteMapper.selectList(Wrappers.<Athlete>lambdaQuery()
                .eq(Athlete::getCompetitiveType, athleteMapper.selectOne(Wrappers.<Athlete>lambdaQuery().eq(Athlete::getAthleteId, athleteId)).getCompetitiveType())
                .eq(Athlete::getMatchGroup, athleteMapper.selectOne(Wrappers.<Athlete>lambdaQuery().eq(Athlete::getAthleteId, athleteId)).getMatchGroup())
        );
        //查询对应运动员的总分
        List<Score> scoreList = new ArrayList<>();
        for (Athlete athlete : athleteList) {
            scoreList.add(totalScoreMapper.selectOne(Wrappers.<Score>lambdaQuery().eq(Score::getAthleteId, athlete.getAthleteId())));
        }
        //对总分进行排序
        Collections.sort(scoreList, Comparator.comparing(Score::getTotalScore).reversed());
        for (int i = 0; i < scoreList.size(); i++) {
            if (scoreList.get(i).getAthleteId().equals(athleteId)) {
                return i + 1;
            }
        }
        return null;

    }

    @Override
    public void copyTo(String competitionName, CompetitionTypeEnum competitiveType) {
        DynamicDataSourceContextHolder.push("signprj");
        List<User> userList = copyfrom(competitionName, competitiveType.getKey());
        System.out.println(userList);
        for (User user : userList) {
            DynamicDataSourceContextHolder.poll();
            DynamicDataSourceContextHolder.push("signprj");
            Athlete athlete = new Athlete(
                    null,
                    selectUserName(user.getUser()),
                    competitiveType.getDisplay(),
                    user.getGroupp(),
                    user.getSort(),
                    user.getActionsScores()
            );
            DynamicDataSourceContextHolder.poll();
            DynamicDataSourceContextHolder.push("local");
            //如果运动员不存在则插入，否则更新
            if (athleteMapper.selectOne(Wrappers.<Athlete>lambdaQuery()
                    .eq(Athlete::getAthleteName, athlete.getAthleteName())
                    .eq(Athlete::getCompetitiveType, athlete.getCompetitiveType())
                    .eq(Athlete::getMatchGroup, athlete.getMatchGroup())
            ) == null){
                athleteMapper.insert(athlete);
            }else{
                athleteMapper.update(athlete,Wrappers.<Athlete>lambdaQuery()
                        .eq(Athlete::getAthleteName, athlete.getAthleteName())
                        .eq(Athlete::getCompetitiveType, athlete.getCompetitiveType())
                        .eq(Athlete::getMatchGroup, athlete.getMatchGroup()));
            }
        }
    }

    @DS("signprj")
    private List<User> copyfrom(String competitionName, Integer competitionType) {
        //查询signprj数据库中的运动员列表
        List<User> userList = userMapper.selectList(Wrappers.<User>lambdaQuery()
                .eq(User::getType, competitionType)
                .eq(User::getCompetition, competitionName)
        );
        return userList;
    }

    private String selectUserName(String userIdentity) {
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 创建数据库连接
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://121.40.35.81:3306/signproject?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "adminadminadmin"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM user WHERE id = " + userIdentity);
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                // 关闭连接
                resultSet.close();
                statement.close();
                connection.close();
                // 返回查询结果
                return username;
            } else {
                // 关闭连接
                resultSet.close();
                statement.close();
                connection.close();
                // 如果没有查询到结果，返回null
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
