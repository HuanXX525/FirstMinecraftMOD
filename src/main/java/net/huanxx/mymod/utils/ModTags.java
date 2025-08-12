package net.huanxx.mymod.utils;

import net.huanxx.mymod.MyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        private static TagKey<Block> createTag(String name){
            // 这里是其中一种数据驱动方式
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MyMod.MOD_ID, name));
        }
    }

    /**
     * <p>
     *     此处使用内部静态类封装Tag实例和创建方法，也有避免不同类型重名的作用
     * </p>
     */
    public static class Items{
        public static final TagKey<Item> ALICE_MAGIC_BLOCK_TRANSFORMABLE = createTag("alice_block_transformable_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MyMod.MOD_ID, name));
        }
    }
}
