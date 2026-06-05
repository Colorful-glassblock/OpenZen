package shit.zen.modules;

public enum Category {
    COMBAT("战斗"),
    MOVEMENT("移动"),
    PLAYER("玩家"),
    RENDER("渲染"),
    EXPLOIT("漏洞"),
    WORLD("世界"),
    MISC("辅助");

    public String displayName;

    Category(String string2) {
        this.displayName = string2;
    }

    public static Category fromString(String string) {
        for (Category category : Category.values()) {
            if (!category.displayName.equalsIgnoreCase(string)) continue;
            return category;
        }
        return COMBAT;
    }
}