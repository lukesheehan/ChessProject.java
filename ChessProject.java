/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            } else {
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
            }
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
    private Boolean checkWhiteOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp2 = awaitingPiece.getIcon().toString();
        if (((tmp2.contains("Black")))) {
            opponent = true;
            if (((tmp2.contains("King")))) {
                JOptionPane.showMessageDialog(null, "Game Over-White wins!!!!!!!!!!!!!");
                System.out.println("Game over");
                System.exit(0);
            }

        } else {
            opponent = false;
        }
        return opponent;
    }

    // This is a method to check if a piece is a white piece.
    private Boolean checkBlackOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp2 = awaitingPiece.getIcon().toString();
        if (((tmp2.contains("White")))) {
            opponent = true;
            if (((tmp2.contains("King")))) {//if the piece is king then you win
                JOptionPane.showMessageDialog(null, "Game Over-Black wins!!!!!!!!!!!!!");
                System.out.println("Game over");
                System.exit(0);

            }

        } else {
            opponent = false;
        }
        return opponent;
    }

//method that check for another king ;
    private Boolean ProtectKing(int barX, int barY) {
        Boolean myKing = true;

        String tempo = "";
//loop to check the radient for king
        for (int x = barX - 1; x <= barX + 1; x++) {
            for (int y = barY - 1; y <= barY + 1; y++) {
                Component c2 = chessBoard.findComponentAt(x * 75, y * 75);
                if ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7)) {
                    if (c2 instanceof JLabel) {
                        JLabel kingCheck = (JLabel) c2;
                        tempo = kingCheck.getIcon().toString();
                        if (tempo.contains("King")) {
                            myKing = false;
                        }
                    }
                }
            }
        }
        return myKing;
    }


    /*
     This method is called when we press the Mouse. So we need to find out what piece we have
     selected. We may also not have selected a piece!
     */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) {
            return;
        }

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
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
     This method is used when the Mouse is released...we need to make sure the move was valid before
     putting the piece back on the board.
     */
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }

        chessPiece.setVisible(false);
