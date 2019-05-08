package Decorators;

import Service.ScreenService;
import Types.Cell;

public class ScreenDecorator implements ScreenService {
	private final ScreenService delegate;
	
	public ScreenDecorator(ScreenService delegate) {
		this.delegate = delegate;
	}
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}
	
	public ScreenService getDelegate() {
		return delegate;
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public Cell getCellNature(int n, int m) {
		return delegate.getCellNature(n, m);
	}

	@Override
	public void init(int h, int w) {
		delegate.init(h, w);
	}

	@Override
	public void dig(int n, int m) {
		delegate.dig(n, m);
	}

	@Override
	public void fill(int n, int m) {
		delegate.fill(n, m);
	}

}
