package main;

import java.io.File;
import java.util.Scanner;

import piece.Piece;
import score.Chess;
import score.ScoreCalculation;

public class main {
	public static void main(String[] args) {
		
		System.out.println("Puanlanmasini istediginiz .txt uzantili satranc dosyasinin adini .txt olmadan giriniz.");
		String fileName;
		//Kullanıcıdan veri girişi istenir ve girilen değer scanner nesnesinde tutulur.
		Scanner scanner = new Scanner(System.in);
		//scanner içeriği fileName'e atanır.
		fileName = scanner.nextLine();
		scanner.close();
		
		
		try {
			
			//Dosya okuma 
			File inputFile = new File(fileName + ".txt");
			Scanner f_scanner = new Scanner(inputFile);
			
			//Tahtanın boyutunu ölçme
            Scanner tempScanner = new Scanner(inputFile);
            int boardSize = 0;
            
            //tempScanner içerisinde nesne bulundukça döngü çalışacak.
            while (tempScanner.hasNext()) {

                tempScanner.nextLine();
                boardSize++;

            }
            
            tempScanner.close();
            
            //boardSize tempScanner içerisinden aldığımız boyutu temsil eder. Bu boyutta satranç tahtasını oluştururuz.
            Piece[][] board = new Piece[boardSize][boardSize];
            
            //Dosya satırlarını okuyarak satranç tahtasını doldurma
            for (int i = 0; i < boardSize; i++) {

                String inputLine = f_scanner.nextLine();
                //Boşluklara göre dosya içeriğinde ayrım yapar, yani boşlukları almaz.
                String[] pieces = inputLine.split(" ");

                for (int j = 0; j < boardSize; j++) {

                	//Eğer tahtada gezdiğimiz nokta boş değilse
                    if (!pieces[j].equals("--")) {

                        //Taşın tipinin kısaltması
                        String pieceAbbreviation = pieces[j].substring(0, 1);

                        //Taşın bütün hali
                        String fullAbbreviation = pieces[j];

                        //Taş nesnesinin oluşturulması
                        ScoreCalculation factory = new ScoreCalculation();
                        Piece piece = factory.createChessPiece(pieceAbbreviation, fullAbbreviation);
                        board[i][j] = piece;

                    }
                }
            }
            
            Chess chess = new Chess();
            
            double whiteScore = 0;
            double blackScore = 0;
            
            //Tahtadaki bütün taşları puanlama
            for (int row = 0; row < boardSize; row++) {

                for (int col = 0; col < boardSize; col++) {

                    //Belirlenen satranç taşı
                    Piece piece = board[row][col];

                    if (piece != null) {

                        double pieceScore = chess.calculationScore(piece, board, row, col);	

                        //Belirlenen taş hangi renge aitse o rengin skorunu arttırma
                        if (piece.getPieceAbbreviation().substring(1).equals("b")) {

                            whiteScore += pieceScore;

                        } else if (piece.getPieceAbbreviation().substring(1).equals("s")) {

                            blackScore += pieceScore;

                        }
                    }
                }
            }
            
            //Sonuçlar
            System.out.println("Beyaz Puani: " + whiteScore);
            System.out.println("Siyah Puani: " + blackScore);
            
            f_scanner.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
