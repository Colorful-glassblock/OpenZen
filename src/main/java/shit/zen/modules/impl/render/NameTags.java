package shit.zen.modules.impl.render;

import shit.zen.event.impl.PacketEvent;
import shit.zen.event.impl.Render2DEvent;
import shit.zen.event.impl.RenderEvent;
import shit.zen.modules.Category;
import shit.zen.modules.Module;
import shit.zen.modules.impl.render.nametag.NameTagStyle;
import shit.zen.settings.impl.BooleanSetting;
import shit.zen.settings.impl.ModeSetting;
import shit.zen.settings.impl.NumberSetting;
import shit.zen.event.EventTarget;

public class NameTags
extends Module {
    public static NameTags INSTANCE;
    public final ModeSetting styleSetting = new ModeSetting("风格", "蛋白石", "简单").withDefault("蛋白石");
    public final NumberSetting scaleSetting = new NumberSetting("缩放", 0.3, 0.1, 1.0, 0.01);
    public final NumberSetting distanceSetting = new NumberSetting("最大距离", 64.0, 8.0, 256.0, 1.0);
    public final BooleanSetting showHealthSetting = new BooleanSetting("隐藏玩家", false);
    public final BooleanSetting showArmorSetting = new BooleanSetting("显示装备", true);
    public final BooleanSetting showPingSetting = new BooleanSetting("隐藏队友", false);

    public NameTags() {
        super("血条名牌", Category.RENDER);
        INSTANCE = this;
        NameTagStyle.registerStyles();
    }

    @Override
    public void onEnable() {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onEnable();
        }
    }

    @Override
    public void onDisable() {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onDisable();
        }
    }

    @EventTarget
    public void onRender(RenderEvent renderEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onRender(renderEvent);
        }
    }

    @EventTarget
    public void onRender2D(Render2DEvent render2DEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onRender2D(render2DEvent);
        }
    }

    @EventTarget
    public void onPacket(PacketEvent packetEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onPacket(packetEvent);
        }
    }
}