// booleans to turn pawn into a queen
        Boolean whitesuccess = false;
        Boolean blacksuccess = false;

        Boolean progression = false;
        Boolean inTheWay = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;

        /*
         This code helps us to understand what is happening when a user starts
         to move pieces around the board. We are using the standard output to show
         - the name of the piece that is being moved
         - the starting square of a piece that is clicked
         - the distance in the x-direction that a player is trying to move the piece
         - the distance in the y-direction that a player is trying to move the piece
         - the landing square of where a player is returning the piece to the board

         Having this information printed out to the standard output as we test and construct the
         solution allows us to understand the constructs of the game...This code snippet below
         should be pasted into the following place:

         */
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int distance = Math.abs(startX - landingX);

        System.out.println("----------------------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + distance);
        System.out.println("The landing coordinates are : " + "( " + landingX + "," + landingY + ")");
        System.out.println("----------------------------------------------");

        // // this condition uses the name of each piece
        //if pawn is on its first move then he can move 1 or 2 sqares
        if (pieceName.equals("WhitePawn")) {
            if ((startY == 1) && (startX == landingX) && (((landingY - startY) == 1) || (landingY - startY) == 2)) {
                if ((!piecePresent(e.getX(), e.getY()) && (!piecePresent(e.getX(), e.getY() - 75)))) {
                    validMove = true;
                    System.out.println("Valid move 1");
                }
//check if the sqare were the pawn is put back on the board is 1 diagonal to the left or right of the starting square
            } else if ((Math.abs(landingX - startX) == 1) && (((landingY - startY) == 1))) {
                if (piecePresent(e.getX(), e.getY())) {
                    if (checkWhiteOpponent(e.getX(), e.getY())) {
                        validMove = true;
                        System.out.println("Valid move 2");
                        //if pawn reached the end of the board (opponent side)
                        if (landingY == 7) {
                            whitesuccess = true;
                        }
                    } else {
                        validMove = false;
                        System.out.println("Invalid move 3");
                    }
                } else {
                    validMove = false;
                    System.out.println("Invalid move 4");
                }
            } else if ((startY != 1) && ((startX == landingX) && (((landingY - startY) == 1)))) {
                // If there is a piece in the way
                if (!piecePresent(e.getX(), e.getY())) {
                    validMove = true;
                    System.out.println("Valid move 4.1");
                    if (landingY == 7) {
                        whitesuccess = true;
                    }
                } else {
                    validMove = false;
                    System.out.println("Invalid move 5");
                }
            } else {
                validMove = false;
                System.out.println("Invalid move 6");
            }
        }
        //black pawn
        if (pieceName.equals("BlackPawn")) {
            if ((startY == 6) && (startX == landingX) && (((startY - landingY) == 1) || (startY - landingY) == 2)) {
                if ((startY - landingY) == 2) {
                    for (int i = 0; i < yMovement; i++) {
                        //in the way condition when black pawn has no moves
                        if (piecePresent(e.getX(), initialY - (i * 75))) {
                            inTheWay = true;
                            System.out.println("inthe way for BlackPawn");
                            break;
                        } else {
                            inTheWay = false;
                        }
                    }
                }
                if (inTheWay) {
                    validMove = false;
                } else {
                    if (!piecePresent(e.getX(), e.getY())) {

                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            } else if ((Math.abs(startX - landingX) == 1) && (((startY - landingY) == 1))) {//check diagonal movements and opponent
                if (piecePresent(e.getX(), e.getY())) {
                    if (checkBlackOpponent(e.getX(), e.getY())) {

                        validMove = true;
                        if (landingY == 0) {
                            blacksuccess = true;

                        }
                    } else {
                        validMove = false;
                    }
                } else {
                    validMove = false;
                }
            } else if ((startY != 6) && ((startX == landingX) && (((startY - landingY) == 1)))) {
                if (!piecePresent(e.getX(), e.getY())) {

                    validMove = true;
                    if (landingY == 0) {
                        blacksuccess = true;

                    }
                } else {
                    validMove = false;
                }
            } else {
                validMove = false;
            }
        }

        //End of Pawn Moves
        //start of Knight
        if (pieceName.contains("Knight")) {
            if (((landingX < 0) || landingX > 7) || ((landingY < 0) || landingY > 7)) {
                validMove = false;
                System.out.println("invalid move for horsie");
                //all possible L moves
            } else if (((landingX == startX + 1) && (landingY == startY + 2)) || ((landingX == startX - 1)
                    && (landingY == startY + 2)) || (landingX == startX + 2)
                    && (landingY == startY + 1)
                    || (landingX == startX - 2)
                    && (landingY == startY + 1) || (landingX == startX + 1)
                    && (landingY == startY - 2) || ((landingX == startX - 1)
                    && (landingY == startY - 2))
                    || ((landingX == startX + 2)
                    && landingY == startY - 1) || ((landingX == startX - 2)
                    && (landingY == startY - 1))) {
                if (piecePresent(e.getX(), (e.getY()))) {
                    if (pieceName.contains("White")) {
                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            System.out.print("check opponent");
                        } else {
                            validMove = false;
                            System.out.print("invalid check black opponent");
                        }
                    } else if (checkBlackOpponent(e.getX(), e.getY())) {
                        validMove = true;

                    } else {
                        validMove = false;
                        System.out.println("invalid move");
                    }
                } else {
                    validMove = true;
                }
            } else {
                validMove = false;
            }
        }
//start bishup
        if (pieceName.contains("Bishup")) {
            //determine the diagonal moves
            if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                validMove = false;
            } else {

                validMove = true;
                if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                    if ((startX - landingX < 0) && (startY - landingY < 0)) {
                        //check by using a loop for a piece in the way
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent(initialX + (i * 75), initialY + (i * 75))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent(initialX + (i * 75), initialY - (i * 75))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent(initialX - (i * 75), initialY - (i * 75))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent(initialX - (i * 75), initialY + (i * 75))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else if (piecePresent(e.getX(), e.getY())) {
                        if (pieceName.contains("White")) {
                            validMove = checkWhiteOpponent(e.getX(), e.getY());
                        } else {
                            validMove = checkBlackOpponent(e.getX(), e.getY());
                        }
                    } else {
                        validMove = true;
                    }
                } else {
                    validMove = false;
                }
            }
        }

        //end of bishop
        //start rook
        if (pieceName.contains("Rook")) {
            if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                validMove = false;
            } else if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
                    || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
                if (Math.abs(startX - landingX) != 0) {
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
                } else if (startY - landingY > 0) {
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
                if (inTheWay) {
                    validMove = false;
                } else if (piecePresent(e.getX(), e.getY())) {
                    if (pieceName.contains("White")) {
                        validMove = checkWhiteOpponent(e.getX(), e.getY());
                    } else {
                        validMove = checkBlackOpponent(e.getX(), e.getY());
                    }
                } else {
                    validMove = true;
                }
            } else {
                validMove = false;
            }
        }
