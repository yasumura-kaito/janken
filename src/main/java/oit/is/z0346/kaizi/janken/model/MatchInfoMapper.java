package oit.is.z0346.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT * from matchinfo;")
  ArrayList<MatchInfo> selectAll();

  @Insert("INSERT INTO matchinfo (id,user1,user2,user1Hand,isActive) VALUES (#{id},#{user1},#{user2},#{user1Hand},#{isActive});")
  void insertMatchInfo(MatchInfo matching);
}
