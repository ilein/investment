package com.gdtest.investment.model.enums;

import java.util.stream.Stream;

public enum LegalFormEnum {
    OOO(0, "ООО"),
    IP(1, "ИП"),
    OAO(2, "ОАО");
    private int legalFormId;
    private String legalFormName;

    LegalFormEnum(int legalFormId, String legalFormName) {
        this.legalFormId = legalFormId;
        this.legalFormName = legalFormName;
    }

    public int getlegalFormId() {
        return legalFormId;
    }

    public String getLegalFormName() {
        return legalFormName;
    }

    public static LegalFormEnum of(int legalFormId) {
        return Stream.of(LegalFormEnum.values())
                .filter(p -> p.getlegalFormId() == legalFormId)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static LegalFormEnum ofName(String legalFormName) {
        return Stream.of(LegalFormEnum.values())
                .filter(p -> p.getLegalFormName().equals(legalFormName))
                .findFirst()
                .orElse(null);
    }
}
