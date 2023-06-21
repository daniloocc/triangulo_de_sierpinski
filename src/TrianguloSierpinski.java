import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class TrianguloSierpinski {

	private Dimension size;
	private int N_VERTICES = 3;
	private int POINT_WIDTH = 2;
	private Point[] vertices = new Point[N_VERTICES];
	
	private Point pontoAtual;
	
	public TrianguloSierpinski(Dimension size) {
		if(size.getWidth() != size.getHeight())
			throw new IllegalArgumentException("A largura deve ser igual a altura");
		this.size = size;
		
		// triangulo equilátero tem lado igual
		// a 90% do tamanho da tela
		
		int base = (int) (size.getWidth() * .9);
		int sqrSize = (int) size.getWidth();
		
		vertices[0] = new Point((int)(sqrSize * 0.05), (int)(sqrSize * .95 ));
		vertices[1] = new Point(vertices[0].x + base, vertices[0].y);
		vertices[2] = new Point((vertices[0].x + (int)(Math.cos(45)*base)), (vertices[0].y - (int)(Math.sin(45)*base)));
		
		pontoAtual = calculaPontoAleatorio();
		
	}

	private Point calculaPontoAleatorio() {
		
		double r1 = Math.random();
		double r2 = Math.random();
		
		int x = (int)((1 - Math.sqrt(r1)) * vertices[0].x + (Math.sqrt(r1) * (1 - r2)) * vertices[1].x + (Math.sqrt(r1) * r2) * vertices[2].x);
		int y = (int)((1 - Math.sqrt(r1)) * vertices[0].y + (Math.sqrt(r1) * (1 - r2)) * vertices[1].y + (Math.sqrt(r1) * r2) * vertices[2].y);
		
		return new Point(x, y);
	}

	public void draw(Graphics2D g2d) {
		//* desenha o triangulo container
		var v = vertices;
		g2d.drawLine(v[0].x, v[0].y, v[1].x, v[1].y);
		g2d.drawLine(v[1].x, v[1].y, v[2].x, v[2].y);
		g2d.drawLine(v[2].x, v[2].y, v[0].x, v[0].y);
		//*/
		
		// desenha o ponto atual
		g2d.fillOval(pontoAtual.x, pontoAtual.y, POINT_WIDTH, POINT_WIDTH);		
		
		//System.out.println(String.format("Desenhou em [[%d] [%d]]", pontoAtual.x, pontoAtual.y));
		
	}

	public void calcNextDot() {
		// escolhe uma das vertices
		int v = (int)(Math.random()*N_VERTICES);
		
		// calcula o ponto médio entre a vertice o ponto atual
		int x = (pontoAtual.x + vertices[v].x) / 2;
		int y = (pontoAtual.y + vertices[v].y) / 2;
		
		pontoAtual = new Point(x,y);
		//System.out.println(String.format("A vertice escolhida foi [[%d] [%d]]", vertices[v].x, vertices[v].y));
		
	}

}
