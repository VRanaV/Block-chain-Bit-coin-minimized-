public String generaterandomstring(){
		//generate random stringggggggggggggs
		String str = "";
		String[] randomly = {"nura","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam"
				,"mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam","mona", "maryam"
				,"mona", "maryam"};
		 Random random = new Random();
	      str = randomly[random.nextInt(randomly.length)];
		
		return str;
	}
