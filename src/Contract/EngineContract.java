package Contract;

import java.util.ArrayList;
import java.util.Arrays;

import Decorators.EngineDecorator;
import Errors.InvariantError;
import Errors.PostconditionError;
import Errors.PreconditionError;
import Service.EditableScreenService;
import Service.EngineService;
import Service.EntityService;
import Service.EnvironmentService;
import Service.GuardService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Cell;
import Types.Command;
import Types.State;

public class EngineContract extends EngineDecorator implements EngineService {

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		if(getDelegate().getPlayerLifes()<0) {
			throw new InvariantError("Le joueur n'a plus de vie");
		}
	}

	@Override
	public EnvironmentService getEnv() {
		checkInvariants();
		EnvironmentService e = getDelegate().getEnv();
		checkInvariants();
		return e;
	}

	@Override
	public PlayerService getPlayer() {
		checkInvariants();
		PlayerService p = getDelegate().getPlayer();
		checkInvariants();
		return p;
	}

	@Override
	public State computeStatus() {
		checkInvariants();
		int n = getDelegate().getPlayerLifes();
		int x = getDelegate().getPlayer().getPosX();
		int y = getDelegate().getPlayer().getPosY();
		State s = getDelegate().computeStatus();
		checkInvariants();
		if(getDelegate().getPlayerLifes() != n-1) {
			for(EntityService e : getDelegate().getEnv().getCellContent(x,y)) {
				if(e instanceof GuardService) {
					throw new PostconditionError("Le joueur aurait du perdre une vie, et ne l'a pas perdu");
				}
			}
			//si les cases sur les cotés sont "solides"  et que la case en dessous est "solide" alors :
			if(getDelegate().getEnv().getCellNature(x, y)==Cell.HOL && ((y>0 && getDelegate().getEnv().getCellNature(x, y-1)!=Cell.EMP && getDelegate().getEnv().getCellNature(x, y-1)!=Cell.HDR && getDelegate().getEnv().getCellNature(x, y-1)!=Cell.LAD)||y==0) && getDelegate().getEnv().getCellNature(x-1, y)!= Cell.EMP && getDelegate().getEnv().getCellNature(x-1, y)!= Cell.LAD && getDelegate().getEnv().getCellNature(x-1, y)!= Cell.HDR){
				if(getDelegate().getEnv().getCellNature(x, y)==Cell.HOL && ((y<getDelegate().getEnv().getWidth()-1 && getDelegate().getEnv().getCellNature(x, y+1)!=Cell.EMP && getDelegate().getEnv().getCellNature(x, y+1)!=Cell.HDR && getDelegate().getEnv().getCellNature(x, y+1)!=Cell.LAD)||y==getDelegate().getEnv().getWidth()-1)){
					throw new PostconditionError("Le joueur est tombe dans un fatal mais n'est pas mort");					
				}
			}
			if(getDelegate().getTimeLef()<=0) {
				throw new PostconditionError("Timer ecoule, le joueur aurait du perdre une vie");
			}
		}
		
		return s;
	}

	@Override
	public Command getNextCommand() {
		checkInvariants();
		Command c = getDelegate().getNextCommand();
		checkInvariants();
		return c;
	}
	
	@Override
	public ArrayList<GuardService> getGuards(){
		checkInvariants();
		ArrayList<GuardService> l = getDelegate().getGuards();
		checkInvariants();
		
		return l;
		
	}
	
	
	@Override
	public ArrayList<TreasureService> getTreasures(){
		checkInvariants();
		ArrayList<TreasureService> l = getDelegate().getTreasures();
		checkInvariants();
		
		return l;
	}
	
	@Override
	public int getNbGuards() {
		return getDelegate().getNbGuards();
	}
	
	@Override
	public int getSpawnRate() {
		return getDelegate().getSpawnRate();
	}
	

	@Override
	public void step() {
		
		
		checkInvariants();
		
		int time = getDelegate().getTimeLef();
		int x = getDelegate().getPlayer().getPosX();
		int y = getDelegate().getPlayer().getPosY();
		boolean containsTreasure = false;
		int nbLifes = getDelegate().getPlayerLifes();
		for(EntityService e : getDelegate().getEnv().getCellContent(x, y)){
			if(e instanceof TreasureService) {
				containsTreasure=true;
			}
		}
		
		
		getDelegate().step();
		checkInvariants();
		
		
		boolean stillTreasure = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(getDelegate().getPlayer().getPosX(), getDelegate().getPlayer().getPosY())){
			if(e instanceof TreasureService) {
				stillTreasure=true;
			}
		}
		
		if(containsTreasure && stillTreasure ) {
			throw new PostconditionError("Tresor n'a pas ete ramasse");
		}
		
		
		x = getDelegate().getPlayer().getPosX();
		y = getDelegate().getPlayer().getPosY();
		boolean isGuard = false;
		for(EntityService e : getDelegate().getEnv().getCellContent(x, y)) {
			if(e instanceof GuardService) {
				isGuard = true;
			}
		}
		Cell[] tmp = {Cell.MTL, Cell.PLT};
		ArrayList<Cell> block = new ArrayList<Cell>(Arrays.asList(tmp));
		Cell cg = null;
		Cell cd = null;
		Cell cb = null;
		if(y>0) {
			cg = getDelegate().getEnv().getCellNature(x, y-1);
		}
		
		if(y<getDelegate().getEnv().getWidth()-1) {
			cd = getDelegate().getEnv().getCellNature(x, y+1);
		}
			cb = getDelegate().getEnv().getCellNature(x-1, y);
		
		if(((y==0 || (cg!=null &&  block.contains(cg)))&&(y==getDelegate().getEnv().getWidth()-1 || (block.contains(cd))) && (block.contains(cb) || cb == Cell.DOR) ) && !(nbLifes != getDelegate().getPlayerLifes()-1)) {
			throw new PostconditionError("Le joueur est dans un trou fatal mais n'a pas perdu de vie");			
		}
		if(isGuard && !(nbLifes != getDelegate().getPlayerLifes()-1)) {
			throw new PostconditionError("Personnage sur un garde mais pas de vie perdue");
		}
		
		if(getDelegate().getTimeLef() != 500  && time ==0) {
			throw new PostconditionError("le temps n'a pas ete reinitialise");
		}
		
	}

	@Override
	public void init(EditableScreenService es, int x, int y, ArrayList<int[]> gardes, ArrayList<int[]> tresors, String[] types, int[] spawn, PlayerService p , int nbLevel) {
		
		for(int i=0;i<gardes.size(); i++) {
			if(gardes.get(i).length!=2) {
				throw new PreconditionError("Tableau de coordonees des gardes faux");
			}
		}
		for(int i=0;i<tresors.size(); i++) {
			if(tresors.get(i).length!=2) {
				throw new PreconditionError("Tableau de coordonees des tresors faux");
			}
		}
		if(spawn.length != 2 ) {
			throw new PreconditionError("Tableau de coordonnï¿½es du spawn des ennemis de taille incorrecte");
		}
		
		if(types.length != tresors.size()) {
			throw new PreconditionError("Tableau du type des tresors de taille differente que la liste des tresors, erreur");
		}
			
		
		
		getDelegate().init(es, x, y, gardes, tresors, types, spawn, p, nbLevel);
		checkInvariants();
		
		if(getDelegate().getEnv().getHeight()!=es.getHeight()) {
			throw new PostconditionError("Hauteur errone");
		}
		if(getDelegate().getEnv().getWidth()!=es.getWidth()) {
			throw new PostconditionError("Largeur errone");
		}
		for(int i=0; i<es.getHeight(); i++) {
			for(int j=0; j<es.getWidth();j++) {
				if(getDelegate().getEnv().getCellNature(i, j)!= es.getCellNature(i, j)) {
					throw new PostconditionError("Case mal init (" +i+","+j+")");
				}
			}
		}
		
		if(getDelegate().getPlayer().getPosX() != x) {
			throw new PostconditionError("initialisation de la posX du joueur errone");
		}
		if(getDelegate().getPlayer().getPosY() != y) {
			throw new PostconditionError("initialisation de la posY du joueur errone");
		}
		if(getDelegate().getSpawnRate()<=0) {
			throw new PostconditionError("Rythme de respawn nul ou negatif");
		}
		
		/* Postcondition du placement des tresors */
		boolean okT = false;
		for(int[] t : tresors){
			
			for(EntityService e :  getDelegate().getEnv().getCellContent(t[0], t[1])){
				if(e instanceof TreasureService){
					okT = true;
				}
			}
		}
		if(tresors.size()==0) {
			okT=true;
		}
		if(!okT){
			throw new PostconditionError("une ou plusieurs cases qui devaient contenir un tresor n'en contiennent pas");
		}
		
		/* Postcondition sur le placement correct des gardes */
		boolean okG = false;
		for(int[] t : gardes){
			
			for(EntityService e :  getDelegate().getEnv().getCellContent(t[0], t[1])){
				if(e instanceof GuardService){
					okG = true;
				}
			}
		}
		if(gardes.size()==0) {
			okG=true;
		}
		if(!okG){
			throw new PostconditionError("une ou plusieurs cases qui devaient contenir un garde n'en contiennent pas");
		}
		
	}
	
	
	public ArrayList<int[]> getHoles(){
		return getDelegate().getHoles();
	}
	
	public ArrayList<Command> getCommands(){
		return getDelegate().getCommands();
	}
	
	public int getPlayerLifes() {
		return getDelegate().getPlayerLifes();
	}
	
	public int getTimeLeft() {
		return getDelegate().getTimeLef();
	}
	
	public void changeLifes(int n) {
		if(n!=1 && n!= -1) {
			throw new PreconditionError("Montant de vie a ajouter invalide, doit etre 1 ou -1");
		}
		int nb = getDelegate().getPlayerLifes();
		checkInvariants();
		getDelegate().changeLifes(n);
		checkInvariants();
		if(getDelegate().getPlayerLifes()!= nb+n) {
			throw new PostconditionError("Ajout ou retrait de vie echoue");
		}
	}
	
	

}
