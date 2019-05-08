package Decorators;

import Service.CharacterService;
import Service.EngineService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.TreasureService;
import Types.Move;

public class GuardDecorator extends CharacterDecorator implements GuardService{

	public GuardDecorator(CharacterService delegate) {
		super(delegate);
	}
	
	
	public GuardService getDelegate() {
		return (GuardService)super.getDelegate();
	}

	
	@Override
	public int getId() {
		return getDelegate().getId();
	}

	@Override
	public Move getBehaviour() {
		return getDelegate().getBehaviour();
	}

	@Override
	public CharacterService getTarget() {
		return getDelegate().getTarget();
	}

	@Override
	public int getTimeInHole() {
		return getDelegate().getTimeInHole();
	}

	@Override
	public void climbLeft() {
		getDelegate().climbLeft();
	}

	@Override
	public void climbRight() {
		getDelegate().climbRight();
	}

	@Override
	public void step() {
		getDelegate().step();
		
	}

	@Override
	public void init(int x, int y, EnvironmentService s, EngineService eng) {
		getDelegate().init(x, y, s, eng);
		
	}


	@Override
	public void die() {
		getDelegate().die();
	}


	@Override
	public boolean isDead() {
		return getDelegate().isDead();
	}


	@Override
	public TreasureService hasTreasure() {
		return getDelegate().hasTreasure();
	}


	@Override
	public void takeTreasure(int x, int y) {
		getDelegate().takeTreasure(x,y);
	}


	@Override
	public void releaseTreasure() {
		getDelegate().releaseTreasure();
	}
	
	




}
