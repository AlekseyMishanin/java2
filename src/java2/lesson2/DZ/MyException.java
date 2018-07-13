package java2.lesson2.DZ;


class MySizeArrayException extends Exception implements Display{
    @Override
    public void display(){
        System.out.println("Ошибка. Размер массива отличен от [4][4]");
    }
}

class MyArrayDataException extends Exception implements Display{
    private int x;
    private int y;

    MyArrayDataException(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public void display(){
        System.out.println("Ошибка. Элемент в ячейке [" + x + "][" + y + "] не может быть преобразован в int");
    }
}