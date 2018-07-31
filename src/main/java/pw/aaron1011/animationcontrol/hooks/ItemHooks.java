package pw.aaron1011.animationcontrol.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pw.aaron1011.animationcontrol.AnimationControl;

public class ItemHooks {

    public static int lastMainHandSlot;

    public static boolean onShouldCauseRequipAnimation(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return AnimationControl.INSTANCE.reEquipManager.shouldRequip(item, oldStack, newStack, slotChanged, Minecraft.getMinecraft().player.inventory.currentItem != lastMainHandSlot);
    }
}
