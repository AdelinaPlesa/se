package dise;

public enum SimpleEnums {
    LT("10"),
    GT("11"),
    EQ("14"),
    CALLVALUE("34"),
    CALLDATALOAD("35"),
    CALLDATASIZE("36"),
    POP("50"),
    MLOAD("51"),
    MSTORE("52"),
    JUMP("56"),
    JUMPI("57"),
    JUMPDEST("5B"),
    DUP1("80"),
    DUP2("81"),
    DUP3("82"),
    DUP4("83"),
    DUP5("84"),
    DUP6("85"),
    DUP7("86"),
    DUP8("87"),
    DUP9("88"),
    DUP10("89"),
    DUP11("8A"),
    DUP12("8B"),
    DUP13("8C"),
    DUP14("8D"),
    DUP15("8E"),
    DUP16("8F"),
    SWAP1("90"),
    SWAP2("91"),
    SWAP3("92"),
    SWAP4("93"),
    SWAP5("94"),
    SWAP6("95"),
    SWAP7("96"),
    SWAP8("97"),
    SWAP9("98"),
    SWAP10("99"),
    SWAP11("9A"),
    SWAP12("9B"),
    SWAP13("9C"),
    SWAP14("9D"),
    SWAP15("9E"),
    SWAP16("9F")
    ;

    String value;
    SimpleEnums(String value) {
        this.value = value;
    }
}
