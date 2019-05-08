package Decorators;

import Service.EntityService;
import Service.EnvironmentService;

public class EntityDecorator implements EntityService {
	private final EntityService delegate;
	
	public EntityDecorator(EntityService delegate) {
		this.delegate = delegate;
	}
	
	
	@Override
	public EnvironmentService getEnv() {
		return delegate.getEnv();
	}

	@Override
	public int getPosX() {
		return delegate.getPosX();
	}

	@Override
	public int getPosY() {
		return delegate.getPosY();
	}


	@Override
	public void init(int x, int y, EnvironmentService env) {
		delegate.init(x, y, env);
	}
	
	public EntityService getDelegate() {
		return delegate;
	}

}
