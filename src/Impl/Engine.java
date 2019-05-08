package Impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import Contract.EngineContract;
import Contract.GuardContract;
import Contract.PlayerContract;
import Contract.TreasureContract;
import Service.EditableScreenService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Cell;
import Types.Command;
import Types.Item;
import Types.State;




public class Engine extends KeyAdapter implements EngineService{
	private Command nextCommand=Command.Nothing;
	private EnvironmentService env;
	private PlayerService player;
	private ArrayList<GuardService> guards;
	private ArrayList<TreasureService> objects;
	private ArrayList<Command> commands;
	private ArrayList<int[]> holes;
	private int[] spawn;
	private static int timeBeforeRespawn = 20;
	private int spawnRate;
	private int nbgardes;
	private int playerLifes=3;
	private static int level=1;
	private int nbLevel;
	private boolean over = false;
	private State status;
	private int timeLeft = 500;
	private EngineContract contrat;
	
	
	
	public Engine() {
		//DOES NOTHING
	}
	
	@Override
	public void init(EditableScreenService es, int x, int y, ArrayList<int[]> gardes, ArrayList<int[]> objets, String[] types, int[] spawn, PlayerService p, int nbLevel) {
		env = new Environment();
		objects =  new ArrayList<TreasureService>();
		guards =  new ArrayList<GuardService>();
		holes = new ArrayList<int[]>();
		this.spawn=spawn;
		this.status=State.Playing;
		this.nbLevel=nbLevel;
		this.spawnRate=timeBeforeRespawn;
		this.nbgardes=gardes.size();
		this.contrat=new EngineContract(this);
		env.init(es);
		p.init(x,y, env, this);
		player=p;
		for(int i=0; i<gardes.size(); i++) {
			Guard guard = new Guard();
			GuardContract g = new GuardContract(guard);
			g.init(gardes.get(i)[0],gardes.get(i)[1],env,this);
			guards.add(g);
		}
		for(int i=0; i<objets.size(); i++) {
			Treasure tres = new Treasure();
			TreasureService t = new TreasureContract(tres);
			switch (types[i]) {
			case "T":
				t.init(objets.get(i)[0], objets.get(i)[1], env, Item.Treasure);
				break;
			case "K" :
				t.init(objets.get(i)[0], objets.get(i)[1], env, Item.Key);
				break;
			case "P" : 
				t.init(objets.get(i)[0], objets.get(i)[1], env, Item.Potion);
				break;
			}
			
			
			objects.add(t);
			env.getCellContent(objets.get(i)[0],objets.get(i)[1]).add(t);
		}
		commands = new ArrayList<Command>();
		
	}

