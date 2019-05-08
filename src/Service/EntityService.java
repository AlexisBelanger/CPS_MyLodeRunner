package Service;

public interface EntityService {
	
	/* INVARIANTS */
	
	/**
	 * x>0 and x < height(env)
	 * y>=0 and x < width(env)
	 */
	
	
	
	public EnvironmentService getEnv();
	
	public int getPosX();
	
	public int getPosY();
	/**
	 * pre : x>0 and x < height(env)
	 * pre : y>=0 and x < width(env)
	 * pre : env != null
	 * pre : cellnature(env, x, y) = EMP
	 * pre : cellnature(env, x-1,y)= {PLT, MTL}  or cellnature(env, x-1, y)= HOL and cellContent(env,x-1,y) contains Character c
	 * post : getPosX(init(x,y)) = x
	 * post : getPosY(init(x,y)) = y
	 * 
	 */
	public void init(int x, int y, EnvironmentService env);
	
	

}
