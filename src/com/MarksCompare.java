package com;

import java.util.Comparator;

public class MarksCompare implements Comparator<User>{

	@Override
	public int compare(User u1, User u2) {
	
						
				if(u1.qualifications > u2.qualifications){					
					return 1;
				}			
				else 
					return -1;	
	}

}
