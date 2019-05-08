package Service;

import java.util.ArrayList;
import Service.EntityService;

public interface EnvironmentService extends ScreenService {

/* INVARIANTS */
	
	/**
	 * forall(x,y) in [0, height(env)-1][0, width(env)-1]
	 * 		if CellContent(env, x, y) is_in {MLT, PLT} then size(CellContent(env, x, y)) = 0
	 * forall(x,y) in [0, height(env)-1][0, width(env)-1]
	 *  	if CellContent(env, x, y) contains Treasure t then CellNature(env, x, y) = EMP AND CellNature(env,x,y-1) is_in {PLT, MTL}
	 */
	
	
	
	
/* INITIALIZER*/
	//TODO
	/**
	 * post : forall(x,y) in [0, height()-1][0, width()-1], cellContent(x,y).size()= 0
	 */
	public void init(EditableScreenService es);
	
	
/* OPERATORS */
	/**
	 * pre : n>=0 && n<getHeight()
	 * pre : m>=0 && m<getWidth()
	 */
	public ArrayList<EntityService> getCellContent(int n, int m);
	
	/**
	 * pre : x > 0 x <height()-1
	 * pre : y > 0 y <widht()-1
	 * post: posX(entity) = x and posY(entity) = y
	 */
	public void moveCellContent(int x, int y, EntityService entity);
	
	//redéfinie dans environnement car on a besoin de connaitre le contenu de la case au dessus
	/**
	 * pre: empty(cell(n+1,m)
	 */
	public void dig(int n, int m);
	
	public void fill(int n, int m);
	
	/**
	 * pre : n>0 and n<Height-1
	 * pre : m >= 0 and m< Width-1
	 * post : cellNature(n,m) = EMP
	 */
	public void openDoor(int n, int m);

}
