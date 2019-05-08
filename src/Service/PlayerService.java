package Service;

import java.util.ArrayList;

import Types.Orientation;

public interface PlayerService extends CharacterService {
	
	
	/**
	 
	 * pre : x>0 and x<height(s)
	 * pre : y>=0 and y<width(s)
	 * pre: engine != null
	 * post: getEngine() = e 
	 * post : inventory(player).size() = 0;
	 * post : posX=x
	 * post : posY=y
	 */
	public void init(int x, int y, EnvironmentService s, EngineService engine);
	
	
	
	public EngineService getEngine();
	
	/**
	 * post: forall command c : (cellNature(env(engine(player)), posX(Player), posY(player)) not in {LAD, HDR} && 
	 * 			(cellNature(env(engine(player)), posX(Player)-1, posY(player)) in {PLT, HDR} && 
	 * 			Character c not in cellContent(env(engine(player)), posX(Player)-1, posY(player))))
	 * 				=> goDown(palyer)
	 *
	 * 
	 */
	public void step();
	
	public ArrayList<TreasureService> getInventory();
	
	public Orientation getOrientation();
	
	
	/**
	 * post : orientation = left && posy>0 && cellNature(env(Player),posx, posy-1) = cell.DOR && (Inventory(Player) contains treasure t && type(t) = Key) => cellNature(env(opendoor(Player))posx, posy-1) = cell.EMP && inventory not contains t
	 * post : orientation = left && posy>0 && cellNature(env(Player),posx, posy-1) != cell.DOR => cellNature(env(opendoor(Player))posx, posy-1) = cellNature(env(Player),posx, posy-1)
	 * post : orientation = left && posy>0 && (Inventory(Player) not contains treasure with type Key) => cellNature(env(opendoor(Player))posx, posy-1) = cellNature(env(Player),posx, posy-1)
	 * post : orientation = right && posy<width(env)-1 && cellNature(posx, posy+1)@pre = cell.DOR && (Inventory(Player) contains treasure t && type(t) = Key) => cellNature(posx, posy+1) = cell.EMP && inventory not contains t
	 * post : orientation = right && posy<width(env)-1 && cellNature(posx, posy+1)@pre != cell.DOR => cellNature(posx, posy+1) = cellNature(posx, posy+1)@pre 
	 * post : orientation = right && posy<width(env)-1 && (Inventory(Player) not contains treasure with type Key) => cellNature(posx, posy+1) = cellNature(posx, posy+1)@pre 
	 * 
	 */
	public void openDoor();
	
	
	

}
