package Contract;

import java.util.ArrayList;

import Decorators.EnvironmentDecorator;
import Errors.InvariantError;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.CharacterService;
import Service.EditableScreenService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.ScreenService;
import Service.TreasureService;
import Types.Cell;

public class EnvironmentContract extends ScreenContract implements EnvironmentService{

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}
	
	
	public void checkInvariant() {
		super.checkInvariant();
		
		
		for(int i =0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				if(getDelegate().getCellContent(i, j).size()>0 && (getDelegate().getCellNature(i, j)== Cell.MTL || getDelegate().getCellNature(i, j)== Cell.PLT || getDelegate().getCellNature(i, j)== Cell.DOR )) {
					throw new PreconditionError("Case de metal ou de plateforme ou porte contient quelque chose");
				}
			}
		}
		
		
		for(int i =1; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				boolean containsTres = false;
				for(EntityService e : getDelegate().getCellContent(i, j)) {
					if(e instanceof TreasureService) {
						containsTres = true;
					}
				}
				boolean charInHole = false;
				for(EntityService e : getDelegate().getCellContent(i-1, j)) {
					if(e instanceof CharacterService) {
						charInHole = true;
					}
				}
				if(containsTres && !(getDelegate().getCellNature(i, j)==Cell.EMP && (getDelegate().getCellNature(i-1, j)==Cell.MTL || getDelegate().getCellNature(i-1, j)==Cell.PLT || (getDelegate().getCellNature(i-1, j)==Cell.HOL && charInHole)))) {
					throw new PostconditionError("tresor ne devrait pas se trouve en ("+i+" , "+j+")");
				}
			}
		}
		
	}

	@Override
	public ArrayList<EntityService> getCellContent(int n, int m) {
		//Preconditions
		if(!(n>=0 || n<getDelegate().getHeight())) {
			throw new PreconditionError("N incorrect");
		}
		
		if(!(m>=0 || m<getDelegate().getWidth())) {
			throw new PreconditionError("M incorrect");
		}
		//Invariants
		
		ArrayList<EntityService> tmp =  getDelegate().getCellContent(n, m);
		
		checkInvariant();
		
		return tmp;
	}
	
	
	@Override
	public void moveCellContent(int x, int y, EntityService entity) {
	
		if(!(x>0)) {
			throw new PreconditionError("destination X impossible en bas");	
		}
		if(!(x<getDelegate().getHeight()-1)) {
			throw new PreconditionError("destination X impossible en haut");	
		}
		if(!(y>=0)) {
			throw new PreconditionError("destination Y impossible a gauche");	
		}
		if(!(y<getDelegate().getWidth()-1)) {
			throw new PreconditionError("destination Y impossible a droite");	
		}
		checkInvariant();
		getDelegate().moveCellContent(x, y, entity);
		checkInvariant();
		if(!(getDelegate().getCellContent(x, y).contains(entity))) {
			throw new PostconditionError("Entite mal deplacee");
		}
	}
	
	
	public void init(EditableScreenService es) {
		
		
		
		
		getDelegate().init(es);
		
		checkInvariant();
		
		for(int i=0; i<es.getHeight(); i++) {
			for(int j=0; j<es.getWidth();j++) {
				if(!(getDelegate().getCellContent(i, j).size() == 0)) {
					throw new PostconditionError("Mauvaise initialisation du contenu des cases");
				}
			}
		}
		
		
	}
	
	
	public void dig(int n, int m) {
		if(getDelegate().getCellContent(n, m).size()>0) {
			throw new  PreconditionError("Il y a une entite dans la case au dessus, impossible de creuser");
		}
		checkInvariant();
		super.dig(n, m);
		checkInvariant();
	}
	
	public void fill(int n, int m) {
		checkInvariant();
		super.fill(n, m);
		checkInvariant();
	}
	
	public void openDoor(int n, int m) {
		if(n<=0 || n>=getDelegate().getHeight()) {
			throw new PreconditionError("coordonnees d'ouverture incorrects en X");
		}
		if(m<0 || m>=getDelegate().getWidth()) {
			throw new PreconditionError("coordonnees d'ouverture incorrects en Y");
		}
		checkInvariant();
		getDelegate().openDoor(n, m);
		checkInvariant();
		
		if(getDelegate().getCellNature(n, m)!= Cell.EMP) {
			throw new PostconditionError("Porte pas ouverte");
		}
	}
	
	
	public EnvironmentService getDelegate() {
		return (EnvironmentService) super.getDelegate();
	}
	




}
