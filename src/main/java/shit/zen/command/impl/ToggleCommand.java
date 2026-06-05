package shit.zen.command.impl;

import shit.zen.ZenClient;
import shit.zen.command.Command;
import shit.zen.exception.ModuleNotFoundException;
import shit.zen.modules.Module;
import shit.zen.utils.misc.ChatUtil;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("toggle", new String[]{"t"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (stringArray.length == 1) {
            String string = stringArray[0];
            try {
                Module module = ZenClient.getInstance().getModuleManager().getModule(string);
                if (module != null) {
                    module.setEnabled(!module.isEnabled());
                    ChatUtil.print("已切换 " + module.getName() + "。");
                } else {
                    ChatUtil.print("无效模块。");
                }
            } catch (ModuleNotFoundException moduleNotFoundException) {
                ChatUtil.print("无效模块。");
            }
        }
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return ZenClient.getInstance().getModuleManager().getModules().stream().map(Module::getName).filter(string -> string.toLowerCase().startsWith(stringArray.length == 0 ? "" : stringArray[0].toLowerCase())).toArray(String[]::new);
    }
}