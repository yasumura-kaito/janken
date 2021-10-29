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

@Controller
public class Lec02Controller{

  @Autowired
  private Room room;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

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

    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(@RequestParam String name, ModelMap model) {
    String user_name = name;
    model.addAttribute("name", user_name);
    return "match.html";
  }

  @GetMapping("/battle")
  public String battle(@RequestParam Integer hand, ModelMap model) {
    Janken janken1 = new Janken(hand);
    model.addAttribute("your_hand", janken1.getYourHand());
    model.addAttribute("cpu_hand", janken1.getCpuHand());
    model.addAttribute("result", janken1.getResult());
    ArrayList<Match> matches = matchMapper.selectAll();
    int cnt = matches.size();
    Match match2 = new Match();
    match2.setId(cnt + 1);
    match2.setUser1(2);
    match2.setUser2(1);
    match2.setUser1Hand(janken1.getYourHand());
    match2.setUser2Hand(janken1.getCpuHand());
    matchMapper.insertMatch(match2);
    return "match.html";
  }

  @GetMapping("/match")
  public String matchCPU(@RequestParam Integer id, ModelMap model){

    return "match.html";
  }
}
