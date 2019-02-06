package com.serkanp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class OyunMain extends ApplicationAdapter implements InputProcessor{
	int AnlikKare=0;
	int LimitKare=5;
	int [][] saha = new int[20][20];
	int KareBirim = 21;
	Yilan yilan=null;
	ShapeRenderer shapeRenderer;
	final static int DUVAR_KARE = 1;
	final static int BOS_KARE = 0;
	final static int YEMEK_KARE = 2;
	final static int YILAN_KARE = 3;
	@Override
	public void create () {
		//InputProcessor Ayarlaniyor.
		Gdx.input.setInputProcessor(this);
		//Yilan 3,3 Noktasinda Konumlaniyor.
		yilan = new Yilan(3,3);
		shapeRenderer = new ShapeRenderer();
		// Saha Duzenlemesi
		
		for(int i = 0;i<20;i++) {
			for(int j =0;j<20;j++) {
				if(j==0||i==0||j==19||i==19) {
					saha[i][j]=DUVAR_KARE;
				}else{
					saha[i][j] = BOS_KARE;
				}
			}
		}
		
		//Sahada Ilk Basta Rastgele Yemek ve Duvar Kareler Yerlestiriliyor.
		for(int i = 0;i<5;i++) {
			saha[(int)(Math.random()*14)+2][(int)(Math.random()*14)+2]=YEMEK_KARE;
		}
		for(int i = 0;i<9;i++) {
			saha[(int)(Math.random()*14)+2][(int)(Math.random()*14)+2]=DUVAR_KARE;
		}
		yilan.KareEkle();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(AnlikKare==LimitKare) {
			yilan.Ilerle(saha);
			AnlikKare=0;
		}
		AnlikKare++;
		//Ekrana Cizilme Islemleri Baslaniyor.
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE);
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(saha[i][j]==BOS_KARE||saha[i][j]==YILAN_KARE) {
					saha[i][j]=BOS_KARE;
					shapeRenderer.setColor(Color.BROWN);
					shapeRenderer.rect(i*KareBirim,j*KareBirim, KareBirim-1, KareBirim-1);
					
				}else if(saha[i][j]==DUVAR_KARE) {
					shapeRenderer.setColor(Color.BLUE);
					shapeRenderer.rect(i*KareBirim,j*KareBirim, KareBirim-1, KareBirim-1);
				}else if (saha[i][j]==YEMEK_KARE) {
					shapeRenderer.setColor(Color.FOREST);
					shapeRenderer.rect(i*KareBirim,j*KareBirim, KareBirim-1, KareBirim-1);
				}
			}
		}
		shapeRenderer.setColor(Color.BLACK);
		OyunMantigiGuncelle();
		
		shapeRenderer.end();
	}	
	
	private void OyunMantigiGuncelle() {
		//Yilan Ekranda Ciziliyor.
		NodeYilan iter = yilan.root;
		
		while(iter!=null) {
			if(iter==yilan.root) {
				shapeRenderer.setColor(Color.RED);
			}else {
				shapeRenderer.setColor(Color.BLACK);
			}
			saha[iter.x][iter.y]=YILAN_KARE;
			shapeRenderer.rect(iter.x*KareBirim,iter.y*KareBirim, KareBirim-1, KareBirim-1);
			iter = iter.next;
		}		
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		//Ters Yone Gitmeme Kosullari Yukariysa Birden Asagiya Asagi ise Birden Yukari Gibi
		if(keycode == Input.Keys.UP) {
			if(yilan.mod!=2)
				yilan.mod=1;
			return true;
		}else if(keycode == Input.Keys.DOWN) {
			if(yilan.mod!=1)
				yilan.mod=2;
			return true;
		}else if(keycode == Input.Keys.RIGHT) {
			if(yilan.mod!=4)
				yilan.mod=3;
			return true;
		}else if(keycode == Input.Keys.LEFT) {
			if(yilan.mod!=3)
			yilan.mod=4;
			return true;
		}else if(keycode == Input.Keys.B) {
			yilan.KareEkle();
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
