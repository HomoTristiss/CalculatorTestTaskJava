import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        String answer = calc(expression.trim());
        System.out.println(answer);
    }
    public static String calc(String input) throws Exception {
        String answer = "";
        String [] args = input.split(" ");
        if(args.length != 3) {
            throw new Exception("Строка не является математической операцией");
        }
        try {
            int number1 = Integer.parseInt(args[0]);
            int number2 = Integer.parseInt(args[2]);
            if(number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10){
                throw new Exception();
            }
            switch (args[1]) {
                case ("+"):
                    answer = String.valueOf(number1 + number2);
                    break;
                case ("-"):
                    answer = String.valueOf(number1 - number2);
                    break;
                case ("*"):
                    answer = String.valueOf(number1 * number2);
                    break;
                case ("/"):
                    answer = String.valueOf(number1 / number2);
                    break;
                default:
                    throw new Exception("Такая операция не доступна");
            }
        } catch (NumberFormatException nfe) {
            RimNumber rim1 = new RimNumber(args[0]);
            RimNumber rim2 = new RimNumber(args[2]);
            switch (args[1]) {
                case ("+"):
                    answer = rim1.add(rim2);
                    break;
                case ("-"):
                    answer = rim1.sub(rim2);
                    break;
                case ("*"):
                    answer = rim1.mult(rim2);
                    break;
                case ("/"):
                    answer = rim1.div(rim2);
                    break;
                default:
                    throw new Exception("Такая операция не доступна");
            }
        }
        return answer;
    }
}

class RimNumber{
    private final String [] names= {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private int value;
    private String name;

    public RimNumber(String nam) throws Exception {
        this.name = nam;
        this.value = 0;
        for(int i = 0; this.names.length > i; i++){
            if(nam.equals(this.names[i])){
                this.value = i;
                break;
            }
        }
        if (this.value == 0){
            throw new Exception();
        }
    }

    public int getValue(){
        return this.value;
    }
    public String add(RimNumber rim2){
        String answer;
        int sum = this.value + rim2.getValue();
        if(sum > 10){
            sum -= 10;
            answer = "X" + this.names[sum];
        } else {
            answer = this.names[sum];
        }
        return answer;
    }

    public String sub(RimNumber rim2) throws Exception {
        String answer;
        int sub = this.value - rim2.getValue();
        if(sub < 1){
            throw new Exception();
        } else {
            answer = this.names[sub];
            return answer;
        }
    }

    public String div(RimNumber rim2) throws Exception {
        String answer;
        int div = this.value / rim2.getValue();
        if(div < 1){
            throw new Exception();
        } else {
            answer = this.names[div];
            return answer;
        }
    }

    public String mult(RimNumber rim2){
        String answer;
        String x = "X";
        int mult = this.value * rim2.getValue();
        if(mult < 10){
            answer = this.names[mult];
            return answer;
        } else if(mult < 40) {
            int n = mult % 10;
            int m = mult / 10;
            answer = x.repeat(m) + this.names[n];
            return answer;
        } else if(mult < 50){
            int n = mult % 10;
            answer = "XL" + this.names[n];
            return answer;
        } else if(mult < 60){
            int n = mult % 10;
            answer = "L" + this.names[n];
            return answer;
        } else if(mult < 90){
            int n = mult % 10;
            int m = mult / 10 - 5;
            answer = "L" + x.repeat(m) + this.names[n];
            return answer;
        } else if(mult < 100){
            int n = mult % 10;
            answer = "XC" + this.names[n];
            return answer;
        }else {
            return "C";
        }
    }
}