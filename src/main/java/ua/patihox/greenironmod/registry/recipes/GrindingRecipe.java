package ua.patihox.greenironmod.registry.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;
import ua.patihox.greenironmod.registry.RegistryHandler;

import javax.annotation.Nullable;

public class GrindingRecipe extends AbstractCookingRecipe {


    public GrindingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
        super(RegistryHandler.GRINDING_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(RegistryHandler.GREEN_GRINDER_ITEM.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return new Serializer();
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrindingRecipe> {
        private final int cookingTime = 300;

        public Serializer() {
        }

        @Override
        public GrindingRecipe read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            JsonElement jsonelement = (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.deserialize(jsonelement);
            //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
            if (!json.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (json.get("result").isJsonObject()) itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            else {
                String s1 = JSONUtils.getString(json, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(() -> {
                    Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
                    if(item == null)
                        throw new IllegalStateException("Item: " + s1 + " does not exist");
                    return item;
                });
            }
            float f = JSONUtils.getFloat(json, "experience", 0.0F);
            int i = JSONUtils.getInt(json, "cookingtime", this.cookingTime);
            return new GrindingRecipe(recipeId, s, ingredient, itemstack, f, i);
        }

        @Nullable
        @Override
        public GrindingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack itemstack = buffer.readItemStack();
            float f = buffer.readFloat();
            int i = buffer.readVarInt();
            return new GrindingRecipe(recipeId, s, ingredient, itemstack, f, i);
        }

        @Override
        public void write(PacketBuffer buffer, GrindingRecipe recipe) {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }

    }
}
