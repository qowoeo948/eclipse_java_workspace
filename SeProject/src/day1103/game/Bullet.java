package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Bullet extends GameObject{
	GamePanel gamePanel;
	
	public Bullet(GamePanel gamePanel,Image img,int x, int y, int width, int height, int velX, int velY) {
		super(img,x, y, width, height, velX, velY);
		this.gamePanel = gamePanel;
	}

	//총알에 알맞는 물리량변화 코드 작성
	@Override
	public void tick() {
		this.x+=this.velX;
		this.y+=this.velY; //만약 총알이 Y축으로 날아갈 경우를 구현
		
		this.rect.x = x;
		this.rect.y=y;
		
		//화면밖으로 나가면, 화살을 BulletArray에서 제거해야, 그려질 대상이 되지 않음..
		//또한 BulletArray의 크기를 줄여놓아야, 추후 충돌검사시 반복문의 횟수를 줄일 수 있음..
		//this.x > 스크린의 너비 (GamePanel.WIDTH)
		if(this.x > GamePanel.WIDTH) {
			gamePanel.bulletList.remove(this); //내가 총알이니까 나를 리스트에서 지워주세요
		}
		
		//총알과 기타 오브젝트와의 충돌 검사!!
		collisionCheck();
	}
	public void collisionCheck() {
		//총알인 나와 다수의 적군과 교차여부를 판단하여, 교차했다면 나죽고, 너죽자..
		//점수도 10올리자!!
		for(int i=0;i<gamePanel.enemyList.size();i++) {
			Enemy enemy = gamePanel.enemyList.get(i);	
			
			if(this.rect.intersects(enemy.rect)) {
				//나죽고 (List에서 제거하면, 더이상 tick(), render()호출이 일어나지 않으므로 화면에서 사라짐..
				gamePanel.bulletList.remove(this);
				//너죽자
				gamePanel.enemyList.remove(enemy);
				
				//점수증가
				gamePanel.score+=10;
				
				break;
			}
		}
		
		//총알인 나와 다수의 블럭간 교차 여부
		for(int i=0;i<gamePanel.blockList.size();i++) {
			Block block = gamePanel.blockList.get(i);
			if(this.rect.intersects(block.rect)) {
			//나죽고
			gamePanel.bulletList.remove(this);
			//너죽자
			gamePanel.blockList.remove(block);
			
			break;
		 }
		}	
	}
	

	//총알에 맞는 그래픽 처리
	@Override
	public void render(Graphics2D g2) {
		
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		g2.drawImage(img, x, y, null);
		
	}
	
	
}
