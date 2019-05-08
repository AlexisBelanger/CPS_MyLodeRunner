package Contract;

import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.TreasureService;
import Types.Cell;
import Types.Item;

public class TreasureContract extends EntityContract implements TreasureService  {

	public TreasureContract(EntityService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	
	
	public TreasureService getDelegate() {
		return (TreasureService)super.getDelegate();
	}
	
	
	public void checkInvariant() {
		super.checkInvariants();
	}
	
	public void init(int h, int w, EnvironmentService env, Item type) {
		
		if(env == null) {
			throw new PreconditionError("Envrionnement null !");
		}
		if(h<=0) {
			throw new PreconditionError("X <= 0, impossible");
		}
		if(h>env.getHeight()-1) {
			throw new PreconditionError("X >= height de l'environnement, impossible");
		}
		if(w<0) {
			throw new PreconditionError("Y <= 0, impossible");
		}
		if(w>env.getWidth()-1) {
			throw new PreconditionError("Y >= width de l'environnement, impossible");
		}
		
		
		

		if(!((env.getCellNature(h-1, w)== Cell.PLT || env.getCellNature(h-1, w)== Cell.MTL || env.getCellNature(h-1, w)== Cell.DOR) && env.getCellNature(h, w)== Cell.EMP)) {
			throw new PreconditionError("Impossible de mettre un tresor ici ("+h+", "+w+")");
		}
		
		
		getDelegate().init(h,w,env, type);
		checkInvariant();
		
		if(getDelegate().getPosX() != h) {
			throw new PostconditionError("X mal initialise");
		}
		if(getDelegate().getPosY() != w) {
			throw new PostconditionError("Y mal initialise");
		}
		if(getDelegate().getType() != type) {
			throw new PostconditionError("Type mal initialise");
		}
		
		
	}


	@Override
	public Item getType() {
		return getDelegate().getType();
	}


	@Override
	public void setPosition(int x, int y) {
		if(x<=0 ||x>= getDelegate().getEnv().getHeight()) {
			throw new PreconditionError("impossible de placer un tresor dans cette case X");
		}
		if(y<0 ||y>= getDelegate().getEnv().getWidth()) {
			throw new PreconditionError("impossible de placer un tresor dans cette case Y");
		}
		if(!(getDelegate().getEnv().getCellNature(x, y)==Cell.EMP)) {
			throw new PreconditionError("La case n'est pas libre pour y placer le tresor");
		}
		boolean charDown = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(x-1, y)) {
			if(e instanceof CharacterService) {
				charDown = true;
			}
		}
		System.out.println(getDelegate().getEnv().getCellNature(x-1, y)+" "+charDown);
		if((getDelegate().getEnv().getCellNature(x-1, y) != Cell.PLT &&getDelegate().getEnv().getCellNature(x-1, y) != Cell.MTL) && !(getDelegate().getEnv().getCellNature(x-1, y) == Cell.HOL && charDown )) {
			throw new PreconditionError("Impossible de placer le tresor au dessus d'une case autre que PLT MTL ou HOL plein");
		}
		
		checkInvariant();
		
		getDelegate().setPosition(x, y);
		
		checkInvariant();
		
		if(getDelegate().getPosX()!=x || getDelegate().getPosY()!= y ) {
			throw new PostconditionError("Deplacement du tresor mal effectue");
		}
		
	}

}
