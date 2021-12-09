package main.Repository;

import main.Model.Course;
import main.Model.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CourseFileRepository extends CourseInMemoryRepository{
    private final String filename;
    private static int idGenerator=0;

    public CourseFileRepository(String filename) {
        this.filename = filename;
        readFromFile();
    }

    //int id, String courseName,  int maxEnrollment, int credits

    private void readFromFile(){
        try(BufferedReader br=new BufferedReader(new FileReader(filename))){
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line); // check for new txt
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line=br.readLine())!=null){
                String[] elems=line.split(";");
                if (elems.length!=4){ // overstep length
                    System.err.println("Invalid line ..."+line);
                    continue;
                }
                try{
                    int id=Integer.parseInt(elems[0]); // parse id
                    int me=Integer.parseInt(elems[2]);
                    int cr=Integer.parseInt(elems[3]);
                    Course Course=new Course(id,elems[1],new Teacher(),me,cr); // create Course
                    super.add(Course); // add course to repo
                    // list is not added as parameter - circular dependencies (imports)

                }catch(NumberFormatException ex){
                    System.err.println("Error converting "+elems[0]); // id parse error
                }catch (IllegalArgumentException ex){
                    System.err.println("Error converting "+elems[3]); // list parse error
                }
            }

        }catch(IOException ex){
            throw new RepositoryException("Error reading "+ex);
        }

    }

    private void writeToFile(){
        try(PrintWriter pw=new PrintWriter(filename)){
            pw.println(idGenerator); // write current number of courses
            for(Course Course:findAll()){ // write each course to file
                pw.println(Course.getId()+";"+Course.getCourseName()+";"+Course.getMaxEnrollment()+";"+Course.getCredits());
            }
        }catch(IOException ex){
            throw new RepositoryException("Error writing "+ex);
        }

    }

    @Override
    public Course add(Course el) {
        el.setId(getNextId());
        super.add(el);
        writeToFile();
        return el;
    }

    @Override
    public void delete(Course el) {
        super.delete(el);
        writeToFile();
    }

    @Override
    public void update(Course el, Integer integer) {
        super.update(el, integer);
        writeToFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }
}
