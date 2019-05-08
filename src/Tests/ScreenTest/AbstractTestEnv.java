package Tests.ScreenTest;



import org.junit.After;
import org.junit.Before;

import Service.EditableScreenService;
import Service.EnvironmentService;

public abstract class AbstractTestEnv{
	
	private EditableScreenService es;
	private EnvironmentService envs;
	
	protected AbstractTestEnv() {
		es=null;
		envs = null;
	}
	
	
	public EditableScreenService getES() {
		return es;
	}
	public EnvironmentService getEnv() {
		return envs;
	}
	
	public final void setES(EditableScreenService es) {
		this.es=es;
	}
	
	public final void setEnv(EnvironmentService env) {
		this.envs=env;
	}
	
	
	@Before
	public abstract void beforeTests(); 

	@After
	public final void afterTests() {
		es = null;
		envs = null;
	}
	
	
}