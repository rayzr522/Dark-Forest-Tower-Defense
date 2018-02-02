package me.rayzr522.towerdefense.gui.font;

public enum Letter {
    A("A", 0),
    B("B", 1),
    C("C", 2),
    D("D", 3),
    E("E", 4),
    F("F", 5),
    G("G", 6),
    H("H", 7),
    I("I", 8),
    J("J", 9),
    K("K", 10),
    L("L", 11),
    M("M", 12),
    N("N", 13),
    O("O", 14),
    P("P", 15),
    Q("Q", 16),
    R("R", 17),
    S("S", 18),
    T("T", 19),
    U("U", 20),
    V("V", 21),
    W("W", 22),
    X("X", 23),
    Y("Y", 24),
    Z("Z", 25),
    QUESTION_MARK("?", 26),
    EXCLAMATION_MARK("!", 27),
    PERIOD(".", 28),
    COMMA(",", 29),
    LEFT_PAREN("(", 30),
    RIGHT_PAREN(")", 31),
    LEFT_SQUARE_BRACKET("[", 32),
    RIGHT_SQUARE_BRACKET("]", 33),
    LEFT_ANGULAR_BRACKET("<", 34),
    RIGHT_ANGULAR_BRACKET(">", 35),
    FORWARD_SLASH("/", 36),
    BACK_SLASH("\\", 37),
    ZERO("0", 38),
    ONE("1", 39),
    TWO("2", 40),
    THREE("3", 41),
    FOUR("4", 42),
    FIVE("5", 43),
    SIX("6", 44),
    SEVEN("7", 45),
    EIGHT("8", 46),
    NINE("9", 47),
    VERTICAL_LINE("|", 48);

    public final String name;
    public final int pos;

    Letter(String name, int pos) {
        this.name = name;
        this.pos = pos;
    }

}
