package net.covenantturtle.miningcooldownfix;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.function.Consumer;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.IntegerListEntry;


public class ConfigScreenBuilder {
	public static Screen createConfigScreen(Screen parent, MyConfig config, Consumer<MyConfig> update) {
		
		ConfigBuilder builder = ConfigBuilder.create().setParentScreen(null).setTitle(Text.translatable("title.miningcooldownfix.config"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		
		IntegerListEntry delayTicks = entryBuilder.startIntField(Text.translatable("option.miningcooldownfix.minedelay"), config.mineDelay).setTooltip(Text.translatable("option.miningcooldownfix.minedelay.tooltip")).setMin(0).build();
		
		ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.miningcooldownfix.general"));
		general.addEntry(delayTicks);
		
		builder.setSavingRunnable(() -> update.accept(new MyConfig()));
		
		return builder.build();
	}
}