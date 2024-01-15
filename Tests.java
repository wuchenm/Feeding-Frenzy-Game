import java.awt.Color;
import javalib.worldimages.*; 
import tester.Tester;
import javalib.funworld.WorldScene;

// Represents the examples for the Frenzy Game
class Examples {

  // Example for the player fish
  Fish player = new Fish(Color.red, 300, 200, Utils.STARTINGSIZE, "right", 0);

  //Eatable Fish
  Fish eatable = new Fish(Color.red, 300, 200, Utils.STARTINGSIZE, "right", 0);

  // Example of mtlist of fish
  ILoFish mt = new MtLoFish();

  // Example of the start of a WorldScene
  WorldScene world1 = new WorldScene(600, 600);

  // Examples of fish in the baackground game
  Fish l1 = new Fish(Color.red, 300, 200, 20, "left", 0);
  Fish l2 = new Fish(Color.yellow, 500, 100, 10, "left", 0);
  Fish l3 = new Fish(Color.pink, 300, 200, 2, "right", 0);
  Fish l4 = new Fish(Color.black, 400, 500, 20, "left", 0);
  Fish l5 = new Fish(Color.orange, 300, 450, 10, "left", 0);
  Fish l6 = new Fish(Color.green, 500, 550, 5, "right", 0);

  // Examples of list of fish 
  ILoFish list = new ConsLoFish(this.l1, 
      new ConsLoFish(this.l2, 
          new ConsLoFish(this.l3, this.mt)));
  ILoFish list1 = new ConsLoFish(this.l1, 
      new ConsLoFish(this.l2, 
          new ConsLoFish(this.l3,
              new ConsLoFish(this.l4, 
                  new ConsLoFish(l5, new 
                      ConsLoFish(this.l6, this.mt))))));
  ILoFish list2 = new ConsLoFish(this.l1,  
      new ConsLoFish(this.l2, 
          new ConsLoFish(this.l4,
              new ConsLoFish(this.l6, this.mt))));
  ILoFish listMoved = new ConsLoFish(this.l1.move(Utils.FISHSPEED), 
      new ConsLoFish(this.l2.move(Utils.FISHSPEED),
          new ConsLoFish(this.l3.move(Utils.FISHSPEED), this.mt)));

  // Example of FrenzyWorld
  FrenzyWorld testWorld = new FrenzyWorld(this.player, this.mt, 0);


  // tests for the bigbang world
  boolean testBigBang(Tester t) {
    FrenzyWorld world = new FrenzyWorld(this.player, this.mt, 0);
    int worldWidth = 600;
    int worldHeight = 600;
    double tickRate = .01;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }


  // seed only used for OnTickTesting
  Utils testUtils = new Utils(123); // Seed 

  // SCENARIO 1: These are for the scenario where player wins 
  ILoFish expectedFishList = new ConsLoFish(
      new Fish(Color.black, 49, 176, 4, "left", 1),
      new ConsLoFish(
          new Fish(Color.blue, 38, 185, 3, "right", 1),
          new ConsLoFish(
              new Fish(Color.black, 366, 37, 4, "right", 1),
              new MtLoFish()
              )
          )
      );
  // Define the expected player fish
  Fish expectedPlayer = new Fish(Color.blue, 250, 250, 60, "right", 0);
  // Define the expected world
  FrenzyWorld expectedWorld =
      new FrenzyWorld(expectedPlayer, expectedFishList, 1000, testUtils);  
  // Game Over - Player Wins
  Fish playerWins = new Fish(Color.blue, 250, 250, Utils.MAXSIZE, "right", 0);
  ILoFish fishWins = new MtLoFish(); // Assuming this is a list of MAXNUMFISH fishes.
  FrenzyWorld worldWins = new FrenzyWorld(playerWins, fishWins, 1000, testUtils);


  // SCENARIO 2: These are for the scenatio where game starts
  ILoFish expectedFishStartList = new ConsLoFish(
      new Fish(Color.blue, 215, 122, 2, "left", 1),
      new ConsLoFish(
          new Fish(Color.black, 52, 208, 2, "left", 1),
          new ConsLoFish(
              new Fish(Color.green, 573, 330, 3, "right", 1),
              new MtLoFish()
              )
          )
      );
  // Define the expected player fish for the WorldStart case
  Fish expectedPlayerStart = new Fish(Color.blue, 250, 250, 5, "right", 0);
  // Define the expected world for the WorldStart case
  FrenzyWorld expectedWorldStart = 
      new FrenzyWorld(expectedPlayerStart, expectedFishStartList, 0, testUtils);
  // Game Beginning
  Fish playerStart = new Fish(Color.blue, 250, 250, 5, "right", 0);
  ILoFish fishStart = new MtLoFish();
  FrenzyWorld worldStart = new FrenzyWorld(playerStart, fishStart, 0, testUtils);


