package graphix.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

class ImageDrawTest extends JComponent {

//	class ImageComponent  {
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

//		Image img = Toolkit.getDefaultToolkit().getImage("random.png");
//		g2.drawImage(img, 0, 0, this);
//		g2.finalize();
		
		g2.setColor(Color.BLACK);
		g2.drawLine(10, 10, 50, 50);
		
//		g2.image
		
	}
//	}

	public static void main(String[] args) {

//	}
//	@Test
//	void test() {
		var window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(10, 10, 100, 100);
		window.getContentPane().add(new ImageDrawTest());
		window.setVisible(true);

	}

}
