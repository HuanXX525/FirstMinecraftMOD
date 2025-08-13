package net.huanxx.mymod.item.custom;

import com.sun.jna.platform.unix.solaris.LibKstat;
import net.huanxx.mymod.utils.GameTextMessage;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * <ol>
 *     <li>不论是单人还是多人，游戏都会同时运行客户端和服务端，因此有些回调函数会以不同的端调用两次，本工具默认使用服务端输出信息</li>
 *
 * </ol>
 */
public class TestItem extends Item {

    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * <p>经过测试 useOn 会拦截 use 但是单机空气或不可交互的动物时不会拦截</p>
     * @param pContext 游戏上下文
     * @return 操作参数
     */
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        if(!level.isClientSide()){
            player.sendSystemMessage(GameTextMessage.info("Item.useOn()"));
            player.sendSystemMessage(GameTextMessage.info("getClickedPos(): " + pContext.getClickedPos().toString()));
            player.sendSystemMessage(GameTextMessage.info("This is: " + Component.translatable(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock().getDescriptionId()).getString()));
//            player.sendSystemMessage(GameTextMessage.info("getItemInHand(): " + pContext.getItemInHand().toString()));
            player.sendSystemMessage(GameTextMessage.info("getClickedFace(): " + pContext.getClickedFace().toString()));
            player.sendSystemMessage(GameTextMessage.info("getClickLocation(): " + pContext.getClickLocation().toString()));
            player.sendSystemMessage(GameTextMessage.info("getHorizontalDirection(): " + pContext.getHorizontalDirection().toString()));
//            player.sendSystemMessage(GameTextMessage.info("getLevel(): " + pContext.getLevel().toString()));
            player.sendSystemMessage(GameTextMessage.info("getRotation(): " + pContext.getRotation()));
            player.sendSystemMessage(GameTextMessage.info("isInside(): " + pContext.isInside()));
            player.sendSystemMessage(GameTextMessage.info("isSecondaryUseActive(): " + pContext.isSecondaryUseActive()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        try (Level level = pPlayer.level()) {
            if(!level.isClientSide()){
                pPlayer.sendSystemMessage(GameTextMessage.info("Item.interactLivingEntity()"));
                pPlayer.sendSystemMessage(GameTextMessage.info("pStack.getItem(): " + pStack.getItem().toString()));
                pPlayer.sendSystemMessage(GameTextMessage.info("pInteractionTarget: " + pInteractionTarget.toString()));
                pPlayer.sendSystemMessage(GameTextMessage.info("pUsedHand: " + pUsedHand.toString()));
            }
        }catch (Exception e){
            pPlayer.sendSystemMessage(GameTextMessage.error(e.getMessage()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()){
            pPlayer.sendSystemMessage(GameTextMessage.info("Item.use()"));
            pPlayer.sendSystemMessage(GameTextMessage.info("pUsedHand:" + pUsedHand.toString()));
            pPlayer.sendSystemMessage(GameTextMessage.info("pPlayer: " + pPlayer.toString()));
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        // 添加可翻译内容，当游戏选择不同语言时，该内容随着lang文件夹下的翻译文件变化
        pTooltipComponents.add(Component.translatable("tooltip.huanxx1mod.testitem"));
        pTooltipComponents.add(Component.literal("按下").append(Component.literal("shift").withStyle(style -> style.withUnderlined(true).withColor(0xf1c40f))).append("显示更多内容"));
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.literal("我是继承自Item类的测试物品\n请使用我探索Item类以及与之有关联的内容吧！\n"));
            pTooltipComponents.add(Component.literal("不论是单人还是多人，游戏都会同时运行客户端和服务端，\n因此有些回调函数会以不同的端调用两次，\n本工具默认使用服务端输出信息。\n").withColor(0x66ccff));
        }
        if(pTooltipFlag.isAdvanced()){
            pTooltipComponents.add(Component.literal(String.format("这是高级模式下显示的信息：\n物品数量(pStack.getCount)：%d\n", pStack.getCount())
            ));
            pTooltipComponents.add(Component.literal(pContext.toString()));
        }
    }
}