  // SCENARIO 3: These are for the scenario of Adding the fish in the world
  ILoFish expectedFishAddList = new ConsLoFish(
      new Fish(Color.green, 233, 335, 1, "left", 1),
      new ConsLoFish(
          new Fish(Color.pink, 549, 14, 1, "left", 1),
          new ConsLoFish(
              new Fish(Color.orange, 491, 306, 4, "right", 1),
              new MtLoFish()
              )
          )
      );
  // Define the expected player fish for the WorldAdd case
  Fish expectedPlayerAdd = new Fish(Color.blue, 250, 250, 5, "right", 0);
  // Define the expected world for the WorldAdd case
  FrenzyWorld expectedWorldAdd = 
      new FrenzyWorld(expectedPlayerAdd, expectedFishAddList, 0, testUtils);
  // Adding Fish
  Fish playerAdd = new Fish(Color.blue, 250, 250, 5, "right", 0);
  ILoFish fishAdd = new MtLoFish(); // This should be a list with fewer fishes than MAXNUMFISH
  FrenzyWorld worldAdd = new FrenzyWorld(playerAdd, fishAdd, 0, testUtils);


  // SCENARIO 4: These are for the scenatio of worldeat (fish eating)
  ILoFish expectedFishEatList = new ConsLoFish(
      new Fish(Color.pink, 22, 172, 8, "right", 0),
      new ConsLoFish(
          new Fish(Color.red, 249, 250, 3, "left", 0),
          new MtLoFish()
          )
      );
  // Define the expected player fish for the WorldEat case
  Fish expectedPlayerEat = new Fish(Color.blue, 250, 250, 5, "right", 0);
  // Define the expected world for the WorldEat case
  FrenzyWorld expectedWorldEat = 
      new FrenzyWorld(expectedPlayerEat, expectedFishEatList, 100, testUtils);
  // Player Eats a Fish
  Fish playerEat = new Fish(Color.blue, 250, 250, 5, "right", 0);
  ILoFish fishEat = new ConsLoFish(new Fish(Color.red, 250, 250, 3, "left", 0), new MtLoFish());
  FrenzyWorld worldEat = new FrenzyWorld(playerEat, fishEat, 100, testUtils);


  // SCENARIO 5: These are for the scenario of worldlose (game over)
  ILoFish expectedFishLosesList = new ConsLoFish(
      new Fish(Color.green, 138, 243, 9, "left", 0),
      new ConsLoFish(
          new Fish(Color.red, 249, 250, 10, "left", 0),
          new MtLoFish()
          )
      );
  // Define the expected player fish for the WorldLoses case
  Fish expectedPlayerLoses = new Fish(Color.blue, 250, 250, 5, "right", 0);
  // Define the expected world for the WorldLoses case
  FrenzyWorld expectedWorldLoses = 
      new FrenzyWorld(expectedPlayerLoses, expectedFishLosesList, 100, testUtils);
  // Player is Eaten
  Fish playerLoses = new Fish(Color.blue, 250, 250, 5, "right", 0);
  ILoFish fishLoses = new ConsLoFish(new Fish(Color.red, 250, 250, 10, "left", 0), new MtLoFish());
  FrenzyWorld worldLoses = new FrenzyWorld(playerLoses, fishLoses, 100, testUtils);

  // Tests all onTickTesting scenarios in a single method
  boolean testOnTickTesting(Tester t) {
    return t.checkExpect(worldWins.onTickTesting(testUtils), expectedWorld) // Win
        && t.checkExpect(worldStart.onTickTesting(testUtils), expectedWorldStart) // Start
        && t.checkExpect(worldAdd.onTickTesting(testUtils), expectedWorldAdd) // Add
        && t.checkExpect(worldEat.onTickTesting(testUtils), expectedWorldEat) // Eat
        && t.checkExpect(worldLoses.onTickTesting(testUtils), expectedWorldLoses); // Lose
  }

