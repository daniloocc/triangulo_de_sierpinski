import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame implements Runnable {
	
	private Dimension SIZE = new Dimension(700, 700);
	private final int INTERVALO = 0;
	private TrianguloSierpinski triangulo;
	
	public Main() {		

		setSize(SIZE);
		
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		triangulo = new TrianguloSierpinski(SIZE);		
		
		Thread t1 = new Thread(this);
		t1.start();
		
	}
	
	private void drawTriangle(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		triangulo.draw(g2d);
		
	}
	
	private void calculaProxPonto() {
		triangulo.calcNextDot();
	}
	
	public void paint(Graphics g) {
		//super.paint(g);
		drawTriangle(g);
	}
	

	@Override
	public void run() {
		
		while(true) {			
			calculaProxPonto();
			
			//*
			try {
				Thread.sleep(INTERVALO);;
			}catch(InterruptedException e) { }
			//*/
			repaint();			
		}		
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}
}