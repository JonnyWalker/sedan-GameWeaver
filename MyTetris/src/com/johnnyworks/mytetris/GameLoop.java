package com.johnnyworks.mytetris;

import android.graphics.Canvas;

public class GameLoop extends Thread{
	private boolean isRunning = false;
	private GameView theView;
	private GameState state;
	private static final long FPS = 40;
	private static final long IDLE_LOOPS = 15;


	public GameLoop(GameView gv,GameState state)
	{
		this.theView = gv;
		this.state   = state;
	}
	
	public void setRunning(boolean b)
	{
		isRunning = b;
	}
	
	public void run()
	{
		   long TPS = 1000/FPS;
		   long startTime, sleepTime;
		   long idle =0;
	       while (isRunning) {
	            Canvas theCanvas = null;
	            startTime = System.currentTimeMillis();
	            try {
	                theCanvas = theView.getHolder().lockCanvas();
	                synchronized (theView.getHolder()) {
	                    theView.doDraw(theCanvas);
	                }
	            } finally {
	                if (theCanvas != null) {
	                    theView.getHolder().unlockCanvasAndPost(theCanvas);
	                }
	            }
	            idle++;
	            synchronized (state)
	            {
	            	if(idle==IDLE_LOOPS)
	            	{
	            		idle=0;
		            	if(state.canMoveTile(0,1,state.rotation_index))
		            	{
		            		state.moveTileY();
		            	}   
		            	else
		            	{
		            		state.checkAndRemoveFullRow();
		            		state.createNewTile();        	
		            	}
		            }
		            
	            	if (state.action_rotate)
	            	{
	            		state.rotateCurrentTile();
	            		state.action_rotate=false;
	            	}
	            	if (state.action_move)
	            		state.moveTileX();	
	            }
	            sleepTime = TPS- (System.currentTimeMillis()-startTime);
	            try { if (sleepTime > 0) sleep(sleepTime); else sleep(10); } catch (Exception e) { }
	        }
	}
}
