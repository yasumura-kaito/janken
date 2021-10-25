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

import oit.is.z0346.kaizi.janken.model.janken;
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

  @GetMapping("/lec02A")
  public String lec02A(ModelMap model) {
    String your_hand = "Gu";
    String cpu_hand = "Gu";
    String result = "Draw!";
    ArrayList<Match> matches = matchMapper.selectAll();
    int cnt = matches.size();
    Match match2 = new Match();
    match2.setId(cnt + 1);
    match2.setUser1(2);
    match2.setUser2(1);
    match2.setUser1Hand(your_hand);
    match2.setUser2Hand(cpu_hand);
    matchMapper.insertMatch(match2);
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "match.html";
  }

  @GetMapping("/lec02B")
  public String lec02B(ModelMap model) {
    String your_hand = "Choki";
    String cpu_hand = "Gu";
    String result = "Lose!";
    ArrayList<Match> matches = matchMapper.selectAll();
    int cnt = matches.size();
    Match match2 = new Match();
    match2.setId(cnt + 1);
    match2.setUser1(2);
    match2.setUser2(1);
    match2.setUser1Hand(your_hand);
    match2.setUser2Hand(cpu_hand);
    matchMapper.insertMatch(match2);
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "match.html";
  }

  @GetMapping("/lec02C")
  public String lec02C(ModelMap model) {
    String your_hand = "Pa";
    String cpu_hand = "Gu";
    String result = "Win!";
    ArrayList<Match> matches = matchMapper.selectAll();
    int cnt = matches.size();
    Match match2 = new Match();
    match2.setId(cnt + 1);
    match2.setUser1(2);
    match2.setUser2(1);
    match2.setUser1Hand(your_hand);
    match2.setUser2Hand(cpu_hand);
    matchMapper.insertMatch(match2);
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "match.html";
  }

  @GetMapping("/match")
  public String matchCPU(@RequestParam Integer id, ModelMap model){

    return "match.html";
  }
}
