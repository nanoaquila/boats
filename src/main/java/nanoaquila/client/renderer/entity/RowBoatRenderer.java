package nanoaquila.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import nanoaquila.boats.Boats;
import nanoaquila.world.entity.vehicle.RowBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class RowBoatRenderer extends BoatRenderer {

    private final Map<RowBoat.Type, Pair<ResourceLocation, BoatModel>> resources;

    public RowBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        this.resources = Stream.of(RowBoat.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type,
                (type) -> Pair.of(new ResourceLocation(Boats.MOD_ID,"textures/entity/boat/" + type.getName() + ".png"),
                        new BoatModel(context.bakeLayer(
                                new ModelLayerLocation(new ResourceLocation("minecraft", "boat/oak"),"main"))))));

    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat) {
        if(boat instanceof RowBoat modBoat) {
            return this.resources.get(modBoat.getRowBoatType()).getFirst();
        }

        return new ResourceLocation("minecraft", "boat/oak");
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        if(boat instanceof RowBoat modBoat) {
            return this.resources.get(modBoat.getRowBoatType());
        }

        return null;
    }
}
