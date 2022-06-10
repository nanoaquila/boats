package nanoaquila.client;

import nanoaquila.boats.Boats;
import nanoaquila.boats.registry.EntityTypeRegistry;
import nanoaquila.client.renderer.entity.RowBoatRenderer;
import nanoaquila.world.level.block.state.properties.WoodBreed;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Boats.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT })
public class ClientProxy {

    @SubscribeEvent
    public static void setupRenderLayers(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(EntityTypeRegistry.BOAT.get(), RowBoatRenderer::new);

            WoodType.register(WoodBreed.RED);
            WoodType.register(WoodBreed.GREEN);
            WoodType.register(WoodBreed.BLUE);
        });
    }
}
