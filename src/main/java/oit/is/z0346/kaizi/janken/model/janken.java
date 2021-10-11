package oit.is.z0346.kaizi.janken.model;

public class janken{
  String result;

  public void Janken(String hand){
    if(hand.equals("Gu")){
      result = "Draw!";
    }else if(hand.equals("Choki")){
      result = "Lose!";
    }
      result = "Win!";
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