//queen
//combine rock and bishop moves
        if (pieceName.contains("Queen")) {
            if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                validMove = false;
            } else if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
                    || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
                if (Math.abs(startX - landingX) != 0) {
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
                } else if (startY - landingY > 0) {
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
                if (inTheWay) {
                    validMove = false;
                } else if (piecePresent(e.getX(), e.getY())) {
                    if (pieceName.contains("White")) {
                        validMove = checkWhiteOpponent(e.getX(), e.getY());
                    } else {
                        validMove = checkBlackOpponent(e.getX(), e.getY());
                    }
                } else {
                    validMove = true;
                }
            } else if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                if ((startX - landingX < 0) && (startY - landingY < 0)) {
                    for (int i = 0; i < distance; i++) {
                        if (piecePresent(initialX + (i * 75), initialY + (i * 75))) {
                            inTheWay = true;
                        }
                    }
                } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                    for (int i = 0; i < distance; i++) {
                        if (piecePresent(initialX + (i * 75), initialY - (i * 75))) {
                            inTheWay = true;
                        }
                    }
                } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                    for (int i = 0; i < distance; i++) {
                        if (piecePresent(initialX - (i * 75), initialY - (i * 75))) {
                            inTheWay = true;
                        }
                    }
                } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                    for (int i = 0; i < distance; i++) {
                        if (piecePresent(initialX - (i * 75), initialY + (i * 75))) {
                            inTheWay = true;
                        }
                    }
                }
                if (inTheWay) {
                    validMove = false;
                } else if (piecePresent(e.getX(), e.getY())) {
                    if (pieceName.contains("White")) {
                        validMove = checkWhiteOpponent(e.getX(), e.getY());
                    } else {
                        validMove = checkBlackOpponent(e.getX(), e.getY());
                    }
                } else {
                    validMove = true;
                }
            } else {
                validMove = false;
            }
        }
//king
        if (pieceName.contains("King")) {                                 //Movement logic for the all Kings,  (This piece is finished)
            // <editor-fold>
            if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || landingY > 7)) { //If landing square is within the board...
                validMove = false;
            } else if (((Math.abs(startX - landingX) <= 1)) && ((Math.abs(startY - landingY) <= 1))) { //limiting move to 1 square horizontal, diagonal or vertical...
                if (ProtectKing(landingX, landingY)) {                      //Checking King has required buffer zone...
                    System.out.println("*** Testing evoking Protection King method...");

                    if (piecePresent(e.getX(), e.getY())) {
                        if (pieceName.contains("White")) {                  //If active piece is White...
                            if (checkWhiteOpponent(e.getX(), e.getY())) {   //if  piece is Black, take it.
                                validMove = true;
                            } else {
                                validMove = false;                          //friedly piece is already on this spot.
                                System.out.println("King test 1: ");
                            }
                        } else if (pieceName.contains("Black")) {           //If selected piece is Black...
                            if (checkBlackOpponent(e.getX(), e.getY())) {   //if  piece is Black, take it.
                                validMove = true;
                            } else {
                                validMove = false;
                                System.out.println("King test 2: ");
                            }
                        }
                    } else if (!piecePresent(e.getX(), e.getY())) {         //If there's no pieces on landing square...
                        validMove = true;
                    }
                }
            } else {
                validMove = false;
                System.out.println("Failed to move King: ");
            }
        }

        // end of king
        // valid moves
        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else if (progression) {
            int location = 0 + (e.getX() / 75);
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                pieces = new JLabel(new ImageIcon("BlackQueen.png"));//change pawn into a qeen
                parent = (JPanel) chessBoard.getComponent(location);
                parent.add(pieces);
            }
        } else if (whitesuccess) {
            int location = 56 + (e.getX() / 75);
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                parent = (JPanel) chessBoard.getComponent(location);
                parent.add(pieces);
            } else {
                Container parent = (Container) c;
                pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                parent = (JPanel) chessBoard.getComponent(location);
                parent.add(pieces);
            }
        } //from here for back
        else if (blacksuccess) {

            int location = 0 + (e.getX() / 75);
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                parent = (JPanel) chessBoard.getComponent(location);
                parent.add(pieces);
            } else {
                Container parent = (Container) c;
                pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                parent = (JPanel) chessBoard.getComponent(location);
                parent.add(pieces);
            }
        } else {
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
