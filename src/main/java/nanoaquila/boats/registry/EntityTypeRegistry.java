package nanoaquila.boats.registry;

import nanoaquila.boats.Boats;
import nanoaquila.world.entity.vehicle.RowBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    private static boolean isRegistered;

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, Boats.MOD_ID);

    public static final RegistryObject<EntityType<RowBoat>> BOAT =
            ENTITY_TYPE.register("boat", () -> EntityType.Builder.<RowBoat>of(RowBoat::new,
                            MobCategory.MISC).fireImmune().sized(1.375F, 0.5625F)
                    .setCustomClientFactory((spawnEntity, world) -> new RowBoat(world, 0, 0, 0))
                    .build("boat"));

    public static void register(IEventBus bus) {
        if(isRegistered) {
            throw new IllegalStateException("Class 'EntityTypeRegistry' has already been registered");
        }
        ENTITY_TYPE.register(bus);
        isRegistered = true;
    }

}