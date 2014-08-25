package entity;


import java.io.Serializable;
import java.util.Date;

public class DetailDayWorkTime implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -4424839531001520836L;
		private int id;
        private String name;
//        private Date fromTime;
//        private Date toTime;
//        private int eachCircleDayId;
        
        private String startTime;
        private int durationTime; //in minutes
        
        //extended
        private EachCircleDay circleDay;
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		/*public int getEachCircleDayId() {
			return eachCircleDayId;
		}
		public void setEachCircleDayId(int eachCircleDayId) {
			this.eachCircleDayId = eachCircleDayId;
		}*/
		public EachCircleDay getCircleDay() {
			return circleDay;
		}
		public void setCircleDay(EachCircleDay circleDay) {
			this.circleDay = circleDay;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public int getDurationTime() {
			return durationTime;
		}
		public void setDurationTime(int durationTime) {
			this.durationTime = durationTime;
		}
}