  // Examples for the OnKeyEvent method
  Fish initialPlayer = new Fish(new Color(0, 0, 255), 250, 250, 5, "right", 0);
  ILoFish initialFish = new MtLoFish();
  Utils utils = new Utils();
  FrenzyWorld initialWorld = new FrenzyWorld(initialPlayer, initialFish, 0, utils);

  // Test for the onKeyEvent method:
  boolean testOnKeyEvent(Tester t) {
    return t.checkExpect(initialWorld.onKeyEvent("left"),                  // left
        new FrenzyWorld(initialPlayer.moveLeft(Utils.PLAYERSPEED),
            initialFish, 0))        
        && t.checkExpect(initialWorld.onKeyEvent("right"),                 // right
            new FrenzyWorld(initialPlayer.moveRight(Utils.PLAYERSPEED), 
                initialFish, 0))   
        && t.checkExpect(initialWorld.onKeyEvent("up"),                    // up
            new FrenzyWorld(initialPlayer.moveUp(Utils.PLAYERSPEED),
                initialFish, 0))      
        && t.checkExpect(initialWorld.onKeyEvent("down"),                  // down
            new FrenzyWorld(initialPlayer.moveDown(Utils.PLAYERSPEED),
                initialFish, 0))                    
        && t.checkExpect(initialWorld.onKeyEvent("anyOtherKey"), initialWorld);// Doesn't change
  }

  // Examples of Scenes
  WorldScene sceneWin = new WorldScene(600, 600).placeImageXY(
      new TextImage("You won", 40, Color.black), 300, 300);
  WorldScene sceneLost = new WorldScene(600, 600).placeImageXY(
      new TextImage("You lost", 40, Color.black), 300, 300);

  // Tests for lastScene method
  boolean testerLastScene(Tester t) {
    return t.checkExpect(new WorldScene(600, 600).placeImageXY(
        new TextImage("You won", 40, Color.black), 300, 300),
        this.sceneWin)
        &&  t.checkExpect(new WorldScene(600, 600).placeImageXY(
            new TextImage("You lost", 40, Color.black), 300, 300),
            this.sceneLost);
  }


  // Tests for count method
  boolean testCount(Tester t) {
    return t.checkExpect(this.list1.count(), 6) && t.checkExpect(this.mt.count(), 0);
  }

  // Tests for DrawFishLeft method
  boolean testDrawFishLeft(Tester t) {
    return t.checkExpect(this.l1.drawFishLeft(),
        new BesideAlignImage(AlignModeY.MIDDLE, new CircleImage(this.l1.radius, "solid", this.l1.c),
            new RotateImage(new EquilateralTriangleImage(this.l1.radius + 2, "solid", this.l1.c),
                270.0)))
        && t.checkExpect(this.l2.drawFishLeft(),
            new BesideAlignImage(AlignModeY.MIDDLE,
                new CircleImage(this.l2.radius, "solid", this.l2.c),
                new RotateImage(
                    new EquilateralTriangleImage(this.l2.radius + 2, "solid", this.l2.c), 270.0)))
        && t.checkExpect(player.drawFishLeft(),
            new BesideAlignImage(AlignModeY.MIDDLE,
                new CircleImage(this.player.radius, "solid", this.player.c),
                new RotateImage(
                    new EquilateralTriangleImage(this.player.radius + 2, "solid", this.player.c),
                    270.0)));
  }

  // Tests for DrawFishRight method
  boolean testDrawFishRight(Tester t) {
    return t
        .checkExpect(this.l1.drawFishRight(), new BesideAlignImage(AlignModeY.MIDDLE,
            new RotateImage(new EquilateralTriangleImage(this.l1.radius + 2, "solid", this.l1.c),
                90.0),
            new CircleImage(this.l1.radius, "solid", this.l1.c)))
        && t.checkExpect(this.l2.drawFishRight(), new BesideAlignImage(AlignModeY.MIDDLE,
            new RotateImage(new EquilateralTriangleImage(this.l2.radius + 2, "solid", this.l2.c),
                90.0),
            new CircleImage(this.l2.radius, "solid", this.l2.c)))
        && t.checkExpect(player.drawFishRight(), new BesideAlignImage(AlignModeY.MIDDLE,
            new RotateImage(
                new EquilateralTriangleImage(this.player.radius + 2, "solid", this.player.c), 90.0),
            new CircleImage(this.player.radius, "solid", this.player.c)));
  }

