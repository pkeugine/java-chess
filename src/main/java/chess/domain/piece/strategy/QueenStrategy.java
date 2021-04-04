package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class QueenStrategy implements MoveStrategy {

    @Override
    public boolean canGoFrom(Position from, Position to) {
        return from.isStraight(to) || from.isDiagonal(to);
    }
}
