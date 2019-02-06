package com.serkanp.game;

public class NodeYilan {
	int x,y;
	NodeYilan next;
	public NodeYilan(int x,int y) {
		this.x = x;
		this.y = y;
		this.next = null;
	}
	public void Yuru(int Yenix,int Yeniy,int[][] saha) {
		
		//Metod ilk Olarak roottan Cagiriliyor fakat Linked List Ozelligi Ile Birbirini 
		//Cagirarak Yilandaki Butunluk Saglanmis Oluyor.
		
		if(next!=null) {
			next.Yuru(x, y,saha);
		}
		x = Yenix;
		y = Yeniy;
	}
}
