package Impl;

import java.util.ArrayList;

import Errors.PostconditionError;
import Service.EditableScreenService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.GuardService;
import Types.Cell;

public class Environment extends Screen implements EnvironmentService {
	protected ArrayList<EntityService>[][] entities;
	
	
	
	public Environment() {
		//Does nothing
	}
	
	
	public void init(EditableScreenService es) {
		super.init(es.getHeight(), es.getWidth());
		entities = (ArrayList<EntityService>[][]) new ArrayList[es.getHeight()][es.getWidth()];
		for(int i=0; i<es.getHeight(); i++) {
			for(int j=0; j<es.getWidth();j++) {
				entities[i][j]= new ArrayList<EntityService>();
			}
		}
		for(int i=0; i<es.getHeight(); i++) {
			for(int j=0; j<es.getWidth();j++) {
				screen[i][j]=es.getCellNature(i, j);
			}
		}
	}

	@Override
	public ArrayList<EntityService> getCellContent(int n, int m) {
		return entities[n][m];
	}


	@Override
	public void moveCellContent(int x, int y, EntityService entity) {
		entities[entity.getPosX()][entity.getPosY()].remove(entity);
		entities[x][y].add(entity);
	}
	
	
	public void dig(int n, int m) {
		if(entities[n+1][m].size()==0) {
			super.dig(n, m);
		}
	}
	
	public void fill(int n, int m) {
		for(EntityService e : getCellContent(n, m)) {
			if(e instanceof GuardService) {
				((GuardService) e).die();
			}
		}
		getCellContent(n, m).clear();
		super.fill(n, m);
	}
	
	public void openDoor(int n, int m) {
		screen[n][m]= Cell.EMP;
	}
	
	

}
