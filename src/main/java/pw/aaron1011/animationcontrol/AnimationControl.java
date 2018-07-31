package pw.aaron1011.animationcontrol;

import net.minecraftforge.fml.common.Mod;
import pw.aaron1011.animationcontrol.requip.ReEquipManager;

@Mod(
        modid = AnimationControl.MOD_ID,
        name = AnimationControl.MOD_NAME,
        version = AnimationControl.VERSION
)
public class AnimationControl {

    public static final String MOD_ID = "animationcontrol";
    public static final String MOD_NAME = "AnimationControl";
    public static final String VERSION = "1.0.0-SNAPSHOT";

    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static AnimationControl INSTANCE;

    public final ReEquipManager reEquipManager = new ReEquipManager();

    public static String ANIMATION_CONTROL_TAG = "AnimationControl";
}
