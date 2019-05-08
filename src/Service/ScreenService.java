package Service;

import Types.Cell;

public interface ScreenService {
	
	public int getHeight();
	public int getWidth();
	/**
	 * pre : n>=0 && n<getHeight()
	 * pre : m>=0 && m<getWidth()
	 */
	public Cell getCellNature(int n, int m);
	
	/* INVARIANTS*/
	
	/**
	 * 
	 */
	
	/* INITIALIZER*/
	
	/**
	 * pre : h > 0
	 * pre : w > 0 
	 * post : height() = h
	 * post : width() = w
	 * forall(x,y) in [0, height()][0, width()], cellNature(x,y)= Cell.EMP
	 */
	public void init(int h, int w);
	
	
	/*OPERATORS*/
	/**
	 * pre : CellNature(n,m) = Cell.PLT
	 * pre : cell(n+1,m) = emp and empty(cell(n,m)
	 * post : CellNature(n,m) = Cell.HOL
	 * post : forall(n,m), for all(u,v) if (n!=u || m!=v) then CellNature(u,v) = CellNature(u,v)@pre
	 */
	public void dig(int n, int m);
	
	/**
	 * pre : CellNature(n,m) = Cell.HOL
	 * post : CellNature(n,m) = Cell.PLT
	 * post : forall(n,m), for all(u,v) if (n!=u || m!=v) then CellNature(u,v) = CellNature(u,v)@pre
	 */
	public void fill(int n, int m);
	

}