	@Override
	public EnvironmentService getEnv() {
		return env;
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public ArrayList<GuardService> getGuards() {
		return guards;
	}

	@Override
	public ArrayList<TreasureService> getTreasures() {
		return objects;
	}
	


	@Override
	public State computeStatus() {
		boolean noMoreTreasure = true;
		for(EntityService e : objects) {
			if(e instanceof TreasureService) {
				if(((TreasureService)e).getType()==Item.Treasure) {
					noMoreTreasure=false;
				}
			}
		}
		if(noMoreTreasure) {
			status = State.Win;
			commands.clear();
			return status;
		}else{
			/* CORRECTION*/
			boolean guardHere=false;
			for(EntityService e : env.getCellContent(player.getPosX(), player.getPosY())) {
				if(e instanceof GuardService) {
					guardHere=true;
				}
			}
			int x = player.getPosX();
			int y = player.getPosY();
			if(guardHere) {
				contrat.changeLifes(-1);
				System.out.println("+---------------------------------------+");
				System.out.println("|                                       |");
				System.out.println("|          "+getPlayerLifes()+" lifes remaining           |");
				System.out.println("|                                       |");
				System.out.println("+---------------------------------------+");
				if(playerLifes==0) {
					status = State.End;
					commands.clear();
					return status;
				}else{
					status = State.Loss;
					commands.clear();
					return status;
				}	
			}else {
				if(env.getCellNature(x, y)==Cell.HOL && ((y>0 && env.getCellNature(x, y-1)!=Cell.EMP && env.getCellNature(x, y-1)!=Cell.HDR && env.getCellNature(x, y-1)!=Cell.LAD)||y==0) && env.getCellNature(x-1, y)!= Cell.EMP && env.getCellNature(x-1, y)!= Cell.LAD && env.getCellNature(x-1, y)!= Cell.HDR){
					if(env.getCellNature(x, y)==Cell.HOL && ((y<env.getWidth()-1 && env.getCellNature(x, y+1)!=Cell.EMP && env.getCellNature(x, y+1)!=Cell.HDR && env.getCellNature(x, y+1)!=Cell.LAD)||y==env.getWidth()-1)){
						contrat.changeLifes(-1);
						System.out.println("+---------------------------------------+");
						System.out.println("|                                       |");
						System.out.println("|          "+getPlayerLifes()+" lifes remaining            |");
						System.out.println("|                                       |");
						System.out.println("+---------------------------------------+");
						if(playerLifes==0) {
							status = State.End;
							commands.clear();
							return status;
						}else{
							status = State.Loss;
							commands.clear();
							return status;
						}	
					}
				}else {
					if(timeLeft==0) {
						contrat.changeLifes(-1);
						System.out.println("+---------------------------------------+");
						System.out.println("|                                       |");
						System.out.println("|          "+getPlayerLifes()+" lifes remaining           |");
						System.out.println("|                                       |");
						System.out.println("+---------------------------------------+");
						if(playerLifes==0) {
							status = State.End;
							commands.clear();
							return status;
						}else{
							status = State.Loss;
							commands.clear();
							return status;
						}	
					}
				}
				
			}
			
			status = State.Playing;
			return status;
		}
	}

	@Override
	public Command getNextCommand() {
		if(commands.size()==0) {
			return Command.Nothing;
		}else {
			Command c = commands.get(commands.size()-1);
			commands.remove(commands.size()-1);
			return c;
		}
	}

	@Override
	public void step() {
		timeLeft--;
		if(nextCommand == Command.Left) {
			commands.add(Command.Left);
			nextCommand=Command.Nothing;
		}else {
			if(nextCommand == Command.Right) {
				commands.add(Command.Right);
				nextCommand=Command.Nothing;
			}else {
				if(nextCommand == Command.Up) {
					commands.add(Command.Up);
					nextCommand=Command.Nothing;
				}else {
					if(nextCommand == Command.Down) {
						commands.add(Command.Down);
						nextCommand=Command.Nothing;
					}else {
						if(nextCommand == Command.Nothing) {
							commands.add(Command.Nothing);
						}else {
							if(nextCommand == Command.DigL) {
								commands.add(Command.DigL);
								nextCommand=Command.Nothing;
							}else {
								if(nextCommand== Command.DigR) {
									commands.add(Command.DigR);
									nextCommand=Command.Nothing;
								}else {
									if(nextCommand== Command.Open) {
										commands.add(Command.Open);
										nextCommand=Command.Nothing;
									}else {
									}
								}
							}
						}
					}
					
				}
			}
			
		}
		player.step();
		
		ArrayList<GuardService> tmp = new ArrayList<GuardService>();
		for(GuardService g : guards) {
			if(g.isDead()) {
				tmp.add(g);
			}else {
				g.step();
			}
			
		}
		
		for(GuardService g : tmp) {
			guards.remove(g);
		}
		
		if(nbgardes>guards.size()) {
			if(spawnRate != 0) {
				spawnRate--;
			}else {
				Guard guard = new Guard();
				GuardContract g = new GuardContract(guard);
				g.init(spawn[0],spawn[1],env,this);
				guards.add(g);
				spawnRate=timeBeforeRespawn;
			}
		}
		
		ArrayList<int[]> temp = new ArrayList<int[]>();
		for(int[] tab : holes) {
			tab[2]++;
			if(tab[2]==25) {
				if(player.getPosX()==tab[0] && player.getPosY() == tab[1]) {
					status = State.Loss;
				}else {
					env.fill(tab[0], tab[1]);
					temp.add(tab);
				}
				
			}
			
		}
		for(int[] tab : temp) {
			holes.remove(tab);
		}
		temp.clear();
		
		contrat.computeStatus();
		if(status== State.Win) {
			System.out.println("Level "+level+" complete");
			if(level>=nbLevel) {
				over=true;
				System.out.println("+---------------------------------------+");
				System.out.println("|                                       |");
				System.out.println("|                YOU WIN                |");
				System.out.println("|        *playing victory music*        |");
				System.out.println("|                                       |");
				System.out.println("+---------------------------------------+");
			}
			level++;
		} else {
			if(status==State.Loss) {
				System.out.println("Too bad :(");
			}else {
				if(status!= State.Playing) {
					System.out.println("+---------------------------------------+");
					System.out.println("|                                       |");
					System.out.println("|                YOU LOSE               |");
					System.out.println("|        *playing dramatic music*       |");
					System.out.println("|                                       |");
					System.out.println("+---------------------------------------+");
					over=true;
				}
			}
			
		}
		if(timeLeft==0) {
			timeLeft=10;
		}
	
	}
	
	@Override
    public void keyPressed(KeyEvent event) {

    int ch = event.getKeyCode();
    
    switch(ch) {
    case KeyEvent.VK_Z:
    	
    	nextCommand=Command.Up;
    	
    	break;
    case KeyEvent.VK_S:
    	nextCommand=Command.Down;
    	break;
    case KeyEvent.VK_Q:
    	nextCommand=Command.Left;
    	break;
    case KeyEvent.VK_D:
    	nextCommand=Command.Right;
    	break;
    case KeyEvent.VK_I:
    	nextCommand=Command.DigL;
    	break;
    case KeyEvent.VK_P:
    	nextCommand=Command.DigR;
    	break;
    case KeyEvent.VK_O:
    	nextCommand=Command.Open;
    	break;
    }

}

	@Override
	public ArrayList<int[]> getHoles() {
		return holes;
	}

	@Override
	public int getNbGuards() {
		return nbgardes;
	}
	
	@Override
	public int getSpawnRate() {
		return spawnRate;
	}
	
	@Override
	public ArrayList<Command> getCommands(){
		return commands;
	}

	@Override
	public int getPlayerLifes() {
		return playerLifes;
	}

	@Override
	public void changeLifes(int n) {
		playerLifes+=n;
	}

	@Override
	public boolean isOver() {
		return over;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public State getStatus() {
		return status;
	}

	@Override
	public int getTimeLef() {
		return timeLeft;
	}
	
	

}




