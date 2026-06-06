package shit.zen.render;

import java.awt.Font;
import java.io.InputStream;
import org.jetbrains.annotations.NotNull;
import shit.zen.utils.misc.Assets;

public class FontStore {
    public static CustomFont OPENSANS_16 = loadFont(16.0f, "opensans.ttf");
    public static CustomFont OPENSANS_18 = loadFont(18.0f, "opensans.ttf");
    public static CustomFont NOTO_SANS_CJK_16 = loadFont(16.0f, "NotoSansCJKsc-Regular.otf");
    public static CustomFont NOTO_SANS_CJK_18 = loadFont(18.0f, "NotoSansCJKsc-Regular.otf");
    public static CustomFont ICON_18 = loadFont(18.0f, "icon.ttf");
    public static CustomFont ICON_30 = loadFont(30.0f, "icon.ttf");
    public static CustomFont MATERIAL_20 = loadFontWithFallback(20.0f, "material.ttf");
    public static CustomFont MATERIAL_14 = loadFontWithFallback(14.0f, "material.ttf");
    public static CustomFont ZENICON_28 = loadFont(28.0f, "zenicon-Regular.ttf");
    public static CustomFont AXIFORMA_REGULAR_14 = loadFont(14.0f, "axiforma_regular.ttf");
    public static CustomFont AXIFORMA_REGULAR_16 = loadFont(16.0f, "axiforma_regular.ttf");
    public static CustomFont AXIFORMA_REGULAR_18 = loadFont(18.0f, "axiforma_regular.ttf");
    public static CustomFont AXIFORMA_BOLD_13 = loadFont(13.0f, "axiforma_bold.ttf");
    public static CustomFont AXIFORMA_BOLD_18 = loadFont(18.0f, "axiforma_bold.ttf");
    public static CustomFont AXIFORMA_EXTRABOLD_16 = loadFont(16.0f, "axiforma_extrabold.ttf");
    public static CustomFont AXIFORMA_EXTRABOLD_18 = loadFont(18.0f, "axiforma_extrabold.ttf");
    public static CustomFont AXIFORMA_BOLD_16 = loadFont(16.0f, "axiforma_bold.ttf");

    @NotNull
    public static CustomFont loadFont(float size, String name) {
        try (InputStream stream = Assets.open("/assets/zen/fonts/" + name)) {
            if (stream == null) {
                throw new java.io.IOException("Font not found: " + name);
            }
            return new CustomFont(Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(0, size / 2.0f), size / 2.0f);
        } catch (Exception e) {
            return new CustomFont(new Font("SansSerif", 0, (int)(size / 2)), size / 2.0f);
        }
    }

    @NotNull
    private static CustomFont loadFontWithFallback(float size, String name) {
        try {
            return loadFont(size, name);
        } catch (Exception e) {
            System.err.println("Failed to load " + name + ", using fallback font.");
            return new CustomFont(new Font("SansSerif", 0, (int)(size / 2)), size / 2.0f);
        }
    }

    /**
     * 返回一个可用于渲染中文等 CJK 字符的回退 Font。
     * 回退链：
     *   1) 项目内置的 Noto Sans CJK SC（思源黑体）
     *   2) 系统已安装的跨平台 CJK 字体
     *   3) 逻辑字体 SansSerif（最终兜底）
     */
    static Font getCjkFallbackFont() {
        return CjkFallbackHolder.FONT;
    }

    private static final class CjkFallbackHolder {
        static final Font FONT = loadCjkFallback();

        private static Font loadCjkFallback() {
            // 1) 尝试加载项目内置的思源黑体（Noto Sans CJK SC）
            try (InputStream stream = Assets.open("/assets/zen/fonts/NotoSansCJKsc-Regular.otf")) {
                if (stream != null) {
                    return Font.createFont(Font.TRUETYPE_FONT, stream);
                }
            } catch (Exception e) {
                System.err.println("Failed to load bundled NotoSansCJKsc-Regular.otf for CJK fallback");
            }
            // 2) 尝试系统已安装的 CJK 字体（跨平台，按优先级排列）
            String[] cjkFonts = {
                // Linux / 跨平台
                "Noto Sans CJK SC", "Noto Sans CJK", "WenQuanYi Micro Hei", "WenQuanYi Zen Hei",
                // Windows
                "Microsoft YaHei", "微软雅黑", "SimSun", "宋体",
                // macOS
                "PingFang SC", "Hiragino Sans GB", "STHeiti",
                // 通用
                "NSimSun", "新宋体", "KaiTi", "楷体", "FangSong", "仿宋"
            };
            for (String fontName : cjkFonts) {
                Font f = new Font(fontName, Font.PLAIN, 1);
                if (f.canDisplay('中')) {
                    return f;
                }
            }
            // 3) 最终回退：逻辑字体 SansSerif
            return new Font("SansSerif", Font.PLAIN, 1);
        }
    }
}
