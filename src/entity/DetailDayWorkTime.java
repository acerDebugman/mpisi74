package entity;

import java.util.Date;

public class DetailDayWorkTime {
		private int id;
        private String description;
        private Date fromTime;
        private Date toTime;
//        private int eachCircleDayId;
        
        //extended
        private EachCircleDay circleDay;
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getFromTime() {
			return fromTime;
		}
		public void setFromTime(Date fromTime) {
			this.fromTime = fromTime;
		}
		public Date getToTime() {
			return toTime;
		}
		public void setToTime(Date toTime) {
			this.toTime = toTime;
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
}