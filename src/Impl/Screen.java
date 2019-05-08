package Impl;

import Service.ScreenService;
import Types.Cell;

public class Screen implements ScreenService {
	protected int height;
	protected int width;
	protected Cell[][] screen;
	
	
	public Screen() {
		// Does nothing
	}
	

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Cell getCellNature(int n, int m) {
		return screen[n][m];
	}

	@Override
	public void init(int h, int w) {
		this.height = h;
		this.width = w;
		this.screen = new Cell[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				screen[i][j]= Cell.EMP;
			}
		}
	}

	@Override
	public void dig(int n, int m) {
			screen[n][m]= Cell.HOL;

	}

	@Override
	public void fill(int n, int m) {
		screen[n][m]= Cell.PLT;
	}

}
