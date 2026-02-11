public class Student {
    private int id;
    private String name;
    private String rollno;
    private String course;

    public Student(int id, String name, String rollno, String course) {
        this.id = id;
        this.name = name;
        this.rollno = rollno;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRollno() { return rollno; }
    public String getCourse() { return course; }
}
