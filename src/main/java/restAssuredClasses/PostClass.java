package restAssuredClasses;

public class PostClass {
	private float id;
	 private String title;
	 private String author;

	 public PostClass(float id,String title,String author)
	 {
		 
		 this.id=id;
		 this.title=title;
		 this.author=author;
	 }

	 // Getter Methods 

	 public float getId() {
	  return id;
	 }

	 public String getTitle() {
	  return title;
	 }

	 public String getAuthor() {
	  return author;
	 }

	 // Setter Methods 

	 public void setId(float id) {
	  this.id = id;
	 }

	 public void setTitle(String title) {
	  this.title = title;
	 }

	 public void setAuthor(String author) {
	  this.author = author;
	 }
}
