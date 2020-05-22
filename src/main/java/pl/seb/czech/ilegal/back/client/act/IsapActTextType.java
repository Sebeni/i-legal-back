package pl.seb.czech.ilegal.back.client.act;

public enum IsapActTextType {
    PUBLISHED("O"),
    UNIFIED("U");

    private String symbol;

    IsapActTextType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
