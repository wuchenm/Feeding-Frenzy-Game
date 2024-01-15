import javalib.funworld.WorldScene;

// Represents the list of fish in the game (background fish)
class ConsLoFish implements ILoFish {
  Fish first;
  ILoFish rest;

  ConsLoFish(Fish first, ILoFish rest) {
    this.first = first;
    this.rest = rest;
  }

  /* TEMPLATE:
   * Fields:
   * ... this.first ... -- Fish
   * ... this.rest ... -- ILoFish
   * Methods:
   * ... this.drawLoFish((WorldScene)... -- WorldScene
   * ... this.count()... -- int
   * ... this.moveLoFish()... -- ILoFish
   * ... this.overlap(Fish)... -- boolean
   * ... this.eat(Fish)... -- ILoFish
   * ... this.fishScore(Fish)... -- int
   * ... this.isBiggest(Fish)... -- boolean
   * ... this.canEatFish(Fish)... -- boolean
   * Methods for Fields:
   * ... this.first.draw ... -- WorldScene
   * ... this.rest.draw ... -- WorldScene
   * ... this.first.count... -- int
   * ... this.rest.count... -- int
   * ... rests of methods for this.first and this.rest
   */

  // Draw this list of Fish onto a WorldScene
  public WorldScene drawLoFish(WorldScene acc) {
    /* TEMPLATE FOR ACC
     * Fields: none
     * Methods of acc:
     * ... acc.drawLoFish... --- WorldScene
     */
    return this.rest.drawLoFish(
        acc.placeImageXY(this.first.drawFish(this.first.dir), 
            this.first.x, this.first.y));
  }

  //Total number of Fish in non-empty ILoFish
  public int count() {
    return 1 + this.rest.count();
  }

  //Move a ConsLoFish in its direction
  public ILoFish moveLoFish() {
    // TODO Auto-generated method stub
    return new ConsLoFish(this.first.move(Utils.FISHSPEED),
        this.rest.moveLoFish());
  }

  // Are any Fish overlapping w Player Fish
  public boolean overlap(Fish player) {
    /* TEMPLATE FOR PLAYER
     * Fields: none
     * Methods of acc:
     * ... player.overlap... --- boolean
     */
    return player.fishOverlap(this.first) || this.rest.overlap(player);
  }

  //New ILoFish with eaten Fish removed
  public ILoFish eat(Fish player) {
    /* TEMPLATE FOR PLAYER
     * Fields: none
     * Methods of acc:
     * ... player.eat... --- ILoFish
     */
    if (this.first.canEat(player)) {
      return this.rest.eat(player);
    }
    else {
      return new ConsLoFish(this.first, this.rest.eat(player));
    }
  }

  //Returns the eaten Fish
  public int fishScore(Fish player) {
    /* TEMPLATE FOR PLAYER
     * Fields: none
     * Methods of acc:
     * ... player.fishScore... --- int
     */
    if (this.first.canEat(player)) {
      return ((this.first.radius / 10) % 10) + 1;
    }
    else {
      return this.rest.fishScore(player);
    }
  }

  // Is the player Fish bigger than all Fish in ConsLoFish?
  public boolean isBiggest(Fish player) {
    /* TEMPLATE FOR PLAYER
     * Fields: none
     * Methods of acc:
     * ... player.isBiggest... --- boolean
     */
    return player.radius 
        > this.first.radius && this.rest.isBiggest(player) && player.radius > Utils.MAXSIZE;
  }

  // Can eat fish? method
  public boolean canEatFish(Fish player) {
    /* TEMPLATE FOR PLAYER
     * Fields: none
     * Methods of acc:
     * ... player.canEatFish... --- boolean
     */
    return this.first.canEat(player) || this.rest.canEatFish(player);
  }





}
