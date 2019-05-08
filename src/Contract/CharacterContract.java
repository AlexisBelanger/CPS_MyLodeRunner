package Contract;


import Errors.InvariantError;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EntityService;
import Service.EnvironmentService;
import Types.Cell;

public class CharacterContract extends EntityContract implements CharacterService {

	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		super.checkInvariants();
		if(!(getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY())== Cell.EMP ||
				getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY())== Cell.HOL ||
				getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY())== Cell.LAD ||
				getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY())== Cell.HDR)){
			throw new InvariantError("Personnage dans une case interdite");
		}	

	}
	
	public void init(int x, int y, EnvironmentService env) {

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
			throw new PreconditionError("on ne peut pas placer un character ici, la case en dessous n'est pas PLT ou HOL avec un personnage ou MTL ou une porte, ou la case n'est pas EMP");
		}
		getDelegate().init(x, y, env);
		checkInvariants();
		if(getDelegate().getEnv().getCellNature(x, y) != Cell.EMP) {
			throw new InvariantError("La case d'init n'est pas vide !");
		}
		
		if(getDelegate().getPosX()!=x) {
			throw new PostconditionError("X mal initilaise ! :(");
		}
		if(getDelegate().getPosY()!=y) {
			throw new PostconditionError("Y mal initilaise ! :(");
		}
	}

	public CharacterService getDelegate() {
		return (CharacterService)super.getDelegate();
	}
	
	public void goLeft() {
		checkInvariants();
		int posx = getDelegate().getPosX();
		int posy = getDelegate().getPosY();
		
		getDelegate().goLeft();
		checkInvariants();
		
		if(posx != getDelegate().getPosX()) {
			throw new PostconditionError("X modifie, n'aurait pas du arriver");
		}
		if(posy == 0 && getDelegate().getPosY()!=0) {
			throw new PostconditionError("Y aurait pas du bouger car contre le mur gauche");
		}
		if(posy > 0 && (getDelegate().getEnv().getCellNature(posx, posy-1) == Cell.MTL ||
				getDelegate().getEnv().getCellNature(posx, posy-1) == Cell.PLT || getDelegate().getEnv().getCellNature(posx, posy-1) == Cell.DOR ) && posy != getDelegate().getPosY() ) {
			throw new PostconditionError("Y a bouge mais aurait pas du car bloc infranchissable a gauche");			
		}
			
		if(((getDelegate().getEnv().getCellNature(posx, posy) != Cell.LAD &&
				getDelegate().getEnv().getCellNature(posx, posy) != Cell.HDR) &&
				(getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.LAD && 
						getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.MTL &&
								getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.DOR &&
						getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.PLT))) {
			boolean charHere = false;
			for(EntityService e : getDelegate().getEnv().getCellContent(posx-1, posy)) {
				if(e instanceof CharacterService) {
					charHere = true;
				}
				
			}
			if(!charHere && posy != getDelegate().getPosY()) {
				throw new PostconditionError("Tu ne peux pas voler vers la gauche enfin !");
				
			}
		}
		
		if(posy != 0) {
			if(getDelegate().getEnv().getCellNature(posx, posy-1) != Cell.MTL && getDelegate().getEnv().getCellNature(posx, posy-1) != Cell.PLT && getDelegate().getEnv().getCellNature(posx, posy-1) != Cell.DOR ) {
				boolean charHereDown=false;
				for(EntityService e : getDelegate().getEnv().getCellContent(posx-1, posy)) {	
					if((getDelegate().getEnv().getCellNature(posx, posy) == Cell.LAD || getDelegate().getEnv().getCellNature(posx, posy) == Cell.HDR) 	||
							(getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.PLT || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.MTL || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.LAD || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.DOR ) ||
						e instanceof CharacterService) {
						charHereDown=true;
					}
				}
				boolean charHereLeft=false;
				for(EntityService e : getDelegate().getEnv().getCellContent(posx, posy-1)) {
					if(e instanceof CharacterService) {
						charHereLeft=false;
					}
				}
				if(charHereDown && charHereLeft && getDelegate().getPosY() != posy-1) {
					throw new PostconditionError("Pas bougé a gauche mais aurait du");
				}
			}
		}
	}
	
	public void goRight() {
		checkInvariants();
		int posx = getDelegate().getPosX();
		int posy = getDelegate().getPosY();
		getDelegate().goRight();
		checkInvariants();
		
		if(posx != getDelegate().getPosX()) {
			throw new PostconditionError("X modifie, n'aurait pas du arriver");
		}
		if(posy == getDelegate().getEnv().getWidth()-1 && getDelegate().getPosY()!=getDelegate().getEnv().getWidth()-1) {
			throw new PostconditionError("Y aurait pas du bouger car contre le mur droit");
		}
		if(posy< getDelegate().getEnv().getWidth()-1) {
			if((getDelegate().getEnv().getCellNature(posx, posy+1) == Cell.MTL ||
					getDelegate().getEnv().getCellNature(posx, posy+1) == Cell.PLT ||
					getDelegate().getEnv().getCellNature(posx, posy+1) == Cell.DOR) && posy != getDelegate().getPosY() ) {
				throw new PostconditionError("Y a bougé mais aurait pas du car bloc infranchissable a droite");			
			}
		}
		if(((getDelegate().getEnv().getCellNature(posx, posy) != Cell.LAD &&
				getDelegate().getEnv().getCellNature(posx, posy) != Cell.HDR) &&
				(getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.LAD && 
						getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.MTL && 
						getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.PLT && 
						getDelegate().getEnv().getCellNature(posx-1, posy) != Cell.DOR))) {
			boolean charHere = false;
			for(EntityService e : getDelegate().getEnv().getCellContent(posx-1, posy)) {
				if(e instanceof CharacterService) {
					charHere = true;
				}
				
			}
			if(!charHere && posy != getDelegate().getPosY()) {
				throw new PostconditionError("Tu ne peux pas voler vers la droite enfin !");
				
			}
		}
		
		

		
		if(posy < getDelegate().getEnv().getWidth()-1) {
			if(getDelegate().getEnv().getCellNature(posx, posy+1) != Cell.MTL && getDelegate().getEnv().getCellNature(posx, posy+1) != Cell.PLT&& getDelegate().getEnv().getCellNature(posx, posy+1) != Cell.DOR ) {
				boolean charHereDown=false;
				for(EntityService e : getDelegate().getEnv().getCellContent(posx-1, posy)) {	
					if((getDelegate().getEnv().getCellNature(posx, posy) == Cell.LAD || getDelegate().getEnv().getCellNature(posx, posy) == Cell.HDR) 	||
							(getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.PLT || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.MTL || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.LAD  || getDelegate().getEnv().getCellNature(posx-1, posy) == Cell.DOR ) ||
						e instanceof CharacterService) {
						charHereDown=true;
					}
				}
			
				if(charHereDown && getDelegate().getPosY() != posy+1) {
					throw new PostconditionError("Pas bougé a gauche mais aurait du");
				}
			}
		}
	}
	
	public void goUp() {
		checkInvariants();
		int posy = getDelegate().getPosY();
		int posx = getDelegate().getPosX();
		boolean charInHole = false;
		if(posx<getDelegate().getPosX()-1) {
			for(EntityService e : getDelegate().getEnv().getCellContent(posx+1, posy)) {
				if(e instanceof CharacterService) {
					charInHole = true;
				}
			}
		}
		getDelegate().goUp();
		checkInvariants();
		
		
		if(posy != getDelegate().getPosY()) {
			throw new PostconditionError("Y a change mais n'aurait pas du");
		}
		
		if(posx == getDelegate().getEnv().getHeight()-1 && !(getDelegate().getPosX()==posx)) {
			throw new PostconditionError("Passe a travers le plafond");
		}
		if(posx<getDelegate().getPosX()-1) {
			 if((getDelegate().getEnv().getCellNature(posx, posy)!= Cell.LAD ||
					 (getDelegate().getEnv().getCellNature(posx, posy)== Cell.LAD &&
							(getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.PLT || getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.MTL|| getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.DOR) ||
							(getDelegate().getEnv().getCellNature(posx+1, posy) == Cell.HOL && charInHole==true )
							) ) && ! (posx == getDelegate().getPosX())) {
				 throw new PostconditionError("perso est monte dans une case interdite");
				 
			 }
			 
			 if((getDelegate().getEnv().getCellNature(posx, posy)== Cell.LAD &&  
					 ((/*LAD EMP HDR*/getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.LAD ||getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.EMP || getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.HDR ) ||
							 ( getDelegate().getEnv().getCellNature(posx+1, posy)== Cell.HOL && charInHole==false) )  
					 ) && !(posx+1 == getDelegate().getPosX())) {
				 throw new PostconditionError("le perso aurait du monter");
				 
			 }
		}
	}
	
	public void goDown() {
		checkInvariants();
		int posy = getDelegate().getPosY();
		int posx = getDelegate().getPosX();
		boolean charInHole = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(posx-1, posy)) {
			if(e instanceof CharacterService) {
				charInHole = true;
			}
		}
		getDelegate().goDown();
		checkInvariants();
		
		if(posy != getDelegate().getPosY()) {
			throw new PostconditionError("Y a change mais n'aurait pas du");
		}
		
		if(posx==0 && !(getDelegate().getPosX()==0)) {
			throw new PostconditionError("le perso a traverse le sol");
		}
		
		if((getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.PLT || getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.MTL || getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.DOR ) && 
				!(posx == getDelegate().getPosX() )) {
			throw new PostconditionError("le perso a traverse une plateforme");
		}
		
		if((getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.HOL && charInHole) && !(posx == getDelegate().getPosX() )) {
			throw new PostconditionError("le perso est tombe dans un trou deja occupe");
		}
		
		if(((getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.HOL ||
				getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.EMP ||
				getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.LAD ||
				getDelegate().getEnv().getCellNature(posx-1,  posy)== Cell.HDR) && !charInHole) && !(posx-1 == getDelegate().getPosX())) {
			throw new PostconditionError("Le perso n'es pas descendu");
		}
		
		
	}
	
}
