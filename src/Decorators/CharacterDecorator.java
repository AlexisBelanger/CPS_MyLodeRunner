package Decorators;

import Service.CharacterService;
import Service.EnvironmentService;

public class CharacterDecorator extends EntityDecorator implements CharacterService{

	public CharacterDecorator(CharacterService delegate) {
		super(delegate);
	}
	
	public CharacterService getDelegate() {
		return (CharacterService)getDelegate();
	}

	@Override
	public int getPosX() {
		return getDelegate().getPosX();
	}

	@Override
	public int getPosY() {
		return getDelegate().getPosY();
	}

	@Override
	public void init(int x, int y, EnvironmentService s) {
		getDelegate().init(x, y, s);
	}

	@Override
	public void goLeft() {
		getDelegate().goLeft();
	}

	@Override
	public void goRight() {
		getDelegate().goRight();
	}

	@Override
	public void goUp() {
		getDelegate().goUp();
	}

	@Override
	public void goDown() {
		getDelegate().goDown();
	}
	

	
}
