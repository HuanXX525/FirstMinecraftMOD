package net.huanxx.mymod.item;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeItemTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MyMod.MOD_ID);

    /**
     * <p>当有多个选项卡时会随机排列，可以使用withTabBefore来确定位置</p>
     */
    public static final RegistryObject<CreativeModeTab> MY_MOD_1_TAB = CREATIVE_MODE_TAB.register("my_mod_1_tab", ()-> CreativeModeTab.builder()
            .icon(()-> new ItemStack(ModItems.HUANXX_1_ITEM.get()))
            .title(Component.translatable("creativetab.huanxx1mod.mymod1"))
            .displayItems((itemDisplayParamters, output)->{
                output.accept(ModItems.HUANXX_1_ITEM.get());
                output.accept(ModBlocks.HUANXX_1_BLOCK.get());
                output.accept(ModItems.HUANXX_GET_GOLD_TOOL.get());
                output.accept(ModBlocks.MAGIC_BLOCK.get());
                output.accept(ModItems.SPECIAL_FOOD.get());
                // 将物品添加到物品栏的自定义Tab中
                output.accept(ModItems.TEST_ITEM.get());
            }).build()
    );

    public static void register(IEventBus iEventBus){
        CREATIVE_MODE_TAB.register(iEventBus);
    }
}
