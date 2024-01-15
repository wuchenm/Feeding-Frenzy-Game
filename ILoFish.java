import javalib.funworld.WorldScene;

// Represents the list of fish for the game
interface ILoFish {

  // Draw this list of Fish onto a WorldScene
  public WorldScene drawLoFish(WorldScene acc);

  // Count how many Fish are in the list
  public int count();

  // Move an ILoFish in its direction
  public ILoFish moveLoFish();

  // Are any Fish overlapping w Player Fish
  public boolean overlap(Fish player);

  // Remove the eaten Fish from ILoFish
  public ILoFish eat(Fish player);

  // Is the player Fish the biggest Fish out of ILoFish
  public boolean isBiggest(Fish player);

  //Returns the eaten Fish
  public int fishScore(Fish player);

  //
  public boolean canEatFish(Fish player);
}
