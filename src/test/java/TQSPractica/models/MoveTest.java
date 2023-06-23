package TQSPractica.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	@Test
	public void testGetPieceType() {
		
		Move m;
		
		// Good ones -- Simple
		m = new MoveImp("e2");
		assertTrue(m.getPieceType() == Pawn.class);
		m = new MoveImp("Ke1");
		assertTrue(m.getPieceType() == King.class);
		m = new MoveImp("Rh2");
		assertTrue(m.getPieceType() == Rook.class);
		m = new MoveImp("Qd3");
		assertTrue(m.getPieceType() == Queen.class);
		m = new MoveImp("Nh7");
		assertTrue(m.getPieceType() == Knight.class);
		m = new MoveImp("Ba1");
		assertTrue(m.getPieceType() == Bishop.class);
		
		// Don't accept non capital letters
		m = new MoveImp("ke1");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("rh2");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("qd3");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("nh7");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("ba1");					// This is way dont accept capital letters B bishop and b column get mixed
		assertTrue(m.getPieceType() == Pawn.class);
		
		// Good ones -- Ambiguous
		m = new MoveImp("ce2");
		assertTrue(m.getPieceType() == Pawn.class);
		m = new MoveImp("Rbh2");
		assertTrue(m.getPieceType() == Rook.class);
		m = new MoveImp("Qed3");
		assertTrue(m.getPieceType() == Queen.class);
		m = new MoveImp("Ngh7");
		assertTrue(m.getPieceType() == Knight.class);
		
		// Bad ones -- bad piece
		m = new MoveImp("Ph7");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("Wh7");
		assertTrue(m.getPieceType() == null);
		
		// Bad ones -- bad destination
		m = new MoveImp("7");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("a");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("aa");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("Ba");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("h9");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("a9");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("h0");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("Na0");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("Qi8");
		assertTrue(m.getPieceType() == null);
		m = new MoveImp("i1");
		assertTrue(m.getPieceType() == null);
		
		// Bad ones -- ambiguous (king cannot be)
		m = new MoveImp("Kgh8");
		assertTrue(m.getPieceType() == null);
	}

	@Test
	public void testGetColumn() {
		// only for ambiguous
		// Non ambiguous err column not set
		Move m;
		m = new MoveImp("e2");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Ke1");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Rh2");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Qd3");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Nh7");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Ba1");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		
		// ambiguous -- good ones
		m = new MoveImp("ce2");
		assertTrue(m.getColumn() == 2);
		m = new MoveImp("Rbh2");
		assertTrue(m.getColumn() == 1);
		m = new MoveImp("Qed3");
		assertTrue(m.getColumn() == 4);
		m = new MoveImp("Ngh7");
		assertTrue(m.getColumn() == 6);
		
		// ambigous -- non existant
		m = new MoveImp("ie2");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Rzh2");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Qqd3");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		m = new MoveImp("Noh7");
		assertTrue(m.getColumn() == Move.UNSET_COLUMN);
		
	}

	@Test
	public void testGetDestination() {
		
		Position e1 = new Position("e1");
		Position e2 = new Position("e2");
		Position e3 = new Position("e3");
		Position e4 = new Position("e4");
		Position e5 = new Position("e5");
		Position e6 = new Position("e6");
		Position e7 = new Position("e7");
		Position e8 = new Position("e8");
		Position d6 = new Position("d6");
		Position d2 = new Position("d2");
		Position f6 = new Position("f6");
		Position f2 = new Position("f2");
		Position g3 = new Position("g3");
		Position g5 = new Position("g5");
		Position c3 = new Position("c3");
		Position c5 = new Position("c5");
		
		Move m;
		// Good ones -- Simple
		m = new MoveImp("e2");
		assertTrue(e2.equals(m.getDestination()));
		m = new MoveImp("Ke1");
		assertTrue(e1.equals(m.getDestination()));
		m = new MoveImp("Rf2");
		assertTrue(f2.equals(m.getDestination()));
		m = new MoveImp("Qg3");
		assertTrue(g3.equals(m.getDestination()));
		m = new MoveImp("Ne7");
		assertTrue(e7.equals(m.getDestination()));
		m = new MoveImp("Bc5");
		assertTrue(c5.equals(m.getDestination()));
		
		// Good ones -- Ambiguous
		m = new MoveImp("ce2");
		assertTrue(e2.equals(m.getDestination()));
		m = new MoveImp("Rbf2");
		assertTrue(f2.equals(m.getDestination()));
		m = new MoveImp("Qec3");
		assertTrue(c3.equals(m.getDestination()));
		m = new MoveImp("Ngc5");
		assertTrue(c5.equals(m.getDestination()));
		
		// Bad ones
		// Bad ones -- bad piece
		m = new MoveImp("Ph7");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("Wh7");
		assertTrue(m.getDestination() == null);
		
		// Bad ones -- bad destination
		m = new MoveImp("7");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("a");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("aa");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("Ba");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("h9");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("a9");
		assertTrue(m.getDestination() == null);
		m = new MoveImp("h0");

	}

	@Test
	public void testValid() {

		Move m;
		// Good ones
		m = new MoveImp("Rh2");	// -- Normal
		assertTrue(m.valid());
		m = new MoveImp("Qd3");
		assertTrue(m.valid());
		m = new MoveImp("ce2");	// -- Ambiguous
		assertTrue(m.valid());
		m = new MoveImp("Rbf2");
		assertTrue(m.valid());
		m = new MoveImp("Qec3");
		assertTrue(m.valid());
		
		// Bad ones
		m = new MoveImp("Ba");	// -- Bad destination
		assertFalse(m.valid());
		m = new MoveImp("h9");
		assertFalse(m.valid());
		m = new MoveImp("a9");
		assertFalse(m.valid());
		m = new MoveImp("Ph7");	// -- Bad piece
		assertFalse(m.valid());
		m = new MoveImp("Wh7");
		assertFalse(m.valid());
		m = new MoveImp("Qqd3");
		assertFalse(m.valid());
		m = new MoveImp("Noh7");
		assertFalse(m.valid());
		m = new MoveImp("Kgh8"); // -- king special case
		assertFalse(m.valid());
		m = new MoveImp("nh7");	// -- Lower case
		assertFalse(m.valid());

	}

}
