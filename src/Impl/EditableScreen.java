package Impl;

import Service.EditableScreenService;
import Types.Cell;

public class EditableScreen extends Screen implements EditableScreenService{

	@Override
	public boolean playable() {
		for(int i=0; i< getWidth(); i++) {
			if(getCellNature(0, i)!= Cell.MTL) return false;
		}
		
		for(int i=0; i< getHeight(); i++) {
			for(int j=0; j< getWidth(); j++) {
				if(getCellNature(i, j)== Cell.HOL) return false;
			}
		}
		return true;
	}
	
	
	
	public void init(int h, int w) {
		super.init(h, w);
		for(int i=0; i< getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				if(i==0) {
					setNature(i, j, Cell.MTL);
				}else {
					setNature(i, j, Cell.EMP);
				}
			}
		}
	}

	@Override
	public void setNature(int n, int m, Cell c) {
		screen[n][m]=c;
	}

	

}
