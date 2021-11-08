package oit.is.z0346.kaizi.janken.model;


public class Janken{
  String your_hand;
  String cpu_hand = "Gu";
  String result;

  public Janken(Integer hand){
    if(hand==1){
      this.your_hand = "Gu";
      this.result = "Draw!";
    }else if(hand==2){
      this.your_hand = "Choki";
      this.result = "Lose!";
    }else if(hand==3){
      this.your_hand = "Pa";
      this.result = "Win!";
    }
  }

  public String getYourHand() {
    return your_hand;
  }

  public void setYourHand(String your_hand) {
    this.your_hand = your_hand;
  }

  public String getCpuHand() {
    return cpu_hand;
  }

  public void setCpuHand(String cpu_hand) {
    this.cpu_hand = cpu_hand;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
