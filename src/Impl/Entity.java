package Impl;

import Service.EntityService;
import Service.EnvironmentService;

public class Entity implements EntityService {
	protected int posX;
	protected int posY;
	protected EnvironmentService env;
	
	
	@Override
	public EnvironmentService getEnv() {
		return env;
	}

	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public void init(int x, int y, EnvironmentService env) {
		this.posX=x;
		this.posY=y;
		this.env=env;
		
		
	}

}
