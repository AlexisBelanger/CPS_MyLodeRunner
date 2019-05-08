package Impl;

import java.util.ArrayList;
import java.util.Arrays;

import Contract.GuardContract;
import Errors.PostconditionError;
import Service.CharacterService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.TreasureService;
import Types.Cell;
import Types.Move;

public class Guard extends Character implements GuardService{
	protected static int cpt=0;
	protected static int maxTimeInHole = 10;
	protected int id;
	protected Move behaviour;
	protected CharacterService target;
	protected int timeInHole;
	protected EngineService eng;
	protected boolean dead;
	protected TreasureService treasure;
	protected GuardContract contrat;
	
	
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public Move getBehaviour() {
		return behaviour;
	}

	@Override
	public CharacterService getTarget() {
		return target;
	}

	@Override
	public int getTimeInHole() {
		return timeInHole;
	}

	@Override
	public void climbLeft() {
		boolean charUpLeft = false;
		for(EntityService e : env.getCellContent(posX+1, posY-1)) {
			if(e instanceof CharacterService) {
				charUpLeft = true;
			}
		}
		boolean charTop = false;
		for(EntityService e : env.getCellContent(posX+1, posY)){
			if(e instanceof CharacterService){
				charTop = true;
				break;
			}
		}
		if(posY > 0 && !charUpLeft && !charTop && !(env.getCellNature(posX+1, posY-1) == Cell.PLT || env.getCellNature(posX+1, posY-1) == Cell.MTL || env.getCellNature(posX+1, posY-1) == Cell.DOR)) {
			posX++;
			posY--;
			env.moveCellContent(posX, posY, this);
			env.getCellContent(posX-1, posY+1).clear();
		}	
		
	}

	@Override
	public void climbRight() {
		boolean charUpRight = false;
		for(EntityService e : env.getCellContent(posX+1, posY+1)) {
			if(e instanceof CharacterService) {
				charUpRight = true;
			}
		}
		boolean charTop = false;
		for(EntityService e : env.getCellContent(posX+1, posY)){
			if(e instanceof CharacterService){
				charTop = true;
				break;
			}
		}
		
		if(posY < env.getWidth()-1 && !charUpRight && !charTop && !(env.getCellNature(posX+1, posY+1) == Cell.PLT || env.getCellNature(posX+1, posY+1) == Cell.MTL || env.getCellNature(posX+1, posY+1) == Cell.DOR)) {
			posX++;
			posY++;
			env.moveCellContent(posX, posY, this);
			env.getCellContent(posX-1, posY-1).clear();
		}		
	}

