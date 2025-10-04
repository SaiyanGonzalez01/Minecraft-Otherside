package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

import net.peyton.eagler.minecraft.EntityConstructor;

public class EntityList {
	private static Map<String, EntityConstructor> stringToClassMapping = new HashMap();
	private static Map classToStringMapping = new HashMap();
	
	private static void addMapping(Class var0, String var1, EntityConstructor entity) {
		stringToClassMapping.put(var1, entity);
		classToStringMapping.put(var0, var1);
	}

	public static Entity createEntityInWorld(String var0, World var1) {
		Entity var2 = null;

		try {
			EntityConstructor var3 = (EntityConstructor)stringToClassMapping.get(var0);
			if(var3 != null) {
				var2 = var3.createEntity(var1);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static Entity createEntityFromNBT(NBTTagCompound var0, World var1) {
		Entity var2 = null;

		try {
			EntityConstructor var3 = (EntityConstructor)stringToClassMapping.get(var0.getString("id"));
			if(var3 != null) {
				var2 = var3.createEntity(var1);
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if(var2 != null) {
			var2.readFromNBT(var0);
		} else {
			System.out.println("Skipping Entity with id " + var0.getString("id"));
		}

		return var2;
	}

	public static String getEntityString(Entity var0) {
		return (String)classToStringMapping.get(var0.getClass());
	}

	static {
		addMapping(EntityArrow.class, "Arrow", EntityArrow::new);
		addMapping(EntityItem.class, "Item", EntityItem::new);
		addMapping(EntityPainting.class, "Painting", EntityPainting::new);
		addMapping(EntityLiving.class, "Mob", EntityLiving::new);
		addMapping(EntityMonster.class, "Monster", EntityMonster::new);
		addMapping(EntityCreeper.class, "Creeper", EntityCreeper::new);
		addMapping(EntitySkeleton.class, "Skeleton", EntitySkeleton::new);
		addMapping(EntitySpider.class, "Spider", EntitySpider::new);
		addMapping(EntityGiant.class, "Giant", EntityGiant::new);
		addMapping(EntityZombie.class, "Zombie", EntityZombie::new);
		addMapping(EntityGhost.class, "Ghost", EntityGhost::new);
		addMapping(EntityPig.class, "Pig", EntityPig::new);
		addMapping(EntitySheep.class, "Sheep", EntitySheep::new);
		addMapping(EntityTNTPrimed.class, "PrimedTnt", EntityTNTPrimed::new);
		addMapping(EntityFallingSand.class, "FallingSand", EntityFallingSand::new);
		addMapping(EntityMinecart.class, "Minecart", EntityMinecart::new);
	}
}
