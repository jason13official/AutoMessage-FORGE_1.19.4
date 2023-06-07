package net.ggwpgaming.automessage;

import com.mojang.logging.LogUtils;
import net.ggwpgaming.automessage.config.AMClientConfigs;
import net.ggwpgaming.automessage.config.AMServerConfigs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkConstants;
import org.slf4j.Logger;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AutoMessage.MOD_ID)
public class AutoMessage
{
    public static final String MOD_ID = "automessage";
    public static final String AMLogPrefix = "[AUTOMESSAGE LOG] ";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void println(String s){LOGGER.debug(AMLogPrefix + s);}

//    public static final ArrayList<Integer> CLIENT_LIMIT_COUNTER = new ArrayList<>();
//    public static final ArrayList<Integer> SERVER_LIMIT_COUNTER = new ArrayList<>();
//    public static final ArrayList<Integer> HARD_COUNTER = new ArrayList<>();


    public AutoMessage()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);


        // Register our configuration
//        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, AMServerConfigs.SPEC, "automessage-server.toml");
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AMClientConfigs.SPEC, "automessage-client.toml");

        // somehow ignores check for client not having mod installed?
        ModLoadingContext
                .get()
                .registerExtensionPoint(
                        IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(
                                () -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true
                        )
                );

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        // LOGGER.info("HELLO FROM COMMON SETUP");
        // LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        // LOGGER.info("HELLO from server starting");
//        println("SERVER STARTING EVENT FIRED!");

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            // LOGGER.info("HELLO FROM CLIENT SETUP");
            // LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
