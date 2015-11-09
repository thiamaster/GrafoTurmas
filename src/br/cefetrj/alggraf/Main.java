package br.cefetrj.alggraf;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Main {
	public static Turma[] t;
	
		public static void main(String args[]) {
	/*		int x;
			Scanner sc = new Scanner(System.in);
			Grafo my = new Grafo();
			System.out.println("Número de vértices:");
			int no_of_ver = sc.nextInt();
	
			for (int i = 1; i <= no_of_ver; i++) {
				my.addVertex("v" + new Integer(i).toString());
			}
	
			do {
				System.out.println("Forneça uma aresta:");
				String e1 = sc.next();
				String e2 = sc.next();
				my.addEdge(e1, e2);
				System.out.println("Continuar? Sim=1; Não=0");
				x = sc.nextInt();
			} while (x == 1);
	
			System.out.println("Grafo criado:\n" + my.getGraph().toString());
			*/
			Grafo G = new Grafo();
			
			String colunas[] = { "COURSEID", "INSTRUCTOR", "NUMDAYS", "DAYS", "TIMEOFDAY",
									"ROOMTYPE", "CLASSMAXSIZE", "PERIODO", "COURSENAME"};
			List<String> colunasList = Arrays.asList(colunas);
			
			File input = new File("imp.xls");
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("Cp1252");
			try {
				Workbook w = Workbook.getWorkbook(input, ws);
				Sheet sheet = w.getSheet(0);
				t = new Turma[sheet.getRows()];
				
				for (int i = 1; i < sheet.getRows(); i++) {
					
					t[i-1] = new Turma(
							Integer.parseInt(sheet.getCell(colunasList.indexOf("COURSEID"), i).getContents()),
							sheet.getCell(colunasList.indexOf("INSTRUCTOR"), i).getContents(),
							Integer.parseInt(sheet.getCell(colunasList.indexOf("NUMDAYS"), i).getContents()),
							sheet.getCell(colunasList.indexOf("DAYS"), i).getContents(),
							sheet.getCell(colunasList.indexOf("TIMEOFDAY"), i).getContents(),
							sheet.getCell(colunasList.indexOf("ROOMTYPE"), i).getContents(),
							Integer.parseInt(sheet.getCell(colunasList.indexOf("CLASSMAXSIZE"), i).getContents()),
							Integer.parseInt(sheet.getCell(colunasList.indexOf("PERIODO"), i).getContents()),
							sheet.getCell(colunasList.indexOf("COURSENAME"), i).getContents()
					);
					G.addVertex(t[i-1]);
					
				}
				
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Grafo Criado.");
			
			verificarInstrutores(G);
			
			adicionaRegras(G);
			
			System.out.println("Grafo G: "+G.getGraph());
		}
	
		/**
		 * Verifica instrutores. Caso instrutor de uma matéria seja o mesmo de outra, adiciona-se uma
		 * aresta entre as duas matérias.
		 * @param G Array de grafos g1 a g10
		 */
		public static void verificarInstrutores(Grafo G){
			for (int i = 0; i < t.length-1; i++)
				for (int j = 0; j < t.length-1; j++)
					if (t[i] != t[j])
						if (t[i].getInstructor().equals(t[j].getInstructor()))
							G.addEdge(t[i], t[j]);
		}
		
		public static void adicionaRegras(Grafo G){
			for (int i = 0; i < t.length - 1; i++)
				for (int j = 0; j < t.length - 1; j++)
					if(t[i] != t[j]){
						// 1 a 16
						if ((t[i].retornaTipo() >= 0) && (t[i].retornaTipo() <= 3) && (t[j].retornaTipo() > 3) && (t[j].retornaTipo() < 8))
							G.addEdge(t[i], t[j]);
						
						// 17 / 18
						if ((t[i].retornaTipo() == 0) && ((t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 3)))
							G.addEdge(t[i], t[j]);
						
						// 19
						if ((t[i].retornaTipo() == 1) && (t[j].retornaTipo() == 2))
							G.addEdge(t[i], t[j]);
						
						// 20 / 21
						if ((t[i].retornaTipo() == 4) && ((t[j].retornaTipo() == 5) || (t[j].retornaTipo() == 6)))
							G.addEdge(t[i], t[j]);
						
						// 22
						if ((t[i].retornaTipo() == 5) && (t[j].retornaTipo() == 6))
							G.addEdge(t[i], t[j]);
						
						// 23 / 24
						if ((t[j].getTimeOfDay().equals("4")) && ((t[i].retornaTipo() == 2) || (t[i].retornaTipo() == 3)))
							G.addEdge(t[i], t[j]);
						
						// 25 a 28
						if (t[i].retornaTipo() == 8){
							if ((t[i].getTimeOfDay().equals("1") && ((t[j].retornaTipo() == 1)  || (t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 5) || (t[j].retornaTipo() == 6))))
								G.addEdge(t[i], t[j]);
							if ((t[i].getTimeOfDay().equals("2") && ((t[j].retornaTipo() == 0)  || (t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 4) || (t[j].retornaTipo() == 6))))
								G.addEdge(t[i], t[j]);
							if ((t[i].getTimeOfDay().equals("3") && ((t[j].retornaTipo() == 0)  || (t[j].retornaTipo() == 1) || (t[j].retornaTipo() == 4) || (t[j].retornaTipo() == 5))))
								G.addEdge(t[i], t[j]);
						}
						// 29 a 34
						if (t[i].retornaTipo() == 9){
							if ((t[i].getDays().equals("M")) || (t[i].getDays().equals("W")) || (t[i].getDays().equals("F")))
								if ((t[j].retornaTipo() >= 4) && (t[j].retornaTipo() <=7))
										G.addEdge(t[i], t[j]);

								if ((t[i].getDays().equals("T")) || (t[i].getDays().equals("R")))
									if ((t[j].retornaTipo() >= 0) && (t[j].retornaTipo() <=3))
											G.addEdge(t[i], t[j]);

								if (t[i].getTimeOfDay().equals("1"))
									if ((t[j].retornaTipo() == 1) || (t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 5) || (t[j].retornaTipo() == 6))
											G.addEdge(t[i], t[j]);

								if (t[i].getTimeOfDay().equals("2"))
									if ((t[j].retornaTipo() == 0) || (t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 4) || (t[j].retornaTipo() == 6))
											G.addEdge(t[i], t[j]);

								if (t[i].getTimeOfDay().equals("3"))
									if ((t[j].retornaTipo() == 0) || (t[j].retornaTipo() == 1) || (t[j].retornaTipo() == 4) || (t[j].retornaTipo() == 5))
											G.addEdge(t[i], t[j]);

								if (t[i].getTimeOfDay().equals("4"))
									if ((t[j].retornaTipo() == 2) || (t[j].retornaTipo() == 6))
											G.addEdge(t[i], t[j]);
							
						}
						
						
					}
		}
	}
