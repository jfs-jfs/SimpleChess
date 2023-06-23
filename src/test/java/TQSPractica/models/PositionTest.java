package TQSPractica.models;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testGetChessPosition() {
		
		System.out.println("[TESTING: Position->getChessPosition()] : Chess notation to chess notation");
		String[][] payload = new String[][] {
			// Tests
			{
				"", "1a","11","aa", "asjq83",	// Rubbish
				"a1","h1","a8","h8",			// Limits
				"a0","h0","a7","h7",
				"a2","h2","a9","h9",
				"z1","g1","z8","g8",
				"b1","i1","b8","i8",
				"d4","f6"						// Some in the center
				
			},
			// Solutions
			{
				null, null, null, null, null,
				"a1","h1","a8","h8",
				null, null,"a7","h7",
				"a2","h2",null,null,
				null,"g1",null,"g8",
				"b1",null,"b8",null,
				"d4","f6"
			}
		};
		
		for (int i = 0; i < payload[0].length; i++) {
			Position test = new Position(payload[0][i]);
			String aux = test.getChessPosition();
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",payload[0][i], aux, payload[1][i]);
			assertEquals(payload[1][i], aux);
		}
		
		System.out.println("[TESTING: Position->getChessPosition()] : Coordinates to chess notation");
		int[][] payload2 = new int[][] {
			{1,31},{-1,12},{2,-1}, {2, 25},		// Rubbish
			{0, 7},{7,7},{0,0},{7,0},			// Limits
			{0, 8},{7,8},{0,1},{7,1},
			{0, 6},{7,6},{0,-1},{7,-1},
			{-1, 7},{6,7},{-1,0},{6,0},
			{1, 7},{8,7},{1,0},{8,0},
			{3,4},{5,2}							// Some in the center
				
			};
			// Solutions
		String[] solutions = new String[] {
			null, null, null, null,
			"a1","h1","a8","h8",
			null, null,"a7","h7",
			"a2","h2",null,null,
			null,"g1",null,"g8",
			"b1",null,"b8",null,
			"d4","f6"
		};
		
		for (int i = 0; i < solutions.length; i++) {
			Position test = new Position(payload2[i][0],payload2[i][1]);
			String aux = test.getChessPosition();
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",Arrays.toString(payload2[i]), aux, solutions[i]);
			assertEquals(solutions[i], aux);
		}
	}

	@Test
	public void testGetCoorPosition() {
		System.out.println("[TESTING: Position->getCoorPosition()] : Chess notation to coordinates");
		String[] payload = new String[] {
			"", "1a","11","aa", "asjq83",	// Rubbish
			"a1","h1","a8","h8",			// Limits
			"a0","h0","a7","h7",
			"a2","h2","a9","h9",
			"z1","g1","z8","g8",
			"b1","i1","b8","i8",
			"d4","f6"						// Some in the center
		};
		int[][] solutions = new int[][] {
			null, null, null, null, null,	// Rubbish
			{0, 7},{7,7},{0,0},{7,0},		// Limits
			null, null,{0,1},{7,1},
			{0, 6},{7,6}, null, null,
			null,{6,7}, null,{6,0},
			{1, 7}, null,{1,0}, null,
			{3,4},{5,2}	
		};
		
		for (int i = 0; i < payload.length; i++) {
			Position test = new Position(payload[i]);
			int[] aux = test.getCoorPosition();
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",payload[i], Arrays.toString(aux), Arrays.toString(solutions[i]));
			assertEquals(Arrays.toString(solutions[i]), Arrays.toString(aux));
		}
		
		System.out.println("[TESTING: Position->getCoorPosition()] : Coordiantes to coordinates");
		
		int[][][] payload2 = new int[][][] {
			// Test
			{
				{1,31},{-1,12},{2,-1}, {2, 25},		// Rubbish
				{0, 7},{7,7},{0,0},{7,0},			// Limits
				{0, 8},{7,8},{0,1},{7,1},
				{0, 6},{7,6},{0,-1},{7,-1},
				{-1, 7},{6,7},{-1,0},{6,0},
				{1, 7},{8,7},{1,0},{8,0},
				{3,4},{5,2}
			},
			// Solutions
			{
				null, null, null, null,			// Rubbish
				{0, 7},{7,7},{0,0},{7,0},		// Limits
				null, null,{0,1},{7,1},
				{0, 6},{7,6}, null, null,
				null,{6,7}, null,{6,0},
				{1, 7}, null,{1,0}, null,
				{3,4},{5,2}	
			}
		};
		
		for (int i = 0; i < payload2[0].length; i++) {
			Position test = new Position(payload2[0][i][0], payload2[0][i][1]);
			int[] aux = test.getCoorPosition();
			System.out.printf(
					"\t>IN:%s OUT:%s EXPECTED:%s\n",Arrays.toString(payload2[0][i]),
					Arrays.toString(aux), Arrays.toString(payload2[1][i])
				);
			assertEquals(Arrays.toString(payload2[1][i]), Arrays.toString(aux));
		}
		
	}

	@Test
	public void testGetTileId() {
		
		System.out.println("[TESTING: Position->getTileId()] : Coordiantes to coordinates");
		String[] payload = new String[] {
				"", "1a","11","aa", "asjq83",	// Rubbish
				"a1","h1","a8","h8",			// Limits
				"a0","h0","a7","h7",
				"a2","h2","a9","h9",
				"z1","g1","z8","g8",
				"b1","i1","b8","i8",
				"d4","f6"						// Some in the center
			};
		int[] solutions = new int[] {
				Position.ERR_NON_EXISTENT_TILE,
				Position.ERR_NON_EXISTENT_TILE,
				Position.ERR_NON_EXISTENT_TILE,
				Position.ERR_NON_EXISTENT_TILE,
				Position.ERR_NON_EXISTENT_TILE,
				56, 63, 0, 7,
				Position.ERR_NON_EXISTENT_TILE, Position.ERR_NON_EXISTENT_TILE, 8, 15,
				48, 55, Position.ERR_NON_EXISTENT_TILE, Position.ERR_NON_EXISTENT_TILE,
				Position.ERR_NON_EXISTENT_TILE, 62, Position.ERR_NON_EXISTENT_TILE, 6,
				57, Position.ERR_NON_EXISTENT_TILE, 1, Position.ERR_NON_EXISTENT_TILE,
				35, 21
		};
		
		for (int i = 0; i < solutions.length; i++) {
			Position test = new Position(payload[i]);
			int aux = test.getTileId();
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",payload[i], aux, solutions[i]);
			assertEquals(solutions[i], aux);
		}
	}

	@Test
	public void testSetPositionString() {
		
		System.out.println("[TESTING: Position->setPosition(Strnig)]");
		
		String[] payload = new String[] {
				"", "1a","11","aa", "asjq83",	// Rubbish
				"a1","h1","a8","h8",			// Limits
				"a0","h0","a7","h7",
				"a2","h2","a9","h9",
				"z1","g1","z8","g8",
				"b1","i1","b8","i8",
				"d4","f6"						// Some in the center
			};
		// b4 tile = 33
		// It has to change only when a valid argument is passed
		int[] solutions = new int[] {
				33, 33, 33, 33, 33,
				56, 63, 0, 7,
				33, 33, 8, 15,
				48, 55, 33, 33,
				33, 62, 33, 6,
				57, 33, 1, 33,
				35, 21
			};
		
		boolean[] solutions2 = new boolean[] {
				false, false,false,false, false,
				true, true, true, true,
				false, false, true,true,
				true, true, false, false,
				false, true, false, true,
				true, false, true, false,
				true, true
			};
		
		for (int i = 0; i < solutions.length; i++) {
			Position test = new Position("b4");
			boolean res = test.setPosition(payload[i]);
			int aux = test.getTileId();
			System.out.printf("\t>IN:%s OUT:%s,%s EXPECTED:%s,%s\n",payload[i], res, aux, solutions2[i], solutions[i]);
			assertEquals(solutions[i], aux);
			assertEquals(res, solutions2[i]);
		}
		
	}

	@Test
	public void testSetPositionIntInt() {
		System.out.println("[TESTING: Position->setPosition(int, int)]");
		int[][] payload = new int[][] {
			{1,31},{-1,12},{2,-1}, {2, 25},		// Rubbish
			{0, 7},{7,7},{0,0},{7,0},			// Limits
			{0, 8},{7,8},{0,1},{7,1},
			{0, 6},{7,6},{0,-1},{7,-1},
			{-1, 7},{6,7},{-1,0},{6,0},
			{1, 7},{8,7},{1,0},{8,0},
			{3,4},{5,2}							// Some in the center
		};
		
		// b4 tile = 33
		// It has to change only when a valid argument is passed
		int[] solutions = new int[] {
			33, 33, 33, 33,
			56, 63, 0, 7,
			33, 33, 8, 15,
			48, 55, 33, 33,
			33, 62, 33, 6,
			57, 33, 1, 33,
			35, 21
		};
		
		boolean[] solutions2 = new boolean[] {
				false, false,false,false,
				true, true, true, true,
				false, false, true,true,
				true, true, false, false,
				false, true, false, true,
				true, false, true, false,
				true, true
			};
		
		for (int i = 0; i < solutions.length; i++) {
			Position test = new Position("b4");
			boolean res = test.setPosition(payload[i][0],payload[i][1]);
			int aux = test.getTileId();
			System.out.printf("\t>IN:%s OUT:%s,%s EXPECTED:%s,%s\n",Arrays.toString(payload[i]), res,aux, solutions2[i],solutions[i]);
			assertEquals(solutions[i], aux);
			assertEquals(solutions2[i], res);
		}
		
	}
	
	@Test
	public void testCoord2Chess() {
		
		System.out.println("[TESTING: Position->coord2Chess(int, int)]");
		
		int[][] payload = new int[][] {
			{1,31},{-1,12},{2,-1}, {2, 25},			// Rubbish
			{0, 7},{7,7},{0,0},{7,0},			// Limits
			{0, 8},{7,8},{0,1},{7,1},
			{0, 6},{7,6},{0,-1},{7,-1},
			{-1, 7},{6,7},{-1,0},{6,0},
			{1, 7},{8,7},{1,0},{8,0},
			{3,4},{5,2}							// Some in the center
		};
		String[] solutions = new String[] {
			null, null, null, null,
			"a1","h1","a8","h8",
			null, null,"a7","h7",
			"a2","h2",null,null,
			null,"g1",null,"g8",
			"b1",null,"b8",null,
			"d4","f6"
		};
		
		for (int i = 0; i < solutions.length; i++) {
			String aux = Position.coord2Chess(payload[i][0], payload[i][1]);
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",Arrays.toString(payload[i]), aux, solutions[i]);
			assertEquals(solutions[i], aux);
		}
	}
	
	@Test
	public void testChess2Coord() {
		
		System.out.println("[TESTING: Position->Chess2Coord(int, int)]");
		
		int[][] solutions = new int[][] {
			null, null, null, null, null,	// Rubbish
			{0, 7},{7,7},{0,0},{7,0},		// Limits
			null, null,{0,1},{7,1},
			{0, 6},{7,6}, null, null,
			null,{6,7}, null,{6,0},
			{1, 7}, null,{1,0}, null,
			{3,4},{5,2}						// Some in the center
		};
		String[] payload = new String[] {
				"", "1a","11","aa", "asjq83",	// Rubbish
				"a1","h1","a8","h8",			// Limits
				"a0","h0","a7","h7",
				"a2","h2","a9","h9",
				"z1","g1","z8","g8",
				"b1","i1","b8","i8",
				"d4","f6"						// Some in the center
			};
		
		for (int i = 0; i < solutions.length; i++) {
			int[] aux = Position.chess2Coord(payload[i]);
			System.out.printf("\t>IN:%s OUT:%s EXPECTED:%s\n",payload[i], Arrays.toString(aux), Arrays.toString(solutions[i]));
			assertEquals(Arrays.toString(solutions[i]), Arrays.toString(aux));
		}
	}
	
	@Test
	public void testequals() { 
		System.out.println("[TESTING: Position->equals(Position)]");
		
		Position a = new Position("e2");
		Position b = new Position("e2");
		
		// Some good ones
		assertTrue(a.equals(b));
		a = new Position("c5");
		b = new Position("c5");
		assertTrue(a.equals(b));
		a = new Position("asda"); // null one
		b = new Position("casda");
		assertTrue(a.equals(b));
		
		// Bad ones
		a = new Position("e2");
		assertFalse(a.equals(b));
		b = null;
		assertFalse(a.equals(b));
		b = new Position("e3");
		assertFalse(a.equals(b));
		
	}

}
