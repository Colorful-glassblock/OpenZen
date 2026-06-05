package shit.zen.modules.impl.render;

import shit.zen.modules.Category;
import shit.zen.modules.Module;

public class ItemTags extends Module {
    public static ItemTags INSTANCE;

    public ItemTags() {
        super("物品标签", Category.RENDER);
        INSTANCE = this;
    }
}
