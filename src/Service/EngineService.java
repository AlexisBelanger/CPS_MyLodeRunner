package Service;

import java.util.ArrayList;

import Types.Command;
import Types.State;

public interface EngineService {
	
	/* INVARIANTS */
	/**
	 * 
	 * 
	 */
	
	/**
	 * pre: forall g in gardes, len(g)=2
	 * pre: forall t in tresors, len(t)=2
	 * pre : len(types) = len(tresors)
	 * pre : len(spawn) = 2
	 * post height(es) = height(env(init(es,x,y,g,t,typ,s,l)))
	 * post width(es) = width(env(init(es,x,y,g,t,typ,s,l)))
	 * post: forall (x,y) in [0,height(es)-1][0, width(es)-1] cellNature(Env(init(es,x,y,g,t,typ,s,l)), x, y) = cellNature(es, x, y)
	 * post: getPosX(getPlayer(init(es,x,y,g,t,typ,s,l))) = x
	 * post: getPosY(getPlayer(init(es,x,y,g,t,typ,s,l))) = y
	 * 
	 */
	public void init(EditableScreenService es, int x, int y, ArrayList<int[]> gardes, ArrayList<int[]> tresors, String[] types, int[] spawn, PlayerService p , int nbLevel); 
	
	

	public EnvironmentService getEnv();
	
	public PlayerService getPlayer();
	
	public ArrayList<GuardService> getGuards();
	
	public ArrayList<TreasureService> getTreasures();
	
	public State getStatus();
	
	public State computeStatus();
	
	public int getNbGuards();
	
	public int getSpawnRate();
	
	public ArrayList<int[]> getHoles();
	
	public Command getNextCommand();
	
	public int getPlayerLifes();
	
	public boolean isOver();
	
	public int getLevel();
	
	public int getTimeLef();
	
	/**
	 * pre : n==1 or n==-1
	 * post : Lifes = lifes@pre + n
	 */
	public void changeLifes(int n);
	
	
	
	public ArrayList<Command> getCommands();
	/**
	 * post: CellContent(env, posX(player()), posY(player())) contains Treasure t =>
	 *   CellContent(step(env), posX(player()), posY(player())) = CellContent(env, posX(player()), posY(player()))\[t]
	 *   
	 * post : (size(getGuards(eng))@pre < nbGuards(eng)@pre  &&  spawnrate(eng)@pre == 0 )=> size(getGuards(eng)) = size(getGuards(eng))@pre+1 && spawnrate(eng)=20;
	 */
	public void step();
	
}
