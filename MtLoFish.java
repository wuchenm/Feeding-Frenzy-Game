import javalib.funworld.WorldScene;

// Represents the mtlist of fish
class MtLoFish implements ILoFish {
  /* TEMPLATE:
   * Fields: None
   * Methods:
   * ... this.drawLoFish((WorldScene)... -- WorldScene
   * ... this.count()... -- int
   * ... this.moveLoFish()... -- ILoFish
   * ... this.overlap(Fish)... -- boolean
   * ... this.eat(Fish)... -- ILoFish
   * ... this.fishScore(Fish)... -- int
   * ... this.isBiggest(Fish)... -- boolean
   * ... this.canEatFish(Fish)... -- boolean
   */

  //Draw this list of Fish onto a WorldScene
  public WorldScene drawLoFish(WorldScene acc) {
    return acc;
  }

  //Total amount of BackgroundFish in empty list
  public int count() {
    return 0;
  }

  //Move an empty LoFish in its direction
  public ILoFish moveLoFish() {
    return new MtLoFish();
  }

  // Eat fish
  public ILoFish eat(Fish player) {
    return new MtLoFish();
  }

  // Is player Fish bigger than empty LoFish?
  public boolean isBiggest(Fish player) {
    return true;
  }

  //Returns the eaten Fish - Will never call on MtLoFish()
  public int fishScore(Fish player) {
    return 0;
  }

  // Are any Fish overlapping w Player Fish
  public boolean overlap(Fish player) {
    return false;
  }

  // Can Player Fish eat MtLoFish?
  public boolean canEatFish(Fish player) {
    return false;
  }

}
