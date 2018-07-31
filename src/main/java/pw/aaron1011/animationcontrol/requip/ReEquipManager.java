package pw.aaron1011.animationcontrol.requip;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pw.aaron1011.animationcontrol.AnimationControl;

import java.util.Optional;

public class ReEquipManager {

    public static String REQUIP_ANIMATION_TAG = "ReEquipAnimation";

    public boolean shouldRequip(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        Optional<Boolean> newStackVal = this.getRequipValue(newStack);
        if (newStackVal.isPresent()) {
            return newStackVal.get();
        }
        Optional<Boolean> oldStackVal = this.getRequipValue(oldStack);
        if (oldStackVal.isPresent()) {
            return oldStackVal.get();
        }
        return item.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    private Optional<Boolean> getRequipValue(ItemStack stack) {
        if (!(stack.hasTagCompound() && stack.getTagCompound().hasKey(AnimationControl.ANIMATION_CONTROL_TAG))) {
            return Optional.empty();
        }

        NBTTagCompound tag = stack.getTagCompound().getCompoundTag(AnimationControl.ANIMATION_CONTROL_TAG);
        if (tag.hasKey(REQUIP_ANIMATION_TAG)) {
            return Optional.of(tag.getBoolean(REQUIP_ANIMATION_TAG));
        }
        return Optional.empty();
    }

}
