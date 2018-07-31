package pw.aaron1011.animationcontrol.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import pw.aaron1011.animationcontrol.hooks.ItemHooks;

@Mixin(ForgeHooksClient.class)
public abstract class MixinForgeHooksClient {

    @Redirect(method = "shouldCauseReequipAnimation", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;shouldCauseReequipAnimation(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Z)Z"), remap = false)
    private static boolean onShouldCauseReequipAnimation(Item this$0, ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ItemHooks.shouldCauseRequipAnimation(this$0, oldStack, newStack, slotChanged);
    }
}
