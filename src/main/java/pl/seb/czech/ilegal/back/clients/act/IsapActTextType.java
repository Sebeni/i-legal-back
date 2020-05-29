package pl.seb.czech.ilegal.back.clients.act;

public enum IsapActTextType {
    PUBLISHED("O"),
    UNIFIED("U");

    private final String symbol;

    IsapActTextType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
