package piece;

public class Piece {
	
	//Satranç tahtasının adı
	private String pieceName;
	
	//Satranç tahtasının kısaltması
	private String pieceAbbreviation;
	
	//Satranç tahtasının katsayı puanı
	private int pieceCoefficient;
	
	public Piece(String pieceName, String pieceAbbreviation, int pieceCoefficient) {
		this.pieceName = pieceName;
		this.pieceAbbreviation = pieceAbbreviation;
		this.pieceCoefficient = pieceCoefficient;
	}
	
	//Bu metod satranç taşını getirir.
	public String getPieceName() {
		return pieceName;
	}
	
	//Bu metod satranç taşının kısaltılmış halini getirir.
	public String getPieceAbbreviation() {
		return pieceAbbreviation;
	}
	
	//Bu metod satranç taşının katsayı puanını getirir.
	public int getPieceCoefficient() {
		return pieceCoefficient;
	}
	
	//Bu fonksiyon ile tehdit altındaki taşları tespit edeceğiz.
		public boolean isThreat(Piece[][] board, int row, int col) {
			
			//Kontrol edilen satranç taşı
			Piece piece = board[row][col];
			
			//Satranç taşının rengi
			String pieceColor = piece.getPieceAbbreviation().substring(1);
			
			//For döngüsü ile rakip taşları kontrol ediyoruz.
			for (int r = 0; r < board.length; r++) { //Row

	            for (int c = 0; c < board[0].length; c++) { //Col

	                //Rakip taş
	                Piece opponentPiece = board[r][c];

	                //Rakip taş saptandıysa ve rakip taş ile kontrol ettiğimiz taş aynı renkte değilse koşul içine girer. 
	                if (opponentPiece != null && !opponentPiece.getPieceAbbreviation().substring(1).equals(pieceColor)) {

	                    //Rakip taşın, kontrol edilen taşı tehdit etme kontrolü
	                    if (canThreat(opponentPiece, r, c, row, col, board)) {
	                    	
	                        return true;
	                    }
	                }
	            }
	        }
			return false;
		}
		
		 //Rakip taşın (Vezir, At ve Piyon için), kontrol edilen taşı tehdit etme durumunu kontrol eden fonksiyon
	    private boolean canThreat(Piece attacker, int attackerRow, int attackerCol, int destinationRow, int destinationCol, Piece[][] board) {

	        //Rakip taş ve kontrol edilen taşın satırları arasındaki fark
	        int rowDifference = Math.abs(destinationRow - attackerRow);

	        //Rakip taş ve kontrol edilen taşın sütunları arasındaki fark
	        int colDiff = Math.abs(destinationCol - attackerCol);

	        //Rakip taşa göre (Piyon, At ve Vezir) tehdit durumunu kontrol etme
	        //charAt(0) ile string ifadenin ilk karakterini alırız.
	        switch (attacker.getPieceAbbreviation().charAt(0)) {

	            case 'p':

	                // Piyon tehdidi varsa true döner.
	                if (rowDifference == 1 && colDiff == 1)
	                    return true;
	                
	                break;

	            case 'a':

	                // At tehdidi varsa true döner.
	                if ((rowDifference == 2 && colDiff == 1) || (rowDifference == 1 && colDiff == 2)) 
	                    return true;
	                
	                break;

	            case 'v':

	                // Vezir tehdit kontrolü
	                if (attackerRow == destinationRow || attackerCol == destinationCol || rowDifference == colDiff) {

	                    // Atak yapan taş ile hedef taş arasındaki taşları kontrol et
	                    int rowStep = (destinationRow > attackerRow) ? 1 : ((destinationRow < attackerRow) ? -1 : 0);
	                    int colStep = (destinationCol > attackerCol) ? 1 : ((destinationCol < attackerCol) ? -1 : 0);

	                    int currentRow = attackerRow + rowStep;
	                    int currentCol = attackerCol + colStep;

	                    /* currentRow destinationRow'a eşit olmadığı sürece veya currentCol destinationCol'a eşit olmadığı sürece gez. */
	                    while (currentRow != destinationRow || currentCol != destinationCol) {

	                    	/* Eğer gezilen satırlarda atak yapan taş ile hedef taş arası boş değilse, yani arada taş varsa false döner, yani tehdit yoktur. */
	                        if (board[currentRow][currentCol] != null) {

	                            return false;
	                        }

	                        currentRow += rowStep;
	                        currentCol += colStep;
	                    }

	                    return true;
	                }

	                break;
	        }

	        return false;
	    }
}
