package pw.aaron1011.animationcontrol.requip;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Optional;

public enum ReEquipMode {

    /**
     * The default Vanilla re-equip animation behavior.
     * The animation is played whenver the stack changes in any way
     */
    DEFAULT {
        @Override
        Optional<Boolean> shouldAnimate(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            return Optional.empty();
        }

        @Override
        Optional<Boolean> terminateAnimationEarly(ItemStack currentItem, ItemStack newItem) {
            return Optional.empty();
        }
    },
    /**
     * The re-equip animation is only played when the player switches to
     * a different slot
     */
    SLOT_CHANGED {
        @Override
        Optional<Boolean> shouldAnimate(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            return Optional.of(slotChanged);
        }

        @Override
        Optional<Boolean> terminateAnimationEarly(ItemStack currentItem, ItemStack newItem) {
            return Optional.of(true);
        }
    };

    abstract Optional<Boolean> shouldAnimate(Item item, ItemStack oldStack, ItemStack newStack, boolean slotChanged);
    abstract Optional<Boolean> terminateAnimationEarly(ItemStack currentItem, ItemStack newItem);

}
