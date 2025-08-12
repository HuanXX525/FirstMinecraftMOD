package net.huanxx.mymod.item.custom;

import net.huanxx.mymod.MyMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Huanxx1Custom extends Item {

    public Huanxx1Custom(Properties pProperties) {
        super(pProperties);
    }

    /**
     * 右键点击
     * @param pContext 游戏上下文
     * @return 交互结果
     */
    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block block = level.getBlockState(pContext.getClickedPos()).getBlock();
        MyMod.LOGGER.info(block.toString());

        // 这里判断不是客户端端才能执行，既是是单机也有服务端用来处理世界逻辑，多人防止数据不同步
        if(!level.isClientSide()){
            level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.GOLD_BLOCK.defaultBlockState());
            pContext.getItemInHand().hurtAndBreak(1, (ServerLevel) level, (ServerPlayer) pContext.getPlayer(), item-> {
                if (pContext.getPlayer() != null) {
                    pContext.getPlayer().onEquippedItemBroken(item, pContext.getHand() == InteractionHand.MAIN_HAND?EquipmentSlot.MAINHAND:EquipmentSlot.OFFHAND);
                }
            });

            level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }
        return InteractionResult.SUCCESS;
    }

    /**
     * 覆写此方法返回有效值即可让物品能够燃烧
     * @param itemStack
     * @param recipeType
     * @return 燃烧时长 游戏刻(0.05s)
     */
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 100;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.huanxx1mod.huanxx1custom.detail"));
        }else{;
            pTooltipComponents.add(Component.translatable("tooltip.huanxx1mod.huanxx1custom"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
