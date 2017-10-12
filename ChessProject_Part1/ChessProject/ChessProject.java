import java.awt.*;
import java.awt.event.*;
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
	Boolean correctMove = true;
	Boolean validMove;
	Boolean wrongMove = true;



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
		Checks if the piece is a King (same premise as checkWhiteOpponent
	*/
	private String KingChecker(int newX, int newY){
		Component c1 = chessBoard.findComponentAt(newX, newY);
		if(c1 instanceof JLabel){
			JLabel awaitingPiece = (JLabel)c1;
			String tmp1 = awaitingPiece.getIcon().toString();
			return tmp1;
		}else{
			return "";
		}
	}

	/*Checks is the kings are within one space of each other */

	private boolean KingSpace(int newX, int newY){
		if ((piecePresent(newX, newY + 75) && KingChecker(newX, newY + 75).contains("King")) || (piecePresent(newX, newY - 75) && KingChecker(newX, newY - 75).contains("King"))) {
			return true;

		} else if ((piecePresent(newX + 75, newY) && KingChecker(newX + 75, newY).contains("King")) || (piecePresent(newX - 75, newY) && KingChecker(newX - 75, newY).contains("King"))) {
			return true;

		} else if ((piecePresent(newX - 75, newY + 75) && KingChecker(newX - 75, newY + 75).contains("King")) || (piecePresent(newX + 75, newY - 75) && KingChecker(newX + 75, newY - 75).contains("King"))) {
			return true;

		}  else if ((piecePresent(newX - 75, newY - 75) && KingChecker(newX - 75, newY - 75).contains("King")) || (piecePresent(newX + 75, newY + 75) && KingChecker(newX + 75, newY + 75).contains("King"))) {
			return true;

		}
		return false;
	}

	/*
		White Wins
	*/

	public void WhiteWinner(Boolean a)
	{
		JOptionPane.showMessageDialog(null, "Congratulations! White Wins!");
		System.exit(0);
	}

	/*
    Black Wins
    */
	public void BlackWinner(Boolean b)
	{
		JOptionPane.showMessageDialog(null, "Congratulations! Black Wins!");
		System.exit(0);
	}


	/*

			Checks if piece moved onto is Black

		*/

	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black"))&& (!tmp1.contains("King")))){
			opponent = true;
		}
		else if (((tmp1.contains("BlackKing")))) /* This is when a piece captures the black king.
		 White wins due to this*/
		{
			WhiteWinner(true);
			JOptionPane.showMessageDialog(null, "");
			opponent = true;
		}
		else
		{
			opponent = false;
		}
		return opponent;
	}
	/*

	Checks if piece moved onto is White

	*/
	private Boolean checkBlackOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White"))&& (!tmp1.contains("King")))){
			opponent = true;
		}
		else if (((tmp1.contains("WhiteKing")))) /* This is when a piece captures the black king.
		 White wins due to this*/
		{
			BlackWinner(true);
			JOptionPane.showMessageDialog(null, "");
			opponent = true;
		}
		else
		{
			opponent = false;
		}
		return opponent;
	}
	/*
		Makes White go first - Turns
	*/
	private void switchTurns(){
		if(validMove){
			correctMove = !correctMove;
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
	This prints a message to the console when the user makes an invalid move
	 */
	private void invalidMove(){
		if (validMove){
			wrongMove =!validMove;
		}
		if (wrongMove){
			System.out.println("Invalid Move");
		}
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
		//correctMove = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));
		 validMove = false;
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

	/*	if (pieceName.contains("Pawn") && wrongMove){
			//System.out.println("Invalid Move: \n " +"The pawn can only move +1 or -1 on the y axis \n " + "It can only move 1 on the y and x axis when taking a piece \n" + "It cannot take it's own pieces \n" + "All pieces must stay on the board");
		invalidMove();
		}
		if(pieceName.contains("Knight") && !validMove){
			System.out.println("Invalid Move: \n"+ "The knight can only move x1&&y2 or x2&&y1 \n" + "It cannot take it's own pieces");
		}
		if(pieceName.contains("Bishup") && !validMove){
			System.out.println("Invalid Move: \n" + "The bishop can only move on the diagonal \n" + "It cannot move through pieces \n" + "It cannot take it's own pieces \n" + "All pieces must stay on the board");
		}
		if(pieceName.contains("Rook") && !validMove){
			System.out.println("Invalid Move: \n" + "The rook can only move on one axis at a time \n" + "It cannot move through pieces \n" + "It cannot take it's own pieces \n" + "All pieces must stay on the board");
		}
		if(pieceName.contains("Queen")&& !validMove){
			System.out.println("Invalid Move: \n" + " The queen cannot move through pieces \n" + "It cannot take it's own pieces \n" + "All pieces must stay on the board \n" + "All pieces must stay on the board");
		}
		if (pieceName.contains("King") && !validMove){
			System.out.println("Invalid Move: \n" + "The king can only move +1 or -1 on each axis per move \n " + "It cannot move within one square of the other king \n" +"It cannot move into check \n" +"If in check, it must move out if possible \n" + "It cannot move through pieces \n" + "It cannot take it's own pieces \n" + "All pieces must stay on the board \n" + "All pieces must stay on the board");
		}
*/
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

		 if (pieceName.equals("BlackPawn") && !correctMove) {
			 //If black is in starting position you should be allowed move it
			 //6 spaces down on Y axis is where black pawn starts
			 if (startY == 6) {
				 //PAWN'S FIRST MOVE (only 1 or 2 spaces up on y axis)
				 //start y > landing y as you can't go backwards & xmovement == 0 so you can't move diagonally
				 if (((yMovement == 1 || yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
					 if (yMovement == 2) {
						 if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
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
				 //piece must be present to move 1 on xmovement
				 else if (((yMovement == 1) && (startY > landingY) && (xMovement == 1))) {
					 if (piecePresent(e.getX(), e.getY())) {
						 if (checkBlackOpponent(e.getX(), e.getY())) {
							 validMove = true;
							 if (landingY == 0) {
								 Blacksuccess = true;
							 }

						 }
					 }
				 }
			 } else { //WHERE PAWN MAKES ALL MOVES AFTER THE FIRST
				 if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
					 if (!piecePresent(e.getX(), e.getY())) {
						 validMove = true;
					 }
				 } else if (((yMovement == 1) && (startY > landingY) && (xMovement == 1))) {
					 if (piecePresent(e.getX(), e.getY())) {
						 if (checkBlackOpponent(e.getX(), e.getY())) {
							 validMove = true;
							 if (landingY == 0) {
								 Blacksuccess = true;
							 }
						 }
					 }
				 } else {
					 validMove = false;

				 }
			 }
			 switchTurns();
		 }
		if (pieceName.equals("WhitePawn") && correctMove) {
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
							if (checkWhiteOpponent(e.getX(), e.getY())) {
								validMove = true;
								if (landingY == 7) {
									Whitesuccess = true;
								}
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
								if(checkWhiteOpponent(e.getX(),e.getY())){
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

		else if (pieceName.equals("WhiteKnight") && correctMove){
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;

			}
		else {
				/*
				ALLOWS THE KNIGHT TO MOVE CORRECTLY
				STOPS THE KNIGHT FROM TAKING IT'S OWN PIECES
				*/

				if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement ==1 )){
					if (piecePresent(e.getX(), e.getY())) {
						if (pieceName.contains("White")){
							validMove = checkWhiteOpponent(e.getX(),(e.getY()));
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

		else if (pieceName.equals("BlackKnight") && !correctMove){
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			}
			else {

				/*
				ALLOWS THE KNIGHT TO MOVE CORRECTLY
				STOPS THE KNIGHT FROM TAKING IT'S OWN PIECES
				*/

				if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement ==1 )){
					if (piecePresent(e.getX(), e.getY())) {
						if (pieceName.contains("Black")){
							validMove = checkBlackOpponent(e.getX(),(e.getY()));
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

		else if (pieceName.equals("WhiteBishup")&& correctMove) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);

			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;


			} else {
				validMove = true;
				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {

					/*
					4 conditions to detect what diagonal the bishop will travel and if there is a piece in the way or not.
					 */
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
								validMove = checkWhiteOpponent(e.getX(), e.getY());
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

		else if (pieceName.equals("BlackBishup")&& !correctMove) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;

			}
				/*
					4 conditions to detect what diagonal the bishop will travel and if there is a piece in the way or not.
					 */
			else {
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
								validMove = checkWhiteOpponent(e.getX(), e.getY());
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

		  if (pieceName.equals("WhiteRook")&& correctMove){
			Boolean inTheWay = false;
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0 || landingY > 7))) {
				validMove = false;

			} else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						xMovement = Math.abs(startX - landingX);
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						 yMovement = Math.abs(startY - landingY);
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}
					if (inTheWay) {
						validMove = false;

					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								validMove = checkWhiteOpponent(e.getX(), e.getY());
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

		if (pieceName.equals("BlackRook")&& !correctMove){
			Boolean inTheWay = false;
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0 || landingY > 7))) {
				validMove = false;

			} else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						xMovement = Math.abs(startX - landingX);
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						yMovement = Math.abs(startY - landingY);
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}
					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("Black")) {
								validMove = checkBlackOpponent(e.getX(), e.getY());
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
				/*
				The queen is essentially a combination of the bishop and rook
				 */
		else if(pieceName.equals("WhiteQueen")&& correctMove){
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0 || landingY > 7))) {
				validMove = false;
			}
			else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						 xMovement = Math.abs(startX - landingX);
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						 yMovement = Math.abs(startY - landingY);
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {
								validMove = checkWhiteOpponent(e.getX(), e.getY());

							}

						} else {
							validMove = true;

						}

					}
				} else {
					/*
					essentially the same movement as the bishop - diagonal movement
					 */
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
									validMove = checkWhiteOpponent(e.getX(), e.getY());
								}
							} else {
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}

			}
			switchTurns();
		}

		/*
				The queen is essentially a combination of the bishop and rook
				 */
		else if(pieceName.equals("BlackQueen")&& !correctMove){
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0 || landingY > 7))) {
				validMove = false;
			}
			else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						xMovement = Math.abs(startX - landingX);
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						yMovement = Math.abs(startY - landingY);
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("Black")) {
								validMove = checkBlackOpponent(e.getX(), e.getY());

							}

						} else {
							validMove = true;

						}

					}
				} else {
					/*
					essentially the same movement as the bishop - diagonal movement
					 */
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
								if (pieceName.contains("Black")) {
									validMove = checkBlackOpponent(e.getX(), e.getY());
								}
							} else {
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}

			}
			switchTurns();
		}

		else if(pieceName.equals("WhiteKing")&& correctMove) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7)) || KingSpace(e.getX(), e.getY())) {
				validMove = false;
			} else {
				if ((yMovement == 1 && xMovement == 0) || (yMovement == 0 && xMovement == 1) || (yMovement ==1 && xMovement ==1)) {
					{
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White")) {

								validMove = checkWhiteOpponent(e.getX(), e.getY());
							}
						} else {
							validMove = true;

						}
					}
				}
					else{
						validMove = false;

					}

				}
				switchTurns();
			}

		else if(pieceName.equals("BlackKing")&& !correctMove) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7)) || KingSpace(e.getX(), e.getY())) {
				validMove = false;
			} else {
				if ((yMovement == 1 && xMovement == 0) || (yMovement == 0 && xMovement == 1) || (yMovement == 1 && xMovement ==1 )) {
					{
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("Black")) {

								validMove = checkBlackOpponent(e.getX(), e.getY());
							}
						} else {
							validMove = true;

						}
					}
				}
				else{
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
				invalidMove();
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


