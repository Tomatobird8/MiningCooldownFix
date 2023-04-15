package net.covenantturtle.miningcooldownfix.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.util.math.BlockPos;
import net.covenantturtle.miningcooldownfix.MyConfig;

import me.shedaniel.autoconfig.AutoConfig;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class MiningCooldownFixMixin {
	MyConfig config = AutoConfig.getConfigHolder(MyConfig.class).getConfig();
	
	@Shadow
	private int blockBreakingCooldown;
	
	@Shadow private BlockPos currentBreakingPos;

	@Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
	private void blockBreakingCooldownModifier(CallbackInfoReturnable<Boolean> cir) {
		if (this.blockBreakingCooldown > config.mineDelay)
		this.blockBreakingCooldown = config.mineDelay;
	}

	@Inject(method = "cancelBlockBreaking", at = @At("HEAD"), cancellable = true)
	private void stopBreakCancel(CallbackInfo ci) {
		if (!config.allowBreakCancel) {
			ci.cancel();
		}
	}
	
	@Inject(method = "isCurrentlyBreaking", at = @At(value = "HEAD"), cancellable = true)
	private void blChange(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (!config.allowBreakReset) {
			cir.setReturnValue(pos.equals(this.currentBreakingPos));
		}
	}
}