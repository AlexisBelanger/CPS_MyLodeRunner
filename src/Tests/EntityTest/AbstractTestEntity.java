package Tests.EntityTest;


import org.junit.After;
import org.junit.Before;

import Service.CharacterService;
import Service.EntityService;
import Service.GuardService;
import Service.PlayerService;
import Service.TreasureService;

public abstract class AbstractTestEntity{
	
	private EntityService entity;
	private CharacterService character;
	private PlayerService player;
	private TreasureService treasure;
	private GuardService guard;
	
	
	protected AbstractTestEntity() {
		entity=null;
		character = null;
		player=null;
		treasure=null;
		guard = null;
	}
	
	
	public EntityService getE() {
		return entity;
	}
	public CharacterService getChar() {
		return character;
	}
	public PlayerService getPlayer() {
		return player;
	}
	public TreasureService getTreasure() {
		return treasure;
	}
	
	public GuardService getGuard() {
		return guard;
	}
	
	public final void setE(EntityService es) {
		this.entity=es;
	}
	
	public final void setChar(CharacterService chara) {
		this.character=chara;
	}
	
	public final void setPlayer(PlayerService p) {
		this.player = p;
	}
	
	public final void setTreasure(TreasureService t) {
		this.treasure=t;
	}
	
	public final void setGuard(GuardService g) {
		this.guard=g;
	}
	
	
	
	@Before
	public abstract void beforeTests(); 

	@After
	public final void afterTests() {
		entity = null;
		character = null;
		player = null;
		treasure = null;
		guard = null;
	}
	
	
}