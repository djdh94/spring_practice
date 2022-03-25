package com.ict.controller.di.classfile;

import org.springframework.stereotype.Component;
@Component
public class Broadcast {
		//Stage에 의존하도록 설정
		private Stage stage;
		
		public Broadcast(Stage stage) {
			
			this.stage = stage;
		}
		public void onAir() {
			System.out.print("방송송출중인");
			stage.perform();
		}

	

}
