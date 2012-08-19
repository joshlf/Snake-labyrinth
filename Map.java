import java.awt.Color;
import java.awt.Graphics;
public class Map{
	public static final byte BLANK = 0, WALL = 1;
	byte[][] tiles;
	int screenWidth;
	int screenHeight;
	int tileSize;
	int width;
	int height;
	public Map(int screenWidth, int screenHeight, int tileSize, int width, int height){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.tileSize = tileSize;
		this.width = width;
		this.height = height;
		tiles = new byte[width][height];
		initTiles();
	}
	//Expects x and y in terms of tiles.
	public void renderMap(Graphics g, int camX, int camY, int frame){
		camX -= width; //so that - values will % around.
		camY -= height;
		for(int a = 0; a < screenWidth; a++){
			for(int b = 0; b < screenHeight; b++){
				int x = (a + camX) % width;
				int y = (b + camY) % height;
				if(tiles[x][y] == WALL){
					g.setColor(colorGet(x, y, frame));
					g.fillRect((a - camX) % width, (b - camY) % height, tileSize, tileSize);
				}
			}
		}
	}
	public Color colorGet(int x, int y, int frame){
		return new Color((x + frame) % 255, (y - frame) % 255, (int)(128 * Math.sin((x + y + frame) * .1f)));
	}

void initTiles() {
 byte[][] tiles = this.tiles;   
 for(int i = 0; i < width; i++) {
  for(int j = 0; j < height; j++) {
    tiles[i][j] = BLANK;
    if(((i & 1) != 0 || (j & 1) != 0)) {
      tiles[i][j] = WALL;
    }
  } 
 }
 randPath(0, 0, 100);
}

void randPath(int i, int j, int depth) {
  if (depth <= 0)
    return;
  tiles[i][j] = BLANK;
  if (rand(0.5f)) {
    randPath((i + 1) % width, j, depth - 1);
  }
  if (rand(0.5f)) {
    randPath(i, (j + 1) % height, depth - 1);
  }
}

boolean rand(float chance) {
  return (Math.random() < chance);
}
}
