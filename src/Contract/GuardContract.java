package Contract;

import java.util.ArrayList;
import java.util.Arrays;

import Errors.InvariantError;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.TreasureService;
import Types.Cell;
import Types.Move;

public class GuardContract extends CharacterContract implements GuardService{

	public GuardContract(CharacterService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		super.checkInvariants();
		if(getDelegate().getPosX()<=0 || getDelegate().getPosX()>=getDelegate().getEnv().getHeight() ) {
			throw new InvariantError("PosX n'est plus bon");
		}
		if(getDelegate().getPosY()<0 || getDelegate().getPosY()>=getDelegate().getEnv().getWidth() ) {
			throw new InvariantError("PosX n'est plus bon");
		}
	}
	
	public GuardService getDelegate() {
		return (GuardService) super.getDelegate();
	} 
	@Override
	public int getId() {
		checkInvariant();
		int id = getDelegate().getId();
		checkInvariant();
		return id;
	}

	@Override
	public Move getBehaviour() {
		checkInvariant();
		Move m = getDelegate().getBehaviour();
		checkInvariant();
		return m;
	}

	@Override
	public CharacterService getTarget() {
		checkInvariant();
		CharacterService c = getDelegate().getTarget();
		checkInvariant();
		return c;
	}

	@Override
	public int getTimeInHole() {
		checkInvariant();
		int t = getDelegate().getTimeInHole();
		checkInvariant();
		return t;
	}

