package net.huanxx.mymod.block;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.block.custom.MagicBlock;
import net.huanxx.mymod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MyMod.MOD_ID);

    public static final RegistryObject<Block> HUANXX_1_BLOCK = registerBlock("huanxx1block", ()->new Block(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(
            SoundType.AMETHYST
    )));

    public static final RegistryObject<Block> MAGIC_BLOCK = registerBlock("magicblock",
            ()->new MagicBlock(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.GLASS)));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T>block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    /**
     * <p><u>方块</u>是指可以放置在世界中的实体，而<u>物品</u>是指“掉落物”放在物品栏中的东西<br>
     * 因此在注册方块的同时要注册其物品栏形态，在Register方法中将Block与item关联了起来
     * </p>
     * @param name 注册的方块的名词
     * @param block 方块的注册类
     * @param <T> 限制必须为继承自Block的类
     */
    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T>block){
        ModItems.ITEMS.register(name, ()->new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus iEventBus){
        BLOCKS.register(iEventBus);
    }
}
