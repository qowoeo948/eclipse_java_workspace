//주인공을 정의한다
package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hero extends GameObject{

	public Hero(int x , int y, int width, int height, int velX, int velY) {
		super(x,y,width,height,velX,velY);	
	}
	
	//물리량 변화(데이터의 변화)
	public void tick() {
		this.x +=this.velX;
		this.y += this.velY;
		
	}
	
	//그래픽 처리 (화면 그려질 처리)
	//모든 게임 케릭터는 패널에 그려져야 하므로, g2를 패널의 paint()메서드의
	//지역변수를 받아오자...!!
	public void render(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(x, y, 100, 45);
	}
}
