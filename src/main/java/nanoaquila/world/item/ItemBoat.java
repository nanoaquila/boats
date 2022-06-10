package nanoaquila.world.item;

import nanoaquila.world.entity.vehicle.RowBoat;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class ItemBoat extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);
    private final RowBoat.Type type;

    public ItemBoat(Item.Properties properties, RowBoat.Type typeIn) {
        super(properties);
        this.type = typeIn;
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3d = player.getEyePosition(1.0F);
            double d0 = 5.0D;
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3d.scale(d0)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec3d1 = player.getEyePosition(1.0F);
                for (Entity entity : list) {
                    AABB axis = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (axis.contains(vec3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                RowBoat entityBoat = new RowBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
                entityBoat.setBoatType(this.type);
                entityBoat.setYRot(player.getYRot());
                if (!level.noCollision(entityBoat, entityBoat.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(entityBoat);
                    }
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.success(itemstack);
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }
}