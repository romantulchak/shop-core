package org.computerShop.model.enums;

public enum EPromo {
    UNCORRECTED_PRODUCT("UNCORRECTED_PRODUCT"), DETERMINED("DETERMINED");

    private String name;
    EPromo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
