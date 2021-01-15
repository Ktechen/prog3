package controller.cli;

public enum Keys {
    ADD(":c"), SHOW(":r"), DELETE(":d"), CHANGE(":u"), CONFIG(":config"), PERSISTENCE(":p");

    private String s;

    Keys(String s) {
        this.s = s;
    }

    public String get() {
        return s;
    }
}
