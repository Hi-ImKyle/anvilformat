package com.kingfisher.anvilformat.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AnvilBlock;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.LoomScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.LiteralText;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ScreenHandler {

	protected AnvilScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
		super(type, syncId);
		// TODO Auto-generated constructor stub
	}

	@Shadow
	private String newItemName;
	
	@Shadow
	public abstract void updateResult();

	@Inject(at = @At("INVOKE"), method = "setNewItemName", cancellable = true)
	public void setNewItemName(String string, CallbackInfo info) {

		info.cancel();
		
		this.newItemName = string.replaceAll("&", "\u00A7");
		if (this.getSlot(2).hasStack()) {
			ItemStack itemStack = this.getSlot(2).getStack();
			if (StringUtils.isBlank(string)) {
				itemStack.removeCustomName();
			} else {
				itemStack.setCustomName(new LiteralText(this.newItemName));
			}
		}

		this.updateResult();

	}
}
