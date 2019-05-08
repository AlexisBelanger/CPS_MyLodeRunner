package Tests.ScreenTest;

import Contract.EditableScreenContract;
import Contract.EnvironmentContract;
import Errors.ContractError;
import Impl.EditableScreen;
import Impl.Environment;
import Types.Cell;

import org.junit.Test;

public class TestEnv extends AbstractTestEnv{

	@Override
	public void beforeTests() {
		setES(new EditableScreenContract(new EditableScreen()));
		setEnv(new EnvironmentContract(new Environment()));
	}
	
	
	
/* -------------------- TEST INIT -------------------- */ 	
	@Test
	public void testInitES_Neg() {
		//Operation
		try {
			getES().init(-1, 2);
			System.out.println("Test InitES Negatif :  OK");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test InitES Negatif : ");
			System.out.println(e);	
			System.out.println();			
		}
	}
	
	@Test
	public void testInitES_Pos() {
		//Operation
		try {
			getES().init(1, 2);
			System.out.println("Test InitES positif : OK ");
			System.out.println();
		}catch(ContractError e) {
			System.out.println("Test InitES positif : ");
			System.out.println(e);	
			System.out.println();
		}
	}
	
	
	@Test 
	public void testInitEnv_Pos() {
		try {
			getES().init(1, 2);
			getEnv().init(getES());
			System.out.println("Test InitEnv positif : OK");
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test InitEnv positif : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	
	@Test 
	public void testSetCellNature_Pos() {
		try {
			getES().init(10, 20);
			getES().setNature(5,5 , Cell.HDR);
			System.out.println("Test SetCellNature positif : OK");
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test SetCellNature positif : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test 
	public void testSetCellNature_Neg1() {
		try {
			getES().init(10, 20);
			getES().setNature(0,5 , Cell.HOL);
			System.out.println("Test SetCellNature Negatif 1 : OK");
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test SetCellNature Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	@Test 
	public void testSetCellNature_Neg2() {
		try {
			getES().init(10, 20);
			getES().setNature(5,30 , Cell.HOL);
			System.out.println("Test SetCellNature Negatif 2 : OK");
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test SetCellNature Negatif 2 : ");
			System.out.println(e);
			System.out.println();
		}		
	}
	
	@Test 
	public void test_Dig() {
		try {
			getES().init(10, 10);
			getES().setNature(1, 0, Cell.PLT);
			getES().setNature(1, 1, Cell.PLT);
			getES().setNature(1, 2, Cell.PLT);
			getES().setNature(1, 3, Cell.PLT);
			getES().setNature(1, 4, Cell.PLT);
			getES().setNature(1, 5, Cell.PLT);
			getEnv().init(getES());
			Cell c = getES().getCellNature(1, 3);
			getES().dig(1, 3);
			System.out.println("Test Dig Positif 1 : OK");
			System.out.println("Case avant dig(1,3) : " + c + " Case apres : "+getES().getCellNature(1, 3) );
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test Dig Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}		
	}
	
	
	@Test 
	public void test_Dig_neg() {
		try {
			getES().init(10, 10);
			getES().setNature(1, 0, Cell.MTL);
			getES().setNature(1, 1, Cell.MTL);
			getES().setNature(1, 2, Cell.MTL);
			getES().setNature(1, 3, Cell.MTL);
			getES().setNature(1, 4, Cell.MTL);
			getES().setNature(1, 5, Cell.MTL);
			getEnv().init(getES());
			Cell c = getES().getCellNature(1, 3);
			getES().dig(1, 3);
			System.out.println("Test Dig Positif 1 : OK");
			System.out.println("Case avant dig(1,3) : " + c + " Case apres : "+getES().getCellNature(1, 3) );
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test Dig Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}		
	}
	
	
	
	@Test 
	public void test_Fill() {
		try {
			getES().init(10, 10);
			getES().setNature(1, 0, Cell.PLT);
			getES().setNature(1, 1, Cell.PLT);
			getES().setNature(1, 2, Cell.PLT);
			getES().setNature(1, 3, Cell.PLT);
			getES().setNature(1, 4, Cell.PLT);
			getES().setNature(1, 5, Cell.PLT);
			Cell c = getES().getCellNature(1, 3);
			getES().dig(1, 3);
			getES().fill(1, 3);
			System.out.println("Test Fill Positif 1 : OK");
			System.out.println("Case avant dig(1,3) et fill(1,3) : " + c + " Case apres : "+getES().getCellNature(1, 3) );
			System.out.println();
		}catch(ContractError e){
			System.out.println("Test Fill Negatif 1 : ");
			System.out.println(e);
			System.out.println();
		}		
	}

	
	
	
	

	
	
	
	
	

}
