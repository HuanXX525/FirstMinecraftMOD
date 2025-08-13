package net.huanxx.mymod.datagen;

import net.huanxx.mymod.block.ModBlocks;
import net.huanxx.mymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider
{
    /**
     * <p>
     *    哈希表是处理方块与其掉落物品生成器的，会从getKnownBlocks中获取的方块挨个剔除，有剩余就说明有未定义的战利品表
     * </p>
     * @param pRegistries
     */
    protected ModBlockLoot(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), new HashMap<>(), pRegistries);
    }

    /**
     * 调用父类的方法编辑构造函数中传入的map
     */
    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.MAGIC_BLOCK.get());
        this.add(ModBlocks.HUANXX_1_BLOCK.get(), createSizeableOreDrops(ModBlocks.HUANXX_1_BLOCK.get(), ModItems.HUANXX_1_ITEM.get(), 6, 9));
    }

    /**
     * 这是从官方文档中挖来的获取LootTable.Builder的函数，受爆炸影响，有时运加成，有精准采集
     * @param pBlock 挖掘的方块
     * @param item 掉落物
     * @param min 最小值
     * @param max 最大值
     * @return 掉落物生成表
     */
    protected LootTable.Builder createSizeableOreDrops(Block pBlock, Item item, int min, int max) {
        // 从已知的数据中获取附魔的信息
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 时运模板，下面的逻辑是非时运的
        return this.createSilkTouchDispatchTable(
                pBlock,
                // 随爆炸减少模板
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(item)
                                // 生成数量
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                // 时运加成
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    /**
     * Iterable是一个接口，必须实现iterator方法，下面的return通过Stream::Iterator刚好返回了一个函数的引用
     * ，这个引用被编译器包装为了一个匿名类实现了接口
     * @return
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
