package Decorators;

import Service.EditableScreenService;
import Types.Cell;

public class EditableScreenDecorator extends ScreenDecorator implements EditableScreenService{
	
	
	public EditableScreenDecorator(EditableScreenService delegate) {
		super(delegate);
	}
	@Override
	public EditableScreenService getDelegate() {
		return (EditableScreenService) super.getDelegate();
	}
	

	public void init(int h, int w) {
		getDelegate().init(h, w);
	}
	
	@Override
	public boolean playable() {
		return getDelegate().playable();
	}

	@Override
	public void setNature(int n, int m, Cell c) {
		getDelegate().setNature(n, m, c);		
	}
	
	

}
