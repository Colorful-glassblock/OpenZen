package shit.zen.modules.impl.movement;

import shit.zen.modules.Category;
import shit.zen.modules.Module;
import shit.zen.settings.impl.BooleanSetting;

public class NoDelay
extends Module {
    public static NoDelay INSTANCE;
    public final BooleanSetting fastDig = new BooleanSetting("无跳跃延迟", true);

    public NoDelay() {
        super("无延迟", Category.MOVEMENT);
        INSTANCE = this;
    }
}