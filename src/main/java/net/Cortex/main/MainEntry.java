package net.Cortex.main;

import net.Cortex.main.Fabricator.Fabricator;
import net.Cortex.main.Fabricator.Fabricator_Entity;
import net.Cortex.main.Fabricator.Fabricator_Gui_Description;
import net.Cortex.main.Ore_Generator.Ore_Generator_Controller;
import net.Cortex.main.Ore_Generator.Ore_Generator_Controller_Entity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MainEntry implements ModInitializer
{
	public static final ItemGroup CORTEX_ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("cortex", "general"), () -> new ItemStack(Blocks.COBBLESTONE));

	public static final Ore_Generator_Controller ore_generator_controller_block = new Ore_Generator_Controller(FabricBlockSettings.of(Material.GLASS));
	public static final Fabricator fabricator_block = new Fabricator(FabricBlockSettings.of(Material.GLASS));

	public static ScreenHandlerType<Fabricator_Gui_Description> FABRICATOR_SCREEN_HANDLER_TYPE = null;

	public static BlockEntityType<Ore_Generator_Controller_Entity> ore_generator_controller_entity;
	public static BlockEntityType<Fabricator_Entity> fabricator_entity;

	@Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.BLOCK, new Identifier("cortex", "ore_generator_controller"), ore_generator_controller_block);
		Registry.register(Registry.ITEM, new Identifier("cortex", "ore_generator_controller"), new BlockItem(ore_generator_controller_block, new Item.Settings().group(CORTEX_ITEM_GROUP)));
		ore_generator_controller_entity = Registry.register(Registry.BLOCK_ENTITY_TYPE, "cortex:ore_generator_controller", FabricBlockEntityTypeBuilder.create(Ore_Generator_Controller_Entity::new, ore_generator_controller_block).build());

		Registry.register(Registry.BLOCK, new Identifier("cortex", "fabricator"), fabricator_block);
		Registry.register(Registry.ITEM, new Identifier("cortex", "fabricator"), new BlockItem(fabricator_block, new Item.Settings().group(CORTEX_ITEM_GROUP)));
		fabricator_entity = Registry.register(Registry.BLOCK_ENTITY_TYPE, "cortex:fabricator", FabricBlockEntityTypeBuilder.create(Fabricator_Entity::new, fabricator_block).build());
		FABRICATOR_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerExtended(new Identifier("cortex", "fabricator"), (syncId, inventory, buf) -> new Fabricator_Gui_Description(syncId, inventory, ScreenHandlerContext.create(inventory.player.world, buf.readBlockPos())));

		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));
		Fabricator_Gui_Description.addMultiblockOption(fabricator_block.asItem(), new TranslatableText(fabricator_block.getTranslationKey()));

		System.out.println("Hello Fabric world!");
	}
}