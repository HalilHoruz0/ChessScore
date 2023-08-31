package score;
import piece.Piece;

public class Chess {

	public double calculationScore(Piece piece, Piece[][] board, int row, int col) {
		
		ScoreCalculation scoreCalculation = new ScoreCalculation();
		
		return scoreCalculation.calculationScore(piece, board, row, col);
	}
	
	//Belirtilen satraç taşının kısaltılmış haliyle adını döndüren metot
	public static String getPieceNameByPieceAbbreviation(String pieceAbbreviation) {
		
		switch (pieceAbbreviation) {
		
		 	case "p":
		 		return "Piyon";

		 	case "a":
		 		return "At";

		 	case "f":
		 		return "Fil";

		 	case "k":
		 		return "Kale";

		 	case "v":
		 		return "Vezir";

		 	case "s":
		 		return "�ah";

		 	default:
		 		return "Bilinmeyen Ta�";
		}
	}
	
	//Belirtilen satranç taşının kısaltılmış haliyle sahip olduğu katsayı puanını döndüren fonksiyon
    public static int getPieceCoefficientByPieceAbbreviation(String pieceAbbreviation) {

        switch (pieceAbbreviation) {

            case "p":
                return 1;

            case "a":
                return 3;

            case "f":
                return 3;

            case "k":
                return 5;

            case "v":
                return 9;

            case "s":
                return 100;
                
            default:
                return 0;  //Bilinmeyen bir karakter taşın puanına etki etmeyecek.

        }
    }
}
