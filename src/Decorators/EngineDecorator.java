package Decorators;

import java.util.ArrayList;

import Service.EditableScreenService;
import Service.EngineService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.PlayerService;
import Service.ScreenService;
import Service.TreasureService;
import Types.Command;
import Types.State;

public class EngineDecorator implements EngineService{
	private EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate=delegate;
	}

	@Override
	public EnvironmentService getEnv() {
		return delegate.getEnv();
	}

	@Override
	public PlayerService getPlayer() {
		return delegate.getPlayer();
	}

	@Override
	public State computeStatus() {
		return delegate.computeStatus();
	}

	@Override
	public Command getNextCommand() {
		return delegate.getNextCommand();
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public void init(EditableScreenService es, int x, int y, ArrayList<int[]> gardes, ArrayList<int[]> tresors, String[] types, int[] spawn, PlayerService p, int nbLevel) {
		delegate.init(es, x, y, gardes, tresors, types, spawn, p, nbLevel);
		
	}
	
	
	public EngineService getDelegate() {
		return delegate;
	}

	@Override
	public ArrayList<GuardService> getGuards() {
		return delegate.getGuards();
	}

	@Override
	public ArrayList<TreasureService> getTreasures() {
		return delegate.getTreasures();
	}

	@Override
	public ArrayList<int[]> getHoles() {
		return delegate.getHoles();
	}

	@Override
	public int getNbGuards() {
		return delegate.getNbGuards();
	}

	@Override
	public int getSpawnRate() {
		return delegate.getSpawnRate();
	}

	@Override
	public ArrayList<Command> getCommands() {
		return delegate.getCommands();
	}

	@Override
	public int getPlayerLifes() {
		return delegate.getPlayerLifes();
	}

	@Override
	public void changeLifes(int n) {
		delegate.changeLifes(n);
	}

	@Override
	public boolean isOver() {
		return delegate.isOver();
	}

	@Override
	public int getLevel() {
		return delegate.getLevel();
	}

	@Override
	public State getStatus() {
		return delegate.getStatus();
	}

	@Override
	public int getTimeLef() {
		return delegate.getTimeLef();
	}
	

}
