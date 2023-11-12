package net.davido152.toovisegradforyou.server;

import com.mojang.logging.LogUtils;
import net.davido152.toovisegradforyou.core.ModCauldronInteraction;
import net.minecraft.core.Registry;
import net.minecraft.server.DebugLoggedPrintStream;
import net.minecraft.server.LoggedPrintStream;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;

import java.io.PrintStream;
import java.util.function.Supplier;

public class ModBootstrap{
    public static final PrintStream STDOUT = System.out;    private static volatile boolean isBootstrapped;
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void bootStrap() {
        if (!isBootstrapped) {
            isBootstrapped = true;
            if (Registry.REGISTRY.keySet().isEmpty()) {
                throw new IllegalStateException("Unable to load registries");
            } else {
                if (EntityType.getKey(EntityType.PLAYER) == null) {
                    throw new IllegalStateException("Failed loading EntityTypes");
                } else {
                    ModCauldronInteraction.bootStrap();
                    if (false) // skip redirectOutputToLog, Forge already redirects stdout and stderr output to log so that they print with more context
                        wrapStreams();
                }
            }
        }
    }

    public static void validate() {
        checkBootstrapCalled(() -> {
            return "validate";
        });
    }

    public static void checkBootstrapCalled(Supplier<String> p_179913_) {
        if (!isBootstrapped) {
            throw createBootstrapException(p_179913_);
        }
    }

    private static RuntimeException createBootstrapException(Supplier<String> p_179917_) {
        try {
            String s = p_179917_.get();
            return new IllegalArgumentException("Not bootstrapped (called from " + s + ")");
        } catch (Exception exception) {
            RuntimeException runtimeexception = new IllegalArgumentException("Not bootstrapped (failed to resolve location)");
            runtimeexception.addSuppressed(exception);
            return runtimeexception;
        }
    }

    private static void wrapStreams() {
        if (LOGGER.isDebugEnabled()) {
            System.setErr(new DebugLoggedPrintStream("STDERR", System.err));
            System.setOut(new DebugLoggedPrintStream("STDOUT", STDOUT));
        } else {
            System.setErr(new LoggedPrintStream("STDERR", System.err));
            System.setOut(new LoggedPrintStream("STDOUT", STDOUT));
        }

    }
}
