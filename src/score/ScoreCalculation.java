package score;

import piece.Piece;

public class ScoreCalculation {
	
	
	public double calculationScore(Piece piece, Piece[][] board, int row, int col) {
		
		if(piece.isThreat(board, row, col))
			return piece.getPieceCoefficient() / 2.0;
		else
			return piece.getPieceCoefficient();		
	}
	
	public Piece createChessPiece(String pieceAbbreviation, String pieceFullAbbreviation) {
		
		//Taşın kısaltılmış haliyle ait olduğu taşın katsayı puanı elde edilir.
		int pieceCoefficient = Chess.getPieceCoefficientByPieceAbbreviation(pieceAbbreviation);
		
		return new Piece(Chess.getPieceNameByPieceAbbreviation(pieceAbbreviation), pieceFullAbbreviation, pieceCoefficient);
		
		
	}
}
