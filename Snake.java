// SnakeLabyrinth: A Joshua Lebow Feeser and Cyrus Cousins production.

public class Snake{
// The frame manages all graphical things.
SnakeFrame frame;

//Keep track of & render the labyrinth.
Map map;

//Do timing
int FPS, TPF; //Frames per second and millis per frame.

public static final int WIDTH = 400;
public static final int HEIGHT = 400;

//snake stuff
int[] x = new int[256];
int[] y = new int[256];

int foodX, foodY;
int snakelength;
boolean kwp = false;
int xChange, yChange;
int addFoodLength;
//boolean wasReset = false;
boolean go = true;
int xblocks = ((int)(WIDTH*0.9/5));
int yblocks = ((int)(HEIGHT*0.9/5));
boolean dead;

//1 = up; 2 = right; 3 = down; 4 = left;
int direction;
int score = 1;

//implement processing functionality
public void fill(int i){
	frame.fill(i, i, i);
}
public void fill(int r, int g, int b){
	frame.fill(r, g, b);
}
public void ellipse(int x, int y, int w, int h){
	frame.ellipse(x, y, w, h);
}
public void rect(int x, int y, int w, int h){
	frame.rect(x, y, w, h);
}
public int random(int a){
	return (int) (Math.random() * a);
}

public void textSize(int s){
	//do this later.
}
public void text(String s, int x, int y){
	frame.bufferG.drawString(s, x, y);
}
//The main method

public static void main(String[] args){
	int mapWidth = 200;
	int mapHeight = 200;
	for(int i = 0; i < args.length; i++){
		if(args[i].equals("-w")){
			mapWidth = Integer.parseInt(args[++i]);
		}
		else if(args[i].equals("-h")){
			mapHeight = Integer.parseInt(args[++i]);
		}
	}
	Snake snake = new Snake(WIDTH, HEIGHT);
	// Snake snake = new Snake(mapWidth, mapHeight);
	snake.run();
}

//The constructor
public Snake(int width, int height){
	frame = new SnakeFrame(width, height);
	map = new Map(width, height, 8, width, height);


  //initTiles();
  
  FPS = 30;
  TPF = 1000 / FPS;
}

//Main game loop
public void run(){
	long t0 = System.currentTimeMillis();
	long t1;	
	while(!dead){
		t1 = System.currentTimeMillis();
		long timePassed = t1 - t0;
		if(timePassed < TPF){
			try{
				Thread.sleep(TPF - timePassed);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

frame.clear();
				//Check to see if ate food
if (direction == 1) {
			xChange = 0;

    yChange = -5;
    if (Math.abs(y[0] - 5 - foodY) < 5 && Math.abs(x[0] - foodX) < 5) {
      setFood();
      score++;
      for (int j = 0; j < addFoodLength; j++) {
        snakelength++;
        x[snakelength] += x[snakelength - 2] - x[snakelength - 1];
        y[snakelength] += y[snakelength - 2] - y[snakelength - 1];
      }
    }
  }
  else if (direction == 2) {
    xChange = 5;
    yChange = 0;
    if (Math.abs(y[0] - foodY) < 5 && Math.abs(x[0] + 5 - foodX) < 5) {
      setFood();
      score++;
      for (int j = 0; j < addFoodLength; j++) {
        snakelength++;
        x[snakelength] += x[snakelength - 2] - x[snakelength - 1];
        y[snakelength] += y[snakelength - 2] - y[snakelength - 1];
      }
    }
  } 
  else if (direction == 3) {
    xChange = 0;
    yChange = 5;
    if (Math.abs(y[0] + 5 - foodY) < 5 && Math.abs(x[0] - foodX) < 5) {
      setFood();
      score++;
      for (int j = 0; j < addFoodLength; j++) {
        snakelength++;
        x[snakelength] += x[snakelength - 2] - x[snakelength - 1];
        y[snakelength] += y[snakelength - 2] - y[snakelength - 1];
      }
    }
  } 
  else if (direction == 4) {
    xChange = -5;
    yChange = 0;
    if (Math.abs(y[0] - foodY) < 5 && Math.abs(x[0] - 5 - foodX) < 5) {
      setFood();
      score++;
      for (int j = 0; j < addFoodLength; j++) {
        snakelength++;
        x[snakelength] += x[snakelength - 2] - x[snakelength - 1];
        y[snakelength] += y[snakelength - 2] - y[snakelength - 1];
      }
    }
  }

  //move each joint in snake to wx[ihere the previous one is
  fill(255);
  if (snakelength != 1) {
    for(int i = snakelength - 1; i > 0; i-- ) {
      x[i] = x[i-1];
      y[i] = y[i-1];
      ellipse(x[i],y[i],5,5);
      if (x[i] == x[0] && y[i] == y[0] && i > 2)
        crash();
    }
  }
  fill(255);
  for(int i = 0; i < xblocks; i++) {
    for(int j = 0; j < yblocks; j++) {
      if(map.tiles[i][j] == Map.WALL) {
        rect(i * 5, j * 5, 5, 5);
      }
    }
  }
  x[0] += xChange;
  y[0] += yChange;

  //Crash into walls
  if (x[0] == 0 || x[0] == xblocks*5 || y[0] == 0 || y[0] == yblocks*5 || map.tiles[x[0]/5][y[0]/5] == Map.WALL)
    crash();

  fill(255,0,0);
  ellipse(x[0],y[0],5,5);
  fill(0,255,0);
  ellipse(foodX,foodY,5,5);
  textSize(20);
  fill(0,255,0);
  text("" + score,10,30);
  frame.paint();
	}

}


void keyPressed() {
  int key = 0;
  char w = 'w', d = 'd', s = 's', a = 'a';
  if (key == 32) {
    reset();
    //    if (wasReset)
    go = true;
    //loop();
  }
  if (!kwp) {
    if (key == w && direction != 3)
      direction = 1;
    else if (key == d && direction != 4)
      direction = 2;
    else if (key == s && direction != 1)
      direction = 3;
    else if (key == a && direction != 2)
      direction = 4;
  }
}

void crash() {
  go = false;
  // dead = true;
  //noLoop();
  //  wasReset = false;
}

void reset() {
  //  if (!wasReset) {
  score = 1;
  snakelength = 3;
  xChange = 0;
  yChange = 0;
  addFoodLength = (int)(4);
  direction = (int)(random(4));
  x[0] = (int)(random(80))*5;
  y[0] = (int)(random(80))*5;
  x[1] = x[0] - 5;
  y[1] = y[0];
  x[2] = x[1] - 5;
  y[2] = y[1];
  setFood();
  //  }
  //  wasReset = !wasReset;
}

void setFood() {
  foodX = (int)(random(xblocks))*5;
  foodY = (int)(random(yblocks))*5;
  if (foodX == 0 || foodX == xblocks*5 || foodY == 0 || foodY == yblocks*5)
    setFood();
}
}
