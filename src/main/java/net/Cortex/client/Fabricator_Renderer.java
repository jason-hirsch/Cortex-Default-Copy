package net.Cortex.client;

import com.google.common.collect.ImmutableMap;
import net.Cortex.main.Fabricator.Fabricator_Entity;
import net.Cortex.main.Fabricator.MultiblockOption;
import net.Cortex.main.MainEntry;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Fabricator_Renderer implements BlockEntityRenderer<Fabricator_Entity>
{
    public Fabricator_Renderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(Fabricator_Entity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        int multiblockOptionIs = entity.multiblockOptionIs.get();
        if(multiblockOptionIs >= 0) {
            MultiblockOption optionIs = Fabricator_Entity.multiblockOptions.get(multiblockOptionIs);
            ImmutableMap<BlockPos, Block> multiblock = optionIs.getMultiblock(entity.multiblockSize.get());
            for(BlockPos pos : multiblock.keySet()) {
                matrices.push();
                matrices.translate(pos.getX(), pos.getY(), pos.getZ());
                MinecraftClient.getInstance().getItemRenderer().renderItem(multiblock.get(pos).asItem().getDefaultStack(), ModelTransformation.Mode.GROUND, 15728640, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 42);
                matrices.pop();
            }
        }
    }
}
