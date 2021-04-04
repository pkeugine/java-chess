package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("흰색 폰이 시작점에서 이동가능한 전체 위치를 구한다. 상황 : 흰폰-e2 흰피스-없음 검은피스-없음")
    @Test
    void generatePath_White() {
        Position current = Position.ofName("e2");
        Piece pawn = new Pawn(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE2WithoutObstaclesWhite());
    }

    @DisplayName("흰색 폰이 시작점에서 이동가능한 전체 위치를 구한다. 상황 : 흰폰-e2 흰피스-없음 검은피스-d3,f3")
    @Test
    void generateAttackPath_White() {
        Position current = Position.ofName("e2");
        Piece pawn = new Pawn(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        Board board = new Board();
        board.putPiece(new Bishop(PieceColor.BLACK), Position.ofName("d3"));
        board.putPiece(new Bishop(PieceColor.BLACK), Position.ofName("f3"));
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE2WithEnemyWhite());
    }

    @DisplayName("흰색 폰이 시작점이 아닌 곳에 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰폰-e4 흰피스-d5 검은피스-f5")
    @Test
    void generateObstacleConsideredPath_White() {
        Position current = Position.ofName("e4");
        Piece pawn = new Pawn(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        Board board = new Board();
        board.putPiece(new Bishop(PieceColor.WHITE), Position.ofName("d5"));
        board.putPiece(new Bishop(PieceColor.BLACK), Position.ofName("f5"));
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE4WithObstacleWhite());
    }

    @DisplayName("검은색 폰이 시작점에서 이동가능한 전체 위치를 구한다. 상황 : 검은폰-e7 흰피스-없음 검은피스-없음")
    @Test
    void generatePath_Black() {
        Position current = Position.ofName("e7");
        Piece pawn = new Pawn(PieceColor.BLACK);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE2WithoutObstaclesBlack());
    }

    @DisplayName("검은색 폰이 시작점에서 이동가능한 전체 위치를 구한다. 상황 : 검은폰-e7 흰피스-d6,f6 검은피스-없음")
    @Test
    void generateAttackPath_Black() {
        Position current = Position.ofName("e7");
        Piece pawn = new Pawn(PieceColor.BLACK);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        Board board = new Board();
        board.putPiece(new Bishop(PieceColor.WHITE), Position.ofName("d6"));
        board.putPiece(new Bishop(PieceColor.WHITE), Position.ofName("f6"));
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE2WithEnemyBlack());
    }

    @DisplayName("검은색 폰이 시작점이 아닌 곳에 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 검은폰-e4 흰피스-d3 검은피스-f3")
    @Test
    void generateObstacleConsideredPath_Black() {
        Position current = Position.ofName("e4");
        Piece pawn = new Pawn(PieceColor.BLACK);
        Paths paths = new Paths();
        paths = paths.findAllPath(pawn, current);
        Board board = new Board();
        board.putPiece(new Bishop(PieceColor.BLACK), Position.ofName("f3"));
        board.putPiece(new Bishop(PieceColor.WHITE), Position.ofName("d3"));
        assertThat(paths.pathsToPosition()).isEqualTo(pawnE4WithObstacleBlack());
    }

    List<Position> pawnE2WithoutObstaclesWhite() {
        return Arrays.asList(
                Position.ofName("e3"),
                Position.ofName("e4")
                );
    }

    List<Position> pawnE2WithEnemyWhite() {
        return Arrays.asList(
                Position.ofName("e3"),
                Position.ofName("e4"),
                Position.ofName("d3"),
                Position.ofName("f3")
        );
    }

    List<Position> pawnE4WithObstacleWhite() {
        return Arrays.asList(
                Position.ofName("e5"),
                Position.ofName("f5")
        );
    }

    List<Position> pawnE2WithoutObstaclesBlack() {
        return Arrays.asList(
                Position.ofName("e6"),
                Position.ofName("e5")
        );
    }

    List<Position> pawnE2WithEnemyBlack() {
        return Arrays.asList(
                Position.ofName("e6"),
                Position.ofName("e5"),
                Position.ofName("d6"),
                Position.ofName("f6")
        );
    }

    List<Position> pawnE4WithObstacleBlack() {
        return Arrays.asList(
                Position.ofName("e3"),
                Position.ofName("d3")
        );
    }
}