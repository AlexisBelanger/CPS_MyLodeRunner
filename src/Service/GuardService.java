package Service;

import Types.Move;

public interface GuardService extends CharacterService{
	/*Comportement global, ici pour s'en rappeler*/
	/**
	 * (posX(target(G)) > posX(G) and cellNature(env, posx, posy) = LAD)  => behaviour = Up
	 * (posX(target(G)) < posX(G) and cellNature(env, posx, posy) = LAD) => behaviour = Down
	 * (posX(target(G)) = posX(G) and cellNature(env, posx, posy) = LAD) => behaviour = Neutral
	 * 
	 * (cellnature(env, posx, posy) = HDR or 
	 *  cellnature(env, posx, posy) = HOL or 
	 * (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c))) and
	 *  posY(G) < posY(target(G)) => behaviour = Right
	 *  
	 *  (cellnature(env, posx, posy) = HDR or 
	 *  cellnature(env, posx, posy) = HOL or 
	 * (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c))) and
	 *  posY(G) > posY(target(G)) => behaviour = Left
	 *  
	 *  (cellnature(env, posx, posy) = HDR or 
	 *  cellnature(env, posx, posy) = HOL or 
	 * (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c))) and
	 *  posY(G) = posY(target(G)) => behaviour = Neutral
	 *  
	 *  cellNature(env, posx, posy) = LAD and
	 *  (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c)) and
	 *   abs(posY(G) - posY(target(G))) > abs(posX(G) - posX(target(G))) and
	 *   posX(G) > posX(target(G)  => behaviour = Down 
	 *   
	 *   cellNature(env, posx, posy) = LAD and
	 *  (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c)) and
	 *   abs(posY(G) - posY(target(G))) > abs(posX(G) - posX(target(G))) and
	 *   posX(G) < posX(target(G)  => behaviour = Up 
	 *   
	 *   
	 *   cellNature(env, posx, posy) = LAD and
	 *  (cellnature(env, posx-1, posy) in {LAD, PLT, MTL} || (cellnature(env, posx-1, posy) = HOL and cellContent(env, posx-1, posy) contains Character c)) and
	 *   abs(posY(G) - posY(target(G))) <= abs(posX(G) - posX(target(G))) and
	 *   posY(G) = posY(target(G)  => behaviour = Neutral 
	 * 
	 * 
	 *  
	 * 
	 * 
	 * 
	 */
	
	public int getId();
	
	public Move getBehaviour();
	
	public CharacterService getTarget();
	
	public int getTimeInHole();
	
	/**
	 * pre :  cellnature(env, x,y) = HOL and
	 *
	 *  post : posY(G)@pre = 0 => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : cellNature(env, x+1,y-1) in {PLT, MTL} => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : CellContent(env, x+1, y-1) contains Character c  => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : (!(posY(G)@pre = 0) and
	 *   !(cellNature(env, x+1,y-1) in {PLT, MTL} ) and
	 *   !(CellContent(env, x+1, y-1) contains Character c))
	 *   => (posX(G) = posX(G)@pre+1 and posY(G) = posY(G)@pre-1 )
	 *   
	 *  
	 */
	public void climbLeft();
	/**
	 * pre : cellnature(env, x,y) = HOL
	 *
	 *  post : posY(G)@pre = width(env)-1 => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : cellNature(env, x+1,y+1) in {PLT, MTL} => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : CellContent(env, x+1, y+1) contains Character c  => (posX(G) == posX(G)@pre and posY(G) == posY(G)@pre )
	 *  
	 *  post : (!(posY(G)@pre = width(env)-1 and
	 *   !(cellNature(env, x+1,y+1) in {PLT, MTL} ) and
	 *   !(CellContent(env, x+1, y+1) contains Character c))
	 *   => (posX(G) = posX(G)@pre+1 and posY(G) = posY(G)@pre+1 )
	 *   
	 *   
	 */
	public void climbRight();
	
	
	/*
	 * TODO
	 */
	public void step();
	
	/**
	 * pre : x > 0 and x < height(env)-1
	 * pre : y > 0 and y < width(env)-1
	 * pre : cellNature(env, x, y) is free
	 * 
	 * post : posX(G) = x
	 * post : posY(G) = y
	 * post : Env(G) = s
	 */
	public void init(int x, int y, EnvironmentService s, EngineService eng);


	public void die();
	
	public boolean isDead();
	
	
	public TreasureService hasTreasure();
	/**
	 * pre 0<x<height
	 * pre 0<=y< width
	 * pre : cellContent(env)@pre contains Treasure t
	 * post : hasTreasure(g) = null  => hasTreasure(G) = t and size(cellContent(env)) = size(cellContent(env)@pre) -1 
	 * post : all other cells content are not modified
	 */
	public void takeTreasure(int x, int y);
	
	/**
	 * pre : cellNature(env, posx+1, posy) = EMP && cellnature(env, posx,posy) = HOL && hasTreasure(g) != null
	 * post : cellcontent(env, posx+1,posy) contains hasTreasure(g)@pre && hasTreasure(g)=null; 
	 */
	public void releaseTreasure();
	
	
}
