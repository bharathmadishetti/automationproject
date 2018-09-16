package data.objects;

public class Employee {

	
	String name;
	String department;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Employee) {
        	Employee emp = (Employee) obj;
        	
        	if(emp.getName().equals(this.name)&&emp.getDepartment().equals(this.department)) {
        		 return true;
        	}
        	else {
        		return false;
        	}
        }
        return false;
    }
}
