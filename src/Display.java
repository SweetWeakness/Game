import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    JFrame window;
    static JButton[][] buttons=new JButton[15][10];
    static int step=0;
    //необхожимый материал
    final ImageIcon square=new ImageIcon("Square.jpg");
    final ImageIcon x1=new ImageIcon("x1.jpg");
    final ImageIcon x2=new ImageIcon("x2.jpg");
    final ImageIcon dead1=new ImageIcon("dead1.jpg");
    final ImageIcon dead2=new ImageIcon("dead2.jpg");

    public void create(int width, int height, final String title){
        //окно и его настройки
        window=new JFrame(title);
        window.setPreferredSize(new Dimension(width,height));
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        //панель и ее настройки
        JPanel GamePanel=new JPanel();
        GamePanel.setVisible(true);
        GamePanel.setBounds(0,0,900,600);
        GamePanel.setLayout(null);
        GamePanel.setBackground(Color.white);
        window.getContentPane().add(GamePanel);

        //кнопки (инициализация + действия)
        for(int i=0;i<15;i++){
            for(int j=0;j<10;j++){
                buttons[i][j]=new JButton(square);
                GamePanel.add(buttons[i][j]);
                buttons[i][j].setBounds(i*60,j*60,60,60);
                final JButton tmp=buttons[i][j];
                final int x=i;
                final int y=j;
                //на этом моменте начинается треш - начинаем добавлять действия для кнопок
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        step=step+1;
                        boolean flg=true;
                        //
                        //если ходит 1ый
                        //
                        if((step%6==1||step%6==2||step%6==3)){
                            if(ReasonsToPut(x,y)&&tmp.getIcon()!=dead1&&tmp.getIcon()!=dead2) {
                            if(tmp.getIcon()==x1) {
                                step = step - 1;
                            }else {
                                if(tmp.getIcon()==x2){
                                    tmp.setIcon(dead2);
                                }else {
                                    tmp.setIcon(x1);
                                }
                            }
                            flg=false;
                        }else{
                               if(ReasonsToPut2(x,y)){
                                   if(tmp.getIcon()==x2){
                                       tmp.setIcon(dead2);
                                   }
                               }else{
                                step = step - 1;
                            }
                        }
                        }

                        //
                        //если ходит 2ой
                        //
                            if ((step % 6 == 4 || step % 6 == 5 || step % 6 == 0)) {
                            if (ReasonsToPut(x, y) && tmp.getIcon() != dead1 && tmp.getIcon() != dead2) {
                                if (tmp.getIcon() == x2) {
                                    step = step - 1;
                                } else {
                                    if (tmp.getIcon() == x1) {
                                        tmp.setIcon(dead1);
                                    } else {
                                        tmp.setIcon(x2);
                                    }
                                }
                            } else {
                                if(ReasonsToPut2(x,y)){
                                    if(tmp.getIcon()==x1){
                                        tmp.setIcon(dead1);
                                    }
                                }else{
                                    step = step - 1;
                                }
                            }
                        }
                    }
                });
            }
        }

        buttons[0][0].setIcon(x1);
        buttons[14][9].setIcon(x2);
    }







    //
    //
    //
    //ФУНКЦИИ
    //
    //

    private boolean ReasonsToPut(int x,int y){
        boolean b=false;
        Icon d;
        if(step%6==1||step%6==2||step%6==3){
            d=x1;
        }else{
            d=x2;
        }
        if (x > 0 && y > 0 && x < 14 && y < 9) {
            b=(d==buttons[x+1][y+1].getIcon())||(d==buttons[x+1][y].getIcon())||
                    (d==buttons[x+1][y-1].getIcon())||(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon())||(d==buttons[x-1][y-1].getIcon());
        }
        if (x == 0 && y < 9 && y > 0) {
            b=(d==buttons[x+1][y+1].getIcon())||(d==buttons[x+1][y].getIcon())||
                    (d==buttons[x+1][y-1].getIcon())||(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon());
        }
        if (x==14&&y<9&&y>0){
            b=(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon())||(d==buttons[x-1][y-1].getIcon());
        }
        if (y==0&&x>0&&x<14){
            b=(d==buttons[x+1][y].getIcon())||(d==buttons[x+1][y+1].getIcon())||
                    (d==buttons[x][y+1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon());
        }
        if(y==9&&x>0&&x<14){
            b=(d==buttons[x+1][y].getIcon())||(d==buttons[x+1][y-1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y-1].getIcon())||
                    (d==buttons[x-1][y].getIcon());
        }
        if(y==0&x==0){
            b=(d==buttons[0][1].getIcon())||(d==buttons[1][1].getIcon())||
                    (d==buttons[1][0].getIcon());
        }
        if(y==9&x==0){
            b=(d==buttons[0][8].getIcon())||(d==buttons[1][8].getIcon())||
                    (d==buttons[1][9].getIcon());
        }
        if(y==0&&x==14){
            b=(d==buttons[13][1].getIcon())||(d==buttons[14][1].getIcon())||
                    (d==buttons[13][0].getIcon());
        }
        if(y==9&x==14){
            b=(d==buttons[14][8].getIcon())||(d==buttons[13][9].getIcon())||
                    (d==buttons[13][8].getIcon());
        }
        return b;
    }


    private boolean ReasonsToPut2(int x,int y){
        boolean b=false;
        Icon d;
        if(step%6==1||step%6==2||step%6==3){
            d=dead2;
        }else{
            d=dead1;
        }
        if (x > 0 && y > 0 && x < 14 && y < 9) {
            b=(d==buttons[x+1][y+1].getIcon())||(d==buttons[x+1][y].getIcon())||
                    (d==buttons[x+1][y-1].getIcon())||(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon())||(d==buttons[x-1][y-1].getIcon());
        }
        if (x == 0 && y < 9 && y > 0) {
            b=(d==buttons[x+1][y+1].getIcon())||(d==buttons[x+1][y].getIcon())||
                    (d==buttons[x+1][y-1].getIcon())||(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon());
        }
        if (x==14&&y<9&&y>0){
            b=(d==buttons[x][y+1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon())||(d==buttons[x-1][y-1].getIcon());
        }
        if (y==0&&x>0&&x<14){
            b=(d==buttons[x+1][y].getIcon())||(d==buttons[x+1][y+1].getIcon())||
                    (d==buttons[x][y+1].getIcon())||(d==buttons[x-1][y+1].getIcon())||
                    (d==buttons[x-1][y].getIcon());
        }
        if(y==9&&x>0&&x<14){
            b=(d==buttons[x+1][y].getIcon())||(d==buttons[x+1][y-1].getIcon())||
                    (d==buttons[x][y-1].getIcon())||(d==buttons[x-1][y-1].getIcon())||
                    (d==buttons[x-1][y].getIcon());
        }
        if(y==0&x==0){
            b=(d==buttons[0][1].getIcon())||(d==buttons[1][1].getIcon())||
                    (d==buttons[1][0].getIcon());
        }
        if(y==9&x==0){
            b=(d==buttons[0][8].getIcon())||(d==buttons[1][8].getIcon())||
                    (d==buttons[1][9].getIcon());
        }
        if(y==0&&x==14){
            b=(d==buttons[13][1].getIcon())||(d==buttons[14][1].getIcon())||
                    (d==buttons[13][0].getIcon());
        }
        if(y==9&x==14){
            b=(d==buttons[14][8].getIcon())||(d==buttons[13][9].getIcon())||
                    (d==buttons[13][8].getIcon());
        }
        return b;
    }
}
