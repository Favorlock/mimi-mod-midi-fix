package com.favorlock.mmmf.mixin;

import io.github.tofodroid.mods.mimi.client.midi.MidiFileCasterManager;
import io.github.tofodroid.mods.mimi.client.midi.MidiInputDeviceManager;
import io.github.tofodroid.mods.mimi.client.midi.MidiInputManager;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MidiInputManager.class)
public class MidiInputManagerMixin {
    @Shadow
    @Final
    public MidiInputDeviceManager inputDeviceManager;

    @Shadow
    @Final
    public MidiFileCasterManager fileCasterManager;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void registerInputDeviceManagerEventListener(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.register(this.inputDeviceManager);
        MinecraftForge.EVENT_BUS.register(this.fileCasterManager);
    }
}
