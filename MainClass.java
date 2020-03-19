public class MainClass {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static float[] newMass(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        }
        return arr;
    }

    public static void One(float[] arr) {
        long a = System.currentTimeMillis();
        newMass(arr);
        long b = System.currentTimeMillis();
        System.out.println("Массив c одним потоком заполняется за: " + (b - a));
//        for (int i = 0; i < arr.length; i++) {  //проверка
//            System.out.print(arr[i]);
//        }
    }

    public static void Two(float[] arr) {
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        new Thread(() -> {
            newMass(a1);
        }).start();

        new Thread(() -> {
            newMass(a2);
        }).start();
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        long b = System.currentTimeMillis();
        System.out.println();
        System.out.println("Массив в 2 потока заполняется за: " + (b - a));


//        for (int i = 0; i < arr.length; i++) {  //проверка
//            System.out.print(arr[i]);
//        }
    }
        public static void main(String[] args) {
            float[] arr = new float[SIZE];
            for (int i = 0; i < arr.length; i++)  arr[i] =(1.0f);
            MainClass massiv = new MainClass();
            massiv.One(arr);
            massiv.Two(arr);
    }
}
