import java.util.HashMap;
import java.util.LinkedList;
public class Board {
	String Bord;
	int DLeft;
	boolean up, down, left, right;
	int EPos;
	int lastPos;
	String solvePath;
	String lastDir;
	HashMap has = new HashMap();
	public Board(String Brd, int DepthLeft){
		Bord = Brd;
		DLeft = DepthLeft;
		up = down = left = right = false;
		EPos = Bord.indexOf("0");
	}
	//returns the solved path d=down, l=left, u=up, r=right
	public String getSolvePath(){
		return solvePath;
	}
	//returns the board as a string
	public String toString(){
		return Bord;
	}
	//moves another board's empty node down, useful in the recursive function
	public String moveDown(){
		char tmp = Bord.charAt(EPos+4);
		String tmpBord = Bord;
		tmpBord = tmpBord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		
		return tmpBord;
	}
	//moves empty node down
	public void meDown(){
		char tmp = Bord.charAt(EPos+4);
		Bord=Bord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		EPos+=4;
	}
	//moves another board's empty node up 
	public String moveUp(){
		char tmp = Bord.charAt(EPos-4);
		String tmpBord = Bord;
		tmpBord= tmpBord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		
		return tmpBord;
	}
	//moves empty node up
	public void meUp(){
		char tmp = Bord.charAt(EPos-4);
		Bord=Bord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		EPos-=4;
	}
	//moves another board's current empty node right
	public String moveRight(){
		char tmp = Bord.charAt(EPos+1);
		String tmpBord = Bord;
		tmpBord= tmpBord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		
		return tmpBord;
	}
	//moves current empty node right
	public void meRight(){
		char tmp = Bord.charAt(EPos+1);
		Bord= Bord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
		EPos++;
	}
	//moves a different board's empty node to the left
	public String moveLeft(){
		char tmp = Bord.charAt(EPos-1);
		String tmpBord = Bord;
		tmpBord= tmpBord.replace('0', 'X').replace(tmp,'0').replace('X',tmp);
	
		return tmpBord;
	}
	//moves current empty node to left
	public void meLeft(){
		char tmp = Bord.charAt(EPos-1);
		Bord=Bord.replace('0','X').replace(tmp,'0').replace('X',tmp);
		EPos--;
	}
	//just returns current depth
	public int returnDepth(){
		return DLeft;
	}
	public void boardsFound(){
		
		System.out.println(has.size());
		
	}
	//a hefty function that takes input puzzle as string, and current depth and non-recursively creates the next possible boards
	//culling out any previously seen board if depth is further than when it was seen before. 
	//it will output true if a solved board is found
	public boolean solveable(String a,int depth, LinkedList<String> d){
		boolean solved = false;
		LinkedList<Integer> dp = new LinkedList<Integer>();
		LinkedList<String> movesDone = new LinkedList<String>();
		String a2 = a;
		d.addLast(a2);
		dp.addLast(depth);
		movesDone.addLast("");
		String move = " ";
		//a = d.getFirst();
		int deep = depth;
		while(deep>=0 && solved == false){
		a2 = d.removeFirst();
		move = movesDone.removeFirst();
		deep =dp.removeFirst();
		//System.out.println("a2: " + a2);
		String last = lastDir;
		Board ab = new Board(a2,depth);
		if(has.containsKey(a2)&&(int)has.get(a2)>depth){
			//System.out.println((int)has.get(a));
			return false;}
		//if(has.containsKey(a))
		//System.out.println((int)has.get(a));
		has.put(a2,deep);
		
		int pos = a2.indexOf("0");
		up = false;
		down = false;
		left = false;
		right = false;
		switch(pos){
		case 0 : down = true; right = true; break;
		case 1 : left = true; down = true; right = true; break;
		case 2 : left = true; down = true; right = true; break;
		case 3 : left = true; down = true; break;
		case 4 : up = true; right = true; down = true; break;
		case 5: case 6: case 9: case 10: up = true; down = true; left = true; right = true; break;
		case 7: case 11: up = true; left = true; down = true; break;
		case 8: up = true; right= true; down = true; break;
		case 12: up = true; right = true; break;
		case 13: case 14: left = true; up = true; right = true; break;
		case 15: left = true; up = true; break; 
		default: break;
		
		}
		if (last =="r"){
			left = false;
		}
		else if (last =="l"){
			right = false;
		}
		else if (last =="u"){
			down = false;
		}
		else if (last == "d"){
			up = false;
		}
		//String lastDir="";
		
		
		//boolean solved=false;
		if (a2.equals("123456789abcdef0")){
			solved = true;
			int dep = depth-deep;
			System.out.println("\n" +"depth found at: " + dep);
			System.out.println("moves of the empty node to solve the puzzle, l=left, r=right, d=down, u=up");
			System.out.println(move);
			return solved;}
		if(up==true&& solved ==false){
			d.addLast(ab.moveUp());//lastDir = "u";solved = solveable(ab.moveUp(),depth-1);
			dp.addLast(deep-1);
			movesDone.addLast(move+"u");
		lastDir = "u";
		}
		legality(pos);
		
		if (last =="r"){
			left = false;
		}
		else if (last =="l"){
			right = false;
		}
		else if (last =="u"){
			down = false;
		}
		else if (last == "d"){
			up = false;
		}
		
		if(down==true&& solved == false){
			d.addLast(ab.moveDown());//lastDir="d";solved =solveable(ab.moveDown(),depth-1);
			dp.addLast(deep-1);
			movesDone.addLast(move+"d");
		lastDir="d";
		}
		legality(pos);
		
		if (last =="r"){
			left = false;
		}
		else if (last =="l"){
			right = false;
		}
		else if (last =="u"){
			down = false;
		}
		else if (last == "d"){
			up = false;
		}
		
		if(left==true&& solved == false){
			d.addLast(ab.moveLeft());
			dp.addLast(deep-1);
			movesDone.addLast(move+"l");
			//lastDir = "l";solved =solveable(ab.moveLeft(),depth-1);	
		lastDir = "l";
		}
		legality(pos);
		
		if (last =="r"){
			left = false;
		}
		else if (last =="l"){
			right = false;
		}
		else if (last =="u"){
			down = false;
		}
		else if (last == "d"){
			up = false;
		}
		
		if(right==true&& solved == false){
			d.addLast(ab.moveRight());
			dp.addLast(deep-1);
			movesDone.addLast(move+"r");
			//lastDir = "r";solved =solveable(ab.moveRight(),depth-1);
		lastDir = "r";
		}
		
		if(solved ==true){
			solvePath+=lastDir;
			System.out.println("depth found at: " + deep);
			return solved;
		}
		
		
		}
	
		return solved;
		}
	
	
	//checks for what moves are legal based on board position only, the checks for previous boards are within the recursive solveable function.
	public void legality(int pos){
		up=down=left=right=false;
		switch(pos){
		case 0 : down = true; right = true; break;
		case 1 : left = true; down = true; right = true; break;
		case 2 : left = true; down = true; right = true; break;
		case 3 : left = true; down = true; break;
		case 4 : up = true; right = true; down = true; break;
		case 5: case 6: case 9: case 10: up = true; down = true; left = true; right = true; break;
		case 7: case 11: up = true; left = true; down = true; break;
		case 8: up = true; right= true; down = true; break;
		case 12: up = true; right = true; break;
		case 13: case 14: left = true; up = true; right = true; break;
		case 15: left = true; up = true; break; 
		default: break;
		
		}
	
	}
	//scrambles the board using a set number of moves determined by the input
	public String scramble(int numMoves){
		//Board n = new Board("0123456789abcdef",0);
		for(int j =0;j<numMoves;j++){
		int a = (int)(Math.random()*4);
		legality(EPos);
		if(a ==0 && up == true){
			meUp();
		}
		else if(a==0){
			meDown();
		
		}
		if(a==1 && down == true){
			meDown();
		}
		else if (a==1){
			meUp();
		}
		if(a==2 && left == true){
			meLeft();
		}
		else if (a==2){
			meRight();
			
		}
		if(a==3&& right == true){
			meRight();
		}
		else if (a==3){
			meLeft();
		}
			
			
		}
		
		return Bord;
	}
}
