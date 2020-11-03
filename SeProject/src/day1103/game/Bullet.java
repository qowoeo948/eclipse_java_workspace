package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject{

	public Bullet(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height, velX, velY);
	}

	//총알에 알맞는 물리량변화 코드 작성
	@Override
	public void tick() {
		this.x+=this.velX;
	}

	//총알에 맞는 그래픽 처리
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillOval(x, y, width, height);
	}
	
	
}
