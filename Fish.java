import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color;          // general colors (as triples of red,green,blue values)
// and predefined colors (Red, Green, Yellow, Blue, Black, White)

// Represents the player fish in the game
class Fish {
  Color c;
  int x;
  int y;
  int radius;
  String dir; // Direction of movement
  int score; //# of Fish eaten

  Fish(Color c, int x, int y, int radius, String dir, int score) {
    this.c = c;
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.dir = dir;
    this.score = score;
  }

  Fish(int x, int y, String dir) {
    this.c = Color.black;
    this.x = x;
    this.y = y;
    this.radius = 10;
    this.dir = dir;
    this.score = 0;
  }

  Fish(int x, int y, int radius) {
    this.c = Color.black;
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.dir = "right";
    this.score = 0;
  }

  /* TEMPLATE:
   * Fields:
   * ... this.c... -- color
   * ... this.x... -- x
   * ... this.y... -- y
   * ... this.radius... -- radius
   * ... this.dir... -- direction
   * Methods:
   * ... this.drawFish()... -- WorldImage
   * ... this.placeFish()... -- WorldImage
   * ... this.moveLeft(int)... -- Fish
   * ... this.moveRight(int)... -- Fish
   * ... this.moveUp(int)... -- Fish
   * ... this.moveDown(int)... -- Fish
   * ... this.move(int)... -- Fish
   * ... this.fishOverlap(Fish)... -- boolean
   * ... this.canEat(Fish)... -- boolean
   * ... this.grow(int)... -- Fish
   * Methods for Fields:
   * ... this.color.drawFish()... -- WorldImage
   * ... this.x.drawFish()... -- WorldImage
   * ... this.y.drawFish()... -- WorldImage
   * ... this.radius.drawFish()... -- WorldImage
   * ... this.dir.drawFish()... -- WorldImage
   * ... this.color.placeFish()... -- WorldImage
   * ... this.x.placeFish()... -- WorldImage
   * ... this.y.placeFish()... -- WorldImage
   * ... this.radius.placeFish()... -- WorldImage
   * ... this.dir.placeFish()... -- WorldImage
   * ... Rests of methods each for this.c this.x this.y this.radius and this.dir...
   */

  //Renders a Fish as a Circle moving Right
  public WorldImage drawFishRight() {
    return new BesideAlignImage(AlignModeY.MIDDLE, 
        new RotateImage(new EquilateralTriangleImage(this.radius + 2, "solid", this.c), 90.0),
        new CircleImage(this.radius, "solid", this.c));
  }

  //Renders a Fish as a Circle moving Right
  public WorldImage drawFishLeft() {
    return new BesideAlignImage(AlignModeY.MIDDLE, 
        new CircleImage(this.radius, "solid", this.c),
        new RotateImage(new EquilateralTriangleImage(this.radius + 2, "solid", this.c), 270.0));
  }

  //Renders a Fish
  public WorldImage drawFish(String dir) {
    /* TEMPLATE FOR DIR
     * Fields: none
     * Methods of acc:
     * ... dir.drawFish... --- WorldImage
     */
    if (this.dir.equals("left")) {
      return drawFishLeft();
    }
    else {
      return drawFishRight();
    }
  }

  //Places the Fish onto a given WorldScene
  public WorldScene placeFish(WorldScene acc) {
    /* TEMPLATE FOR ACC
     * Fields: none
     * Methods of acc:
     * ... acc.placeFish... --- WorldScene
     */
    return acc.placeImageXY(this.drawFish(this.dir), this.x, this.y);
  }

  //
  //All movement functions have limits on the border of the playable area
  // 

  //Create new Fish at a new location at a lower X
  public Fish moveLeft(int speed) {
    /* TEMPLATE FOR speed
     * Fields: none
     * Methods of acc:
     * ... speed.moveLeft... --- Fish
     */
    if (this.x - speed < 0) {
      return new Fish(this.c, 600, this.y, this.radius, "left", this.score);
    }
    else {
      return new Fish(this.c, this.x - speed, this.y, this.radius, "left", this.score);
    }
  }

  //Create new Fish at a new location at a higher X
  public Fish moveRight(int speed) {
    /* TEMPLATE FOR speed
     * Fields: none
     * Methods of speed:
     * ... speed.moveRight... --- Fish
     */
    if (this.x + speed > 600) {
      return new Fish(this.c, 0, this.y, this.radius, "right", this.score);
    }
    else {
      return new Fish(this.c, this.x + speed, this.y, this.radius, "right", this.score);
    }
  }

  //Create new Fish at a new location at a higher Y
  public Fish moveDown(int speed) {
    /* TEMPLATE FOR speed
     * Fields: none
     * Methods of speed:
     * ... speed.moveDown... --- Fish
     */
    if (this.y + speed > 600) {
      return new Fish(this.c, this.x, 0, this.radius, this.dir, this.score);
    }
    else {
      return new Fish(this.c, this.x, this.y + speed, this.radius, this.dir, this.score);
    }
  }

  //Create new Fish at a new location at a lower Y
  public Fish moveUp(int speed) {
    /* TEMPLATE FOR speed
     * Fields: none
     * Methods of speed:
     * ... speed.moveUp... --- Fish
     */
    if (this.y - speed < 0) {
      return new Fish(this.c, this.x, 600, this.radius, this.dir, this.score);
    }
    else {
      return new Fish(this.c, this.x, this.y - speed, this.radius, this.dir, this.score);
    }
  }

  //General Movement method for binary directions on background Fish
  public Fish move(int speed) {
    /* TEMPLATE FOR speed
     * Fields: none
     * Methods of speed:
     * ... speed.move... --- Fish
     */
    if (this.dir.equals("left")) {
      return this.moveLeft(speed);
    }
    else {
      return this.moveRight(speed);
    }
  }

  // Is the another Fish and player Fish
  public boolean fishOverlap(Fish other) {
    /* TEMPLATE FOR other
     * Fields: none
     * Methods of other:
     * ... other.fishOverlap... --- boolean
     */
    return (Math.sqrt(Math.pow(other.x - this.x, 2) 
        + Math.pow(other.y - this.y, 2)) <= this.radius);
  }

  // Is the player Fish bigger than the other Fish? i.e. can it eat
  public boolean canEat(Fish player) {
    /* TEMPLATE FOR player
     * Fields: none
     * Methods of player:
     * ... player.canEat... --- boolean
     */
    return player.radius > this.radius && player.fishOverlap(this);
  }

  public Fish grow(int other) {
    /* TEMPLATE FOR other
     * Fields: none
     * Methods of other:
     * ... other.grow... --- Fish
     */
    if (this.score + other > 5) {
      return new Fish(this.c, this.x, this.y, this.radius + 5, this.dir, 0);
    }
    else {
      return new Fish(this.c, this.x, this.y, this.radius, this.dir, other + this.score) ;
    }
  }
}

