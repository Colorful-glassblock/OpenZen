package shit.zen.modules.impl.combat;

import java.util.Arrays;
import java.util.Optional;
import shit.zen.event.impl.DisconnectEvent;
import shit.zen.event.impl.GameTickEvent;
import shit.zen.event.impl.MotionEvent;
import shit.zen.event.impl.PreMotionEvent;
import shit.zen.event.impl.ReceivePacketEvent;
import shit.zen.event.impl.Render2DEvent;
import shit.zen.event.impl.RenderEvent;
import shit.zen.event.impl.RotationEvent;
import shit.zen.event.impl.SprintEvent;
import shit.zen.event.impl.StrafeEvent;
import shit.zen.event.impl.TickEvent;
import shit.zen.modules.Category;
import shit.zen.modules.Module;
import shit.zen.modules.impl.combat.antikb.AntiKBMode;
import shit.zen.modules.impl.movement.FireballBlink;
import shit.zen.modules.impl.movement.HighJump;
import shit.zen.settings.impl.BooleanSetting;
import shit.zen.settings.impl.ModeSetting;
import shit.zen.settings.impl.NumberSetting;
import shit.zen.utils.rotation.Rotation;
import shit.zen.event.EventTarget;

public class AntiKB
extends Module {
    public static AntiKB INSTANCE;
    public static Rotation rotation;
    public static ModeSetting mode;
    public final BooleanSetting autoJump = new BooleanSetting("自动跳跃", false, () -> mode.is("Grim Full") || mode.is("Grim Fast"));
    public final BooleanSetting rotate = new BooleanSetting("旋转", false, () -> mode.is("跳跃重置") || mode.is("混合"));
    public final BooleanSetting tryAttack = new BooleanSetting("尝试攻击", false, () -> mode.is("混合"));
    public final BooleanSetting movementOverride = new BooleanSetting("移动覆盖", false, () -> mode.is("混合"));
    public final BooleanSetting followDirection = new BooleanSetting("跟随方向", false, () -> mode.is("跳跃重置"));
    public final NumberSetting rotateTicks = new NumberSetting("旋转刻数", 12, 3, 20, 1, () -> mode.is("跳跃重置") && (this.rotate.getValue() != false || this.followDirection.getValue() != false));
    public final NumberSetting attackAmount = new NumberSetting("攻击次数", 5.0, 1.0, 20.0, 1, () -> mode.is("NoXZ"));
    public final BooleanSetting instantAttack = new BooleanSetting("瞬间攻击", false, () -> mode.is("NoXZ"));
    public final BooleanSetting sprintStateCheck = new BooleanSetting("疾跑状态检查", true, () -> mode.is("NoXZ"));

    public AntiKB() {
        super("击退抵抗", Category.COMBAT);
        INSTANCE = this;
        AntiKBMode.initModes();
    }

    @Override
    public void onEnable() {
        Optional<AntiKBMode> optional;
        rotation = null;
        if (!Arrays.stream((Object[])mode.getModes()).toList().contains(mode.getValue())) {
            mode.withDefault("NoXZ");
        }
        if ((optional = AntiKBMode.findMode(mode.getValue())).isEmpty()) {
            return;
        }
        optional.get().onEnable();
    }

    @Override
    public void onDisable() {
        rotation = null;
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (optional.isEmpty()) {
            return;
        }
        optional.get().onDisable();
    }

    @EventTarget
    public void onGameTick(GameTickEvent gameTickEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onGameTick(gameTickEvent);
    }

    @EventTarget
    public void onPreMotion(PreMotionEvent preMotionEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onPreMotion(preMotionEvent);
    }

    @EventTarget
    public void onTick(TickEvent tickEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onTick(tickEvent);
    }

    @EventTarget
    public void onSprint(SprintEvent sprintEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onSprint(sprintEvent);
    }

    @EventTarget
    public void onRotation(RotationEvent rotationEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onRotation(rotationEvent);
    }

    @EventTarget
    public void onMotion(MotionEvent motionEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onMotion(motionEvent);
    }

    @EventTarget(value=1)
    public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onReceivePacket(receivePacketEvent);
    }

    @EventTarget
    public void onDisconnect(DisconnectEvent disconnectEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (optional.isEmpty()) {
            return;
        }
        optional.get().onDisconnect(disconnectEvent);
    }

    @EventTarget(value=3)
    public void onStrafe(StrafeEvent strafeEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onStrafe(strafeEvent);
    }

    @EventTarget
    public void onRender(RenderEvent renderEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onRender(renderEvent);
    }

    @EventTarget
    public void onRender2D(Render2DEvent render2DEvent) {
        Optional<AntiKBMode> optional = AntiKBMode.findMode(mode.getValue());
        if (FireballBlink.INSTANCE.isEnabled() || HighJump.INSTANCE.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().onRender2D(render2DEvent);
    }

    static {
        mode = new ModeSetting("模式", "跳跃重置", "混合", "NoXZ").withDefault("NoXZ");
    }
}