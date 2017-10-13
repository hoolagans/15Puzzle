/**
 * @author Nathan Haut
 * project: 15 puzzle solver
 * date: 10/10/17
 * 
 * Non recursive, uses deque
*/
public class Solver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board a = new Board("123456789abcdef0",0);
		a.scramble(15);
		//a = new Board("1234578c960bdaef",0);
		//System.out.println(a.toString());
		System.out.println("puzzle" + "\n" + "______" );
		String puzz = a.toString();
		System.out.println(puzz.substring(0,4));
		System.out.println(puzz.substring(4, 8));
		System.out.println(puzz.substring(8, 12));
		System.out.println(puzz.substring(12, 16));
		long time = System.currentTimeMillis();
		boolean solved = a.solveable(a.toString(),15);
		//System.out.println(solved);
		//System.out.println(a.getSolvePath());
		System.out.println("_______");
		if(solved==true){
			System.out.println("the puzzle is solveable");
			System.out.println("moves of the empty node to solve the puzzle, l=left, r=right, d=down, u=up");
		for(int i = a.getSolvePath().length()-1; i>3;i--){
			System.out.print(a.getSolvePath().charAt(i));
		}}
		else{
			System.out.println("The puzzle is unsolveable");
		}
		System.out.print("\n" + "Number of Boards Found: ");
		a.boardsFound();
		time = System.currentTimeMillis()-time;
		System.out.println("\n" + "time in milliseconds: " + time);
	}

}
