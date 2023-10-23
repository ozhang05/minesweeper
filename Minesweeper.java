import java.util.Scanner;
public class Minesweeper {
  Scanner myObj = new Scanner(System.in);
  private boolean lost, won;
  private int turnCount;
  private int userX, userY;
  private int gridY = 10, gridX = 10;
  private int mineCount = 4;
  private int firstClickY, firstClickX;
  Grid g = new Grid(gridY, gridX, mineCount);
  
  public void runGame() {
    while (!lost && !won) {
      System.out.println("turn: " + turnCount);
      //System.out.println(g.getTrueGrid());
      System.out.println(g);
      System.out.print("input row:");
      userY = myObj.nextInt();
      firstClickY = userY;
      System.out.print("input column:");
      userX = myObj.nextInt();
      firstClickX = userX;
      click(userY, userX);
      resetAllCheck();
      if (g.grid[firstClickY][firstClickX].getTileNum() == 0) {
        for (int y1 = 0; y1 < gridY; y1++) {
          for (int x1 = 0; x1 < gridX; x1++) {
            if (checkNearZero(y1, x1)) {
              if (g.grid[y1][x1].getTileNum() == 0) {
                click(y1, x1);
              } else {
                otherClick(y1, x1);
              }
            }
          }
        }
      }
      resetAllCheck();
      turnCount++;
      checkWon();
      System.out.print("\033[H\033[2J");
    }
    if (won) {
      System.out.println("you won");
      System.out.println(g);
    } else {
      System.out.println("you lost");
      HiddenTile h = new RevealedTile(g.grid[userY][userX]);
      g.grid[userY][userX] = h;
      System.out.println(g);
    }
  }
  
  public void click(int y, int x) {
    if (g.grid[y][x].isMine()) {
      lost = true;
    } else if (g.grid[y][x].getValue().equals("_")) {
      HiddenTile h = new RevealedTile(g.grid[y][x]);
      g.grid[y][x] = h;
      if (g.grid[firstClickY][firstClickX].getTileNum() == 0) {
        checkSurroundings(y, x);
      }
      resetAllCheck();
    }
  }
  public void otherClick(int y, int x) {
    if (g.grid[y][x].getValue().equals("_")) {
      HiddenTile h = new RevealedTile(g.grid[y][x]);
      g.grid[y][x] = h;
      g.grid[y][x].changeCheck(false);
    }
  }
  public void resetAllCheck() {
    for (int y = 0; y < g.grid.length; y++) {
      for (int x = 0; x < g.grid[0].length; x++) {
        g.grid[y][x].changeCheck(false);
      }
    }
  }
  
  private void checkWon() {
    int checkCount = 0;
    for (int y = 0; y < gridY; y++) {
      for (int x = 0; x < gridX; x++) {
        if (g.grid[y][x].getValue().equals("_")) {
          checkCount++;
        }
      }
    }
    if (checkCount == mineCount) {
      won = true;
    }
  }
  
  public boolean check(int y, int x) {
    //System.out.println("chck: " + y + " " + x);
    if (g.grid[y][x].getTileNum() == 0 && !g.grid[y][x].isMine() && !g.grid[y][x].checkStatus()) {
      HiddenTile h = new RevealedTile(g.grid[y][x]);
      g.grid[y][x] = h;
      g.grid[y][x].changeCheck(true);
      return true;
    }
    return false;
  }

  private boolean checkNearZero(int y, int x) {
    //returns true if the tile is next to a zero (both edge and corner)
    if (y == 0) {
      if (x == 0) {
        if (g.grid[y+1][x+1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0")) {
          return true;
        }
      } else if (x == gridX-1) {
        if (g.grid[y+1][x-1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0")) {
          return true;
        }
      } else {
        if (g.grid[y+1][x-1].getValue().equals("0") || g.grid[y+1][x+1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0")) {
          return true;
        }
      }
    } else if (x==0) {
      if (y == gridY-1) {
        if (g.grid[y-1][x+1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0")) {
          return true;
        }
      } else {
        if (g.grid[y-1][x+1].getValue().equals("0") || g.grid[y+1][x+1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0")) {
          return true;
        }
      }
    } else if (y==gridY-1) {
      if (x==gridX-1) {
        if (g.grid[y-1][x-1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0")) {
          return true;
        }
      } else {
        if (g.grid[y-1][x-1].getValue().equals("0") || g.grid[y-1][x+1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0")) {
          return true;
        }
      }
    } else if (x==gridX-1) {
      if (g.grid[y-1][x-1].getValue().equals("0") || g.grid[y+1][x-1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0")) {
        return true;
      }
    } else {
      if (g.grid[y-1][x-1].getValue().equals("0") || g.grid[y-1][x+1].getValue().equals("0") || g.grid[y+1][x-1].getValue().equals("0") || g.grid[y+1][x+1].getValue().equals("0") || g.grid[y-1][x].getValue().equals("0") || g.grid[y][x-1].getValue().equals("0") || g.grid[y+1][x].getValue().equals("0") || g.grid[y][x+1].getValue().equals("0")) {
        return true;
      }
    }
    return false;
  }
  
  private void checkSurroundings(int y, int x) {
    //System.out.println("bruH: " + y + " " + x);
    if (y == 0) {
      if (x == 0) {
        if (check(y+1, x)) {
          checkSurroundings(y+1, x);
        }
        if (check(y, x+1)) {
          checkSurroundings(y, x+1);
        }
      } else if (x == gridX-1) {
        if (check(y+1, x)) {
          checkSurroundings(y+1, x);
        }
        if (check(y, x-1)) {
          checkSurroundings(y, x-1);
        }
      } else {
        if (check(y+1, x)) {
          checkSurroundings(y+1, x);
        }
        if (check(y, x-1)) {
          checkSurroundings(y, x-1);
        }
        if (check(y, x+1)) {
          checkSurroundings(y, x+1);
        }
      }
    } else if (x==0) {
      if (y == gridY-1) {
        if (check(y, x+1)) {
          checkSurroundings(y, x+1);
        }
        if (check(y-1, x)) {
          checkSurroundings(y-1, x);
        }
      } else {
        if (check(y-1, x)) {
          checkSurroundings(y-1, x);
        }
        if (check(y+1, x)) {
          checkSurroundings(y+1, x);
        }
        if (check(y, x+1)) {
          checkSurroundings(y, x+1);
        }
      }
    } else if (y==(gridY-1)) {
      if (x==(g.grid[0].length-1)) {
        if (check(y-1, x)) {
          checkSurroundings(y-1, x);
        }
        if (check(y, x-1)) {
          checkSurroundings(y, x-1);
        }
      } else {
        if (check(y-1, x)) {
          checkSurroundings(y-1, x);
        }
        if (check(y, x-1)) {
          checkSurroundings(y, x-1);
        }
        if (check(y, x+1)) {
          checkSurroundings(y, x+1);
        }
      }
    } else if (x==(gridX-1)) {
      if (check(y, x-1)) {
        checkSurroundings(y, x-1);
      }
      if (check(y+1, x)) {
        checkSurroundings(y+1, x);
      }
      if (check(y-1, x)) {
        checkSurroundings(y-1, x);
      }
    } else {
      if (check(y-1, x)) {
        checkSurroundings(y-1, x);
      }
      if (check(y+1, x)) {
        checkSurroundings(y+1, x);
      }
      if (check(y, x-1)) {
        checkSurroundings(y, x-1);
      }
      if (check(y, x+1)) {
        checkSurroundings(y, x+1);
      }
    }
  }
}