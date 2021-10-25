package oit.is.z0346.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches;")
  ArrayList<Match> selectAll();

  @Insert("INSERT INTO matches (id,user1,user2,user1Hand,user2Hand) VALUES (#{id},#{user1},#{user2},#{user1Hand},#{user2Hand});")
  void insertMatch(Match match2);
}
