package pw.aaron1011.animationcontrol.hooks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pw.aaron1011.animationcontrol.AnimationControl;

public class ItemHooks {

    public static boolean shouldCauseRequipAnimation(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return AnimationControl.INSTANCE.reEquipManager.shouldRequip(item, oldStack, newStack, slotChanged);
    }

}
