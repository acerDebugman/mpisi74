package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EachCircleDay implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -7026925201082637264L;
		private int id;
        private int daySeq;
        private String description;
        private String name;
//        private int workTimePatternId;
        private Double overTimeRate; //for over time
        
        //extended 
        private WorkTimePattern workTimePattern; //standard work time many need to find which work time pattern it belong to
        private Set<DetailDayWorkTime> detailWorkTimeItems = new HashSet<DetailDayWorkTime>();
        
		
		public int getDaySeq() {
			return daySeq;
		}
		public void setDaySeq(int daySeq) {
			this.daySeq = daySeq;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
/*		public int getWorkTimePatternId() {
			return workTimePatternId;
		}
		public void setWorkTimePatternId(int workTimePatternId) {
			this.workTimePatternId = workTimePatternId;
		}*/
		public WorkTimePattern getWorkTimePattern() {
			return workTimePattern;
		}
		public void setWorkTimePattern(WorkTimePattern workTimePattern) {
			this.workTimePattern = workTimePattern;
		}
		public Set<DetailDayWorkTime> getDetailWorkTimeItems() {
			return detailWorkTimeItems;
		}
		public void setDetailWorkTimeItems(Set<DetailDayWorkTime> detailWorkTimeItems) {
			this.detailWorkTimeItems = detailWorkTimeItems;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Double getOverTimeRate() {
			return overTimeRate;
		}
		public void setOverTimeRate(Double overTimeRate) {
			this.overTimeRate = overTimeRate;
		}
}