package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
           // File your_file_name = File.createTempFile("your_file_name", null);
            String your_file_name = "C://temp//humansave.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
//            Human ivanov = new Human("Ivanov");
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if (ivanov.name.equals(somePerson.name)) System.out.println("names are equal");
            for (int i = 0; i < ivanov.assets.size(); i++) {
                if (ivanov.assets.get(i).getName().equals(somePerson.assets.get(i).getName()))
                    System.out.println(i + "th names are equal");
                if (Double.compare(ivanov.assets.get(i).getPrice(),somePerson.assets.get(i).getPrice()) == 0)
                    System.out.println(i + "th prices are equal");
            }


        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            char delimiter = 0x0A;
            outputStream.write(this.name.getBytes());
            outputStream.write(delimiter);
            int isAssetsPresent = this.assets != null ? 1 : 0;
            outputStream.write(isAssetsPresent);
            if (isAssetsPresent == 1)
                for (Asset a : this.assets
                ) {
                    outputStream.write(delimiter);
                    outputStream.write(a.getName().getBytes());
                    outputStream.write(delimiter);
                    outputStream.write(String.valueOf(a.getPrice()).getBytes());
                }
            outputStream.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.name = reader.readLine();
            int isAssetsPresent = reader.read();
            if (isAssetsPresent == 1) {
                reader.read(); // read delimiter
                List<Asset> assets = new ArrayList<>();
                while (reader.ready()) {
                    String name = reader.readLine();
                    double price = Double.parseDouble(reader.readLine());
                    assets.add(new Asset(name,price));
                }
                this.assets = assets;
            }
        }
    }
}
