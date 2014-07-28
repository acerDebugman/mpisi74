package entity;

import java.util.HashSet;
import java.util.Set;

public class WorkTimePattern {
		private int id;
        private int circleDays;
        private String description;
        private String name;
        private boolean calPubHolidayFlag; //true calculate public holiday
        private boolean calSpecialDayFlag; //true calculate special day
        private boolean applyLeaveIgnorePublicHolidayFlag; //apply leave ignore public holiday
        private Double initialAnnualLeaveDays; //calcualte annual day from employee start day
        private Double addAnnualHoursPM;
        private Double initialSickLeaveDays;
        private Double addSickHoursPM;
        private Double dayWorkHours; //how many hours a day? use this parameter to convert day and hours
        
        //extended
        private Set<EachCircleDay> allCircleDays = new HashSet<EachCircleDay>();
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCircleDays() {
			return circleDays;
		}
		public void setCircleDays(int circleDays) {
			this.circleDays = circleDays;
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
		public boolean isCalPubHolidayFlag() {
			return calPubHolidayFlag;
		}
		public void setCalPubHolidayFlag(boolean calPubHolidayFlag) {
			this.calPubHolidayFlag = calPubHolidayFlag;
		}
		public boolean isCalSpecialDayFlag() {
			return calSpecialDayFlag;
		}
		public void setCalSpecialDayFlag(boolean calSpecialDayFlag) {
			this.calSpecialDayFlag = calSpecialDayFlag;
		}
		public Double getInitialAnnualLeaveDays() {
			return initialAnnualLeaveDays;
		}
		public void setInitialAnnualLeaveDays(Double initialAnnualLeaveDays) {
			this.initialAnnualLeaveDays = initialAnnualLeaveDays;
		}
		public Double getAddAnnualHoursPM() {
			return addAnnualHoursPM;
		}
		public void setAddAnnualHoursPM(Double addAnnualHoursPM) {
			this.addAnnualHoursPM = addAnnualHoursPM;
		}
		public Double getInitialSickLeaveDays() {
			return initialSickLeaveDays;
		}
		public void setInitialSickLeaveDays(Double initialSickLeaveDays) {
			this.initialSickLeaveDays = initialSickLeaveDays;
		}
		public Double getAddSickHoursPM() {
			return addSickHoursPM;
		}
		public void setAddSickHoursPM(Double addSickHoursPM) {
			this.addSickHoursPM = addSickHoursPM;
		}
		public Double getDayWorkHours() {
			return dayWorkHours;
		}
		public void setDayWorkHours(Double dayWorkHours) {
			this.dayWorkHours = dayWorkHours;
		}
		public boolean isApplyLeaveIgnorePublicHolidayFlag() {
			return applyLeaveIgnorePublicHolidayFlag;
		}
		public void setApplyLeaveIgnorePublicHolidayFlag(
				boolean applyLeaveIgnorePublicHolidayFlag) {
			this.applyLeaveIgnorePublicHolidayFlag = applyLeaveIgnorePublicHolidayFlag;
		}
		public Set<EachCircleDay> getAllCircleDays() {
			return allCircleDays;
		}
		public void setAllCircleDays(Set<EachCircleDay> allCircleDays) {
			this.allCircleDays = allCircleDays;
		}
}