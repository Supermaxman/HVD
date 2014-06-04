package me.supermaxman.hvd;

import java.util.Random;

public class HVDGame{
	
	private int minPlayers;
	private int maxPlayers;
    private int lobyLocationX;
    private int lobyLocationY;
    private int lobyLocationZ;

    private int startTime;
    private int timeLimit;
    
    private int gameLocationX;
    private int gameLocationY;
    private int gameLocationZ;
    
    private int appleLocationX;
    private int appleLocationY;
    private int appleLocationZ;
    private int appleTimer;
    private boolean ended;
    private HVDGameThread thread;
    private HVDHandleThread threadHandle;
    private String hunter;
    
    public HVDGame(int min, int max, int x, int y, int z, int start, int limit, int x2, int y2, int z2, int x3, int y3, int z3, int at) {
        minPlayers = min;
        maxPlayers = max;
        lobyLocationX = x;
        lobyLocationY = y;
        lobyLocationZ = z;
        startTime = start;
        timeLimit = limit;
        setGameLocationX(x2);
        setGameLocationY(y2);
        setGameLocationZ(z2);
        
        setAppleLocationX(x3);
        setAppleLocationY(y3);
        setAppleLocationZ(z3);
        setAppleTimer(at);
        thread = new HVDGameThread(HVD.plugin, this);
        threadHandle = new HVDHandleThread(HVD.plugin, this);
        setEnded(false);
        thread.start();
    }
    
    
	public String chooseHunter() {
		Random r = new Random();
		int i = r.nextInt(HVD.players.size());
		return HVD.players.get(i);
	}


	public int getMinPlayers() {
		return minPlayers;
	}


	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}


	public int getMaxPlayers() {
		return maxPlayers;
	}


	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}


	public int getLobyLocationX() {
		return lobyLocationX;
	}


	public void setLobyLocationX(int lobyLocationX) {
		this.lobyLocationX = lobyLocationX;
	}


	public int getLobyLocationY() {
		return lobyLocationY;
	}


	public void setLobyLocationY(int lobyLocationY) {
		this.lobyLocationY = lobyLocationY;
	}


	public int getLobyLocationZ() {
		return lobyLocationZ;
	}


	public void setLobyLocationZ(int lobyLocationZ) {
		this.lobyLocationZ = lobyLocationZ;
	}


	public int getStartTime() {
		return startTime;
	}


	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}


	public int getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}


	public HVDGameThread getThread() {
		return thread;
	}


	public void setThread(HVDGameThread thread) {
		this.thread = thread;
	}


	public boolean isEnded() {
		return ended;
	}


	public void setEnded(boolean ended) {
		this.ended = ended;
	}


	public String getHunter() {
		return hunter;
	}


	public void setHunter(String hunter) {
		this.hunter = hunter;
	}


	public int getGameLocationX() {
		return gameLocationX;
	}


	public void setGameLocationX(int gameLocationX) {
		this.gameLocationX = gameLocationX;
	}


	public int getGameLocationY() {
		return gameLocationY;
	}


	public void setGameLocationY(int gameLocationY) {
		this.gameLocationY = gameLocationY;
	}


	public int getGameLocationZ() {
		return gameLocationZ;
	}


	public void setGameLocationZ(int gameLocationZ) {
		this.gameLocationZ = gameLocationZ;
	}


	public int getAppleLocationX() {
		return appleLocationX;
	}


	public void setAppleLocationX(int appleLocationX) {
		this.appleLocationX = appleLocationX;
	}


	public int getAppleLocationY() {
		return appleLocationY;
	}


	public void setAppleLocationY(int appleLocationY) {
		this.appleLocationY = appleLocationY;
	}


	public int getAppleLocationZ() {
		return appleLocationZ;
	}


	public void setAppleLocationZ(int appleLocationZ) {
		this.appleLocationZ = appleLocationZ;
	}


	public HVDHandleThread getThreadHandle() {
		return threadHandle;
	}


	public void setThreadHandle(HVDHandleThread threadHandle) {
		this.threadHandle = threadHandle;
	}


	public int getAppleTimer() {
		return appleTimer;
	}


	public void setAppleTimer(int appleTimer) {
		this.appleTimer = appleTimer;
	}
    


}
