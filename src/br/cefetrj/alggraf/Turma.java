package br.cefetrj.alggraf;

public class Turma {
	private int courseId;
	private String instructor;
	private int numDays;
	private String days;
	private String timeOfDay;
	private String roomType;
	private int classMaxSize;
	private int period;
	private String courseName;
	
	public Turma(int c, String i, int n, String d, String t, String r, int c2, int p, String c3){
		setCourseId(c);
		setInstructor(i);
		setNumDays(n);
		setDays(d);
		setTimeOfDay(t);
		setRoomType(r);
		setClassMaxSize(c2);
		setPeriod(p);
		setCourseName(c3);
	}
	
	public int retornaTipo(){
		switch(numDays){
			case 3:
				if(timeOfDay.equals("1"))
					return 1;
				if(timeOfDay.equals("2"))
					return 2;
				if(timeOfDay.equals("3"))
					return 3;
				if(timeOfDay.equals("4"))
					return 4;
			break;
			
			case 2:
				if(timeOfDay.equals("1"))
					return 5;
				if(timeOfDay.equals("2"))
					return 6;
				if(timeOfDay.equals("3"))
					return 7;
				if(timeOfDay.equals("4"))
					return 8;
			break;
			
			case 0:
				return 9;
				
			default:
				return 10;
		}
		return 0;
	}

	public Turma(int id, int n, String d, String t, int p){
		this.setCourseId(id);
		this.setNumDays(n);
		this.setDays(d);
		this.setTimeOfDay(t);
		this.setPeriod(p);
	}
	
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getClassMaxSize() {
		return classMaxSize;
	}

	public void setClassMaxSize(int classMaxSize) {
		this.classMaxSize = classMaxSize;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
	public boolean conflito(Turma t){
		if (t.period == this.period)
			if (t.timeOfDay.equals(this.timeOfDay))
				return true;
		return false;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	
}
