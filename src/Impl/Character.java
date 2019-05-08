package Impl;

import java.util.ArrayList;
import java.util.Arrays;

import Service.CharacterService;
import Service.EntityService;
import Types.Cell;

public class Character extends Entity implements CharacterService{

	public Character() {
		//DOES NOTHING
	}
	
	
	@Override
	public void goLeft() {
		
		Cell[] array = {Cell.EMP, Cell.HDR, Cell.HOL, Cell.LAD};
		ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(array));
		if(posY > 0) {
			
			boolean charDown = false;
			for(EntityService e : getEnv().getCellContent(posX-1, posY)) {
				if(e instanceof CharacterService) {
					charDown = true;
					break;
				}
			}
			if(libre.contains(getEnv().getCellNature(posX, posY-1))) {
				if((charDown) || getEnv().getCellNature(posX, posY)== Cell.HDR ||
						(getEnv().getCellNature(posX-1, posY) == Cell.PLT || getEnv().getCellNature(posX-1, posY) == Cell.MTL || getEnv().getCellNature(posX-1, posY) == Cell.LAD || getEnv().getCellNature(posX-1, posY) == Cell.DOR) ||
						(getEnv().getCellNature(posX, posY)==Cell.LAD)) {
					env.moveCellContent(posX, posY-1, this);
					posY--;
				}
			}
		}
	}

	@Override
	public void goRight() {
		Cell[] array = {Cell.EMP, Cell.HDR, Cell.HOL, Cell.LAD};
		ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(array));
		if(posY < getEnv().getWidth()-1) {
			
			boolean charDown = false;
			for(EntityService e : getEnv().getCellContent(posX-1, posY)) {
				if(e instanceof CharacterService) {
					charDown = true;
					break;
				}
			}
			
			if(libre.contains(getEnv().getCellNature(posX, posY+1))) {
				if((charDown) || getEnv().getCellNature(posX, posY)== Cell.HDR ||
						(getEnv().getCellNature(posX-1, posY) == Cell.PLT || getEnv().getCellNature(posX-1, posY) == Cell.MTL || getEnv().getCellNature(posX-1, posY) == Cell.LAD || getEnv().getCellNature(posX-1, posY) == Cell.DOR) ||
						/*CORRECTION */(getEnv().getCellNature(posX, posY)==Cell.LAD) /*FIN CORRECTION*/) {
					env.moveCellContent(posX, posY+1, this);
					posY++;
				}
			}
		}	
	}

	@Override
	public void goUp() {
		Cell[] array = {Cell.EMP, Cell.HDR, Cell.HOL, Cell.LAD};
		ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(array));
		if(posX < getEnv().getHeight()-1) {
			
			if(getEnv().getCellNature(posX, posY)== Cell.LAD) {
				if(libre.contains(getEnv().getCellNature(posX+1, posY))) {
					if(/*!charUp*/true) {
						env.moveCellContent(posX+1, posY, this);
						posX++;
					}
				}
			}
		}
	}

	@Override
	public void goDown() {
		Cell[] array = {Cell.EMP, Cell.HDR, Cell.HOL, Cell.LAD};
		ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(array));
		
		if(posX > 0 ) {
			if(libre.contains(getEnv().getCellNature(posX-1, posY)) /*&& !charDown*/) {
				env.moveCellContent(posX-1, posY, this);
				posX--;
			}
		}
	}
}
