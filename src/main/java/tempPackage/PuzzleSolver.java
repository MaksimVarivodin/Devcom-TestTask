package tempPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class PuzzleSolver {
    private int puzzleWidth;
    private int puzzleHeight;
    private int pieceSize;
    private List<BufferedImage> puzzlePieces;
    private BufferedImage finalImage;
    private boolean[][] usedPieces;

    public PuzzleSolver(int puzzleWidth, int puzzleHeight, int pieceSize) {
        this.puzzleWidth = puzzleWidth;
        this.puzzleHeight = puzzleHeight;
        this.pieceSize = pieceSize;
        this.puzzlePieces = new ArrayList<>();
        this.finalImage = new BufferedImage(puzzleWidth * pieceSize, puzzleHeight * pieceSize, BufferedImage.TYPE_INT_RGB);
        this.usedPieces = new boolean[puzzleHeight][puzzleWidth];
    }

    public boolean solvePuzzle(String imagePath)             {
        try {
            BufferedImage fullImage = ImageIO.read(new File(imagePath));
            splitImageIntoPieces(fullImage);

            return backtrack(0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void splitImageIntoPieces(BufferedImage fullImage) throws IOException {
        int rows = fullImage.getHeight() / pieceSize;
        int cols = fullImage.getWidth() / pieceSize;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                BufferedImage piece = fullImage.getSubimage(x * pieceSize, y * pieceSize, pieceSize, pieceSize);
                File newFile = new File(x + "_" + y + ".png");
                ImageIO.write(piece, "png", newFile);
                puzzlePieces.add(piece);
            }
        }
    }

    private boolean backtrack(int row, int col) {
        if (row == puzzleHeight) {
            // Все строки пазла успешно заполнены, формируем окончательное изображение пазла
            buildFinalImage();
            return true;
        }

        if (col == puzzleWidth) {
            // Переход к следующей строке
            return backtrack(row + 1, 0);
        }

        for (int i = 0; i < puzzlePieces.size(); i++) {
            if (!usedPieces[row][col]) {
                BufferedImage currentPiece = puzzlePieces.get(i);

                // Проверка совпадения граничных пикселей с соседними частями пазла
                if (isMatchedWithNeighbors(row, col, currentPiece)) {
                    // Размещаем часть пазла на текущей позиции
                    placePiece(row, col, currentPiece);

                    // Рекурсивно продолжаем заполнять пазл
                    if (backtrack(row, col + 1)) {
                        return true;
                    }

                    // Если не удалось найти решение, отменяем размещение части пазла
                    removePiece(row, col);
                }
            }
        }

        return false;
    }

    private boolean isMatchedWithNeighbors(int row, int col, BufferedImage currentPiece) {
        if (row > 0 && !usedPieces[row - 1][col]) {
            BufferedImage topPiece = puzzlePieces.get(getPieceIndex(row - 1, col));
            if (!areEdgesMatched(getBottomEdge(topPiece), getTopEdge(currentPiece))) {
                return false;
            }
        }

        if (col > 0 && !usedPieces[row][col - 1]) {
            BufferedImage leftPiece = puzzlePieces.get(getPieceIndex(row, col - 1));
            if (!areEdgesMatched(getRightEdge(leftPiece), getLeftEdge(currentPiece))) {
                return false;
            }
        }

        return true;
    }

    private boolean areEdgesMatched(int[] edge1, int[] edge2) {
        for (int i = 0; i < edge1.length; i++) {
            if (edge1[i] != edge2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getTopEdge(BufferedImage piece) {
        int[] topEdge = new int[pieceSize];
        for (int i = 0; i < pieceSize; i++) {
            topEdge[i] = piece.getRGB(i, 0);
        }
        return topEdge;
    }

    private int[] getBottomEdge(BufferedImage piece) {
        int[] bottomEdge = new int[pieceSize];
        for (int i = 0; i < pieceSize; i++) {
            bottomEdge[i] = piece.getRGB(i, pieceSize - 1);
        }
        return bottomEdge;
    }

    private int[] getLeftEdge(BufferedImage piece) {
        int[] leftEdge = new int[pieceSize];
        for (int i = 0; i < pieceSize; i++) {
            leftEdge[i] = piece.getRGB(0, i);
        }
        return leftEdge;
    }

    private int[] getRightEdge(BufferedImage piece) {
        int[] rightEdge = new int[pieceSize];
        for (int i = 0; i < pieceSize; i++) {
            rightEdge[i] = piece.getRGB(pieceSize - 1, i);
        }
        return rightEdge;
    }

    private void placePiece(int row, int col, BufferedImage piece) {
        // Помещаем часть пазла на текущую позицию
        int x = col * pieceSize;
        int y = row * pieceSize;
        finalImage.createGraphics().drawImage(piece, x, y, null);
        usedPieces[row][col] = true;
    }

    private void removePiece(int row, int col) {
        // Удаляем часть пазла с текущей позиции
        int x = col * pieceSize;
        int y = row * pieceSize;
        finalImage.createGraphics().clearRect(x, y, pieceSize, pieceSize);
        usedPieces[row][col] = false;
    }

    private void buildFinalImage() {
        // Формируем окончательное изображение пазла
        for (int row = 0; row < puzzleHeight; row++) {
            for (int col = 0; col < puzzleWidth; col++) {
                int x = col * pieceSize;
                int y = row * pieceSize;
                BufferedImage piece = puzzlePieces.get(getPieceIndex(row, col));
                finalImage.createGraphics().drawImage(piece, x, y, null);
            }
        }
    }

    private int getPieceIndex(int row, int col) {
        return row * puzzleWidth + col;
    }

    public BufferedImage getFinalImage() {
        return finalImage;
    }
}
