package university.jala.legion.model;

/**
 * Represents an immutable 2D coordinate on the battlefield.
 * This record is a data carrier for a position, defined by a row and a column.
 * Using a record provides a concise and reliable implementation of an immutable data object.
 *
 * @param row    The vertical coordinate (row) on the grid.
 * @param column The horizontal coordinate (column) on the grid.
 */
public record Position(int row, int column) {
}
