package Decorators;

import Service.EntityService;
import Service.EnvironmentService;
import Service.TreasureService;
import Types.Item;

public class TreasureDecorator extends EntityDecorator  implements TreasureService {

	public TreasureDecorator(EntityService delegate) {
		super(delegate);
	}
	
	
	public TreasureService getDelegate() {
		return (TreasureService) super.getDelegate();
	}
	
	
	public Item  getType() {
		return getDelegate().getType();
	}


	@Override
	public void init(int h, int w, EnvironmentService env, Item type) {
		getDelegate().init(h, w, env, type);
	}


	@Override
	public void setPosition(int x, int y) {
		getDelegate().setPosition(x, y);
	}

}
