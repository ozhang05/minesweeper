public class HiddenTile {
  boolean mineOrNo = false;
  int tileNum = 0;
  boolean alreadyChecked = false;
  boolean marked = false;
  public int getTileNum() {
    return tileNum;
  }
  public boolean isMine() {
    if (mineOrNo) {
      return true;
    }
    return false;
  }
  public void addNum() {
    tileNum++;
  }
  public void setNum(int n) {
    tileNum = n;
  }
  public void setMine(boolean m) {
    mineOrNo = m;
  }
  public String getValue() {
    return "_";
  }
  public boolean checkStatus() {
    return alreadyChecked;
  }
  
  public void changeCheck(boolean c) {
    alreadyChecked = c;
  }
  public String getTrueValue() {
    if (isMine()) {
      return "m";
    }
    String temp = "";
    temp+= getTileNum();
    return temp;
  }
  public void mark(boolean m) {
    marked = m;
  }
  public boolean checkMark() {
    return marked;
  }
}