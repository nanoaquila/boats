package nanoaquila.world.level.block.state.properties;

import nanoaquila.boats.Boats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WoodBreed {
    public static final WoodType RED = WoodType.create(new ResourceLocation(Boats.MOD_ID, "red").toString());
    public static final WoodType GREEN = WoodType.create(new ResourceLocation(Boats.MOD_ID, "green").toString());
    public static final WoodType BLUE = WoodType.create(new ResourceLocation(Boats.MOD_ID, "blue").toString());
}
