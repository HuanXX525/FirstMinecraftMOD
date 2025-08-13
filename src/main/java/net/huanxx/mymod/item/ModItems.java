package net.huanxx.mymod.item;

import net.huanxx.mymod.MyMod;
import net.huanxx.mymod.item.custom.Huanxx1Custom;
import net.huanxx.mymod.item.custom.TestItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItems {
    /**<p>
     * 这是一个延时注册器，可以指定所要注册的内容类型，目前使用的是物品注册。<br>
     * 延时：通过订阅全局事件总线<b style="color: #66ccff">(下面的register订阅了全局事件总线)</b>的事件 {@link DeferredRegister} 下的<b style="color: #66ccff">EventDispatcher</b>等来实现延时注册<br>
     * 区分：通过给定的 {@link ForgeRegistries} 下的分类来细分所注册事件<br>
     * 通过MOD_ID区分物品所在“域”
     * </p>
     * */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MyMod.MOD_ID);

    /**
     * <p>使用注册器注册物品</p>
     */
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("testitem", ()->new TestItem(new Item.Properties()));
    /** <p>
     * 这是我添加的第一个物品<br>
     * 第二个参数<i>supplier</i>实际上就是一个工厂函数，使用箭头函数快速书写为如下
     * </p>
     *
     */
    public static final RegistryObject<Item> HUANXX_1_ITEM = ITEMS.register("huanxx1item", ()->{return new Item(new Item.Properties());});
    public static final RegistryObject<Item> HUANXX_GET_GOLD_TOOL = ITEMS.register("getgoldtool", ()->new Huanxx1Custom(new Item.Properties().durability(16)));
    /**
     * <p>
     *     下面的Item实例后面紧跟大括号，是匿名子类的用法，用于临时复写父类方法等<br>
     *     在这里不需要定义单独的物品类来继承Item，直接将内容写在大括号内即可<br>
     *     由于是匿名类，其构造方法变为了初始化块 { }
     * </p>
     */
    public static final RegistryObject<Item> SPECIAL_FOOD = ITEMS.register("specialfood", ()->new Item(new Item.Properties().food(ModFoodProperties.SPECIAL_FOOD)){
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 300;
        }
    });
    /**
     *  <h3>该函数让物品注册器订阅了全局事件总线</h3>
     * */
    public static void register(IEventBus iEventBus){
        ITEMS.register(iEventBus);
    }
}
