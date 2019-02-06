package com.serkanp.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Yilan{
	NodeYilan root= null;
	int mod=-1;
	public Yilan(int x,int y) {
		root = new NodeYilan(x,y);
	}
	public void Ilerle(int[][] saha) {
		
		//Yilanin Hangi modda Nereye Hareket Edecegi Belirleniyor. modlar Sag sol Yukari Asagi Seklinde Sayilarla Temsil Ediliyor.
		
		switch(mod) {
			case 1:
				if(saha[root.x][root.y+1]!=OyunMain.DUVAR_KARE&&saha[root.x][root.y+1]!=OyunMain.YILAN_KARE) {
					root.Yuru(root.x, root.y+1,saha);
				}
				break;
			case 2:
				if(saha[root.x][root.y-1]!=OyunMain.DUVAR_KARE&&saha[root.x][root.y-1]!=OyunMain.YILAN_KARE) {
					root.Yuru(root.x, root.y-1,saha);
				}
				
				break;
			case 3:
				if(saha[root.x+1][root.y]!=OyunMain.DUVAR_KARE&&saha[root.x+1][root.y]!=OyunMain.YILAN_KARE) {
				root.Yuru(root.x+1, root.y,saha);
			}
				break;
			case 4:
				if(saha[root.x-1][root.y]!=OyunMain.DUVAR_KARE&&saha[root.x-1][root.y]!=OyunMain.YILAN_KARE) {
				root.Yuru(root.x-1, root.y,saha);
			}
				break;
		}
		//Ilerlediginde Yemek Karesine Denk Gelirse Yilan Buyutuluyor ve Oyuna Tekrardan Baska Konumda Bir Yemek
		//Karesi Ekleniyor.
		
		if(saha[root.x][root.y]==OyunMain.YEMEK_KARE) {
			saha[root.x][root.y]=OyunMain.BOS_KARE;
			saha[(int)(Math.random()*16)+2][(int)(Math.random()*16)+2] = OyunMain.YEMEK_KARE;
			KareEkle();
		}
	}
	public void KareEkle() {
		
		//Yilanin Tum Kutulari Bir Linked List Seklindedir.Bu Linked Liste Ekleme Yapilmaktadir.
		
		int x  = root.x;
		int y = root.y;
		
		NodeYilan eklenecekKare = new NodeYilan(root.x,root.y);
		
		root.x = x;
		root.y = y;
		eklenecekKare.next = root;
		root = eklenecekKare;
		
	}
}
