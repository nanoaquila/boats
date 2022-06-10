package nanoaquila.boats;

import nanoaquila.boats.registry.EntityTypeRegistry;
import nanoaquila.boats.registry.ItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Boats.MOD_ID)
public class Boats {

    public static final String MOD_ID = "boats";

    public Boats() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.register(bus);
        EntityTypeRegistry.register(bus);
    }
}
