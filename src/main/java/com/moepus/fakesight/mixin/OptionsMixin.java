package com.moepus.fakesight.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Shadow
    private int serverRenderDistance;

    @Shadow
    @Final
    private OptionInstance<Integer> renderDistance;

    @Inject(method = "getEffectiveRenderDistance", at = @At("HEAD"), cancellable = true)
    private void moepus$forceLod(CallbackInfoReturnable<Integer> cir) {
        int rd = this.serverRenderDistance > 0 ? Math.min(this.renderDistance.get(), this.serverRenderDistance) : this.renderDistance.get();
        cir.setReturnValue(Math.min(rd, 12));
    }
}
