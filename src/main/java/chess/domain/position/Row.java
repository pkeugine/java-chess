package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.Arrays;

public enum Row {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String number;

    Row(String number) {
        this.number = number;
    }

    public static Row getRow(String value) {
        return Arrays.stream(values())
                .filter(row -> row.number.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public Row move(Direction direction) {
        if (isBoundary(direction)) {
            return this;
        }
        return getRow(String.valueOf(Integer.parseInt(number) + direction.getCoordinates().get(1)));
    }

    public String getNumber() {
        return number;
    }

    public boolean isBoundary(Direction direction) {
        if (Direction.DOWN.equals(direction) || Direction.DOWN_LEFT.equals(direction)
                || Direction.DOWN_RIGHT.equals(direction) || Direction.LL_D.equals(direction)
                || Direction.RR_D.equals(direction)) {
            return this.equals(ONE);
        }
        if (Direction.UP.equals(direction) || Direction.UP_LEFT.equals(direction)
                || Direction.UP_RIGHT.equals(direction) || Direction.LL_U.equals(direction)
                || Direction.RR_U.equals(direction)) {
            return this.equals(EIGHT);

        }
        if (Direction.R_DD.equals(direction) || Direction.L_DD.equals(direction)) {
            return this.equals(TWO) || this.equals(ONE);
        }
        if (Direction.R_UU.equals(direction) || Direction.L_UU.equals(direction)) {
            return this.equals(SEVEN) || this.equals(EIGHT);
        }
        return false;
    }

    public int getValue() {
        return Integer.parseInt(number);
    }
}
