package main.Repository;

import main.Model.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherFileRepository extends TeacherInMemoryRepository{
    private final String filename;
    private static int idGenerator=0;
    public TeacherFileRepository(String filename) {
        this.filename = filename;
        readFromFile();
    }

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
                    Teacher Teacher=new Teacher(id,elems[1],elems[2]); // create Teacher
                    super.add(Teacher); // add teacher to repo
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
            pw.println(idGenerator); // write current number of teachers
            for(Teacher Teacher:findAll()){ // write each teacher to file
                pw.println(Teacher.getId()+";"+Teacher.getFirstName()+";"+Teacher.getLastName()+";"+Teacher.getCourses());
            }
        }catch(IOException ex){
            throw new RepositoryException("Error writing "+ex);
        }

    }

    @Override
    public Teacher add(Teacher el) {
        el.setId(getNextId());
        super.add(el);
        writeToFile();
        return el;
    }

    @Override
    public void delete(Teacher el) {
        super.delete(el);
        writeToFile();
    }

    @Override
    public void update(Teacher el, Integer integer) {
        super.update(el, integer);
        writeToFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }
}