	@Override
	public void climbLeft() {
		if(!(getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()) == Cell.HOL)) {
			throw new PreconditionError("Il faut etre dans un trou pour grimper a gauche");
		}
		checkInvariant();
		int x = getDelegate().getPosX();
		int y = getDelegate().getPosY();
		boolean charUpLeft = false;
		if(y>0) {
			for(EntityService e : getDelegate().getEnv().getCellContent(x+1, y-1)) {
				if(e instanceof CharacterService) {
					charUpLeft = true;
				}
			}
		}
		boolean charTop = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(x+1, y)){
			if(e instanceof CharacterService){
				charTop = true;
				break;
			}
		}
		
		getDelegate().climbLeft();
		
		checkInvariant();
		
		if(y==0 && (!(getDelegate().getPosX() == x && getDelegate().getPosY()==y))) {
			throw new PostconditionError("impossible de climb vers la gauche quand on est contre le mur");
		}
		
		if(y>0) {
			if((getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.PLT || getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.MTL || getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.DOR) &&
					(!(getDelegate().getPosX() == x && getDelegate().getPosY()==y))) {
				throw new PostconditionError("La case diagonale haut-gauche est encombree (PLT ou MTL ou porte)");
				
			}
			
			if(charUpLeft && (!(getDelegate().getPosX() == x && getDelegate().getPosY()==y)) ) {
				throw new PostconditionError("La case diagonale haut-gauche est encombree par un personnage");
			}
			
			if(y > 0 && !charUpLeft && !charTop && !(getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.PLT || getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.MTL || getDelegate().getEnv().getCellNature(x+1, y-1) == Cell.DOR) && !(getDelegate().getPosX()==x+1 && getDelegate().getPosY()==y-1) ) {
				throw new PostconditionError("Le garde aurait du climb left mais ne l'a pas fait");
			}	
		}
		
		System.out.println(charTop);
		if(charTop && getDelegate().getPosX() != x && getDelegate().getPosY()!=y){
			throw new PostconditionError("Le garde a escalade a travers un personnage au dessus de lui");
		}
	}

	@Override
	public void climbRight() {
		
		if(!(getDelegate().getEnv().getCellNature(getDelegate().getPosX(), getDelegate().getPosY()) == Cell.HOL)) {
			throw new PreconditionError("Il faut etre dans un trou pour grimper a droite");
		}
		checkInvariant();
		int x = getDelegate().getPosX();
		int y = getDelegate().getPosY();
		boolean charUpRight = false;
		if(y < getDelegate().getEnv().getWidth()-1) {
			for(EntityService e : getDelegate().getEnv().getCellContent(x+1, y+1)) {
				if(e instanceof CharacterService) {
					charUpRight = true;
				}
			}
		}
		
		boolean charTop = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(x+1, y)){
			if(e instanceof CharacterService){
				charTop = true;
				break;
			}
		}
		getDelegate().climbRight();
		
		checkInvariant();
		
		if(y==getDelegate().getEnv().getWidth()-1 && (!(getDelegate().getPosX() == x && getDelegate().getPosY()==y))) {
			throw new PostconditionError("impossible de climb vers la droite quand on est contre le mur");
		}
		
		if(y < getDelegate().getEnv().getWidth()-1) {
			if((getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.PLT || getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.MTL || getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.DOR) &&
					(!(getDelegate().getPosX() == x && getDelegate().getPosY()==y))) {
				throw new PostconditionError("La case diagonale haut-droite est encombree (PLT ou MTL ou porte)");
				
			}
			
			
			if(charUpRight&& (!(getDelegate().getPosX() == x && getDelegate().getPosY()==y)) ) {
				throw new PostconditionError("La case diagonale haut-droite est encombree par un personnage");
			}
			
			if(y < getDelegate().getEnv().getWidth()-1 && !charUpRight && !charTop && !(getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.PLT || getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.DOR || getDelegate().getEnv().getCellNature(x+1, y+1) == Cell.MTL) && !(getDelegate().getPosX()==x+1 && getDelegate().getPosY()==y+1) ) {
				throw new PostconditionError("Le garde aurait du climb left mais ne l'a pas fait");
			}	
		}
		
		if(charTop && getDelegate().getPosX() != x && getDelegate().getPosY()!=y){
			throw new PostconditionError("Le garde a escalade a travers un personnage au dessus de lui");
		}
	}

	
	
	
	
	
	
	
	@Override
	public void step() {
		
		checkInvariant();
		int posX=getDelegate().getPosX();
		int posY = getDelegate().getPosY();
		EnvironmentService env = getDelegate().getEnv();
		CharacterService target = getDelegate().getTarget();
		boolean charDown = false;
		for(EntityService e : env.getCellContent(posX-1, posY)) {
			if(e instanceof CharacterService) {
				charDown = true;
			}
		}
		getDelegate().step();
		Move behaviour = getDelegate().getBehaviour();
		checkInvariant();
		if(env.getCellNature(posX, posY)!=Cell.HDR && env.getCellNature(posX, posY)!= Cell.LAD && env.getCellNature(posX, posY)!= Cell.HOL && ((env.getCellNature(posX-1, posY)==Cell.HDR || env.getCellNature(posX-1, posY)== Cell.EMP|| env.getCellNature(posX-1, posY)== Cell.HOL) && !charDown) ) {
			if(!( behaviour == Move.Down)) {
				throw new PostconditionError("Guard aurait du tomber pendant ce step");
			}
		}else{
			Cell[] l = {Cell.PLT, Cell.MTL, Cell.DOR};
			ArrayList<Cell> libre = new ArrayList<Cell>(Arrays.asList(l)); 
			if(env.getCellNature(posX, posY) != Cell.HOL) {
				if(posX<target.getPosX() && env.getCellNature(posX, posY)== Cell.LAD) {
					if(!(behaviour == Move.Up)){
						throw new PostconditionError("Garde aurait du monter a l'echelle");
					}
				}else {
					if(posX>target.getPosX() && (env.getCellNature(posX-1, posY)== Cell.LAD || (env.getCellNature(posX, posY)== Cell.HDR && !charDown && !libre.contains(env.getCellNature(posX-1, posY)))) ) {
						if(!(behaviour == Move.Down)){
							throw new PostconditionError("Garde aurait du descendre l'echelle");
						}
					}else {
						if( env.getCellNature(posX, posY)==Cell.HDR || libre.contains(env.getCellNature(posX-1, posY)) ||
								((!libre.contains(env.getCellNature(posX-1, posY))) && charDown )){
							if(posY>target.getPosY() ) {
								if(!(behaviour ==Move.Left)){
									throw new PostconditionError("Le garde aurait du aller a gauche");
								}
								
							}else {
								if(posY<target.getPosY()){
									if(!(behaviour ==Move.Right)) {
										throw new PostconditionError("Le garde aurait du aller a droite");
									}
								}
							}
						}else {
							
							if(env.getCellNature(posX, posY) == Cell.LAD ) {
								if(Math.abs(posY-target.getPosY())< Math.abs(posX-target.getPosX()) ) {
									if(posX>target.getPosX() ) {
										if(!(behaviour == Move.Down)) {
											throw new PostconditionError("Le garde aurait du descendre");
										}
										
									}else {
										if(!(behaviour == Move.Up)) {
											throw new PostconditionError("Le garde aurait du monter");
										}
									}
								}else {
									if(posY>target.getPosY() ) {
										if(libre.contains(env.getCellNature(posX,posY-1))) {
											if(posX>target.getPosX()) {
												if(behaviour != Move.Down) {
													throw new PostconditionError("Le garde aurait du descendre");
												}
						
											}else {
												if(behaviour != Move.Up) {
													throw new PostconditionError("Le garde aurait du monter");
												}
											}
										}else {
											if(behaviour != Move.Left) {
												throw new PostconditionError("Le garde aurait du aller a gauche");
											}
										}
									}else {
										if(libre.contains(env.getCellNature(posX,posY+1))) {
											if(posX>target.getPosX()) {
												if(behaviour != Move.Down) {
													throw new PostconditionError("Le garde aurait du descendre");
												}
											}else {
												if(behaviour != Move.Up) {
													throw new PostconditionError("Le garde aurait du monter");
												}
											}
										}else {
											if(behaviour != Move.Right) {
												throw new PostconditionError("Le garde aurait du aller a droite");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}	
	}
	
	
	
	

	@Override
	public void init(int x, int y, EnvironmentService s, EngineService eng) {
		if(x<0) {
			throw new PreconditionError("x negatif, erreur");
		}
		if(x>= s.getHeight()) {
			throw new PreconditionError("x trop grand, erreur");
		}
		
		if(y<0) {
			throw new PreconditionError("y negatif, erreur");
		}
		if(y>= s.getWidth()) {
			throw new PreconditionError("y trop grand, erreur");
		}
		
		if(s.getCellNature(x, y)== Cell.PLT || s.getCellNature(x, y)== Cell.MTL  || s.getCellNature(x, y)== Cell.DOR){
			throw new PreconditionError("Impossible d'init le guarde ici, c'est une PLT ou un MTL ou une porte");
		}
		
		
		
		if(eng.getEnv().getCellNature(x-1, y)!= Cell.MTL && eng.getEnv().getCellNature(x-1, y)!= Cell.PLT) {
			throw new PreconditionError("Case en dessous invalide pour l'initialisation");
		}
		
		getDelegate().init(x, y, s, eng);

		checkInvariant();
		
		//POST

		
		if(getDelegate().getPosX() != x) {
			throw new PostconditionError("X mal initialise");
		}
		if(getDelegate().getPosY() != y) {
			throw new PostconditionError("Y mal initialise");
		}
		
		if(getDelegate().getEnv() != s) {
			throw new PostconditionError("Env mal initilaise ");
		}

	}

	@Override
	public void die() {
		getDelegate().die();
	}

	@Override
	public boolean isDead() {
		return getDelegate().isDead();
	}

	@Override
	public TreasureService hasTreasure() {
		return getDelegate().hasTreasure();
	}

	@Override
	public void takeTreasure(int x, int y) {
		if(x<=0 ||x>= getDelegate().getEnv().getHeight()) {
			throw new PreconditionError("impossible de prendre dans cette case X");
		}
		if(y<0 ||y>= getDelegate().getEnv().getWidth()) {
			throw new PreconditionError("impossible de prendre dans cette case Y");
		}
		TreasureService t = null;
		for(EntityService e : getDelegate().getEnv().getCellContent(x,y)) {
			if(e instanceof TreasureService) {
				t=(TreasureService)e;
				break;
			}
		}
		ArrayList<EntityService>[][] tmp = new ArrayList[getDelegate().getEnv().getHeight()][getDelegate().getEnv().getWidth()];
		int size_atpre = getDelegate().getEnv().getCellContent(x,y).size();
		
		TreasureService tres_atpre = getDelegate().hasTreasure();
		
		for(int i=0; i< getDelegate().getEnv().getHeight(); i++) {
			for(int j=0; j< getDelegate().getEnv().getWidth(); j++) {
				tmp[i][j]= getDelegate().getEnv().getCellContent(i, j);
			}
			
		}
		
		
		
		if(t==null) {
			throw new PreconditionError("Pas de tresor a ramasser, aucune raison de lancer la methode");
		}
		
		checkInvariant();
		getDelegate().takeTreasure(x,y);
		checkInvariant();
		
		int size = getDelegate().getEnv().getCellContent(x,y).size();
		if(tres_atpre != null && !(getDelegate().hasTreasure()==t && size == size_atpre-1)) {
			throw new PostconditionError("Tresor pas ramasse par le garde");
		}
		
		for(int i=0; i< getDelegate().getEnv().getHeight(); i++) {
			for(int j=0; j< getDelegate().getEnv().getWidth(); j++) {
				if(tmp[i][j] != getDelegate().getEnv().getCellContent(i, j)) {
					throw new PostconditionError("Contenu d'une autre case changee, erreur ");
				}
			}
			
		}
		
	}

	@Override
	public void releaseTreasure() {
		int x = getDelegate().getPosX();
		int y = getDelegate().getPosY();
		TreasureService t_atpre = getDelegate().hasTreasure();
		if(getDelegate().getEnv().getCellNature( x+1, y)!= Cell.EMP || getDelegate().getEnv().getCellNature( x, y)!= Cell.HOL || getDelegate().hasTreasure() == null) {
			throw new PreconditionError("Pas de raison d'apeller cette methode, le garde n'est pas dans un trou ou n'a pas de tresor");
		}
		
		
		checkInvariant();
		getDelegate().releaseTreasure();
		checkInvariant();
		
		if(!(getDelegate().getEnv().getCellContent(x+1, y).contains(t_atpre) && getDelegate().hasTreasure() == null)) {
			throw new PostconditionError("Le tresor n'a pas ete place au dessus du trou");
		}
	}
	
	


}
