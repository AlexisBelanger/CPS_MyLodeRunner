package Contract;

import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.EditableScreenService;
import Service.ScreenService;
import Types.Cell;

public class EditableScreenContract extends ScreenContract implements EditableScreenService {

	public EditableScreenContract(ScreenService delegate) {
		super(delegate);
	}
	
	
	public void init(int h, int w) {
		
		if(h<=0) {
			throw new PreconditionError("h inferieur a zero");
		}
		if(w<=0) {
			throw new PreconditionError("w inferieur a zero");
		}
		getDelegate().init(h, w);
		checkInvariant();
		
		if(getDelegate().getHeight() != h) {
			throw new PostconditionError("h mal init");
		}
		if(getDelegate().getWidth() != w) {
			throw new PostconditionError("w mal init");
		}
	}
	
	
	public void checkInvariant() {
		super.checkInvariant();
	}

	@Override
	public boolean playable() {
		
		
		
		checkInvariant();
		boolean b = getDelegate().playable();
		checkInvariant();
		
		
		/*postconditions*/
		boolean tmp = true;
		for(int i = 0; i< getDelegate().getWidth(); i++) {
			if(getDelegate().getCellNature(0, i)!=Cell.MTL) {
				tmp=false;
			}
		}
		for(int i = 0; i< getDelegate().getHeight(); i++) {
			for(int j = 0; j< getDelegate().getWidth(); j++) {
				if(getDelegate().getCellNature(0, 1)==Cell.HOL) {
					tmp=false;
				}
			}
		}
			if(tmp!=b) {
				throw new PostconditionError("Mauvais resultat pour playable");
			}
		
		return b;
	}

	@Override
	public void setNature(int n, int m, Cell c) {
		//Preconditions
		if(n==0) {
			throw new PreconditionError("Interdiction de modifier la ligne la plus en bas");
		}
		if((!(n>=0 && n < getDelegate().getHeight())) || (!(m>=0 && m < getDelegate().getWidth()))) {
			throw new PreconditionError("la case ("+n+", "+m+") n'existe pas");
		}
		
		//données pour post conditions
		Cell[][] screen_atPre = new Cell[getDelegate().getHeight()][getDelegate().getWidth()];
		for(int i=0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				screen_atPre[i][j]=getDelegate().getCellNature(i,j);
			}
		}

		
		//invariants
		checkInvariant();
		
		
		
		//Action
		getDelegate().setNature(n, m, c);
		
		checkInvariant();
		
		
		
		//Postconditions
		if(!(getDelegate().getCellNature(n, m) == c)) {
			throw new PostconditionError("Le type de la case transformée n'est pas bon");
		}
		
		for(int i=0; i<getDelegate().getHeight(); i++) {
			for(int j=0; j<getDelegate().getWidth(); j++) {
				if(i!=n && j!=m && getDelegate().getCellNature(i,j) != screen_atPre[i][j]) {
					throw new PostconditionError("Case modifiee n'aurait pas du l'etre");
				}	
			}
		}
	}
	
	
	public EditableScreenService getDelegate() {
		return (EditableScreenService) super.getDelegate();
	}

}
