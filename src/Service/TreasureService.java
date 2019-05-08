package Service;

import Types.Item;

public interface TreasureService extends EntityService{
	
	
	/**
	 * pre : x>0 and x < height(env)
	 * pre : y>=0 and x < width(env)
	 * pre : env != null
	 * pre : cellnature(env, x, y) = EMP
	 * pre : cellnature(env, x-1,y)= {PLT, MTL}
	 * post : getPosX(init(x,y,e,t)) = x
	 * post : getPosY(init(x,y,e,t)) = y
	 * post : type(Treasure) = type
	 */
	public void init(int x, int y, EnvironmentService env, Item type);

	
	public Item getType();
	
	/**
	 * pre : 0<x<height-1
	 * pre : 0<=y< widht-1
	 * pre : cellNature(env,x,y)==EMP
	 * pre : cellNature(env,x-1,y) is Free
	 * post : getX(t)=x
	 * post : getY(t)=y
	 */
	public void setPosition(int x, int y);
	
}
