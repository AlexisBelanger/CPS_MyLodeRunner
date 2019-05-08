package Contract;

import Decorators.ScreenDecorator;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.ScreenService;
import Types.Cell;

public class ScreenContract extends ScreenDecorator implements ScreenService{

	public ScreenContract(ScreenService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		/* DOES NOTHING */
	}
	
	//INIT
	public void init(int h, int w) {
		//Preconditions
		if(!(h>0)) {
			throw new PreconditionError("la hauteur est nulle ou negative");
		}
		
		if(!(w>0)) {
			throw new PreconditionError("la largeur est nulle ou negative");
		}
		
		//Invariants
		
		//Actions
		super.init(h, w);
		
		//Invariants
		checkInvariant();
		
		//Postconditions
		if(!(getHeight()==h)) {
			throw new PostconditionError("Erreur initilaisation hauteur");
		}
		if(!(getWidth()==w)) {
			throw new PostconditionError("Erreur initialisation largeur");
		}
		for(int i=0; i< getDelegate().getHeight(); i++) {
			for( int j=0; j<getDelegate().getWidth(); j++) {
				if(!(getDelegate().getCellNature(i,j)== Cell.EMP)) {
					throw new PostconditionError("case ("+i+", "+j+") mal initialisée");
				}
			}
		}
	}
	
	public Cell getCellNature(int n, int m) {
		//Preconditions
		if((!(n>=0 && n< getDelegate().getHeight())) || (!(m>=0 && m< getDelegate().getWidth()))){
			throw new PreconditionError("La case demandée n'existe pas !");
		}
		
		//Invariants
		checkInvariant();
		
		//Action
		Cell c = super.getCellNature(n, m);
		
		//Invariants
		checkInvariant();
		
		return c;
	}
	
	public void dig(int n, int m) {
		//Preconditions
		boolean charUp=false;
		if(!(getDelegate().getCellNature(n,m) == Cell.PLT)) {
			throw new PreconditionError("La case n'est pas creusable");
		}
		
		if(!(getDelegate().getCellNature(n+1, m)== Cell.EMP)) {
			throw new PreconditionError("La case au dessus n'est pas libre, impossible de creuser");
		}

		
		//Invariants
		checkInvariant();
		
		//données pour post conditions
		Cell[][] screen_atPre = new Cell[getDelegate().getHeight()][getDelegate().getWidth()];
		for(int i=0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				screen_atPre[i][j]=getDelegate().getCellNature(i,j);
			}
		}
		
		//Action
		super.dig(n, m);
		
		//Invariants
		checkInvariant();
		
		//PostConditions 
		if(!(getDelegate().getCellNature(n,m) == Cell.HOL)) {
			throw new PostconditionError("La case n'a pas ete creusee");
		}
		
		
		for(int i=0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				if(i!=n && j!=m && getDelegate().getCellNature(i,j) != screen_atPre[i][j]) {
					throw new PostconditionError("Case creusee n'aurait pas du l'etre");
				}	
			}
		}
	}
	
	public void fill(int n, int m) {
		//Preconditions
		if(!(getDelegate().getCellNature(n,m) == Cell.HOL)) {
			throw new PreconditionError("La case n'est pas un trou");
		}
		
		//Invariants
		checkInvariant();
		
		//données pour post conditions
				Cell[][] screen_atPre = new Cell[getDelegate().getHeight()][getDelegate().getWidth()];
				for(int i=0; i<getDelegate().getHeight(); i++) {
					for(int j=0; j<getDelegate().getWidth(); j++) {
						screen_atPre[i][j]=getDelegate().getCellNature(i,j);
					}
				}
		
		//Action
		super.fill(n, m);
		
		//Invariants
		checkInvariant();
		
		//PostConditions 
		if(!(getDelegate().getCellNature(n,m) == Cell.PLT)) {
			throw new PostconditionError("La case n'a pas ete rebouchee");
		}
		
		for(int i=0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				if(i!=n && j!=m && getDelegate().getCellNature(i,j) != screen_atPre[i][j]) {
					throw new PostconditionError("Case remplie n'aurait pas du l'etre");
				}	
			}
		}
	}
	
	
	

}
