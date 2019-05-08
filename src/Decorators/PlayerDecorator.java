package Decorators;

import java.util.ArrayList;

import Service.CharacterService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Orientation;

public class PlayerDecorator extends CharacterDecorator implements PlayerService {

	public PlayerDecorator(CharacterService delegate) {
		super(delegate);
		
	}
	
	public void init(int x, int y, EnvironmentService s, EngineService engine) {
		getDelegate().init(x, y, s, engine);
	}

	@Override
	public EngineService getEngine() {
		return getDelegate().getEngine();
	}

	@Override
	public void step() {
		getDelegate().step();
	}
	
	
	@Override
	public PlayerService getDelegate() {
		return (PlayerService) super.getDelegate();
	}

	@Override
	public ArrayList<TreasureService> getInventory() {
		return getDelegate().getInventory();
	}

	@Override
	public Orientation getOrientation() {
		return getDelegate().getOrientation();
	}

	@Override
	public void openDoor() {
		getDelegate().openDoor();
	}


}