  // Tests for DrawFish method
  boolean testDrawFish(Tester t) {
    return t.checkExpect(this.l1.drawFish(this.l1.dir),
        new BesideAlignImage(AlignModeY.MIDDLE, new CircleImage(this.l1.radius, "solid", this.l1.c),
            new RotateImage(new EquilateralTriangleImage(this.l1.radius + 2, "solid", this.l1.c),
                270.0)))
        && t.checkExpect(this.l3.drawFish(this.l3.dir), new BesideAlignImage(AlignModeY.MIDDLE,
            new RotateImage(new EquilateralTriangleImage(this.l3.radius + 2, "solid", this.l3.c),
                90.0),
            new CircleImage(this.l3.radius, "solid", this.l3.c)))
        && t.checkExpect(player.drawFish(this.player.dir), new BesideAlignImage(AlignModeY.MIDDLE,
            new RotateImage(
                new EquilateralTriangleImage(this.player.radius + 2, "solid", this.player.c), 90.0),
            new CircleImage(this.player.radius, "solid", this.player.c)));
  }

  // Tests for placeFish method
  boolean testPlaceFish(Tester t) {
    return t.checkExpect(this.l1.placeFish(this.world1),
        new WorldScene(600, 600).placeImageXY(this.l1.drawFish(this.l1.dir), this.l1.x, this.l1.y))
        && t.checkExpect(this.l3.placeFish(new WorldScene(100, 100)),
            new WorldScene(100, 100).placeImageXY(this.l3.drawFish(this.l3.dir), this.l3.x,
                this.l3.y))
        && t.checkExpect(this.player.placeFish(this.world1), new WorldScene(600, 600)
            .placeImageXY(this.player.drawFish(this.player.dir), this.player.x, this.player.y));
  }

  // Tests for Draw method
  boolean testDrawLoFish(Tester t) {
    return t.checkExpect(this.list.drawLoFish(new WorldScene(100, 100)),
        new WorldScene(100, 100).placeImageXY(this.l1.drawFish(this.l1.dir), this.l1.x, this.l1.y)
        .placeImageXY(this.l2.drawFish(this.l2.dir), this.l2.x, this.l2.y)
        .placeImageXY(this.l3.drawFish(this.l3.dir), this.l3.x, this.l3.y))
        && t.checkExpect(this.mt.drawLoFish(new WorldScene(100, 100)), new WorldScene(100, 100));
  }

  // Tests for MoveLeft
  boolean testMoveLeft(Tester t) {
    return t.checkExpect(this.player.moveLeft(Utils.PLAYERSPEED),
        new Fish(this.player.c, this.player.x - Utils.PLAYERSPEED, this.player.y,
            this.player.radius, "left", 0))
        && t.checkExpect(this.l1.moveLeft(Utils.FISHSPEED),
            new Fish(this.l1.c, this.l1.x - Utils.FISHSPEED, this.l1.y, this.l1.radius, "left", 0))
        && t.checkExpect(this.l2.moveLeft(Utils.FISHSPEED),
            new Fish(this.l2.c, this.l2.x - Utils.FISHSPEED, this.l2.y, this.l2.radius, "left", 0))
        && t.checkExpect(new Fish(0, 0, "left").moveLeft(Utils.PLAYERSPEED),
            new Fish(600, 0, "left"));
  }

  // Tests for MoveRight
  boolean testMoveRight(Tester t) {
    return t.checkExpect(this.player.moveRight(Utils.PLAYERSPEED),
        new Fish(this.player.c, this.player.x + Utils.PLAYERSPEED, this.player.y,
            this.player.radius, "right", 0))
        && t.checkExpect(this.l1.moveRight(Utils.FISHSPEED),
            new Fish(this.l1.c, this.l1.x + Utils.FISHSPEED, this.l1.y, this.l1.radius, "right", 0))
        && t.checkExpect(this.l2.moveRight(Utils.FISHSPEED),
            new Fish(this.l2.c, this.l2.x + Utils.FISHSPEED, this.l2.y, this.l2.radius, "right", 0))
        && t.checkExpect(new Fish(600, 0, "left").moveRight(Utils.PLAYERSPEED),
            new Fish(0, 0, "right"));
  }

