import javalib.funworld.*; // the abstract World class and the big-bang library
import java.awt.Color;
import javalib.worldimages.*; 

//Represents the FrenzyWorld 
class FrenzyWorld extends World {
  ILoFish fish;
  Fish player;
  int score;
  Utils utils;

  // Main constructor
  FrenzyWorld(Fish player, ILoFish fish, int score) {
    this.fish = fish;
    this.player = player;
    this.score = score;
  }

  // For OnTickTesting 
  FrenzyWorld(Fish player, ILoFish fish, int score, Utils utils) {
    this.fish = fish;
    this.player = player;
    this.score = score;
    this.utils = utils;
  }

  /* TEMPLATES:
   * Fields:
   * ...this.fish... -- ILoFish
   * ...this.player... -- Player
   * Methods:
   * ...this.makeScene()... -- WorldScene
   * ...this.onTick()... -- WorldScene
   * ...this.onTickTesting()... WorldScene
   * ...this.onKeyEvent(String)... FrenzyWorld
   * ...this.lastScene(String)... WorldScene
   * Methods for Fields:
   * ...this.fish.makeScene()... -- WorldScence
   * ...this.player.makeScene()... -- WorldScence
   * ...this.fish.onTick()... -- WorldScence
   * ...this.player.onTick()... -- WorldScence
   * ...this.fish.onTickTesting()... -- WorldScence
   * ...this.player.onTickTesting()... -- WorldScence
   * ... rests of methods for this.fish and this.player
   */

  //Creates the scene (world)
  public WorldScene makeScene() {
    return this.fish.drawLoFish(this.player.placeFish(new WorldScene(Utils.WIDTH, Utils.HEIGHT)));
  }

  // Creates the ontick game part(running the actual game on tick)
  public World onTick() {
    //Is the Game Over? - Yes
    if (this.fish.count() == Utils.MAXNUMFISH && this.player.radius >= Utils.MAXSIZE) {
      return this.endOfWorld("You Won! Total Score: " + Integer.toString(this.score));
    }
    // Game is not over, so ->
    else {
      // Starting the game
      if (this.fish.count() == 0) {
        return new FrenzyWorld(this.player, new Utils().starterFish().moveLoFish(), this.score);
      }
      // Checks if there are any Fish missing
      else if (this.fish.count() < Utils.MAXNUMFISH) {
        ILoFish addFish = new ConsLoFish(new Utils().randFish(this.player.radius + 5), this.fish);
        return new FrenzyWorld(this.player, addFish.moveLoFish(), this.score);
      }
      // Check for possible eating of Fish
      else {
        if (this.fish.overlap(this.player) && this.fish.canEatFish(player)) {
          System.out.println(this.player.score);
          return new FrenzyWorld(this.player.grow(this.fish.fishScore(this.player)),
              this.fish.eat(player), this.score + this.fish.fishScore(player) * 10);
        }
        else if (this.fish.overlap(player)) {
          return this.endOfWorld("You lost! Total Score: " + Integer.toString(this.score));
        }
        else {
          return new FrenzyWorld(this.player, this.fish.moveLoFish(), this.score);
        }
      }
    }
  }

  // Creates the ontick game for testing
  public World onTickTesting(Utils u) {
    // Use utils instead of creating a new Utils instance
    if (this.fish.count() == Utils.MAXNUMFISH && this.player.radius >= Utils.MAXSIZE) {
      return this.endOfWorld("You Won! Total Score: " + Integer.toString(this.score));
    } else {
      if (this.fish.count() == 0) {
        return new FrenzyWorld(this.player, this.utils.starterFish().moveLoFish(), 
            this.score, this.utils);
      } else if (this.fish.count() < Utils.MAXNUMFISH) {
        ILoFish addFish = new ConsLoFish(this.utils.randFish(this.player.radius + 5), this.fish);
        return new FrenzyWorld(this.player, addFish.moveLoFish(), this.score, this.utils);
      } else {
        if (this.fish.overlap(this.player) && this.fish.canEatFish(player)) {
          return new FrenzyWorld(this.player.grow(this.fish.fishScore(this.player)), 
              this.fish.eat(player), this.score + this.fish.fishScore(player) * 10, this.utils);
        } else if (this.fish.overlap(player)) {
          return this.endOfWorld("You lost! Total Score: " + Integer.toString(this.score));
        } else {
          return new FrenzyWorld(this.player, this.fish.moveLoFish(), this.score, this.utils);
        }
      }
    }
  }

  // Handles onKeyEvents for user Movement
  public FrenzyWorld onKeyEvent(String key) {

    if (key.equals("left")) {
      return new FrenzyWorld(this.player.moveLeft(Utils.PLAYERSPEED), this.fish, this.score);
    }
    else if (key.equals("right")) {
      return new FrenzyWorld(this.player.moveRight(Utils.PLAYERSPEED), this.fish, this.score);
    }
    else if (key.equals("up")) {
      return new FrenzyWorld(this.player.moveUp(Utils.PLAYERSPEED), this.fish, this.score);
    }
    else if (key.equals("down")) {
      return new FrenzyWorld(this.player.moveDown(Utils.PLAYERSPEED), this.fish, this.score);
    }
    else {
      return this;
    }
  }

  // End Screen method
  public WorldScene lastScene(String msg) {
    return new WorldScene(600, 600).placeImageXY(new TextImage(msg, 40, Color.black), 300, 300);
  }

}
