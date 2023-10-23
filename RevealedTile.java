public class RevealedTile extends HiddenTile {
  public RevealedTile(HiddenTile h) {
    setMine(h.isMine());
    setNum(h.getTileNum());
  }
  public String getValue() {
    if (isMine()) {
      return "m";
    }
    String temp = "";
    temp+= getTileNum();
    return temp;
  }
}