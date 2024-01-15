import java.awt.Color;
import java.util.Random;

class Utils {
  public static int WIDTH = 600;
  public static int HEIGHT = 600;
  public static int PLAYERSPEED = 5; 
  public static int FISHSPEED = 1; 
  public static int MAXSIZE = 60;
  public static int MAXNUMFISH = 20;
  public static int STARTINGSIZE = 5;
  // For game
  public Random rand = new Random();
  // For testing
  public Random randSeed = new Random(10);

  // Default game constructor
  public Utils() {
    this.rand = new Random();
  }

  // test random with seed 
  public Utils(int seed) {
    this.rand = new Random(seed);
  }

  //Generates a random Direction for spawning Fish
  public String randDir() {
    int r = rand.nextInt(2);
    if (r == 0) {
      return "left";
    }
    else {
      return "right";
    }
  }

  //Generates a random Color for spawning Fish
  public Color randColor() {
    int r = rand.nextInt(6);
    if (r == 0) {
      return Color.blue;
    }
    else if (r == 1) {
      return Color.green;
    }
    else if (r == 2) {
      return Color.black;
    }
    else if (r == 3) {
      return Color.pink;
    }
    else if (r == 4) {
      return Color.orange;
    }
    else {
      return Color.magenta;
    }
  }

  //Generates a random Fish at random X, Y, Radius, Color, and Direction
  public Fish randFish(int maxSize) {
    return  new Fish(randColor(), rand.nextInt(Utils.WIDTH), rand.nextInt(Utils.HEIGHT),
        rand.nextInt(maxSize), randDir(), 0);
  }

  //Generates a random Fish at random X, Y, Radius with given max, Color, and Direction
  public Fish randFishMax(int max) {
    return new Fish(randColor(), rand.nextInt(Utils.WIDTH), rand.nextInt(Utils.HEIGHT),
        rand.nextInt(max), randDir(), ((rand.nextInt(max) / 10) % 10) + 1) ;
  }

  //Generates 3 Fish that are smaller than the Player Fish
  public ILoFish starterFish() {
    return new ConsLoFish(randFishMax(STARTINGSIZE), new ConsLoFish(randFishMax(STARTINGSIZE), 
        new ConsLoFish(randFishMax(STARTINGSIZE), new MtLoFish())));
  }

}
