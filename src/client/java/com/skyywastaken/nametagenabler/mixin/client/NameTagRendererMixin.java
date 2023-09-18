package com.skyywastaken.nametagenabler.mixin.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MobEntityRenderer.class)
public class NameTagRendererMixin<T extends MobEntity, M extends EntityModel<T>> extends LivingEntityRenderer<T, M> {
    public NameTagRendererMixin(EntityRendererFactory.Context ctx, M model, float shadowRadius) {
        // Need to implement even if we're not using it
        super(ctx, model, shadowRadius);
    }

    /**
     * @author NameTagEnabler
     * @reason Makes it so nametags will ALWAYS render, so long as the entity in question has a name.
     */
    @Overwrite
    public boolean hasLabel(T mobEntity) {
        return super.hasLabel(mobEntity) && (mobEntity.shouldRenderName() || mobEntity.hasCustomName());
    }

    @Override
    public Identifier getTexture(T entity) {
        // Need to implement even if we're not using it
        return null;
    }
}