  // Tests for MoveUp
  boolean testMoveUp(Tester t) {
    return t.checkExpect(this.player.moveUp(Utils.PLAYERSPEED),
        new Fish(this.player.c, this.player.x, this.player.y - Utils.PLAYERSPEED,
            this.player.radius, this.player.dir, 0))
        && t.checkExpect(this.l1.moveUp(Utils.FISHSPEED),
            new Fish(this.l1.c, this.l1.x, this.l1.y - Utils.FISHSPEED, this.l1.radius, this.l1.dir,
                0))
        && t.checkExpect(this.l2.moveUp(Utils.FISHSPEED),
            new Fish(this.l2.c, this.l2.x, this.l2.y - Utils.FISHSPEED, this.l2.radius, this.l2.dir,
                0))
        && t.checkExpect(new Fish(0, 0, "left").moveUp(Utils.PLAYERSPEED),
            new Fish(0, 600, "left"));
  }

  // Tests for MoveDown
  boolean testMoveDown(Tester t) {
    return t.checkExpect(this.player.moveDown(Utils.PLAYERSPEED),
        new Fish(this.player.c, this.player.x, this.player.y + Utils.PLAYERSPEED,
            this.player.radius, this.player.dir, 0))
        && t.checkExpect(this.l1.moveDown(Utils.FISHSPEED),
            new Fish(this.l1.c, this.l1.x, this.l1.y + Utils.FISHSPEED, this.l1.radius, this.l1.dir,
                0))
        && t.checkExpect(this.l2.moveDown(Utils.FISHSPEED),
            new Fish(this.l2.c, this.l2.x, this.l2.y + Utils.FISHSPEED, this.l2.radius, this.l2.dir,
                0))
        && t.checkExpect(new Fish(0, 600, "left").moveDown(Utils.PLAYERSPEED),
            new Fish(0, 0, "left"));
  }

  // Tests for the general Move method
  boolean testMove(Tester t) {
    return t.checkExpect(this.player.move(Utils.PLAYERSPEED),
        this.player.moveRight(Utils.PLAYERSPEED))
        && t.checkExpect(this.l1.move(Utils.PLAYERSPEED), this.l1.moveLeft(Utils.PLAYERSPEED));
  }

  // Tests FishOverlap
  boolean testFishOverlap(Tester t) {
    return t.checkExpect(this.player.fishOverlap(this.eatable), true)
        && t.checkExpect(this.player.fishOverlap(this.l2), false);
  }

  // Tests if a Fish can be eaten by Player
  boolean testCanEat(Tester t) {
    return t.checkExpect(
        new Fish(this.l1.c, this.l1.x, this.l1.y, this.l1.radius - 5, 
            this.l1.dir, 0).canEat(this.l1), true)
        && t.checkExpect(this.player.canEat(this.l2), false)
        && t.checkExpect(this.l1.canEat(this.player), false);
  }

  // Tests MoveLoFish method
  boolean testMoveLoFish(Tester t) {
    return t.checkExpect(this.list.moveLoFish(), this.listMoved)
        && t.checkExpect(this.mt.moveLoFish(), new MtLoFish());
  }

  // Tests for Eat method
  boolean testEat(Tester t) {
    return t.checkExpect(this.list.eat(this.player),
        new ConsLoFish(this.l1, new ConsLoFish(this.l2, this.mt)))
        && t.checkExpect(this.mt.eat(this.player), this.mt)
        && t.checkExpect(new ConsLoFish(this.l1, new ConsLoFish(this.l2, this.mt)).eat(player),
            new ConsLoFish(this.l1, new ConsLoFish(this.l2, this.mt)));
  }

  // Tests for Overlap
  boolean testOverlap(Tester t) {
    return t.checkExpect(this.list.overlap(this.player), true)
        && t.checkExpect(this.mt.overlap(this.player), false)
        && t.checkExpect(this.list2.overlap(this.player), true);
  }

  // Tests Grow method for player Fish
  boolean testGrow(Tester t) {
    return t.checkExpect(this.player.grow(5),
        new Fish(this.player.c, this.player.x, this.player.y, this.player.radius, this.player.dir,
            5))
        && t.checkExpect(this.player.grow(6), new Fish(this.player.c, this.player.x, this.player.y,
            this.player.radius + 5, this.player.dir, 0));
  }

  // Tests FishScore method
  boolean testFishScore(Tester t) {
    return t.checkExpect(this.list.fishScore(this.player), 1)
        && t.checkExpect(this.mt.fishScore(this.player), 0) 
        && t.checkExpect(new ConsLoFish(this.l1, this.mt).fishScore(
            new Fish(this.l1.x, this.l1.y, 30)), 3);
  }

