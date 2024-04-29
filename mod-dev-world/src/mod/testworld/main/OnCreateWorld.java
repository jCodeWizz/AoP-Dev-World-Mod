package mod.testworld.main;

import dev.codewizz.modding.annotations.EventCall;
import dev.codewizz.modding.annotations.Priorities;
import dev.codewizz.modding.annotations.Priority;
import dev.codewizz.modding.events.AddObjectEvent;
import dev.codewizz.modding.events.CreateWorldEvent;
import dev.codewizz.modding.events.EventListener;
import dev.codewizz.modding.events.GenerateChunkEvent;
import dev.codewizz.world.Chunk;
import dev.codewizz.world.World;

public class OnCreateWorld implements EventListener {

	@EventCall
	@Priority(priority = Priorities.SUPER_HIGH)
	public void onCreateWorld(CreateWorldEvent e) {
		World w = e.getWorld();
		
		w.showInfoStartMenu = false;
		
		w.chunks.clear();
		w.chunkTree.clear();
		w.tree.clear();
		w.generationQueue.clear();
		
		if(Main.SIZE <= 1) {
			Chunk c = w.addChunk(0, 0);
			c.markGenerated();
			c.init();
		} else {
			for(int i = -Main.SIZE/2; i <= Main.SIZE/2; i++) {
				for(int j = -Main.SIZE/2; j <= Main.SIZE/2; j++) {
					Chunk c = w.addChunk(i, j);
					c.init();
					c.markGenerated();
				}
			}
		}
	}
	
	@EventCall
	@Priority(priority = Priorities.SUPER_HIGH)
	public void onGenerateChunk(GenerateChunkEvent e) {
		e.cancel();
	}
	
	@EventCall
	@Priority(priority = Priorities.SUPER_HIGH)
	public void onAddObject(AddObjectEvent e) {
		if(e.getGameObject().getId().equals("aop:cow")) {
			e.cancel();
		}
	}
}