	@Override
	public void step() {
		boolean charDown = false;
		for(EntityService e : env.getCellContent(posX-1, posY)) {
			if(e instanceof CharacterService) {
				charDown = true;
			}
		}
		
	
		//Chute
		if(env.getCellNature(posX, posY)!=Cell.HDR && env.getCellNature(posX, posY)!= Cell.LAD && env.getCellNature(posX, posY)!= Cell.HOL && ((env.getCellNature(posX-1, posY)==Cell.HDR || env.getCellNature(posX-1, posY)== Cell.EMP|| env.getCellNature(posX-1, posY)== Cell.HOL) && !charDown)) {
			
			contrat.goDown();
			for(EntityService e : env.getCellContent(posX, posY)) {
				if(e instanceof TreasureService && treasure == null) {
					contrat.takeTreasure(posX,posY);
					break;
				}
			}
			behaviour = Move.Down;

		}else {
			Cell[] l = {Cell.LAD, Cell.PLT, Cell.MTL, Cell.DOR};
			ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(l)); 
			if(env.getCellNature(posX, posY) != Cell.HOL) {
				//Monter a une echelle
				if(posX<target.getPosX() && env.getCellNature(posX, posY)== Cell.LAD) {
					contrat.goUp();
					behaviour = Move.Up;
				}else {
					//Descendre l'echelle si le joueur est en dessous
					if(posX>target.getPosX() && (env.getCellNature(posX-1, posY)== Cell.LAD || (env.getCellNature(posX, posY)== Cell.HDR && !charDown && !libre.contains(env.getCellNature(posX-1, posY))))) {
						contrat.goDown();
						behaviour = Move.Down;
					}else {
						if( env.getCellNature(posX, posY)==Cell.HDR || libre.contains(env.getCellNature(posX-1, posY)) ||
								((!libre.contains(env.getCellNature(posX-1, posY))) && charDown )){
							//Deplacement a gauche si possible et si le joueur est plus a gauche
							if(posY>target.getPosY()) {
								contrat.goLeft();
								behaviour=Move.Left;
							}else {
								//Deplacement a gauche si possible et si le joueur est plus a gauche
								if(posY<target.getPosY()){
									contrat.goRight();
									behaviour = Move.Right;
								}
							}
						}else {
							
							if(env.getCellNature(posX, posY) == Cell.LAD ) {
								if(Math.abs(posY-target.getPosY())< Math.abs(posX-target.getPosX()) ) {
									if(posX>target.getPosX()) {
										contrat.goDown();
										behaviour=Move.Down;
									}else {
										contrat.goUp();
										behaviour = Move.Up;
									}
								}else {
									if(posY>target.getPosY() ) {
										if(libre.contains(env.getCellNature(posX,posY-1))) {
											if(posX>target.getPosX()) {
												contrat.goDown();
												behaviour=Move.Down;
											}else {
												contrat.goUp();
												behaviour = Move.Up;
											}
										}else {
											contrat.goLeft();
											behaviour=Move.Left;
										}
									}else {
										if(libre.contains(env.getCellNature(posX,posY+1))) {
											if(posX>target.getPosX()) {
												contrat.goDown();
												behaviour=Move.Down;
											}else {
												contrat.goUp();
												behaviour = Move.Up;
											}
										}else {
											contrat.goRight();
											behaviour = Move.Right;
										}
										
									}
								}
							}
						}
					}
				}
				for(EntityService e : env.getCellContent(posX, posY)) {
					if(e instanceof TreasureService && treasure == null) {
						contrat.takeTreasure(posX,posY);
						break;
					}
				}
			}else {
				
				if(treasure!=null) {
					contrat.releaseTreasure();
				}
				if( timeInHole==maxTimeInHole) {
					TreasureService t = null;
					for(EntityService e : env.getCellContent(posX+1, posY)) {
						if(e instanceof TreasureService) {
							t=(TreasureService)e;
						}
					}
					if(t!=null) {
						contrat.takeTreasure(posX+1,posY);
					}
					if(target.getPosY()>posY ) {
						if(posY < env.getWidth()-1) {
							contrat.climbRight();
							behaviour = Move.ClimbR;
						}else {
							contrat.climbLeft();
							behaviour = Move.ClimbL;
						}
						
					}else {
						System.out.println("JE climb");
						if(posY >0) {
							contrat.climbLeft();
							System.out.println("Je climb a gauche");
							behaviour = Move.ClimbL;
						}else {
							contrat.climbRight();
							behaviour = Move.ClimbR;
						}
					}
					timeInHole=0;
				}else {
					timeInHole++;
				}
				
			}
		}
	}

	@Override
	public void init(int x, int y, EnvironmentService s, EngineService eng) {
		super.init(x, y, s);
		this.eng=eng;
		this.target=eng.getPlayer();
		this.dead=false;
		this.treasure=null;
		this.contrat=new GuardContract(this);
		s.moveCellContent(x, y, this);
		
	}
	
	public void die() {
		this.dead=true;
	}
	
	public boolean isDead() {
		return dead;
	}

	@Override
	public TreasureService hasTreasure() {
		return treasure;
	}

	@Override
	public void takeTreasure(int x, int y) {
		for(EntityService e : env.getCellContent(x,  y)) {
			if(e instanceof TreasureService) {
				treasure = (TreasureService)e;
				break;
			}
		}
		env.getCellContent(x,  y).remove(treasure);
		
	}

	@Override
	public void releaseTreasure() {
		treasure.setPosition(posX+1, posY);
		env.getCellContent(posX+1, posY).add(treasure);
		treasure=null;
		
	}
	
	

}
