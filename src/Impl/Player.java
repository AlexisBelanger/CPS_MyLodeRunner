package Impl;

import java.util.ArrayList;
import java.util.Arrays;

import Contract.PlayerContract;
import Contract.TreasureContract;
import Service.CharacterService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Cell;
import Types.Command;
import Types.Item;
import Types.Orientation;

public class Player extends Character implements PlayerService{
	private EngineService engine;
	private ArrayList<TreasureService> inventory;
	private Orientation orientation;
	private PlayerContract contrat;
	
	public Player() {
		//DOES NOTHING
	}
	
	@Override
	public void init(int x, int y, EnvironmentService s, EngineService engine) {
		super.init(x, y, s);
		this.engine = engine;
		this.inventory = new ArrayList<TreasureService>();
		this.orientation = Orientation.Right;
		s.moveCellContent(x, y, this);
		contrat = new PlayerContract(this);
	}

	@Override
	public EngineService getEngine() {
		return engine;
	}

	@Override
	public void step() {
		boolean charDown = false;
		for(EntityService e : env.getCellContent(posX-1, posY)) {
			if(e instanceof CharacterService) {
				charDown = true;
			}
		}
		
	
		
		ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(Cell.EMP, Cell.HDR));
		if(env.getCellNature(posX, posY)!=Cell.HDR && env.getCellNature(posX, posY)!= Cell.LAD && ((env.getCellNature(posX-1, posY)==Cell.HDR || env.getCellNature(posX-1, posY)== Cell.EMP|| env.getCellNature(posX-1, posY)== Cell.HOL) && !charDown)) {
			// On ramasse tout d'un coup sur la case il ne reste donc que le joueur
			ArrayList<TreasureService> temp = new ArrayList<TreasureService>();			
			
			for(EntityService e : env.getCellContent(posX-1, posY)) {
				//env.getCellContent(posX-1,  posY).remove(e);
				if(e instanceof TreasureService) {
					if(((TreasureService)e).getType()==Item.Potion) {
						engine.changeLifes(1);
						System.out.println("+---------------------------------------+");
						System.out.println("|                                       |");
						System.out.println("|           +1 life ! ( "+engine.getPlayerLifes()+" lifes )          |");
						System.out.println("|                                       |");
						System.out.println("+---------------------------------------+");
					}//CORRECTION
					temp.add((TreasureService)e);
					
					engine.getTreasures().remove((TreasureService)e);
				}
				
			}
			for(EntityService e : temp) {
				env.getCellContent(posX-1,  posY).remove(e);
				if(e instanceof TreasureService) {
					if(((TreasureService)e).getType()!=Item.Potion) {
						inventory.add((TreasureService)e);
					}
				}
			}
			temp.clear();
			goDown();

		}else {
			
			Command com = engine.getNextCommand();
			switch(com) {
			case Left:
				if(!(posY-1<0)) {
					ArrayList<TreasureService> temp = new ArrayList<TreasureService>();
					for(EntityService e : env.getCellContent(posX, posY-1)) {
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()==Item.Potion) {
								engine.changeLifes(1);
								System.out.println("+---------------------------------------+");
								System.out.println("|                                       |");
								System.out.println("|         +1 life ! ( "+engine.getPlayerLifes()+" lifes )         |");
								System.out.println("|                                       |");
								System.out.println("+---------------------------------------+");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							temp.add((TreasureService)e);
							
							
							engine.getTreasures().remove((TreasureService)e);
						}
					}
					
					for(EntityService e : temp) {
						env.getCellContent(posX,  posY-1).remove(e);
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()!=Item.Potion) {
								inventory.add((TreasureService)e);
							}
						}
					}
					temp.clear();
				
				
				}
				contrat.goLeft();
				orientation=Orientation.Left;
				break;
			case Right:
				if(!(posY+1>env.getWidth()-1)) {
					ArrayList<TreasureService> temp = new ArrayList<TreasureService>();
					for(EntityService e : env.getCellContent(posX, posY+1)) {
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()==Item.Potion) {
								engine.changeLifes(1);
								System.out.println("+---------------------------------------+");
								System.out.println("|                                       |");
								System.out.println("|         +1 life ! ( "+engine.getPlayerLifes()+" lifes )         |");
								System.out.println("|                                       |");
								System.out.println("+---------------------------------------+");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							temp.add((TreasureService)e);
							
							
							engine.getTreasures().remove((TreasureService)e);
						}
					}
					
					for(EntityService e : temp) {
						env.getCellContent(posX,  posY+1).remove(e);
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()!=Item.Potion) {
								inventory.add((TreasureService)e);
							}
						}
					}
					System.out.println(env.getCellContent(posX, posY+1).size());
					temp.clear();
				
				}
				
				contrat.goRight();
				orientation=Orientation.Right;
				break;
			case Up:
				if(!(posX+1>env.getHeight()-1)) {
					ArrayList<TreasureService> temp = new ArrayList<TreasureService>();
					for(EntityService e : env.getCellContent(posX+1, posY)) {
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()==Item.Potion) {
								engine.changeLifes(1);
								System.out.println("+---------------------------------------+");
								System.out.println("|                                       |");
								System.out.println("|         +1 life ! ( "+engine.getPlayerLifes()+" lifes )         |");
								System.out.println("|                                       |");
								System.out.println("+---------------------------------------+");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							temp.add((TreasureService)e);
							
							
							engine.getTreasures().remove((TreasureService)e);
						}
					}
					
					for(EntityService e : temp) {
						env.getCellContent(posX+1,  posY).remove(e);
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()!=Item.Potion) {
								inventory.add((TreasureService)e);
							}
						}
					}
					temp.clear();
				
				}
				
				contrat.goUp();
				break;
			case Down:
				if(!(posX-1<0)) {
					ArrayList<TreasureService> temp = new ArrayList<TreasureService>();
					for(EntityService e : env.getCellContent(posX-1, posY)) {
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()==Item.Potion) {
								engine.changeLifes(1);
								System.out.println("+---------------------------------------+");
								System.out.println("|                                       |");
								System.out.println("|         +1 life ! ( "+engine.getPlayerLifes()+" lifes )         |");
								System.out.println("|                                       |");
								System.out.println("+---------------------------------------+");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							temp.add((TreasureService)e);
							
							
							engine.getTreasures().remove((TreasureService)e);
						}
					}
					
					for(EntityService e : temp) {
						env.getCellContent(posX-1,  posY).remove(e);
						if(e instanceof TreasureService) {
							if(((TreasureService)e).getType()!=Item.Potion) {
								inventory.add((TreasureService)e);
							}
						}
					}
					temp.clear();
				
				}
				
				contrat.goDown();
				break;
			case Nothing:
				break;
			case DigL:				
				if(posY>0) {
					boolean charLeft =false;
					for(EntityService e : env.getCellContent(posX, posY-1)) {
						if(e instanceof CharacterService) {
							charLeft = true;
						}
					}
					if(env.getCellNature(posX, posY-1)== Cell.EMP && env.getCellContent(posX, posY-1).size()==0 && env.getCellNature(posX-1, posY-1)==Cell.PLT) {
						env.dig(posX-1, posY-1);
						int[] tmp = {posX-1, posY-1, 0};
						engine.getHoles().add(tmp);
					}
					
				}				
				break;
			case DigR:
				
				
				
				if(posY<env.getWidth()-1 ) {
					boolean charRight =false;
					for(EntityService e : env.getCellContent(posX, posY+1)) {
						if(e instanceof CharacterService) {
							charRight = true;
						}
					}
					
					if(env.getCellNature(posX, posY+1)== Cell.EMP && env.getCellContent(posX, posY+1).size()==0 && env.getCellNature(posX-1, posY+1)==Cell.PLT) {
						env.dig(posX-1, posY+1);
						int[] tmp = {posX-1, posY+1, 0};
						engine.getHoles().add(tmp);
					}
				}
				break;
			case Open:
				TreasureService key = null;
				for(TreasureService t : inventory) {
					if(t.getType()==Item.Key) {
						key = t;
					}
				}
				if(orientation == Orientation.Left && posY >0) {
					if(env.getCellNature(posX, posY-1)== Cell.DOR && key !=null) {
						contrat.openDoor();
						inventory.remove(key);
					}
				}
				if(orientation == Orientation.Right && posY <env.getWidth()-1) {
					if(env.getCellNature(posX, posY+1)== Cell.DOR && key !=null) {
						contrat.openDoor();
						inventory.remove(key);
					}
				}
				
				
				break;
				
			}
		}
		
	}

	@Override
	public ArrayList<TreasureService> getInventory() {
		return inventory;
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public void openDoor() {
		if(orientation == Orientation.Left) {
			env.openDoor(posX, posY-1);
		}else {
			env.openDoor(posX, posY+1);
		}
	}

}
