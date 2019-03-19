import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Interface extends JFrame{
    JPanel screen;
    boolean[] isControler = new boolean[5];
    boolean isStarting = true;
    int FPS = 1000 / 50;
    Objeto player;
    Color corPlayer = new Color(210, 140, 20);
    ArrayList<Objeto> grupo_shot;
    public Interface() {
        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                setKey(e.getKeyCode(), true);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                setKey(e.getKeyCode(), false);
            }
        });
        player = new Objeto(0, 0, 50, 50);
        player.velocidade = 10;
        grupo_shot = new ArrayList<>();
        screen = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                player.drawBomba(g, Color.YELLOW);
                g.drawString("COOR: " + player.x + " x " +
                        player.y, 10, 100);
                for (Objeto s : grupo_shot) {
                    s.velocidade = 5;
                    s.drawObjeto(g, corPlayer);
                }
                g.setColor(new Color(140,120,83));
                g.fillRect(0,screen.getHeight()/2,30,screen.getHeight()/2);
                g.fillRect(screen.getWidth()-30,screen.getHeight()/2,30,screen.getHeight()/2);
                g.setColor(Color.GREEN);
                g.fillRect(0,screen.getHeight()-30,screen.getWidth(),30);
            }
        };
        super.getContentPane().add(screen);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(680, 680);
        player.y = 440;
        player.x = screen.getWidth() / 2 + player.largura / 2;
    }
    public void controler() {
        if (isControler[0]) { //esquerda
            if (player.y>screen.getHeight()/2) {
                if (player.x >30) {
                    player.x -= player.velocidade;
                }
            }
            else {
                if (player.x>0) {
                    player.x -= player.velocidade;
                }
            }
        }
        if (isControler[1]) { //direita
            if (player.y>screen.getHeight()/2) {
                if (player.x <= screen.getWidth() - 80) {
                    player.x += player.velocidade;
                }
            }
            else {
                if (player.x <= screen.getWidth() - 50) {
                    player.x += player.velocidade;
                }
            }
        }
        if (isControler[2]) {
            Objeto new_shot = new Objeto(player.x +
                    player.largura / 2, player.y, 5, 5);
            grupo_shot.add(new_shot);
        }
        if (isControler[3]) {
            if (player.y>10){
                player.y -= player.velocidade;
            }
        }
        if (isControler[4]) {
            if (player.y<=screen.getHeight()-90){
                player.y += player.velocidade;
            }
        }
    }
    public void setKey(int key, boolean isPress) {
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_LEFT:
                isControler[0] = isPress;
                break;
            case KeyEvent.VK_RIGHT:
                isControler[1] = isPress;
                break;
            case KeyEvent.VK_SPACE:
                isControler[2] = isPress;
                break;
            case KeyEvent.VK_UP:
                isControler[3] = isPress;
                break;
            case KeyEvent.VK_DOWN:
                isControler[4] = isPress;
                break;
        }
    }
    public void start() {
        long prx = 0;
        while (isStarting) {
            if (System.currentTimeMillis() >= prx) {
                screen.repaint();
                update();
                prx = System.currentTimeMillis() + FPS;
            }
        }
    }
    public void update() {
        controler();
        System.out.println(grupo_shot.size());
        for (int i = 0; i<grupo_shot.size();i++){
            Objeto o = grupo_shot.get(i);
            o.y-= o.velocidade;
            if (player.y-o.y >=400){
                grupo_shot.remove(o);
                i-=1;
            }
        }
        //for (Objeto s : grupo_shot) {
        //    s.y -= s.velocidade;
        //}
    }
}