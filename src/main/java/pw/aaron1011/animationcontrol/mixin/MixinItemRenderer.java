package pw.aaron1011.animationcontrol.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.aaron1011.animationcontrol.hooks.ItemHooks;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

    @Shadow @Final private Minecraft mc;

    @Inject(method = "updateEquippedItem", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/ItemRenderer;itemStackMainHand:Lnet/minecraft/item/ItemStack;", opcode = Opcodes.PUTFIELD))
    private void onSetMainHandFirst(CallbackInfo ci) {
        ItemHooks.lastMainHandSlot = this.mc.player.inventory.currentItem;
    }
}
