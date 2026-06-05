package shit.zen.modules.impl.render;

import shit.zen.modules.Category;
import shit.zen.modules.Module;

public class NoHurtCam
extends Module {
    public static NoHurtCam INSTANCE;
    public NoHurtCam() {
        super("无受伤镜头", Category.RENDER);
        INSTANCE = this;
    }
}