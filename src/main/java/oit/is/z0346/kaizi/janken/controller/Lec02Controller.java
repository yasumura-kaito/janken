package oit.is.z0346.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0346.kaizi.janken.model.Janken;
import oit.is.z0346.kaizi.janken.model.Room;
import oit.is.z0346.kaizi.janken.model.UserMapper;
import oit.is.z0346.kaizi.janken.model.User;
import oit.is.z0346.kaizi.janken.model.MatchMapper;
import oit.is.z0346.kaizi.janken.model.Match;
import oit.is.z0346.kaizi.janken.model.MatchInfoMapper;
import oit.is.z0346.kaizi.janken.model.MatchInfo;

@Controller
public class Lec02Controller{

  @Autowired
  private Room room;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

  @GetMapping("/lec02")
  public String lec02(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("room", this.room);

    ArrayList<User> users = userMapper.selectAll();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAll();
    model.addAttribute("matches", matches);

    ArrayList<MatchInfo> multimatchinfo = matchInfoMapper.selectAll();
    MatchInfo matchinfo1 = new MatchInfo();
    int i;
    for(i=0; i<multimatchinfo.size(); i++){
      matchinfo1 = multimatchinfo.get(i);
      if(matchinfo1.getIsActive() == false){
        multimatchinfo.remove(i);
        i--;
      }
    }
    model.addAttribute("multimatchinfo", multimatchinfo);

    return "lec02.html";
  }

  @GetMapping("/match")
  public String matchCPU(@RequestParam Integer id, ModelMap model, Principal prin){
    ArrayList<User> users = userMapper.selectAll();
    User user1 = new User();
    String opponent;
    int i;
    for(i=0; i<users.size(); i++){
      user1 = users.get(i);
      if(id == user1.getId()){
        opponent = user1.getName();
        model.addAttribute("opponent", opponent);
      }
    }
    String loginUser = prin.getName();
    model.addAttribute("login_user", loginUser);
    return "match.html";
  }

  @GetMapping("/wait")
  public String wait(@RequestParam Integer hand, @RequestParam String opponent, ModelMap model, Principal prin){

    ArrayList<MatchInfo> multimatchinfo = matchInfoMapper.selectAll();
    MatchInfo matchinfo1 = new MatchInfo();
    matchinfo1.setId(multimatchinfo.size()+1);

    String loginUser = prin.getName();
    ArrayList<User> users = userMapper.selectAll();
    User user1 = new User();
    int i;
    for(i=0; i<users.size(); i++){
      user1 = users.get(i);
      if(opponent.equals(user1.getName())){
        matchinfo1.setUser2(user1.getId());
      }
      if(loginUser.equals(user1.getName())){
        matchinfo1.setUser1(user1.getId());
      }
    }

    String user1Hand = handChange(hand);
    matchinfo1.setUser1Hand(user1Hand);

    matchinfo1.setIsActive(true);

    ArrayList<Match> matches = matchMapper.selectAll();
    MatchInfo matchinfo2 = new MatchInfo();
    Match match1 = new Match();
    int flag = 0;
    int cnt = matches.size();
    for(i=0; i<multimatchinfo.size(); i++){
      matchinfo2 = multimatchinfo.get(i);
      if(matchinfo1.getIsActive() == true && matchinfo1.getUser1() == matchinfo2.getUser2() && matchinfo1.getUser2() == matchinfo2.getUser1()){
        flag = 1;
        match1.setId(cnt + 1);
        match1.setUser1(matchinfo1.getUser1());
        match1.setUser2(matchinfo2.getUser1());
        match1.setUser1Hand(matchinfo1.getUser1Hand());
        match1.setUser2Hand(matchinfo2.getUser1Hand());
        match1.setIsActive(true);
        matchMapper.insertMatch(match1);
      }
    }

    if(flag == 0){
      matchInfoMapper.insertMatchInfo(matchinfo1);
    }
    model.addAttribute("login_user", loginUser);
    return "wait.html";
  }

  public String handChange(Integer num){
    String hand = "";
    if(num == 1){
      hand = "Gu";
    }else if(num == 2){
      hand = "Choki";
    }else if(num == 3){
      hand = "Pa";
    }
    return hand;
  }
}