  // Tests isBiggest method
  boolean testIsBiggest(Tester t) {
    return t.checkExpect(this.list.isBiggest(this.player), false)
        && t.checkExpect(this.mt.isBiggest(this.player), true)
        && t.checkExpect(this.list2.isBiggest(new Fish(this.l1.x, this.l1.y, 70)), true)
        && t.checkExpect(this.list2.isBiggest(new Fish(this.l1.x, this.l1.y, 50)), false);
  }

  // Tests canEat method
  boolean testCanEatFish(Tester t) {
    return t.checkExpect(this.list.canEatFish(this.player), true)
        && t.checkExpect(this.mt.canEatFish(this.player), false);
  }

  //  Test OnKey method
  boolean testOnKey(Tester t) {
    return t.checkExpect(this.testWorld.onKeyEvent("left"),
        new FrenzyWorld(this.player.moveLeft(Utils.PLAYERSPEED), this.mt, 0));
  }

  // Examples of directions
  String direction1 = new Utils(10).randDir();
  String direction2 = new Utils(14).randDir();
  String direction3 = new Utils(18).randDir();

  // tests for randri (random direction of the fish in background)
  boolean testRandDir(Tester t) {
    return t.checkExpect(direction1, "right") 
        && t.checkExpect(direction2, "right")
        && t.checkExpect(direction3, "right");
  }

  // Tests for RandomColor
  boolean testRandColor(Tester t) {
    return t.checkExpect(new Utils(10).randColor(), Color.pink)
        && t.checkExpect(new Utils(11).randColor(), Color.blue)
        && t.checkExpect(new Utils(13).randColor(), Color.orange)
        && t.checkExpect(new Utils(14).randColor(), Color.magenta)
        && t.checkExpect(new Utils(18).randColor(), Color.black)
        && t.checkExpect(new Utils(36).randColor(), Color.green);
  }

  // Tests for randFish
  boolean testRandFish(Tester t) {
    return t.checkExpect(new Utils(1).randFish(20),
        new Fish(Color.pink, 388, 247, 13, "left", 0))
        && t.checkExpect(new Utils(2).randFish(20), 
            new Fish(Color.orange, 372, 440, 7, "left", 0))
        && t.checkExpect(new Utils(3).randFish(20), 
            new Fish(Color.black, 260, 210, 1, "left", 0))
        && t.checkExpect(new Utils(4).randFish(20), 
            new Fish(Color.black, 52, 303, 18, "right", 0));
  }

  // Tests for randFishMax
  boolean testRandFishMax(Tester t) {
    return t.checkExpect(new Utils(5).randFishMax(20), 
        new Fish(Color.magenta, 292, 74, 4, "left", 1))
        && t.checkExpect(new Utils(6).randFishMax(20),
            new Fish(Color.green, 276, 266, 18, "right", 1))
        && t.checkExpect(new Utils(7).randFishMax(20), 
            new Fish(Color.orange, 164, 285, 4, "left", 2))
        && t.checkExpect(new Utils(8).randFishMax(20),
            new Fish(Color.orange, 556, 130, 1, "left", 2));
  }

  Fish Fish1 = new Fish(Color.green, 196, 148, 0, "right", 1);
  Fish Fish2 = new Fish(Color.magenta, 576, 313, 3, "left", 1);
  Fish Fish3 = new Fish(Color.orange, 58, 95, 3, "left", 1);
  Fish Fish4 = new Fish(Color.pink, 180, 93, 0, "left", 1);
  Fish Fish5 = new Fish(Color.green, 88, 381, 4, "left", 1);
  Fish Fish6 = new Fish(Color.pink, 408, 495, 0, "left", 1);

  // Tests for StarterFish
  boolean testStarterFish(Tester t) {
    return t.checkExpect(new Utils(9).starterFish(), new ConsLoFish(this.Fish1, 
        new ConsLoFish(this.Fish2,
            new ConsLoFish(this.Fish3, new MtLoFish()))))
        && t.checkExpect(new Utils(10).starterFish(), new ConsLoFish(this.Fish4, 
            new ConsLoFish(this.Fish5,
                new ConsLoFish(this.Fish6, new MtLoFish()))));

  }

  // Tests for MakeScene()
  boolean testMakeScene(Tester t) {
    return t.checkExpect(this.testWorld.makeScene(), 
        this.mt.drawLoFish(this.player.placeFish(
            new WorldScene(Utils.WIDTH, Utils.HEIGHT))));
  }

}
