package model;

public enum  Group {
    TEST_1("test1","test2","test3"),
    TEST_2("test4","test5","test6")
    ;

    private String name;
    private String header;
    private String footer;

    Group(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}
