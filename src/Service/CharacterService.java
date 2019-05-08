package Service;

/* INVARIANTS */
/**
 * cellNature(env(C), posX(C), posY(C)) is_in {EMP, LAD, HOL, HDR}
 * 
 *
 */
public interface CharacterService extends EntityService {

	/**
	 * pre : CellContent(s, x, y) = EMP
	 * pre : x>0 && x< s.height
	 * pre: y>=0  && y < s.width
	 * post : posX = x
	 * post : posY= y 
	 */
	public void init(int x, int y, EnvironmentService s);
	
	
	/**
	 * post : PosX(C) = PosX(goLeft(C))
	 * post : posY(C)=0 => posY(goLeft(C))=0
	 * post : CellNature(Env(C), height(C), width(C)-1) is_in {MTL, PLT} => width(goLeft(C)) = widht(C) 
	 * post : (CellNature(Env(C), height(C), width(C)) not is_in {LAD, HDR} AND
	 * 		  	CellNature(Env(C), height(C)-1, width(C)) not is_in {PLT,MTL, LAD} AND
	 * 			and not_exists Character c is_in CellContent(Env(C), height(C)-1, width(C))) => width(goLeft(C) = width(C)
	 * post : if CellContent(Env(C), height(C), width(C)-1) contains Character c then width(goLeft(C) = width(C)
	 * post : ((width(C) != 0) AND
	 * 			CellNature(Env(C), width(C)-1, height(C)) not is_in {PLT, LAD, MTL} AND
	 * 				(CellNature(Env(C), height(C), width(C)) is_in {LAD, HDR} OR
	 * 				CellNature(Env(C), height(C)-1, width(C)) is_in{PLT, MTL, LAD} OR
	 * 				CellContent(Env(C), height(C)-1, width(C)) contains Character c) AND
	 *			CellContent(Env(C), height(C), width(C)-1) not contains Character c => width(goLeft(C) = width(C)-1
	 * 
	 */
	public void goLeft();
	
	/**
	 * post : PosX(C) = PosX(goLeft(C))
	 * post : posY(C)=Width(env(C))-1 => posY(goLeft(C))=Width(env(C))-1 
	 * post : CellNature(Env(C), height(C), width(C)+1) is_in {MTL, PLT, LAD} => width(goLeft(C)) = widht(C) 
	 * post : (CellNature(Env(C), height(C), width(C)) not is_in {LAD, HDR} AND
	 * 		  	CellNature(Env(C), height(C)-1, width(C)) not is_in {PLT,MTL, LAD} AND
	 * 			and not_exists Character c is_in CellContent(Env(C), height(C)-1, width(C))) => width(goLeft(C) = width(C)
	 * post : if CellContent(Env(C), height(C), width(C)+1) contains Character c then width(goLeft(C) = width(C)
	 * post : ((width(C) != width(env(C))) AND
	 * 			CellNature(Env(C), width(C)+1, height(C)) not is_in {PLT, LAD, MTL} AND
	 * 				(CellNature(Env(C), height(C), width(C)) is_in {LAD, HDR} OR
	 * 				CellNature(Env(C), height(C)-1, width(C)) is_in{PLT, MTL, LAD} OR
	 * 				CellContent(Env(C), height(C)-1, width(C)) contains Character c) AND
	 *			CellContent(Env(C), height(C), width(C)+1) not contains Character c) => width(goLeft(C)) = width(C)+1
	 * 
	 */
	public void goRight();
	/**
	 * post : PosY(C) = PosY(goLeft(C))
	 * post : posX(C)=height(Env(C))-1 => posX(goUp(C))=height(Env(C))-1
	 * post : CellNature(Env(C), height(C), width(C)) not is_in {LAD} OR
	 * 			(CellNature(Env(C), height(C), width(C)) is_in {LAD} AND
	 * 				 (CellNature(Env(C), height(C)+1, width(C)) is_in {PLT, MTL} OR
	 * 						(CellNature(Env(C), height(C)+1, width(C)) is {HOL} AND
	 * 							CellContent(Env(C), Height(C)+1, width(C)) contains Character c )))=> height(goDown(C)) = height(C) 
	 * post : CellNposature(Env(C), height(C), width(C)) is {LAD} AND
	 * 			(CellNature(Env(C), height(C)+1, width(C)) is_in {LAD,EMP,HDR} OR
	 * 				(CellNature(Env(C), height(C)+1, width(C)) is {HOL} AND
	 * 					CellContent(Env(C), Height(C)+1, width(C)) not contains Character c )))=> height(goDown(C)) = height(C) +1
	 */
	public void goUp();
	/**
	 * post : PosY(C) = PosY(goLeft(C))
	 * post : posX(C)=0 => posY(goDown(C))=0
	 * post : CellNature(Env(C), height(C)-1, width(C)) is_in {MTL, PLT} => height(goDown(C)) = height(C) 
	 * post : (CellNature(Env(C), height(C)-1, width(C)) is_in {HOL} AND
	 * 			CellContent(Env(C), height(C)-1, width(C)) contains Character c) => height(goDown(C)) = height(C) 
	 * post : (CellNature(Env(C),  height(C)-1, width(C)) is_in {LAD, EMP, HDR, HOL} and 
	 * 			CellContent(Env(C), height(C)-1, width(C)) not contains Character c ) => height(goDown(C)) = height(C)-1 
	 * 
	 * 
	 * 
	 */
	public void goDown();
}
