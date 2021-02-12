package restAssuredTestClasses;

import restAssuredClasses.PostClass;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  PostClass p=new PostClass(125, "test", "test2");
  System.out.print(p.getId());
  
  System.out.print(p.getAuthor());
  System.out.print(p.getTitle());
	}

}
