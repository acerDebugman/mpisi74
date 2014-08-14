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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}