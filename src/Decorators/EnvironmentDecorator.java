package Decorators;

import java.util.ArrayList;

import Service.EditableScreenService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.ScreenService;

public class EnvironmentDecorator extends ScreenDecorator implements EnvironmentService{

	public EnvironmentDecorator(ScreenService delegate) {
		super(delegate);
	}

	@Override
	public ArrayList<EntityService> getCellContent(int n, int m) {
		return getDelegate().getCellContent(n, m);
	}
	
	public void init(EditableScreenService es) {
		getDelegate().init(es);		
	}
	
	
	
	@Override
	public EnvironmentService getDelegate() {
		return (EnvironmentService) super.getDelegate();
	}

	@Override
	public void moveCellContent(int x, int y, EntityService entity) {
		getDelegate().moveCellContent(x, y, entity);
	}
	
	public void dig(int n, int m) {
		getDelegate().dig(n, m);
	}
	public void fill(int n, int m) {
		getDelegate().fill(n, m);
	}

	public void openDoor(int n, int m) {
		getDelegate().openDoor(n, m);
	}


}
