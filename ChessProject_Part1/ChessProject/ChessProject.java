import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	Boolean isWhiteTurn = true;
	Boolean validMove;



	public ChessProject() {
		Dimension boardSize = new Dimension(600, 600);

		//  Use a Layered Pane for this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		//Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
			else
				square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
		}

		// Setting up the Initial Chess board.
		for (int i = 8; i < 16; i++) {
			pieces = new JLabel(new ImageIcon("WhitePawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(0);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(1);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(6);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
		panels = (JPanel) chessBoard.getComponent(2);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
		panels = (JPanel) chessBoard.getComponent(5);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKing.png"));
		panels = (JPanel) chessBoard.getComponent(3);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
		panels = (JPanel) chessBoard.getComponent(4);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(7);
		panels.add(pieces);
		for (int i = 48; i < 56; i++) {
			pieces = new JLabel(new ImageIcon("BlackPawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(56);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(57);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(62);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishup.png"));
		panels = (JPanel) chessBoard.getComponent(58);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishup.png"));
		panels = (JPanel) chessBoard.getComponent(61);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKing.png"));
		panels = (JPanel) chessBoard.getComponent(59);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackQueen.png"));
		panels = (JPanel) chessBoard.getComponent(60);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(63);
		panels.add(pieces);
	}

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y) {
		Component c = chessBoard.findComponentAt(x, y);
		if (c instanceof JPanel) {
			return false;
		} else {
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY) {
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if (((tmp1.contains("Black")))) {
			oponent = true;
		} else {
			oponent = false;
		}
		return oponent;
	}

	/*
		This is a method to check if a piece is a White piece.
	*/
	private Boolean checkBlackOponent(int newX, int newY) {
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if (((tmp1.contains("White")))) {
			oponent = true;
		} else {
			oponent = false;
		}
		return oponent;
	}

	/*
		Makes White go first - Turns
	*/

	private void switchTurns(){
		if(validMove){
			isWhiteTurn = !isWhiteTurn;
		}
	}

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX() / 75);
		startY = (e.getY() / 75);
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}

	/*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null) return;

		chessPiece.setVisible(false);
		Boolean Whitesuccess = false;
		Boolean Blacksuccess = false;
		validMove = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));
		Boolean validMove = false;

		int landingX = (e.getX() / 75);
		int landingY = (e.getY() / 75);
		int xMovement = Math.abs((e.getX() / 75) - startX);
		int yMovement = Math.abs((e.getY() / 75) - startY);


		System.out.println("-----------------------------------------------");
		System.out.println("The piece that is being moved is: " + pieceName);
		System.out.println("The starting coordinates are: " + "( " + startX + "," + startY + ")");
		System.out.println("The x Movement is: " + xMovement);
		System.out.println("The y Movement is: " + yMovement);
		System.out.println("The landing coordinates are: " + "( " + landingX + "," + landingY + ")");
		System.out.println("-----------------------------------------------");
		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
				So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/
		//DELETE THIS WHEN FINISHED, MAKES THE BLACK QUEEN MOVE WHEREVER
		if (pieceName.equals("BlackQueen")) {
			validMove = true;
		}
		if (pieceName.equals("WhiteQueen")) {
			validMove = true;
		}
		else if (pieceName.equals("BlackPawn") && isWhiteTurn) {
			//If black is in starting position you should be allowed move it
			//6 spaces down on Y axis is where black pawn starts
			if (startY == 6) {

				//PAWN'S FIRST MOVE (only 1 or 2 spaces up on y axis)
				//start y > landing y as you can't go backwards & xmovement == 0 so you can't move diagonally
			if (((yMovement == 1 || yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
				if (yMovement == 2) {
					if((!piecePresent(e.getX(),e.getY()))&&(!piecePresent(e.getX(),(e.getY()+75)))){
						validMove = true;
					}
				}
				//Detects if there's a piece in the way
				else {
					if(!piecePresent(e.getX(),e.getY())){
					validMove = true;
					}
				}
			}

			//Taking an enemy piece.. y movement and x movement == 1 so it moves up and diagonally once
			//piece must be present to move 1 on xmovement
			else if (((yMovement == 1) && (startY > landingY) && (xMovement == 1))) {
				if(piecePresent(e.getX(),e.getY())){
					if(checkBlackOponent(e.getX(),e.getY())){
						validMove = true;

						if (landingY == 0){
							Blacksuccess = true;
						}
					}
				}
				}
		}
		else { //WHERE PAWN MAKES ALL MOVES AFTER THE FIRST
				if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					}
				}
				else if (((yMovement == 1) && (startY > landingY) && (xMovement == 1))) {
					if(piecePresent(e.getX(),e.getY())){
						if(checkBlackOponent(e.getX(),e.getY())){
							validMove = true;
							if (landingY == 0){
								Blacksuccess = true;
							}
						}
					}
				}
			else {
					validMove = false;
				}
			}
			switchTurns();
		}
		else if (pieceName.equals("WhitePawn") && isWhiteTurn) {
				if (startY == 1) {
					if (((yMovement == 1 || yMovement == 2)) && (landingY > startY) && (xMovement == 0)) {
						if (yMovement == 2) {
							if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
								validMove = true;
							}
						}
						//Detects if there's a piece in the way
						else {
							if (!piecePresent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
					}
//Taking an enemy piece.. y movement and x movement == 1 so it moves up and diagonally once
					//piece must be present to move 1 on Xmovement
					else if (((yMovement == 1) && (startY > landingY) && (xMovement == 1))) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
								if (landingY == 7) {
									Whitesuccess = true;
								}
							} else {
								validMove = false;
							}
						}
					}
				}
					else { //WHERE PAWN MAKES ALL MOVES AFTER THE FIRST
						if (((yMovement == 1)) && (landingY > startY) && (xMovement == 0)) {
							if (!piecePresent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
						else if (((yMovement == 1) && (landingY > startY) && (xMovement == 1))) {
							if(piecePresent(e.getX(),e.getY())){
								if(checkWhiteOponent(e.getX(),e.getY())){
									validMove = true;
									if (landingY == 7) {
										Whitesuccess = true;
									}
								}
							}
						}
						else {
							validMove = false;
						}
				}
						switchTurns();
		}

		else if (pieceName.equals("WhiteKnight") && isWhiteTurn){
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
		else {
				if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement ==1 )){
					if (piecePresent(e.getX(), e.getY())) {
						if (pieceName.contains("White")){
							validMove = checkWhiteOponent(e.getX(),(e.getY()));
						}
					}
				else {
						validMove = true;
					}
				}
				else {
					validMove = false;
				}
			}
			switchTurns();
		}

		else if (pieceName.equals("BlackKnight") && isWhiteTurn){
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			else {
				if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement ==1 )){
					if (piecePresent(e.getX(), e.getY())) {
						if (pieceName.contains("Black")){
							validMove = checkBlackOponent(e.getX(),(e.getY()));
						}
					}
					else {
						validMove = true;
					}
				}
				else {
					validMove = false;
				}
			}
			switchTurns();
		}

		else if (pieceName.equals("WhiteBishup")&&isWhiteTurn) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				validMove = true;
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {

					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), e.getY())) {
							if (pieceName.contains("White")) {
								validMove = checkWhiteOponent(e.getX(), e.getY());
							}
						} else {
							validMove = true;
						}
					}
				} else {
					validMove = false;
				}
			}
			switchTurns();
		}

		else if (pieceName.equals("BlackBishup")&&isWhiteTurn) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				validMove = true;
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					}  else {
						if (piecePresent(e.getX(), e.getY())) {
							if (pieceName.contains("White")) {
								validMove = checkWhiteOponent(e.getX(), e.getY());
							}
						} else {
							validMove = true;
						}
					}
				} else {
					validMove = false;
				}
			}
			switchTurns();
		}

			if (!validMove) {
				int location;
				if (startY == 0) {
					location = startX;
				} else {
					location = (startY * 8) + startX;
				}
				String pieceLocation = pieceName + ".png";
				pieces = new JLabel(new ImageIcon(pieceLocation));
				panels = (JPanel) chessBoard.getComponent(location);
				panels.add(pieces);
			}
			else {
				if (Blacksuccess) {
					int location = 0 + (e.getX() / 75);
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);
						pieces = new JLabel(new ImageIcon("BlackQueen.png"));
						parent = (JPanel) chessBoard.getComponent(location);
						parent.add(pieces);
					}
				}
				else if (Whitesuccess) {
						int location = 56 + (e.getX() / 75);
						if (c instanceof JLabel) {
							Container parent = c.getParent();
							parent.remove(0);
							pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
							parent = (JPanel) chessBoard.getComponent(location);
							parent.add(pieces);
						}
					}

				else {
					if (c instanceof JLabel) {
						Container parent = c.getParent();
						parent.remove(0);
						parent.add(chessPiece);
					} else {
						Container parent = (Container) c;
						parent.add(chessPiece);
					}
					chessPiece.setVisible(true);
				}
			}
		}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/*
		Main method that gets the ball moving.
	*/
	public static void main(String[] args) {
		JFrame frame = new ChessProject();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}


