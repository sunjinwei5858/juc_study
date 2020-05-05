package com.sunjinwei.countdownlatch.type;

public enum CountryEnum {
    ONE(1, "楚"),
    TWO(2, "齐"),
    THREE(3, "燕"),
    FOUR(4, "魏"),
    FIVE(5, "赵"),
    SIX(6, "韩");

    private Integer code;
    private String countryName;

    CountryEnum(Integer code, String countryName) {
        this.code = code;
        this.countryName = countryName;
    }

    public Integer getCode() {
        return code;
    }

    public String getCountryName() {
        return countryName;
    }

    /**
     * 输入code 找到对应的枚举
     */
    public static CountryEnum getCountryByCode(Integer code) {
        // 获取数组
        CountryEnum[] countryEnums = CountryEnum.values();
        // 遍历
        for (CountryEnum countryEnum : countryEnums) {
            if (countryEnum.code.intValue() == code) {
                return countryEnum;
            }
        }
        return null;
    }

}
