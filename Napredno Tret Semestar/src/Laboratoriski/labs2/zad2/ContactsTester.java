package Laboratoriski.labs2.zad2;
import java.text.DecimalFormat;
import java.util.*;


public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}

abstract class Contact{
    private Date date;

    public Contact(String date){
        String[] split = date.split("-");
        this.date = new Date(Integer.parseInt(split[0]),Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    boolean isNewerThan(Contact c){ return this.date.after(c.date); }
    public abstract String getType();
}

class EmailContact extends Contact{
    private String email;

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }

    @Override
    public String getType() { return "Email"; }

    public String getEmail() { return email; }
}

enum Operator { VIP, ONE, TMOBILE }

class PhoneContact extends Contact{
    private String phone;
    private Operator operator;

    public PhoneContact(String date, String phone) {
        super(date);

        int firstNum = Integer.parseInt(""+phone.charAt(2));
        if (firstNum == 0 || firstNum == 1 || firstNum == 2) { operator = Operator.TMOBILE; }
        else if (firstNum == 5 || firstNum == 6){ operator = Operator.ONE; }
        else { operator = Operator.VIP; }

        this.phone = phone;
    }

    @Override
    public String getType() { return "Phone"; }

    public String getPhone() { return phone; }
    public Operator getOperator() { return operator; }
}

class Student{
    private String firstName, lastName, city;
    private int age;
    private long index;
    private List<Contact> arr;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.arr = new ArrayList<Contact>();
    }
    
    public void addEmailContact(String date, String email){ arr.add(new EmailContact(date,email)); }
    public void addPhoneContact(String date, String phone){ arr.add(new PhoneContact(date, phone)); }
    
    public Contact[] getEmailContacts(){
        List<Contact> eContact = new ArrayList<Contact>();
        for (Contact c : arr){ if(c.getType() == "Email"){ eContact.add(c); } }
        return eContact.toArray(new Contact[eContact.size()]);
    }

    public Contact[] getPhoneContacts(){
        List<Contact> pContact = new ArrayList<Contact>();
        for(Contact c : arr){ if(c.getType() == "Phone"){ pContact.add(c); } }
        return pContact.toArray(new Contact[pContact.size()]);
    }

    public String getCity() { return city; }
    public long getIndex() { return index; }

    public String getFullName(){ return this.firstName + " " + this.lastName; }

    public Contact getLatestContact(){
        Contact last = arr.get(0);
        for(Contact c : arr){ if(c.isNewerThan(last)){ last = c; } }
        return last;
    }

    public int getContactLength(){ return arr.size(); }

    @Override
    public String toString() {
        String str =  "{" +
                "\"ime\":\"" + firstName + "\", " +
                "\"prezime\":\"" + lastName + "\", " +
                "\"vozrast\":" + age + ", " +
                "\"grad\":\"" + city + "\", " +
                "\"indeks\":" + index + ", " +
                "\"telefonskiKontakti\":[";
        for(Contact c : this.getPhoneContacts()){
            PhoneContact pc = (PhoneContact) c;
            str = str.concat("\""+pc.getPhone()+"\"");
            str+=", ";
        }
        if(this.getPhoneContacts().length>0){ str = str.substring(0,str.length()-2); }
        str+="], \"emailKontakti\":[";
        for(Contact c : this.getEmailContacts()){
            EmailContact pc = (EmailContact) c;
            str = str.concat("\""+pc.getEmail()+"\"");
            str+=", ";
        }
        if(this.getEmailContacts().length>0){ str = str.substring(0,str.length()-2); }
        str+="]}";
        return str;
    }
}

class Faculty{
    private String name;
    private Student[] arr;

    public Faculty(String name, Student[] arr) {
        this.name = name;
        this.arr = arr;
    }

    public int countStudentsFromCity(String cityName){
        int cnt=0;
        for(Student s : arr){
            if(s.getCity().compareTo(cityName) == 0){ cnt++; }
        } return cnt;
    }

    public Student getStudent(long index){
        for(Student s : arr){
            if(s.getIndex() == index){ return s; }
        } return null;
    }

    public double getAverageNumberOfContacts(){
        double avg = 0.00;
        for(Student s : arr){
            avg += s.getContactLength();
        } return avg / arr.length;
    }

    public Student getStudentWithMostContacts(){
        Student maxStudent = arr[0];
        for(Student s : arr){
            if(s.getContactLength() > maxStudent.getContactLength()){ maxStudent = s; }
            else if(s.getContactLength() == maxStudent.getContactLength()){
                if(s.getIndex() > maxStudent.getIndex()){ maxStudent = s; }
            }
        } return maxStudent;
    }

    @Override
    public String toString() {
        String str = "{" +
                "\"fakultet\":\"" + name + "\", " +
                "\"studenti\":[";
        for(Student s : arr) {
            str+=s.toString()+", ";
        }
        if(arr.length>0){ str = str.substring(0,str.length()-2); }
        str+="]}";
        return str;
    }
}