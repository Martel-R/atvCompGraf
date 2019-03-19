import java.awt.*;

public class Objeto {
    public int x;
    public int y;
    public int largura;
    public int altura;
    public int velocidade;
    public Objeto() {
    }
    public Objeto(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }
    public void drawObjeto(Graphics g, Color cor) {
        g.setColor(cor);
        g.fillRect(x, y, largura, altura);
    }

    public void drawBomba(Graphics g, Color cor) {
        g.setColor(cor);
        g.fillOval(x,y,largura,altura);
        g.fillRect(x+20,y-10,10,15);
        g.fillRect(x+15,y,20,3);
        g.setColor(Color.BLACK);
        g.fillRect(x+10,y+10, 10,3);
        g.fillRect(x+30,y+10, 10,3);
        g.fillOval(x+10,y+15,10,10);
        g.fillOval(x+30,y+15,10,10);
        g.drawArc(x+5,y+10,40,35,180,180);
    }
}
