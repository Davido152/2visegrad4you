package net.davido152.toovisegradforyou;

import com.mojang.logging.LogUtils;
import net.davido152.toovisegradforyou.block.ModBlocks;
import net.davido152.toovisegradforyou.item.ModItems;
import net.davido152.toovisegradforyou.server.ModBootstrap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TooVisegradForYou.MOD_ID)
public class TooVisegradForYou {
    public static final String MOD_ID = "toovisegradforyou";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public TooVisegradForYou() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);


        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModBootstrap.bootStrap();
        ModBootstrap.validate();

        // some preinit code
        LOGGER.info("Sziasztok!");
    }
}
