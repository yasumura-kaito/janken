package oit.is.z0346.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0346.kaizi.janken.model.janken;

@Controller
public class Lec02Controller{

  @GetMapping("/lec02")
  public String lec02() {
    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(@RequestParam String name, ModelMap model) {
    String user_name = name;
    model.addAttribute("name", user_name);
    return "lec02.html";
  }

  @GetMapping("/lec02A")
  public String lec02A(ModelMap model) {
    String your_hand = "Gu";
    String cpu_hand = "Gu";
    String result = "Draw!";
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "lec02.html";
  }

  @GetMapping("/lec02B")
  public String lec02B(ModelMap model) {
    String your_hand = "Choki";
    String cpu_hand = "Gu";
    String result = "Lose!";
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "lec02.html";
  }

  @GetMapping("/lec02C")
  public String lec02C(ModelMap model) {
    String your_hand = "Pa";
    String cpu_hand = "Gu";
    String result = "Win!";
    model.addAttribute("your_hand", your_hand);
    model.addAttribute("cpu_hand", cpu_hand);
    model.addAttribute("result", result);
    return "lec02.html";
  }
}
