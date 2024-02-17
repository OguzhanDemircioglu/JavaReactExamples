package hackerRank.gradingStudents;

import java.io.IOException;
import java.util.*;
class GradingStudents {

    public static void main(String[] args) throws IOException {
        List<Integer> grades = new ArrayList<>();
        grades.add(73);
        grades.add(67);
        grades.add(38);
        grades.add(33);
        List<Integer> result = GradingStudents.gradingStudents(grades);
        System.out.println(result);//[75, 67, 40, 33]
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {

        List<Integer> roundedGrades = new ArrayList<>();
        for(Integer g:grades){
            if(g<38){
                roundedGrades.add(g);
            }else {
                int roundedVal = ((g/5)+1)*5;
                if(roundedVal-g<3){
                    roundedGrades.add(roundedVal);
                }else {
                    roundedGrades.add(g);
                }
            }
        }

        return roundedGrades;
    }

}