package Contract;

import Decorators.EntityDecorator;
import Errors.InvariantError;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EntityService;
import Service.EnvironmentService;
import Types.Cell;

public class EntityContract extends EntityDecorator{

	public EntityContract(EntityService delegate) {
		super(delegate);
	}
	
	
	public void checkInvariants() {
		if(!(getDelegate().getPosX() > 0)) {
			throw new InvariantError("x est inferieur ou egal a zero, impossible");
		}
		if(!(getDelegate().getPosX() < getDelegate().getEnv().getHeight())) {
			throw new InvariantError("x est superieur ou egal a la hauteur max, impossible");
		}
		if(!(getDelegate().getPosY() >= 0)) {
			throw new InvariantError("y est inferieur a 0, impossible");
		}
		if(!(getDelegate().getPosY() < getDelegate().getEnv().getWidth())) {
			throw new InvariantError("y est superieur ou egal a la largeur max, impossible");
		}
	}
	
	public EnvironmentService getEnv() {
		checkInvariants();
		EnvironmentService env = getDelegate().getEnv();
		checkInvariants();
		return env;
	}
	
	public int getPosX() {
		checkInvariants();
		int tmp = getDelegate().getPosX();
		checkInvariants();
		return tmp;
	}
	
	public int getPosY() {
		checkInvariants();
		int tmp = getDelegate().getPosY();
		checkInvariants();
		return tmp;
	}
	
	
	public void init(int x, int y, EnvironmentService env) {
		if(env == null) {
			throw new PreconditionError("Environnement null");
		}
		if(!(x>0)) {
			throw new PreconditionError("X trop petit");
		}
		if(!(x<env.getHeight())) {
			throw new PreconditionError("X trop grand");
		}
		if(!(y>=0)) {
			throw new PreconditionError("Y trop petit");
		}
		if(!(y<env.getWidth())) {
			throw new PreconditionError("Y trop grand");
		}
		
		boolean charDown = false;
		for(EntityService e : env.getCellContent(x-1, y)) {
			if(e instanceof CharacterService) {
				charDown=true;
			}
		}
		
		if(!((env.getCellNature(x-1, y)== Cell.PLT || (env.getCellNature(x-1, y) == Cell.HOL && charDown || env.getCellNature(x-1, y)== Cell.MTL)) && env.getCellNature(x, y)== Cell.EMP)){
			throw new PreconditionError("on ne peut pas placer une entite ici, la case en dessous n'est pas PLT ou HOL avec un personnage ou MTL ou une porte, ou la case n'est pas EMP");
		}
		
		getDelegate().init(x, y, env);
	
		checkInvariants();
		
		if(!(getDelegate().getPosX()==x)) {
			throw new PostconditionError("X incorrect");
		}
		if(!(getDelegate().getPosY()==y)) {
			throw new PostconditionError("Y incorrect");
		}
	}

}
