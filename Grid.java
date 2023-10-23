import java.util.ArrayList;
public class Grid {
  ArrayList<Integer> yVal = new ArrayList<Integer>();
  ArrayList<Integer> xVal = new ArrayList<Integer>();
  public static int gridX, gridY, numMines;
  HiddenTile[][] grid;
  Grid(int y, int x, int mines) {
    grid = new HiddenTile[y][x];
    for (int makeY = 0; makeY < y; makeY++) {
      for (int makeX = 0; makeX < x; makeX++) {
        grid[makeY][makeX] = new HiddenTile();
        yVal.add(makeY);
        xVal.add(makeX);
      }
    }
    gridX = x;
    gridY = y;
    numMines = mines;
    addMines();
  }

  

  public void addSurroundingNum(int y, int x) {
    if (y==0) {
      if (x==0) {
        grid[y+1][x].addNum();
        grid[y][x+1].addNum();
        grid[y+1][x+1].addNum();
      } else if (x!=gridY-1) {
        grid[y+1][x].addNum();
        grid[y][x+1].addNum();
        grid[y][x-1].addNum();
        grid[y+1][x+1].addNum();
        grid[y+1][x-1].addNum();
      } else {
        grid[y+1][x].addNum();
        grid[y][x-1].addNum();
        grid[y+1][x-1].addNum();
      }
    } else if (x==0) {
      if (y!= gridX-1) {
        grid[y-1][x].addNum();
        grid[y][x+1].addNum();
        grid[y+1][x].addNum();
        grid[y+1][x+1].addNum();
        grid[y-1][x+1].addNum();
      } else {
        grid[y-1][x].addNum();
        grid[y][x+1].addNum();
        grid[y-1][x+1].addNum();
      }
    } else if (y==gridX-1) {
      if (x==gridY-1) {
        grid[y-1][x].addNum();
        grid[y][x-1].addNum();
        grid[y-1][x-1].addNum();
      } else {
        grid[y-1][x].addNum();
        grid[y][x+1].addNum();
        grid[y][x-1].addNum();
        grid[y-1][x+1].addNum();
        grid[y-1][x-1].addNum();
      }
    } else if(x==gridY-1) {
      grid[y][x-1].addNum();
      grid[y+1][x].addNum();
      grid[y-1][x].addNum();
      grid[y-1][x-1].addNum();
      grid[y+1][x-1].addNum();
    } else {
      grid[y+1][x].addNum();
      grid[y-1][x].addNum();
      grid[y][x+1].addNum();
      grid[y][x-1].addNum();
      grid[y+1][x-1].addNum();
      grid[y+1][x+1].addNum();
      grid[y-1][x-1].addNum();
      grid[y-1][x+1].addNum();
    }
  }

  public void addMines() {
    //uses arraylist because it is faster if number of mines is close to total space in grid
    for (int i = 0; i < numMines; i++) {
      int rand = (int)(Math.random() * yVal.size());
      grid[yVal.get(rand)][xVal.get(rand)].setMine(true);
      addSurroundingNum(yVal.get(rand), xVal.get(rand));
      yVal.remove(rand);
      xVal.remove(rand);
    }
  }

  
  public String toString() {
    String gridPrint = "";
    for(int y = 0; y < gridY; y++) {
      for (int x = 0; x < gridX; x++) {
        gridPrint+= grid[y][x].getValue() + " ";
      }
      gridPrint += "\n";
    }
    return gridPrint;
    // alternative printing, where it has index numbers
    /*String gridPrint = "    ";
    for(int y = 0; y < gridY; y++) {
      gridPrint+= y + ". ";
    }
    gridPrint += "\n";
    for(int y = 0; y < gridY; y++) {
      gridPrint += y + ".  ";
      for (int x = 0; x < gridX; x++) {
        gridPrint += grid[y][x].getValue() + "  ";
      }
      gridPrint += "\n";
    }
    return gridPrint;*/
  }
  
  public String getTrueGrid() {
    //to check the true value of the grid
    String gridPrint = "";
    for(int y = 0; y < gridY; y++) {
      for (int x = 0; x < gridX; x++) {
        gridPrint+= grid[y][x].getTrueValue() + " ";
      }
      gridPrint += "\n";
    }
    return gridPrint;
  }
}