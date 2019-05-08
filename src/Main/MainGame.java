package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Contract.EditableScreenContract;
import Contract.EngineContract;
import Contract.EnvironmentContract;
import Contract.PlayerContract;
import Impl.EditableScreen;
import Impl.Engine;
import Impl.Player;
import Service.EntityService;
import Service.GuardService;
import Service.PlayerService;
import Service.TreasureService;
import Types.Cell;
import Types.Item;
import Types.State;

import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGame {
	
	public static void main(String[] args) {
		
		int level =1;
		int nbLevel = 4;
		Engine engine = new Engine();

		boolean over=false;
		while(!over) {
			try
			  {
				
				
				File f = new File("CPS_MyLodeRunner-master/ressources/level"+level+".txt");
			    BufferedReader reader = new BufferedReader(new FileReader(f));
			    
			   
			    
			    
			    String line;
			    String[] tmp = reader.readLine().split(" ");
			    
			    //recuperation du point de respawn des gardes;
			    int[] spawnPoint = new int[2];
			    spawnPoint[0]= Integer.parseInt(tmp[0]);
			    spawnPoint[1]= Integer.parseInt(tmp[1]);
			    
			    
			    //Recuperation du nombre d'items et leur position
			    String[] items = reader.readLine().split(" ");
			    int nb = Integer.parseInt(items[0]);
			    int[][] t= new int[nb][2];
			    String[] types = new String[nb];
			    int n = 0;
			    for(int i=1; i<items.length; i+=3) {
			    	int[] temp = new int[2];
			    	temp[0]=Integer.parseInt(items[i]);
			    	temp[1]=Integer.parseInt(items[i+1]);
			    	t[n]=temp;
			    	types[n]= items[i+2];
			    	n++;
			    }
			    
			    
			    //Recuperation du nombre de gardes et leur position
			    String[] guards = reader.readLine().split(" ");
			    int nbG = Integer.parseInt(guards[0]);
			    int[][] g = new int[nbG][2];
			    n=0;
			    for(int i=1; i<guards.length; i+=2) {
			    	int[] temp = new int[2];
			    	temp[0]=Integer.parseInt(guards[i]);
			    	temp[1]=Integer.parseInt(guards[i+1]);
			    	g[n]=temp;
			    	n++;
			    }
			    
			    ArrayList<int[]> gardes = new ArrayList<int[]>(Arrays.asList(g));
				ArrayList<int[]> tresors = new ArrayList<int[]>(Arrays.asList(t));
				
				//Recuperation de la position initiale du joueur
				String[] posP = reader.readLine().split(" ");
				int x = Integer.parseInt(posP[0]);
				int y = Integer.parseInt(posP[1]);
	
				
				//Initialisation de la map
				EditableScreenContract es = new EditableScreenContract(new EditableScreen());
				es.init(20, 40);
			    int l=es.getHeight()-1;
			    int nbDone = 0;
			    while ((line = reader.readLine()) != null && nbDone<es.getHeight()-1)
			    {
				    for(int c=0; c< line.length(); c++) {
				    	
				    	char type = line.charAt(c);
				    	switch(type) {
				    		case 'M':
				    			es.setNature(l,c, Cell.MTL);
				    			break;
				    		case 'E':
				    			es.setNature(l,c, Cell.EMP);
				    			break;
				    		case 'H':
				    			es.setNature(l,c, Cell.LAD);
				    			break;
				    		case 'P':
				    			es.setNature(l,c, Cell.PLT);
				    			break;
				    		case 'R':
				    			es.setNature(l,c, Cell.HDR);
				    			break;
				    		case 'D':
				    			es.setNature(l,c, Cell.DOR);
				    			break;
				    	}
				    
				    	
				    }
				    l--;
				    nbDone++;
			    }
			    reader.close();
			    
			 
			
			
			
				
				/*Window initialization*/
			    
			    

			    //Engine engine = new Engine();
				
			    
				JTextField textField = new JTextField();
				
			    textField.addKeyListener(engine);
		
			    JFrame jframe = new JFrame();
			    jframe.setLayout(new FlowLayout());
			   
		
			    jframe.add(textField);
		
		
			    
		
			    jframe.setVisible(true);
			    
			    PlayerContract player = new PlayerContract(new Player());
			    
			    EngineContract eng = new EngineContract(engine);
				eng.init(es, x, y, gardes, tresors, types, spawnPoint, player,nbLevel);
				EnvironmentContract envContract = new EnvironmentContract(eng.getEnv());
				
				
				
				
				
				
				
				System.out.println(level);
				
				while(eng.getStatus()== State.Playing) {
					eng.step();
					level = eng.getLevel();
					over = eng.isOver();
					if(!over ) {
						for(int i =envContract.getHeight()-1; i>=0; i--) {
							for(int j=0; j<envContract.getWidth(); j++) {
								boolean containsPlayer =false;
								for(EntityService e : envContract.getCellContent(i,j)) {
									if(e instanceof PlayerService) {
										containsPlayer = true;
									}
								}
								if(containsPlayer) {
									System.out.print("%");
								}else {
									if(envContract.getCellContent(i, j).size()>0) {
										for(EntityService e : envContract.getCellContent(i, j)) {
											if(e instanceof TreasureService && e.getPosX()==i && e.getPosY()==j) {
												if(((TreasureService)e).getType()==Item.Key)
													System.out.print("*");
												else
													if(((TreasureService)e).getType()==Item.Treasure)
														System.out.print("$");
													else
														System.out.print("o");
												break;
											}else {
												if(e instanceof GuardService && e.getPosX()==i && e.getPosY()==j) {
													System.out.print("@");
													break;
												}
											}
										}
									}else {
										switch(envContract.getCellNature(i, j)){
											case MTL:
												System.out.print("X");
												break;
											case PLT:
												System.out.print("T");
												break;
											case LAD:
												System.out.print("H");
												break;
											case HDR:
												System.out.print("-");
												break;
											case EMP :
												System.out.print(" ");
												break;
											case HOL :
												System.out.print("_");
												break;
											case DOR :
												System.out.print("D");
												break;
										}
										
									}
								}
							}
							
							System.out.println();
						}
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {}
					
				}
			  }
			  catch (Exception e)
			  {
				e.printStackTrace();
			    System.out.println(e.getMessage());
			  }
			if(level<=nbLevel && !over) {
				
				for(int i = 5; i>0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					System.out.println("Debut du niveau "+ level+" dans "+i);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
	}
}


