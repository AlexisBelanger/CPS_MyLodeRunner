package Tests.EntityTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Contract.CharacterContract;
import Contract.EditableScreenContract;
import Contract.EngineContract;
import Contract.EntityContract;
import Contract.EnvironmentContract;
import Contract.PlayerContract;
import Contract.TreasureContract;
import Errors.ContractError;
import Impl.Character;
import Impl.EditableScreen;
import Impl.Engine;
import Impl.Entity;
import Impl.Environment;
import Impl.Player;
import Impl.Treasure;
import Service.EngineService;
import Service.EntityService;
import Service.GuardService;
import Service.TreasureService;
import Types.Cell;
import Types.Command;
import Types.Item;

public class TestEntity  extends AbstractTestEntity{

	@Override
	public void beforeTests() {
		setChar(new CharacterContract(new Character()));
		setE(new EntityContract(new Entity()));
		setPlayer(new PlayerContract(new Player()));
		setTreasure(new TreasureContract(new Treasure()));
		
	}
	
	/* ---------- Test Init Entity ---------- */	
	@Test
	public void testInitE_Neg1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getE().init(-2, 0, env);
			System.out.println("Test InitEntity Negatif 1 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitEntity Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	@Test
	public void testInitE_Neg2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getE().init(5, 15, env);
			System.out.println("Test InitEntity Negatif 2 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitEntity Negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test
	public void testInitE_Neg3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getE().init(5, 5, env);
			System.out.println("Test InitEntity Negatif 3 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitEntity Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test
	public void testInitE_Pos() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getE().init(1, 9, env);
			System.out.println("Test InitEntity Positif : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitEntity Positif : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	
	/* ---------- Test Init Character ---------- */	
	
	@Test
	public void testInitChar_Neg1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(-2, 0, env);
			System.out.println("Test InitChar Negatif 1 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitChar Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	@Test
	public void testInitChar_Neg2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(5, 15, env);
			System.out.println("Test InitChar Negatif 2 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitChar Negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test
	public void testInitChar_Neg3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(5, 5, env);
			System.out.println("Test InitChar Negatif 3 : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitChar Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	

	
	@Test
	public void testInitChar_Pos() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 9, env);
			System.out.println("Test InitChar Positif : OK");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test InitChar Positif : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	
	/* ---------- Test Move character ---------- */
	
	@Test
	public void testGoLeft_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goLeft();
			System.out.println("Test GoLeft Positif 1 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoLeft Positif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}

	@Test
	public void testGoLeft_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goLeft();
			
			System.out.println("Test GoLeft Positif 2 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoLeft Positif  2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoLeft_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goLeft();
			System.out.println("Test GoLeft Positif 3 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("Plateforme a gauche \n");
		}catch(ContractError e) {
			System.out.println("Test GoLeft Positif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test
	public void testGoRight_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			
			es.setNature(1, 0, Cell.PLT);
			es.setNature(2, 1, Cell.HDR);
			es.setNature(2, 2, Cell.HDR);
			es.setNature(2, 3, Cell.HDR);
			es.setNature(2, 4, Cell.HDR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(2, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			getChar().goRight();
			System.out.println("Test goRight Positif 4 (Deplacement vers un rail avec du vide en dessous : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
		}catch(ContractError e) {
			System.out.println("Test GoRight Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoRight_Pos4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 0, Cell.PLT);
			es.setNature(2, 1, Cell.LAD);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(2, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			getChar().goRight();
			
			System.out.println("Test goRight Positif 4 (Deplacement vers une echelle avec du vide en dessous puis le vide) : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
		}catch(ContractError e) {
			System.out.println("Test GoRight Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test
	public void testGoRight_Pos5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			
			es.setNature(1, 0, Cell.PLT);
			es.setNature(2, 1, Cell.HDR);
			es.setNature(2, 2, Cell.LAD);
			es.setNature(2, 3, Cell.HDR);
			es.setNature(2, 4, Cell.HDR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(2, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			getChar().goRight();
			System.out.println("Test goRight Positif 5 (Deplacement vers un rail avec du vide en dessous puis vers une echelle avec du vide): OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
		}catch(ContractError e) {
			System.out.println("Test GoRight Negatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoRight_Pos6() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			
			es.setNature(1, 0, Cell.PLT);
			es.setNature(2, 1, Cell.HDR);
			es.setNature(2, 2, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(2, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			getChar().goRight();
			System.out.println("Test goRight Positif 6 (Deplacement vers un rail avec du vide en dessous puis vers une plateforme): OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
		}catch(ContractError e) {
			System.out.println("Test GoRight Negatif 6 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoRight_Pos7() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			
			es.setNature(1, 9, Cell.LAD);

			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(2, 0, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			getChar().goRight();
			System.out.println("Test goRight Positif 7 (Deplacement vers une echelle avec du vide en dessous puis vers une plateforme): OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
		}catch(ContractError e) {
			System.out.println("Test GoRight Negatif 7 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/* ---------- Test Go Right ---------- */
	
	@Test
	public void testGoRight_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 9, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			System.out.println("Test GoRight Positif 1 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("Personnage au bord a droite\n");
		}catch(ContractError e) {
			System.out.println("Test GoRight Positif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}

	@Test
	public void testGoRight_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goRight();
			System.out.println("Test GoRight Positif 2 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoRight Positif 2 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test
	public void testGoUp_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.LAD);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			getChar().goRight();
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goUp();
			System.out.println("Test GoUp Positif 1 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("CellNature("+x+","+y+") = "+env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoU^p Positif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoUp_pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.LAD);
			es.setNature(2, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			getChar().goRight();
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goUp();
			System.out.println("Test GoUp Positif (Aller a droite vers l'echelle, essayer de moonter mais PLT au dessus) : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("CellNature("+x+","+y+") = "+env.getCellNature(x, y));
			System.out.println("CellNature("+x+1+","+y+") = "+env.getCellNature(x+1, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoUp Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoUp_pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.LAD);
			es.setNature(2, 6, Cell.HDR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			getChar().goRight();
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goUp();
			System.out.println("Test GoUp Positif (Aller a droite vers l'echelle, monter et arriver dans un rail) : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("CellNature("+x+","+y+") = "+env.getCellNature(x, y));
			System.out.println("CellNature("+(x+1)+","+y+") = "+env.getCellNature(x+1, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoUp Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test
	public void testGoUp_pos4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.HDR);
			es.setNature(2, 6, Cell.HDR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			getChar().goRight();
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goUp();
			System.out.println("Test GoUp Positif (Essayer de monter a un rail) : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("CellNature("+x+","+y+") = "+env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoUp Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test
	public void testGoUp_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			getChar().init(1, 5, env);
			int x = getChar().getPosX();
			int y = getChar().getPosY();
			getChar().goUp();
			System.out.println("Test GoUp Positif 2 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getChar().getPosX()+"\t Nouveau y : "+getChar().getPosY());
			System.out.println("CellNature("+x+","+y+") = "+env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test GoUp Positif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	
	
	@Test 
	public void testInitPlayer_neg() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			getPlayer().init(-2, 0, env, eng);
			System.out.println("Test InitPlayer Negatif 1 : OK");
		}catch(ContractError e) {
			System.out.println("Test InitPlayer Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/* ---------- Test Step player avec goLeft ---------- */
	@Test 
	public void testStep_playerLeft_Pos() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 5, env, eng);
			eng.getCommands().add(Command.Left);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			System.out.println("Test Step Player Left Positif 1 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}

	
	@Test 
	public void testStep_playerLeft_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.MTL);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 5, env, eng);
			eng.getCommands().add(Command.Left);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			System.out.println("Test Step Player Left Positif 2 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Command = "+Command.Left+", CellNature("+x+","+(y-1)+") = "+env.getCellNature(x, y-1));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerLeft_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.MTL);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 0, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 0, env, eng);
			eng.getCommands().add(Command.Left);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			System.out.println("Test Step Player Left Positif 3 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Command = "+Command.Left);
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerLeft_Pos4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(5, 5, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 6, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(6, 5, env, eng);
			eng.getCommands().add(Command.Left);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			eng.getCommands().add(Command.Left);
			getPlayer().step();
			System.out.println("Test Step Player Left Positif 4 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Command = "+Command.Left+" x2, le deuxieme goLeft est fait dans le vide -> le joueur ne va pas a gauche mais descend");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	/*Test exactement symetriques avec goRight*/
	
	/* ---------- Test Step player avec goUp ---------- */
	
	@Test 
	public void testStep_playerUp_Pos() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 5, env, eng);
			eng.getCommands().add(Command.Up);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			System.out.println("Test Step Player Up Positif 1 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Essayer de monter sans echelle");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Up NEgatif  1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerUp_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.LAD);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 5, env, eng);
			eng.getCommands().add(Command.Right);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			eng.getCommands().add(Command.Up);
			getPlayer().step();
			System.out.println("Test Step Player Up Positif 2 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Monter e une echelle");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Up Negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerUp_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 6, Cell.LAD);
			es.setNature(2, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {1,0};
			eng.init(es, 1, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(1, 5, env, eng);
			eng.getCommands().add(Command.Right);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY();
			getPlayer().step();
			eng.getCommands().add(Command.Up);
			getPlayer().step();
			System.out.println("Test Step Player Up Positif 3 : OK");
			System.out.println("Ancien x : "+x +"\t Ancien y : "+ y+"\nNouveau x : "+ getPlayer().getPosX()+"\t Nouveau y : "+getPlayer().getPosY());
			System.out.println("Monter a une echelle ave un bloc juste au dessus ne fait pas bouger");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Up Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/* ---------- Test Step player avec DigL ---------- */
	@Test 
	public void testStep_playerDigL_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(2, 5, env, eng);
			eng.getCommands().add(Command.DigL);
			int x = getPlayer().getPosX()-1;
			int y = getPlayer().getPosY()-1;
			Cell c = env.getCellNature(x, y);
			System.out.println(c);
			getPlayer().step();
			System.out.println("Test Step Player DigL Positif 1 : OK");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer DigL NEgatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerDigL_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(2, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(2, 5, env, eng);
			eng.getCommands().add(Command.DigL);
			int x = getPlayer().getPosX()-1;
			int y = getPlayer().getPosY()-1;
			Cell c = env.getCellNature(x, y);
			System.out.println(c);
			getPlayer().step();
			System.out.println("Test Step Player DigL Positif 1 : OK");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println("la case ("+(x+1)+","+y+") est de type "+ env.getCellNature(x+1, y)+", impossible de creuser en dessous" );
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer DigL NEgatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void testStep_playerDigL_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.MTL);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(2, 5, env, eng);
			eng.getCommands().add(Command.DigL);
			int x = getPlayer().getPosX()-1;
			int y = getPlayer().getPosY()-1;
			Cell c = env.getCellNature(x, y);
			System.out.println(c);
			getPlayer().step();
			System.out.println("Test Step Player DigL Positif 1 : OK");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer DigL NEgatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	/*Tests symetriques pour goRight*/
	
	/* ---------- Test Step player avec Opendoor ---------- */
	@Test 
	public void testStep_playerOpen_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 4, env, Item.Key);
			env.getCellContent(2, 4).add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			getPlayer().init(2, 5, env, eng);
			eng.getCommands().add(Command.Left);
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()+1;
			Cell c = env.getCellNature(x, y);
			getPlayer().step();
			eng.getCommands().add(Command.Right);
			
			getPlayer().step();
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			System.out.println("Test Step Player Open Positif 1 (ouverture avec une cle dans l'inventaire) : OK");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void testStep_playerOpen_Pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()+1;
			Cell c = env.getCellNature(x, y);
			
			
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 2 (tentative d'ouverture sans cle dans l'inventaire) : OK");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void testStep_playerOpen_Pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 4, Cell.DOR);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 6, env, Item.Key);
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			getPlayer().getInventory().add(t);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()-1;
			Cell c = env.getCellNature(x, y);
			
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 3 (tentative d'ouverture avec cle dans l'inventaire mais dos a la porte) : OK");
			System.out.println("Orientation du joueur : "+getPlayer().getOrientation()+" Position du joueur : ("+x+","+(y+1)+")");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void testStep_playerOpen_Pos4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(1, 8, env, Item.Key);
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			getPlayer().getInventory().add(t);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()+1;
			Cell c = env.getCellNature(x, y);
			
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 4 (ouverture avec cle dans l'inventaire face a la porte) : OK");
			System.out.println("Orientation du joueur : "+getPlayer().getOrientation()+" Position du joueur : ("+x+","+(y+1)+")");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void testStep_playerOpen_Pos5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.MTL);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(1, 0, env, Item.Key);
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			getPlayer().getInventory().add(t);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()+1;
			Cell c = env.getCellNature(x, y);
			
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 5 (ouverture avec cle dans l'inventaire, mais pas sur une porte) : OK");
			System.out.println("Orientation du joueur : "+getPlayer().getOrientation()+" Position du joueur : ("+x+","+(y+1)+")");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void testStep_playerOpen_Pos6() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 4, Cell.DOR);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 6, env, Item.Key);
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			getPlayer().getInventory().add(t);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()-1;
			Cell c = env.getCellNature(x, y);
			
			eng.getCommands().add(Command.Left);
			getPlayer().step();
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 6 (joueur s'oriente vers la porte, puis ouverture avec cle dans l'inventaire face a la porte) : OK");
			System.out.println("Orientation du joueur : "+getPlayer().getOrientation()+" Position du joueur : ("+x+","+(y+1)+")");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 6 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void testStep_playerOpen_Pos7() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 4, Cell.DOR);
			es.setNature(2, 6, Cell.DOR);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(1, 0, env, Item.Key);
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, 2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			getPlayer().getInventory().add(t);
			
			int x = getPlayer().getPosX();
			int y = getPlayer().getPosY()+1;
			Cell c = env.getCellNature(x, y);
			
			
			eng.getCommands().add(Command.Open);
			getPlayer().step();
			
			System.out.println("Test Step Player Open Positif 7 (joueur bloque entre deux porte, ouverture de celle qui correspond a son orientation uniquement) : OK");
			System.out.println("Orientation du joueur : "+getPlayer().getOrientation()+" Position du joueur : ("+x+","+(y+1)+")");
			System.out.println("CellNature("+x+","+y+") avant = " +c+", CellNature("+x+","+y+") apres = " + env.getCellNature(x, y));
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Open NEgatif 7 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_LoseLife1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 5, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 5, env, eng);
			
			
			
			int nbL = eng.getPlayerLifes();

			
			eng.getCommands().add(Command.Open);
			eng.step();
			
			System.out.println("Test Lose life Positif 1 : Vies avant = "+nbL +", apres = "+eng.getPlayerLifes());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test Lose Life Negatif 1");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_FallInHole1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 6, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 6, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			
			eng.getCommands().add(Command.Nothing);
			eng.step();
			eng.getCommands().add(Command.Nothing);
			eng.step();
			
			System.out.println("Test Fall in hole Positif 2 ");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test Fall in hole Negatif 2");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_Climb_Right_neg() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 6, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 6, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.getGuards().get(0).climbRight();
			
			System.out.println("Test Climb Right Positif 2 ");
			System.out.println(eng.getGuards().get(0).getPosX());
			System.out.println(eng.getGuards().get(0).getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 2");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_Climb_Left_neg() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 6, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 6, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.getGuards().get(0).climbLeft();
			
			System.out.println("Test Climb Left Positif 2 ");
			System.out.println(eng.getGuards().get(0).getPosX());
			System.out.println(eng.getGuards().get(0).getPosY());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 2");
			System.out.println(e);
			System.out.println();
		}
	}
	
	

	@Test 
	public void test_Climb_Right_pos2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.step();
			eng.step();
			eng.getGuards().get(0).climbRight();
			
			System.out.println("Test Climb Right Positif 2 ");
			System.out.println("Le garde a tente de remonter dans une plateforme , il est reste dans le trou");
			System.out.println("Pos avant climb = (1,5), pos apres climb = ("+eng.getGuards().get(0).getPosX()+","+eng.getGuards().get(0).getPosY()+")");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 2");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_takeTreasure_Neg1(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 5};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			

			eng.getGuards().get(0).takeTreasure(eng.getGuards().get(0).getPosX(), eng.getGuards().get(0).getPosY());
			
			System.out.println("Test Take Treasure Positif 1 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Take Treasure Negatif 1 ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_takeTreasure_Neg2(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 5};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			

			eng.getGuards().get(0).takeTreasure(-1, 0);
			
			System.out.println("Test Take Treasure Positif 2 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Take Treasure Negatif 2 ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_takeTreasure_Neg3(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 5};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			

			eng.getGuards().get(0).takeTreasure(15, 0);
			
			System.out.println("Test Take Treasure Positif 3 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Take Treasure Negatif 3 ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_takeTreasure_Pos1(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 5};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			

			eng.getGuards().get(0).takeTreasure(2,5);
			
			System.out.println("Test Take Treasure Positif 1 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Take Treasure Negatif 1 ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_release_Neg1(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.HOL);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 6};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			
			eng.step();
			eng.step();
			eng.getGuards().get(0).releaseTreasure();
			
			System.out.println("Test Release Treasure Positif 1 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Release Treasure Negatif 1 ");
			System.out.println(e);
			System.out.println("Ici, pas de trésor a relacher meme si le garde est dans un trou");
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_release_Neg2(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 5};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			
			eng.getGuards().get(0).takeTreasure(2, 5);
			eng.getGuards().get(0).releaseTreasure();
			
			System.out.println("Test Release Treasure Positif 2 ");
			System.out.println("Treasure du garde = "+ eng.getGuards().get(0).hasTreasure());
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Release Treasure Negatif 2 ");
			System.out.println(e);
			System.out.println("Ici, pas dans un trou");
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_release_pos1(){
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 3, Cell.PLT);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.HOL);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
		
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 3};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 4};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			
			eng.getGuards().get(0).takeTreasure(2, 4);
			TreasureService tresor = eng.getGuards().get(0).hasTreasure();
			System.out.println(tresor);
			
			/* Trois step pour emmener le joueur dans le trou puis on lui fait release en appellant directement release, pas avec un step supplementaire*/
			eng.step();
			eng.step();
			eng.step();
			
			
			eng.getGuards().get(0).releaseTreasure();
			
			System.out.println("Test Release Treasure Positif 1 ");
			System.out.println("Treasure du garde avant release = "+ tresor + " et apres = " + eng.getGuards().get(0).hasTreasure() );
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Release Treasure Negatif 1 ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	@Test 
	public void test_Climb_Right_pos3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.LAD);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			eng.getEnv().dig(1, 5);
			
			eng.step();
			eng.step();
			eng.getGuards().get(0).climbRight();
			
			System.out.println("Test Climb Right Positif 3 ");
			System.out.println("Le garde a tente de remonter dans une echelle , il peut sortir");
			System.out.println("Pos avant climb = (1,5), pos apres climb = ("+eng.getGuards().get(0).getPosX()+","+eng.getGuards().get(0).getPosY()+")");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 3");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_Climb_Right_pos4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 6, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 6, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.step();
			eng.step();
			eng.getGuards().get(0).climbRight();
			
			System.out.println("Test Climb Right Positif 4 ");
			System.out.println("Le garde a tente de remonter dans le joueur , il reste dans le trou");
			System.out.println("Pos avant climb = (1,5), pos apres climb = ("+eng.getGuards().get(0).getPosX()+","+eng.getGuards().get(0).getPosY()+")");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 4");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	@Test 
	public void test_Climb_Right_pos5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {2, 6};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.step();
			eng.step();
			eng.getGuards().get(0).climbRight();
			
			System.out.println("Test Climb Right Positif 5 ");
			System.out.println("Le garde a tente de remonter dans une case contenant un tresor , il peut remonter");
			System.out.println("Pos avant climb = (1,5), pos apres climb = ("+eng.getGuards().get(0).getPosX()+","+eng.getGuards().get(0).getPosY()+")");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 5");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	@Test 
	public void test_Climb_Right_pos6() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.EMP);
			es.setNature(1, 7, Cell.PLT);
			
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			String[] type = {"T"};
			guards.add(g);
			tres.add(t);
			eng.init(es, 2, 7, guards, tres, type, tmp, getPlayer(), 1);
			
			getPlayer().init(2, 7, env, eng);
			
			eng.getEnv().dig(1, 5);
			

			eng.step();
			eng.step();
			eng.getGuards().get(0).climbRight();
			
			
			System.out.println("Test Climb Right Positif 6 ");
			System.out.println("Le garde a tente de remonter avec une case vide a sa droite, il peut remonter, mais tombera a la prochaine itération ");
			System.out.println("Pos avant climb = (1,5), pos apres climb = ("+eng.getGuards().get(0).getPosX()+","+eng.getGuards().get(0).getPosY()+")");
			System.out.println();
			
		}catch(ContractError e) {
			System.out.println("Test Climb Right Negatif 6");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/* tests sur le init de engine*/
	
	/*X du player trop petit*/
	@Test 
	public void test_init_engine_neg1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 4, env, Item.Key);
			env.getCellContent(2, 4).add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			eng.init(es, -2, 5, new ArrayList<int[]>(), new ArrayList<int[]>(), new String[0], tmp, getPlayer(), 1);
			
			System.out.println("Test init engine Positif 1 : OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/*Taille des coordonnées du garde trop grande*/
	@Test 
	public void test_init_engine_neg2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4, 7};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"K"};
			eng.init(es, -2, 5, guards, tres, s, tmp, getPlayer(), 1);
			
			System.out.println("Test init engine Positif 2 : OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/*Idem pour tresors*/
	@Test 
	public void test_init_engine_neg3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0, 666};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"K"};
			eng.init(es, -2, 5, guards, tres, s, tmp, getPlayer(), 1);
			
			System.out.println("Test init engine Positif 3 : OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/* Test avec un nombre de tresors plus eleve que la taille du tableau de types*/
	@Test 
	public void test_init_engine_neg4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			int[] t2={1,9};
			tres.add(t2);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"K"};
			eng.init(es, -2, 5, guards, tres, s, tmp, getPlayer(), 1);
			
			System.out.println("Test init engine Positif 4 : OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	/* test taille des coordonnees du spawn = 3*/
	@Test 
	public void test_init_engine_neg5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0, 42};
			String[] s = {"K"};
			eng.init(es, -2, 5, guards, tres, s, tmp, getPlayer(), 1);
			
			System.out.println("Test init engine Positif 5 : OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/* Test de la bonne initialisation des positions des tresors*/
	@Test 
	public void test_init_engine_pos6() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"K"};
			eng.init(es, 2, 5, guards, tres, s, tmp, getPlayer(), 1);
			boolean tresOk = true;
			for(int[] tr : tres){
				boolean posOk = false;
				for(EntityService e : eng.getEnv().getCellContent(tr[0], tr[1])){
					if(e instanceof TreasureService){
						posOk=true;
						break;
					}
				}
				if(!posOk){
					tresOk=false;
				}
			}
			
			
			
			System.out.println("Test init engine Positif 6 : OK");
			assertTrue(tresOk == true);
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 6 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	/* Test de la bonne initialisation de la position des gardes*/
	@Test 
	public void test_init_engine_pos7() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"K"};
			eng.init(es, 2, 5, guards, tres, s, tmp, getPlayer(), 1);
			boolean guardOk = true;
			for(int[] gd : guards){
				boolean posOk = false;
				for(EntityService e : eng.getEnv().getCellContent(gd[0], gd[1])){
					if(e instanceof GuardService){
						posOk=true;
						break;
					}
				}
				if(!posOk){
					guardOk=false;
				}
			}
			
			
			
			System.out.println("Test init engine Positif 7 : OK");
			assertTrue(guardOk == true);
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 7 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/* Test de la bonne initialisation du type des cases*/
	@Test 
	public void test_init_engine_pos8() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] g = {2, 4};
			ArrayList<int[]> guards = new ArrayList<int[]>();
			guards.add(g);
			int[] t = {1, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			int[] t2={1,9};
			tres.add(t2);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0, 42};
			String[] s = {"K"};
			eng.init(es, 2, 5, guards, tres, s, tmp, getPlayer(), 1);
			boolean envOk = true;
			for(int i = 0; i< es.getHeight(); i++){
				for(int j=0; j< es.getWidth(); j++){
					if(es.getCellNature(i, j) != eng.getEnv().getCellNature(i, j)){
						envOk = false;
					}
				}
			}

			System.out.println("Test init engine Positif 8 : OK");
			assertTrue(envOk == true);
		}catch(ContractError e) {
			System.out.println("Test init engine Negatif 8 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
/* Test sur Treasure*/
	
	/*x negatif*/
	@Test 
	public void test_init_treasure_neg1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(-5, 0, env, Item.Key);

			System.out.println("Test init treasure positif 1 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/*x trop grand*/
	@Test 
	public void test_init_treasure_neg2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(12, 0, env, Item.Key);

			System.out.println("Test init treasure positif 2 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	/*Symetrique pour y*/
	
	
	/*cases en dessous invalide*/
	@Test 
	public void test_init_treasure_neg3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(5, 5, env, Item.Key);

			System.out.println("Test init treasure positif 3 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/*Case d'initialisation n'est pas EMP*/
	@Test 
	public void test_init_treasure_neg4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(0, 5, env, Item.Key);

			System.out.println("Test init treasure positif 4 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	/*Env null*/
	@Test 
	public void test_init_treasure_neg5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, null, Item.Key);

			System.out.println("Test init treasure positif 5 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/*init OK*/
	@Test 
	public void test_init_treasure_pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);

			System.out.println("Test init treasure positif 5 : OK");
		}catch(ContractError e) {
			System.out.println("Test init treasure negatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	/*setposition avec x negatif*/
	@Test 
	public void test_setposition_treasure_neg1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);
			
			t.setPosition(-1, 2);

			System.out.println("Test setposition treasure positif 1 : OK");
		}catch(ContractError e) {
			System.out.println("Test setposition treasure negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/*setposition avec x trop grand*/
	@Test 
	public void test_setposition_treasure_neg2() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);
			
			t.setPosition(12, 2);

			System.out.println("Test setposition treasure positif 2 : OK");
		}catch(ContractError e) {
			System.out.println("Test setposition treasure negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
	/*setposition dans une case avec une case vide en dessous*/
	@Test 
	public void test_setposition_treasure_neg3() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 4, Cell.PLT);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			es.setNature(2, 6, Cell.DOR);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);
			
			t.setPosition(5, 5);

			System.out.println("Test setposition treasure positif 3 : OK");
		}catch(ContractError e) {
			System.out.println("Test setposition treasure negatif 3 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/*setposition dans une case avec une case d'echelle en dessous*/
	@Test 
	public void test_setposition_treasure_neg4() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(4, 5, Cell.LAD);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);
			
			t.setPosition(5, 5);

			System.out.println("Test setposition treasure positif 4 : OK");
		}catch(ContractError e) {
			System.out.println("Test setposition treasure negatif 4 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	/*setposition dans une case "pleine"*/
	@Test 
	public void test_setposition_treasure_neg5() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			
			
			TreasureContract t = new TreasureContract(new Treasure());
			t.init(2, 5, env, Item.Key);
			
			t.setPosition(0, 5);

			System.out.println("Test setposition treasure positif 5 : OK");
		}catch(ContractError e) {
			System.out.println("Test setposition treasure negatif 5 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	/*Joueur tombe dans un trou dont il ne pourra pas sortir -> perte d'une vie*/
	@Test 
	public void testStep_playerFall_Pos1() {
		try {
			EditableScreenContract es = new EditableScreenContract(new EditableScreen());
			es.init(10, 10);
			es.setNature(1, 0, Cell.PLT);
			es.setNature(1, 1, Cell.PLT);
			es.setNature(1, 2, Cell.PLT);
			es.setNature(1, 3, Cell.PLT);
			es.setNature(1, 4, Cell.HOL);
			es.setNature(1, 5, Cell.PLT);
			es.setNature(1, 6, Cell.PLT);
			EnvironmentContract env = new EnvironmentContract(new Environment());
			env.init(es);
			int[] t = {2, 0};
			ArrayList<int[]> tres = new ArrayList<int[]>();
			tres.add(t);
			EngineContract eng = new EngineContract(new Engine());
			int[] tmp = {2,0};
			String[] s = {"T"};
			eng.init(es, 2, 5, new ArrayList<int[]>(), tres, s, tmp, getPlayer(), 1);
			getPlayer().init(2, 5, env, eng);
			int nbL = eng.getPlayerLifes();
			eng.getPlayer().goLeft();
			eng.getPlayer().goDown();
			eng.step();
			System.out.println(eng.getPlayer().getPosY());
			System.out.println("Test Step Player Mort dans un trou : OK");
			System.out.println("Ancien nombre de vies = "+ nbL + ", nouveau nombre de vies = "+eng.getPlayerLifes());
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test StepPlayer Up NEgatif  1 : ");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
	
}
