package nanoaquila.world.entity.vehicle;

import nanoaquila.boats.registry.EntityTypeRegistry;
import nanoaquila.boats.registry.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class RowBoat extends Boat {


    private static final EntityDataAccessor<Integer> WOOD_TYPE = SynchedEntityData.defineId(RowBoat.class, EntityDataSerializers.INT);


    public RowBoat(EntityType<? extends RowBoat> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public RowBoat(Level level, double x, double y, double z) {
        this(EntityTypeRegistry.BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", this.getBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            this.setBoatType(RowBoat.Type.byName(tag.getString("Type")));
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, Type.RED.ordinal());
    }

    public void setBoatType(RowBoat.Type type) {
        this.entityData.set(WOOD_TYPE, type.ordinal());
    }

    public RowBoat.Type getRowBoatType() {
        return RowBoat.Type.byId(this.entityData.get(WOOD_TYPE));
    }

    @Override
    @NotNull
    public Item getDropItem() {
        return switch (this.getRowBoatType()) {
            case RED -> ItemRegistry.RED_BOAT.get();
            case GREEN -> ItemRegistry.GREEN_BOAT.get();
            case BLUE -> ItemRegistry.BLUE_BOAT.get();
        };
    }

    @Override
    @NotNull
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type {
        RED("red"),
        GREEN("green"),
        BLUE("blue");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static RowBoat.Type byId(int id) {
            RowBoat.Type[] types = values();
            if(id < 0 || id >= types.length) {
                id = 0;
            }
            return types[id];
        }

        public static RowBoat.Type byName(String name) {
            RowBoat.Type[] types = values();
            for (Type type : types) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return types[0];
        }
    }
}
