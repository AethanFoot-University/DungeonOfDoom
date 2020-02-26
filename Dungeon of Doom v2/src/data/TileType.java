package data;

public enum TileType {
	Gravel("Gravel", true, true, false), Gold("Gold", false, true, true), Exit("Exit", true, true, true), 
	Rock("Rock", false, false, false), HumanRight("PlayerRight", false, true, false), HumanLeft("PlayerLeft", false, true, false), 
	MonsterRight("BotRight", false, true, false), MonsterLeft("BotLeft", false, true, false);
	
	String textureName;
	boolean canSpawnOn;
	boolean canWalkOn;
	boolean canUse;
	
	TileType(String textureName, boolean canSpawnOn, boolean canWalkOn, boolean canUse) {
		this.textureName = textureName;
		this.canSpawnOn = canSpawnOn;
		this.canWalkOn = canWalkOn;
		this.canUse = canUse;
	}
}
