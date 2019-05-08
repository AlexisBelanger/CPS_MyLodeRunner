package Contract;

import java.util.ArrayList;
import java.util.Arrays;

import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Cell;
import Types.Command;
import Types.Item;
import Types.Orientation;

public class PlayerContract extends CharacterContract implements PlayerService {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	
	public void checkInvariants() {
		super.checkInvariants();
	}
	
	
	

	@Override
	public EngineService getEngine() {
		checkInvariants();
		EngineService e = getDelegate().getEngine();
		checkInvariants();
		return e;

		
	}

	@Override
	public void step() {
		int posX = getDelegate().getPosX();
		int posY = getDelegate().getPosY();
		int invSize = getDelegate().getInventory().size();
		int nbItemsLeft = 0;
		if(posY>0) {
			for(EntityService e : getDelegate().getEnv().getCellContent(posX, posY-1)) {
				if(e instanceof TreasureService) {
					if(((TreasureService)e).getType()!=Item.Potion) {
						nbItemsLeft++;
					}
				}
			}
		}
		int nbItemsRight = 0;
		if(posY< getDelegate().getEnv().getWidth()-1) {
			for(EntityService e : getDelegate().getEnv().getCellContent(posX, posY+1)) {
				if(e instanceof TreasureService) {
					if(((TreasureService)e).getType()!=Item.Potion) {
						nbItemsRight++;
					}
				}
			}
		}
		
		
		checkInvariants();
		getDelegate().step();
		checkInvariants();
		
		
		int posYnow = getDelegate().getPosY();
		
		
		
		
		if(getDelegate().getOrientation() == Orientation.Left && posY!=posYnow && getDelegate().getInventory().size()!=nbItemsLeft+invSize) {
			throw new PostconditionError("Le personnage n'a pas bien ramass� le contenu de la case a gauche");
		}
		if(getDelegate().getOrientation() == Orientation.Right && posY!=posYnow && getDelegate().getInventory().size()!=nbItemsRight+invSize) {
			throw new PostconditionError("Le personnage n'a pas bien ramass� le contenu de la case a droite");
		}
		
		
		
		
	}

	@Override
	public void init(int x, int y, EnvironmentService s, EngineService engine) {
		if(engine == null) {
			throw new PreconditionError("Engine est null");
		}

		if(!(x>0)) {
			throw new PreconditionError("X trop petit (player)");
		}
		if(!(x<s.getHeight())) {
			throw new PreconditionError("X trop grand (player)");
		}
		if(!(y>=0)) {
			throw new PreconditionError("Y trop petit (player)");
		}
		if(!(y<s.getWidth())) {
			throw new PreconditionError("Y trop grand (player)");
		}
		
		boolean charDown = false;
		for(EntityService e : s.getCellContent(x-1, y)) {
			if(e instanceof CharacterService) {
				charDown=true;
			}
		}
		
		if(!((s.getCellNature(x-1, y)== Cell.PLT || s.getCellNature(x-1, y)== Cell.DOR || (s.getCellNature(x-1, y) == Cell.HOL && charDown) || s.getCellNature(x-1, y)== Cell.MTL) && s.getCellNature(x, y)== Cell.EMP)){
			throw new PreconditionError("on ne peut pas placer un player ici, la case en dessous n'est pas PLT ou HOL avec un personnage ou MTL ou une porte, ou la case n'est pas EMP");
		}
		
		getDelegate().init(x, y, s, engine);
		
		if(getDelegate().getEngine()!= engine) {
			throw new PostconditionError("engine mal init");
		}
		
		if(getDelegate().getPosX()!=x) {
			throw new PostconditionError("X mal initilaise ! :(");
		}
		if(getDelegate().getPosY()!=y) {
			throw new PostconditionError("Y mal initilaise ! :(");
		}
		
		if(getDelegate().getInventory().size()!=0) {
			throw new PostconditionError("l'inventaire n'est pas vide a l'initialisation...");
		}
		
		checkInvariants();
		
	}
	
	public PlayerService getDelegate() {
		return (PlayerService) super.getDelegate();
	}


	@Override
	public ArrayList<TreasureService> getInventory() {
		return getDelegate().getInventory();
	}


	@Override
	public Orientation getOrientation() {
		return getDelegate().getOrientation();
	}


	@Override
	public void openDoor() {
		checkInvariants();
		boolean isKey = false;
		TreasureService key = null;
		for(TreasureService t : getDelegate().getInventory()) {
			if(t.getType()==Item.Key) {
				key=t;
				isKey=true;
				break;
			}
		}
		Cell c=null;
		if(getDelegate().getOrientation() == Orientation.Left && getDelegate().getPosY()>0) {
			c = getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1);
		}
		if(getDelegate().getOrientation() == Orientation.Right && getDelegate().getPosY()< getDelegate().getEnv().getWidth()-1) {
			c = getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1);
		}
		getDelegate().openDoor();
		
		checkInvariants();
		
		if(getDelegate().getOrientation() ==Orientation.Left) {
			if(getDelegate().getPosY()>0) {
				if(getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1)!= Cell.DOR && isKey && 
						!getDelegate().getInventory().contains(key)) {
					throw new PostconditionError("cle perdue alors que pas de porte a ouvrir a gauche");
				}
				if(!isKey && c==Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1) != Cell.DOR) {
					throw new PostconditionError("La porte a gauche s'est ouverte sans cle...");
				}
				if(c!=Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1) != c ) {
					throw new PostconditionError("La case a gauche n'est pas une porte mais a ete change quand meme");
				}
				
				if(isKey && c==Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()-1) != Cell.EMP) {
					throw new PostconditionError("La porte a gauche ne s'est pas ouverte...");
				}
			}
		}
		
		
		
		if(getDelegate().getOrientation() ==Orientation.Right) {
			if(getDelegate().getPosY()<getDelegate().getEnv().getWidth()-1) {
				if(getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()+1)!= Cell.DOR && isKey && 
						!getDelegate().getInventory().contains(key)) {
					throw new PostconditionError("cle perdue alors que pas de porte a ouvrir a droite");
				}
				if(!isKey && c==Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()+1) != Cell.DOR) {
					throw new PostconditionError("La porte a droite s'est ouverte sans cle...");
				}
				if(c!=Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()+1) != c ) {
					throw new PostconditionError("La case a droite n'est pas une porte mais a ete change quand meme");
				}
				
				if(isKey && c==Cell.DOR && getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()+1) != Cell.EMP) {
					throw new PostconditionError("La porte a droite ne s'est pas ouverte...");
				}
			}
		}
	}
}
