package pw.aaron1011.animationcontrol.requip;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pw.aaron1011.animationcontrol.AnimationControl;

import java.util.Objects;
import java.util.Optional;

public class ReEquipManager {

    public static String REQUIP_ANIMATION_TAG = "ReEquipAnimation";

    public boolean shouldRequip(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged, boolean slotChangedReal) {
        Optional<Boolean> customVal = this.getRequipValue(item, oldStack, newStack, slotChangedReal);
        // Always call the original method to preserve Forge compatibility
        boolean origVal = item.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);

        return customVal.orElse(origVal);
    }

    public boolean shouldCancelAnimation(ItemStack currentItem, ItemStack newItem) {
        return this.getMode(currentItem)
                .flatMap(m -> m.terminateAnimationEarly(currentItem, newItem))
                .orElseGet(() -> !Objects.equals(currentItem, newItem));
    }


    private Optional<Boolean> getRequipValue(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return this.getMode(oldStack).flatMap(m -> m.shouldAnimate(item, oldStack, newStack, slotChanged));
    }

    private Optional<ReEquipMode> getMode(ItemStack oldStack) {
        // We look at the old stack for consitency with Forge, which uses calls shouldCauseReequipAnimation
        // on the old item
        if (!(oldStack.hasTagCompound() && oldStack.getTagCompound().hasKey(AnimationControl.ANIMATION_CONTROL_TAG))) {
            return Optional.empty();
        }

        ReEquipMode mode = null;
        NBTTagCompound tag = oldStack.getTagCompound().getCompoundTag(AnimationControl.ANIMATION_CONTROL_TAG);
        if (tag.hasKey(REQUIP_ANIMATION_TAG)) {
            String name = tag.getString(REQUIP_ANIMATION_TAG);
            try {
                mode = ReEquipMode.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException ignored) {
            }
        }
        return Optional.ofNullable(mode);
    }


}
