import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
public class SnakeFrame{
	int width, height;
	Frame frame;
	public BufferedImage buffer;
	public Graphics bufferG;
	public SnakeFrame(int width, int height){
		this.width = width;
		this.height = height;
		frame = new Frame();
		frame.setSize(width + 10, height + 20);
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// bufferG = buffer.getGraphics();
	// }
	// public void init(){
		bufferG = buffer.getGraphics();
		frame.setVisible(true);
 	}
	public void paint(){
		// bufferG.drawImage(buffer, 5, 20, null);
		frame.getGraphics().drawImage(buffer, 5, 20, null);
		// frame.repaint();
	}
	public void fill(int r, int g, int b){
		bufferG.setColor(new Color(r, g, b));
	}
	public void ellipse(int x, int y, int width, int height){
		bufferG.fillOval(x, y, width, height);
	}
	public void rect(int x, int y, int width, int height){
	}
	Color bg = Color.BLACK;
	public void clear(){
		bufferG.setColor(bg);
		bufferG.fillRect(0, 0, width, height);
	}
}
