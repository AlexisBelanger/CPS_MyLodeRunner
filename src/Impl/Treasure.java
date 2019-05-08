package Impl;

import Service.EnvironmentService;
import Service.TreasureService;
import Types.Item;

public class Treasure  extends Entity implements TreasureService {
	public Item type;
	
	
	
	public Treasure() {
		// DOES NOTHING
	}
	
	
	public Item getType() {
		return type;
	}


	@Override
	public void init(int x, int y, EnvironmentService env, Item type) {
		super.init(x, y, env);
		this.type = type;
	}


	@Override
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
		
	}
	
	

	
}
