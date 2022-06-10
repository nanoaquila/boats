package nanoaquila.boats.registry;

import nanoaquila.boats.Boats;
import nanoaquila.world.entity.vehicle.RowBoat;
import nanoaquila.world.item.ItemBoat;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    private static boolean isRegistered;

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Boats.MOD_ID);

    public static final RegistryObject<Item> RED_BOAT = registerBoat("red", RowBoat.Type.RED);
    public static final RegistryObject<Item> GREEN_BOAT = registerBoat("green", RowBoat.Type.GREEN);
    public static final RegistryObject<Item> BLUE_BOAT = registerBoat("blue", RowBoat.Type.BLUE);

    private static RegistryObject<Item> registerBoat(String name, RowBoat.Type type) {
        return ITEMS.register(name + "_boat", () -> new ItemBoat(new Item.Properties().tab(CreativeModeTab.TAB_MISC), type));
    }

    public static void register(IEventBus bus) {
        if(isRegistered) {
            throw new IllegalStateException("Class 'ItemRegistry' has already been registered");
        }
        ITEMS.register(bus);
        isRegistered = true;
    }
}
