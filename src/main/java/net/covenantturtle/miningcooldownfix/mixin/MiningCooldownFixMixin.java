package net.covenantturtle.miningcooldownfix.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.util.math.BlockPos;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class MiningCooldownFixMixin {

	@Shadow private BlockPos currentBreakingPos;
	
	@ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
	private int MiningCooldownFix(int value) {
		return 2;
	}
	
	@Inject(method = "isCurrentlyBreaking", at = @At(value = "HEAD"), cancellable = true)
	private void blChange(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(pos.equals(this.currentBreakingPos));
	}
}