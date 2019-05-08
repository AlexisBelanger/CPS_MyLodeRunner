package Service;

import Types.Cell;

public interface EditableScreenService extends ScreenService {
	//INVARIANTS

	
	

	public boolean playable();
	/**
	 * pre : n>=0 && n<getHeight()
	 * pre : m>=0 && n<getWidth()
	 * post : getCellNature(SetNature(x,y, cell), x, y) = cell
	 * post : forall (x,y), forall(u,v): getCellNature(Setcell(x,y,cell) u,v) = getCellNAture( u,v)
	 */
	public void setNature(int n, int m, Cell c);
	
	
	/**
	 * pre : h > 0
	 * pre : w > 0
	 * post : height = h
	 * post : width = w
	 */
	public void init(int h, int w);

}
