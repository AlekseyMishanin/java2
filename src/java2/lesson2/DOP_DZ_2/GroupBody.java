package java2.lesson2.DOP_DZ_2;

public class GroupBody {
    private Body first;

    public GroupBody() {
        this.first = null;
    }

    public void insert (int weight, double height){

        Body newBody = new Body(weight,height,first);
        first=newBody;
    }

    public void BodyMassIndex(){
        Body temp = first;

        System.out.println("Расчет Индекса Массы Тела (Body Mass Index = BMI)");
        System.out.println();
        System.out.println("ВЕС\tРОСТ\tКАТЕГОРИЯ");
        while (temp!=null){
            temp.display();
            double index_mass = (temp.getWeight() / Math.pow(temp.getHeight(),2));
            System.out.println(index_mass < 18.5 ? "\tunder": 18.5 <= index_mass&&index_mass < 25.0 ? "\tnormal" :
                    25.0 <= index_mass&&index_mass < 30.0 ? "\tover" : "\tobese");
            temp=temp.next;
        }

        first=null;
    }
}
