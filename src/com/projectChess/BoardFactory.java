package com.projectChess;

public class BoardFactory {

    private  PieceFactory pieceFactory = new PieceFactory();
    public Board fromFEN(String fen){
        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0  1

        Board board = new Board();

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];

        String[] fenRows = piecePositions.split("/");

        for(int i = 0; i < fenRows.length; i++){
            String row = fenRows[i];
            int rank = 8 - i;

            int fileIndex = 0;
            for(int j = 0; j < row.length(); j++){
                char fenChar = row.charAt(j);

                if(Character.isDigit(fenChar)){
                    fileIndex += Character.getNumericValue(fenChar);
                } else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);

                    board.setPieces(coordinates, pieceFactory.fromFenChar(fenChar, coordinates));
                    fileIndex++;
                }
            }
        }
        return board;
    }
}
