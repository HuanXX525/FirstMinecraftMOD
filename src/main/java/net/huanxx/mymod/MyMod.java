package net.huanxx.mymod;

import com.mojang.logging.LogUtils;
import net.huanxx.mymod.block.ModBlocks;
import net.huanxx.mymod.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MyMod.MOD_ID)
public class MyMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "huanxx1mod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MyMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    /**
     * <p>这也是一个回调函数，订阅了事件 {@link BuildCreativeModeTabContentsEvent }</p><br>
     * 通过{@link IEventBus}的<b>addListener订阅<br>
     * accept函数接收实现了ItemLike的类
     * </b>
     * @param event 具体订阅的事件
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // 材料列表
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.HUANXX1ITEM);
        }
        // 建筑方块列表
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.HUANXX1BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
