package com.johnnyworks.mytetris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ClickableViewAccessibility") public class GameView extends SurfaceView {
	private SurfaceHolder sf;
	private GameLoop theGameLoopThread;
	private GameState state;
	private int height;
	
	public GameView(Context context, int width, int height) 
	{
		super(context);
		this.height = height;
		state = new GameState(width, height);
		theGameLoopThread = new GameLoop(this, state);	
		sf = getHolder();
		sf.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
		          boolean retry = true;
	                theGameLoopThread.setRunning(false);
	                while(retry){
	                    try {
	                        theGameLoopThread.join();
	                        retry=false;
	                    }catch(InterruptedException e){
	 
	                    }
	                }
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
		        theGameLoopThread.setRunning(true);
                theGameLoopThread.start();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLUE);
		Paint paint = new Paint();
		paint.setStrokeWidth(10);
		for (int x=0; x< state.width ; x++)
			for (int y=0; y<state.height ; y++ )
			{
				if(state.array!=null)
					paint.setColor(state.array[x][y]);
				canvas.drawRect(state.blockSizeX*x,state.blockSizeY*y,state.blockSizeX-1+state.blockSizeX*x,state.blockSizeY-1+state.blockSizeY*y, paint);
			}
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		int position = state.blockSizeY*(state.height+1);
		canvas.drawText("Points: "+state.points, 10, position, paint);
		//canvas.drawText("rotate:"+state.action_rotate+"x:"+((int) state.touchX/ state.blockSizeX)+"y:"+((int) state.touchY/ state.blockSizeY), 10, position+30, paint);
		
		if(state.gameover)
		{
			paint.setColor(Color.BLACK);
			paint.setTextSize(100);
			canvas.drawText("GAME OVER", 10, height/2, paint);
			paint.setColor(Color.RED);
			paint.setTextSize(96);
			canvas.drawText("GAME OVER", 10, height/2, paint);
		}
	}
	
	// XXX: Race conditions- dont care :D
	public boolean onTouchEvent(MotionEvent event) {
		state.action_move=event.getActionMasked()==MotionEvent.ACTION_MOVE;	
		state.touchX = event.getX();
		state.touchY = event.getY();
		if (event.getActionMasked()==MotionEvent.ACTION_DOWN)
			state.action_rotate = state.touchedCurrentTile();
		return true;
		
	}

}
